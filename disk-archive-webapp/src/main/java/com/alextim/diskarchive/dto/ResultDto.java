package com.alextim.diskarchive.dto;

import java.util.Collection;

public class ResultDto<T> {
    private final Collection<T> entities;
    private final long total;
    public ResultDto(Collection<T> entities, long total) {
        this.entities = entities;
        this.total = total;
    }

    public Collection<T> getEntities() {
        return entities;
    }
    public long getTotal() {
        return total;
    }
}