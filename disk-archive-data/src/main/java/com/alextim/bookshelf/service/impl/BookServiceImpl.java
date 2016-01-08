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
import java.util.stream.Stream;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alextim.bookshelf.dao.IBookDao;
import com.alextim.bookshelf.datauploader.uploader.impl.UploaderContext;
import com.alextim.bookshelf.entity.Book;
import com.alextim.bookshelf.entity.BookAuthor;
import com.alextim.bookshelf.entity.CompleteWork;
import com.alextim.bookshelf.service.IBookService;
import com.alextim.entity.TimestampColumns;

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
    public List<Book> save(final Collection<Book> books) {
        /*TODO: Hibernate won't save a timestampColumns object that contains updated and created fields 
         * if the timestampColumns object is null before calling saveOrUpdate function.
         * Investigate a possibility to create a timestampColumns object during creation of a book object. 
         */
        return books.stream()
            .map(book -> {
                final List<Book> findedBooks = 
                        bookDao.findBook(book.getYearOfPublication(), book.getVolume());
                return findedBooks.stream().filter(findedBook -> !book.isUpdatedFromUI() && book.equals(findedBook)).findFirst().orElse(book);
            })
            .peek(book -> {
                if (book.getTimestampColumns() == null) {
                    book.setTimestampColumns(new TimestampColumns());
                }
            })
            .map(book -> bookDao.addBook(book))
            .flatMap(book -> Stream.of(book))
            .collect(Collectors.toList());
    }
    

    @Override
    public void delete(final Book book) {
        bookDao.delete(book);
    }

    @Override
    public Book getById(final Long id) {
        return bookDao.getById(
                Optional.ofNullable(id).orElseThrow(() -> new IllegalArgumentException("Id value can not be null"))
        );
    }

    @Override
    public Collection<Book> findAll() {
        return bookDao.findAll();
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
