package com.alextim.bookshelf.service.impl;

import static com.alextim.bookshelf.Utilities.FILTER_BY_AUTHOR_NAMES_BI_FUNCTION;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alextim.bookshelf.dao.IBookDao;
import com.alextim.bookshelf.datauploader.uploader.impl.UploaderContext;
import com.alextim.bookshelf.entity.Book;
import com.alextim.bookshelf.entity.BookAuthor;
import com.alextim.bookshelf.entity.CompleteWork;
import com.alextim.bookshelf.service.IBookService;

@Service
public class BookServiceImpl implements IBookService {
    private static final int MIN_VOLUME_VALUE = 1;

    @Resource
    private IBookDao bookDao;

    @Resource
    private UploaderContext uploaderContext;

    @Override
    public <T> Map<T, List<Integer>> getAllAbsentBooks(final Collection<Book> books, final Set<BookAuthor> authors, final Function<Book, T> function) {
        return getAllAbsentBooks(FILTER_BY_AUTHOR_NAMES_BI_FUNCTION.apply(books, authors), function);
    }

    @Override
    public <T> Map<T, List<Integer>> getAllAbsentBooks(final Collection<Book> books, final Function<Book, T> function) {
        final Map<T, AuthorVolumesResult> authorVolumes = new HashMap<>();

        books.stream().forEach(book -> {
            final T key = function.apply(book);
            final AuthorVolumesResult result = getOrCreateAuthorVolumesResult(authorVolumes.get(key));

            result.setCompleteWork(book.getCompleteWork());
            result.getVolumes().add(Optional.ofNullable(book.getVolume()).orElse(MIN_VOLUME_VALUE));
            authorVolumes.put(key, result);
        });

        final Map<T, List<Integer>> result = 
                authorVolumes.entrySet()
                    .stream()
                    .collect(Collectors.toMap(
                            (Entry<T, AuthorVolumesResult> entry) -> entry.getKey(), 
                             entry -> findAbsentBooks(entry))
                    );

        return result;
    }

    @Override
    public void insert(final Collection<Book> books) {
        books.parallelStream().forEach(book -> bookDao.addBook(book));
    }

    private <T> List<Integer> findAbsentBooks(final Entry<T, AuthorVolumesResult> entry) {
        final CompleteWork completeWork = entry.getValue().getCompleteWork();

        List<Integer> rangeList = Collections.emptyList();
        if (completeWork != null) {
            final int minVolume = getMinVolume(completeWork.getFirstVolumeInYear());
            final int maxVolume = getMaxVolume(completeWork.getLastVolumeInYear(), 
                    completeWork.getTotalVolumes());

            rangeList = IntStream.rangeClosed(minVolume, maxVolume)
                    .boxed()
                    .collect(Collectors.toList());
            rangeList.removeAll(entry.getValue().getVolumes());
        }

        return rangeList;
    }

    private int getMinVolume(final Integer firstVolumeInYear) {
        if (firstVolumeInYear == null || firstVolumeInYear.equals(0)) {
            return MIN_VOLUME_VALUE;
        }
        return firstVolumeInYear;
    }

    private int getMaxVolume(final Integer lastVolumeInYear, final Integer totalVolumes) {
        if (lastVolumeInYear == null || lastVolumeInYear.equals(0)) {
            return Optional.ofNullable(totalVolumes).orElse(MIN_VOLUME_VALUE);
        }
        return lastVolumeInYear;
    }

    private AuthorVolumesResult getOrCreateAuthorVolumesResult(final AuthorVolumesResult authorVolumesResult) {
        if (authorVolumesResult == null) {
            final AuthorVolumesResult volumesResult = new AuthorVolumesResult();
            volumesResult.setVolumes(new ArrayList<>());

            return volumesResult;
        }
        return authorVolumesResult;
    }

    @Override
    public Collection<Book> uploadBookFile() {
        return uploaderContext.perform();
    }
}
