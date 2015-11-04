package com.alextim.bookshelf.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
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
    public List<AbsentVolumesResult> getAllAbsentBooks(final Function<Book, Object> function) {
        final List<Book> books = bookDao.findAllFromCompleteWork();
        final List<AbsentVolumesResult> result = getAllAbsentBooks(books, function);

        return result;
    }

    @Override
    public List<Integer> getAllAbsentBooks(final String firstAuthorName, 
            final String lastAuthorName, final Function<Book, Object> function) {

        final BookAuthor bookAuthor = authorDao.findAuthor(firstAuthorName, lastAuthorName);

        final Set<BookAuthor> bookAuthors = Arrays.asList(bookAuthor).stream().collect(Collectors.toSet());
        final List<Book> books = bookDao.findByAuthor(bookAuthors);

        final List<AbsentVolumesResult> result = getAllAbsentBooks(books, function);
        return result.get(0).getAbsentVolumes();
    }

    private List<AbsentVolumesResult> getAllAbsentBooks(final List<Book> books, final Function<Book, Object> function) {
        final Map<Object, AuthorVolumesResult> authorVolumes = new LinkedHashMap<>();

        books.stream().filter(b -> b.getCompleteWork() != null).forEach(book -> {
            final Object key = function.apply(book);
            book.getAuthors().forEach(author -> {
                final AuthorVolumesResult result = getOrCreateAuthorVolumesResult(authorVolumes.get(key));

                result.setCompleteWork(book.getCompleteWork());
                result.getVolumes().add(book.getVolume());
                authorVolumes.put(key, result);
            });
        });

        List<AbsentVolumesResult> result = 
                authorVolumes.entrySet()
                .stream()
                .map(entry -> {return findAbsentBooks(entry);})
                .filter(e -> e.getAbsentVolumes().size() > 0).collect(Collectors.toList());

        return result;
    }

    private AbsentVolumesResult findAbsentBooks(final Entry<Object, AuthorVolumesResult> entry) {
        int maxVolume = MIN_VOLUME_VALUE;

        if (entry.getValue().getCompleteWork() != null) {
            maxVolume = entry.getValue().getCompleteWork().getTotalVolumes();
        }

        final List<Integer> absentVolumes = IntStream.rangeClosed(MIN_VOLUME_VALUE, maxVolume)
                .filter(volume -> !entry.getValue().getVolumes().contains(volume))
                .boxed().collect(Collectors.toList());

        return createAbsentBookResult(entry.getKey(), absentVolumes);
    }

    private AuthorVolumesResult getOrCreateAuthorVolumesResult(AuthorVolumesResult authorVolumesResult) {
        if (authorVolumesResult == null) {
            authorVolumesResult = new AuthorVolumesResult();
            authorVolumesResult.setVolumes(new ArrayList<>());
        }
        return authorVolumesResult;
    }

    private AbsentVolumesResult createAbsentBookResult(final Object key, List<Integer> absentVolumes) {
        final AbsentVolumesResult result = new AbsentVolumesResult();

        result.setAbsentVolumes(absentVolumes);
        result.setKey(key);

        return result;
    }

    @Override
    public Collection<Book> uploadBookFile() {
        return uploaderContext.perform();
    }
}
