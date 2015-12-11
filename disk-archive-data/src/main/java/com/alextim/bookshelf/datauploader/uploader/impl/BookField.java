package com.alextim.bookshelf.datauploader.uploader.impl;

import java.util.function.Function;

public enum BookField {
    AUTHOR(0),
    NAME(1),
    VOLUME(2),
    VOLUMES(3),
    YEAR_OF_PUBLICATION(4),
    FIRST_VOLUME_IN_YEAR(5),
    LAST_VOLUME_IN_YEAR(6);

    private final int index;

    private BookField(int index) {
        this.index = index;
    }

    public <T> T apply(Function<Integer, T> f) {
        return f.apply(this.index);
    }
}
