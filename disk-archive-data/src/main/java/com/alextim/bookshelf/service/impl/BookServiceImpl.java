package com.alextim.bookshelf.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
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

import com.alextim.bookshelf.dao.IAuthorDao;
import com.alextim.bookshelf.dao.IBookDao;
import com.alextim.bookshelf.datauploader.uploader.impl.UploaderContext;
import com.alextim.bookshelf.entity.Book;
import com.alextim.bookshelf.entity.BookAuthor;
import com.alextim.bookshelf.service.IBookService;

@Service
public class BookServiceImpl implements IBookService {
    private static final int MIN_VOLUME_VALUE = 1;

    @Resource
    private IBookDao bookDao;

    @Resource
    private IAuthorDao authorDao;

    @Resource
    private UploaderContext uploaderContext;

    @Override
    public Map<Object, List<Integer>> getAllAbsentBooks(final Function<Book, Object> function) {
        final List<Book> books = bookDao.findAllFromCompleteWork();
        return getAllAbsentBooks(books, function);
    }

    @Override
    public Map<Object, List<Integer>> getAllAbsentBooks(final String firstAuthorName, 
            final String lastAuthorName, final Function<Book, Object> function) {

        final BookAuthor bookAuthor = authorDao.findAuthor(firstAuthorName, lastAuthorName);

        final Set<BookAuthor> bookAuthors = Arrays.asList(bookAuthor).stream().collect(Collectors.toSet());
        final List<Book> books = bookDao.findByAuthor(bookAuthors);

        return getAllAbsentBooks(books, function);
    }

    private Map<Object, List<Integer>> getAllAbsentBooks(final List<Book> books, final Function<Book, Object> function) {
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

    private List<Integer> findAbsentBooks(final Entry<Object, AuthorVolumesResult> entry) {
        int maxVolume = MIN_VOLUME_VALUE;

        if (entry.getValue().getCompleteWork() != null) {
            maxVolume = entry.getValue().getCompleteWork().getTotalVolumes();
        }

        List<Integer> rangeList = IntStream.rangeClosed(MIN_VOLUME_VALUE, maxVolume)
            .boxed()
            .collect(Collectors.toList());

        rangeList.removeAll(entry.getValue().getVolumes());

        return rangeList;
    }

    private AuthorVolumesResult getOrCreateAuthorVolumesResult(AuthorVolumesResult authorVolumesResult) {
        if (authorVolumesResult == null) {
            authorVolumesResult = new AuthorVolumesResult();
            authorVolumesResult.setVolumes(new ArrayList<>());
        }
        return authorVolumesResult;
    }

    @Override
    public Collection<Book> uploadBookFile() {
        return uploaderContext.perform();
    }
}
