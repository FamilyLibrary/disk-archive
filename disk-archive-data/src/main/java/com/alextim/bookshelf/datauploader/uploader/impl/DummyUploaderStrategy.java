package com.alextim.bookshelf.datauploader.uploader.impl;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.alextim.bookshelf.datauploader.uploader.IUploaderStrategy;
import com.alextim.bookshelf.entity.Book;
import com.alextim.bookshelf.entity.BookAuthor;
import com.alextim.bookshelf.entity.CompleteWork;

public class DummyUploaderStrategy implements IUploaderStrategy {
    private static final String SEPARATOR = ",";

    private final List<String> data = Arrays.asList(new String[] {
            "Автор,Название,Том,Количество томов,Год издания (начало),Год издания (завершение)",
            "Мигель де Сервантес Сааведра,Хитроумный Идальго Дон Кихот Ламанчский,1,2,1979",
            "Мигель де Сервантес Сааведра,Хитроумный Идальго Дон Кихот Ламанчский,2,2,1979",
            "Н.С. Лесков,Собрание сочинений в 5-ти томах,1,5,1981",
            "Н.С. Лесков,Собрание сочинений в 5-ти томах,2,5,1981",
            "Н.С. Лесков,Собрание сочинений в 5-ти томах,3,5,1981",
            "Этель Лилиан Войнич,Собрание сочинений в 3-ти томах,1,3,1975",
            "Этель Лилиан Войнич,Собрание сочинений в 3-ти томах,2,3,1975",
            "Этель Лилиан Войнич,Собрание сочинений в 3-ти томах,3,3,1975",
            "И.А. Гончаров,Собрание сочинений в 4-ти томах,1,4,1981",
            "И.А. Гончаров,Собрание сочинений в 4-ти томах,2,4,1981",
            "И.А. Гончаров,Собрание сочинений в 4-ти томах,3,4,1981"
    });

    @Override
    public Collection<Book> load() {
        final List<Book> result = data.subList(1, data.size())
                .stream()
                .map(line -> {
            return mapToBook(line);
        }).collect(Collectors.toList());
        return result;
    }

    private Book mapToBook(final String line) {
        final Row row = createRow(Stream
            .of(line.split(SEPARATOR))
            .collect(Collectors.toList()));

        final Book book = new Book();

        createGeneralFields(book, row);
        createBookAuthor(book, row);
        createCompleteWork(book, row);

        return book;
    }

    private void createGeneralFields(final Book book, final Row row) {
        book.setName(row.name);
        book.setVolume(Integer.valueOf(row.volume));
        book.setYearOfPublication(Integer.valueOf(row.startYear));
    }
    private void createBookAuthor(Book book, Row row) {
        book.setAuthors(
            Stream.of(row.author.split(SEPARATOR)).flatMap(authorName -> {
                final BookAuthor author = new BookAuthor();
                author.setLastName(authorName);
                return Stream.of(author);
            }).collect(Collectors.toSet())
        );
    }
    private void createCompleteWork(Book book, Row row) {
        CompleteWork completeWork = new CompleteWork();
        completeWork.setTotalVolumes(Integer.valueOf(row.volumes));
        book.setCompleteWork(completeWork);
    }

    private Row createRow(List<String> parts) {
        final Row row = new Row();

        row.author = parts.get(0);
        row.name = parts.get(1);
        row.volume = parts.get(2);
        row.volumes = parts.get(3);
        row.startYear = parts.get(4);

        return row;
    }

    private static final class Row {
        private String author;
        private String name;
        private String volume;
        private String volumes;
        private String startYear;
    }
}
