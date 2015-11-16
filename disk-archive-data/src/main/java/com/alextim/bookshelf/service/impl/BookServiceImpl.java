package com.alextim.bookshelf.service.impl;

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
    public Map<Object, List<Integer>> getAllAbsentBooks(final Collection<Book> books, final Set<BookAuthor> authors, final Function<Book, Object> function) {
        final String authorNames = authors.stream()
            .map(author -> author.getLastName())
            .collect(Collectors.joining(","));

        final List<Book> filteredByAuthor = books.stream()
                .filter(book -> {
                    return book.getAuthors().stream()
                        .map(author -> author.getLastName())
                        .collect(Collectors.joining(","))
                        .equals(authorNames);
                }
        ).collect(Collectors.toList());

        return getAllAbsentBooks(filteredByAuthor, function);
    }

    @Override
    public Map<Object, List<Integer>> getAllAbsentBooks(final Collection<Book> books, final Function<Book, Object> function) {
        final Map<Object, AuthorVolumesResult> authorVolumes = new HashMap<>();

        books.stream().forEach(book -> {
            final Object key = function.apply(book);
            final AuthorVolumesResult result = getOrCreateAuthorVolumesResult(authorVolumes.get(key));

            result.setCompleteWork(book.getCompleteWork());
            result.getVolumes().add(Optional.ofNullable(book.getVolume()).orElse(MIN_VOLUME_VALUE));
            authorVolumes.put(key, result);
        });

        final Map<Object, List<Integer>> result = 
                authorVolumes.entrySet()
                    .stream()
                    .collect(Collectors.toMap(
                            (Entry<Object, AuthorVolumesResult> entry) -> entry.getKey(), 
                             entry -> findAbsentBooks(entry))
                    );

        return result;
    }

    @Override
    public void insert(final Collection<Book> books) {
        books.forEach(book -> bookDao.addBook(book));
    }

    private List<Integer> findAbsentBooks(final Entry<Object, AuthorVolumesResult> entry) {
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
