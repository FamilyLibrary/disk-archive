package com.alextim.bookshelf.datauploader.uploader.impl;

import java.util.Arrays;
import java.util.List;

import com.alextim.bookshelf.datauploader.uploader.IUploader;

public class DummyUploader implements IUploader {
    private final List<String> data = Arrays.asList(new String[] {
            "Автор,Название,Том,Количество томов,Год издания (начало),Год издания (завершение)",
            "Мигель де Сервантес Сааведра,Хитроумный Идальго Дон Кихот Ламанчский,1,2,1979,",
            "Мигель де Сервантес Сааведра,Хитроумный Идальго Дон Кихот Ламанчский,2,2,1979,",
            "Н.С. Лесков,Собрание сочинений в 5-ти томах,1,5,1981,",
            "Н.С. Лесков,Собрание сочинений в 5-ти томах,2,5,1981,",
            "Н.С. Лесков,Собрание сочинений в 5-ти томах,3,5,1981,",
            "Этель Лилиан Войнич,Собрание сочинений в 3-ти томах,1,3,1975,",
            "Этель Лилиан Войнич,Собрание сочинений в 3-ти томах,2,3,1975,",
            "Этель Лилиан Войнич,Собрание сочинений в 3-ти томах,3,3,1975,"
    });

    @Override
    public void load() {
        throw new UnsupportedOperationException("Not implemented");
    }

}
