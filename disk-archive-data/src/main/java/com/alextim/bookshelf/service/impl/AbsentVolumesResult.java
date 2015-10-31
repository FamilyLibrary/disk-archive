package com.alextim.bookshelf.service.impl;

import java.util.List;

public class AbsentVolumesResult {
    private Object key;
    private List<Integer> absentVolumes;

    public Object getKey() {
        return key;
    }
    public void setKey(Object key) {
        this.key = key;
    }

    public List<Integer> getAbsentVolumes() {
        return absentVolumes;
    }
    public void setAbsentVolumes(List<Integer> absentVolumes) {
        this.absentVolumes = absentVolumes;
    }
}
