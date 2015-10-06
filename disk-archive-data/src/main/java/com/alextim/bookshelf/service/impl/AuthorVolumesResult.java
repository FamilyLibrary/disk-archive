package com.alextim.bookshelf.service.impl;

import java.util.List;

import com.alextim.bookshelf.entity.CompleteWork;

public class AuthorVolumesResult {
    private CompleteWork completeWork;
    private List<Integer> volumes;

    public CompleteWork getCompleteWork() {
        return completeWork;
    }
    public void setCompleteWork(CompleteWork completeWork) {
        this.completeWork = completeWork;
    }

    public List<Integer> getVolumes() {
        return volumes;
    }
    public void setVolumes(List<Integer> volumes) {
        this.volumes = volumes;
    }
}
