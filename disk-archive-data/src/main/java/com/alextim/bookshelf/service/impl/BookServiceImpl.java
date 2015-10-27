package com.alextim.bookshelf.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.alextim.bookshelf.dao.impl.AuthorDaoImpl;
import com.alextim.bookshelf.dao.impl.BookDaoImpl;
import com.alextim.bookshelf.entity.Book;
import com.alextim.bookshelf.entity.BookAuthor;
import com.alextim.bookshelf.entity.CompleteWork;
import com.alextim.bookshelf.service.BookService;
import com.alextim.diskarchive.entity.Author;

public class BookServiceImpl implements BookService {
    private static final int MIN_VOLUME_VALUE = 1;

    private BookDaoImpl bookDao;
    private AuthorDaoImpl authorDao;

    @Override
    public List<AbsentVolumesResult> getAllAbsentBooks() {
        final List<Book> books = bookDao.findAllFromCompleteWork();
        final List<AbsentVolumesResult> result = getAllAbsentBooks(books);

        return result;
    }

    @Override
    public List<Integer> getAllAbsentBooks(final String firstAuthorName, final String lastAuthorName) {
        final BookAuthor bookAuthor = authorDao.findAuthor(firstAuthorName, lastAuthorName);

        final Set<BookAuthor> bookAuthors = Arrays.asList(bookAuthor).stream().collect(Collectors.toSet());
        final List<Book> books = bookDao.findByAuthor(bookAuthors);

        final List<AbsentVolumesResult> result = getAllAbsentBooks(books);
        return result.get(0).getAbsentVolumes();
    }

    private List<AbsentVolumesResult> getAllAbsentBooks(final List<Book> books) {
        final List<AbsentVolumesResult> result = new ArrayList<>();

        final Map<BookAuthor, AuthorVolumesResult> authorVolumes = new HashMap<>();

        books.stream().forEach(book -> {
            final Set<BookAuthor> authors = book.getAuthors();

            authors.forEach(author -> {
                AuthorVolumesResult authorVolumesResult = authorVolumes.get(author);
                if (authorVolumesResult == null) {
                    authorVolumesResult = new AuthorVolumesResult();
                    authorVolumesResult.setVolumes(new ArrayList<>());
                }
                if (book.getCompleteWork() != null) {
                    authorVolumesResult.setCompleteWork(book.getCompleteWork());
                }
                authorVolumesResult.getVolumes().add(book.getVolume());

                authorVolumes.put(author, authorVolumesResult);
            });
        });

        authorVolumes.entrySet().stream().forEach(entry -> {
            int maxVolume = MIN_VOLUME_VALUE;

            if (entry.getValue().getCompleteWork() != null) {
                maxVolume = entry.getValue().getCompleteWork().getTotalVolumes();
            }

            List<Integer> absentVolumes = IntStream.rangeClosed(MIN_VOLUME_VALUE, maxVolume)
                    .filter(volume -> !entry.getValue().getVolumes().contains(volume))
                    .boxed().collect(Collectors.toList());

            result.add(createAbsentBookResult(entry.getKey(), absentVolumes));
        });

        return result;
    }

    @Override
    public Book findBook(final Integer volume, final Integer yearOfPublication, final CompleteWork completeWork, final Author author) {
        // TODO Auto-generated method stub
        return null;
    }

    private AbsentVolumesResult createAbsentBookResult(final BookAuthor author, List<Integer> absentVolumes) {
        final AbsentVolumesResult result = new AbsentVolumesResult();

        result.setAbsentVolumes(absentVolumes);
        result.setBookAuthor(author);

        return result;
    }
}
