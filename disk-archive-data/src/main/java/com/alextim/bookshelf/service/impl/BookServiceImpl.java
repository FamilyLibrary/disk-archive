package com.alextim.bookshelf.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alextim.bookshelf.dao.IAuthorDao;
import com.alextim.bookshelf.dao.IBookDao;
import com.alextim.bookshelf.datauploader.uploader.IUploader;
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
    private IUploader dummyInstance;

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

    private AbsentVolumesResult createAbsentBookResult(final BookAuthor author, List<Integer> absentVolumes) {
        final AbsentVolumesResult result = new AbsentVolumesResult();

        result.setAbsentVolumes(absentVolumes);
        result.setBookAuthor(author);

        return result;
    }

    @Override
    public void uploadBookFile() {
        dummyInstance.load();
    }
}
