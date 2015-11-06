package com.alextim.bookshelf.datauploader.uploader.impl;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.alextim.bookshelf.datauploader.uploader.IUploaderStrategy;
import com.alextim.bookshelf.entity.Book;

public class DummyUploaderStrategy extends AbstractUploaderStrategy implements IUploaderStrategy {
    private final List<String> data = Arrays.asList(new String[] {
            "Автор,Название,Том,Количество томов,Год издания,Номер первого тома в году,Номер последнего тома в году,Том (старая маркировка)",
            "Мигель де Сервантес Сааведра,Хитроумный Идальго Дон Кихот Ламанчский,1,2,1979,,,",
            "Мигель де Сервантес Сааведра,Хитроумный Идальго Дон Кихот Ламанчский,2,2,1979,,,",
            "Н.С. Лесков,Собрание сочинений в 5-ти томах,1,5,1981,,,",
            "Н.С. Лесков,Собрание сочинений в 5-ти томах,2,5,1981,,,",
            "Н.С. Лесков,Собрание сочинений в 5-ти томах,3,5,1981,,,",
            "Этель Лилиан Войнич,Собрание сочинений в 3-ти томах,1,3,1975,,,",
            "Этель Лилиан Войнич,Собрание сочинений в 3-ти томах,2,3,1975,,,",
            "Этель Лилиан Войнич,Собрание сочинений в 3-ти томах,3,3,1975,,,",
            "И.А. Гончаров,Собрание сочинений в 4-ти томах,1,4,1981,,,",
            "И.А. Гончаров,Собрание сочинений в 4-ти томах,2,4,1981,,,",
            "И.А. Гончаров,Собрание сочинений в 4-ти томах,3,4,1981,,,",
            "\"Александр Блок, Андрей Белый\",Диалог поэтов о России и революции,,,1990,,,"
    });

    @Override
    public Collection<Book> load() {
        final List<Book> result = data.stream()
                .skip(1) //Skip the first line
                .map(line -> {return mapToBook(line);})
                .collect(Collectors.toList());
        return result;
    }
}
