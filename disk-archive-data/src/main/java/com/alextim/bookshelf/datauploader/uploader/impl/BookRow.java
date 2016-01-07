package com.alextim.bookshelf.datauploader.uploader.impl;

final class BookRow {
    private String author;
    private String name;
    private Integer volume;
    private Integer volumes;
    private Integer yearOfPublication;
    private Integer firstVolumeInYear;
    private Integer lastVolumeInYear;

    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Integer getVolume() {
        return volume;
    }
    public void setVolume(Integer volume) {
        this.volume = volume;
    }

    public Integer getVolumes() {
        return volumes;
    }
    public void setVolumes(Integer volumes) {
        this.volumes = volumes;
    }

    public Integer getYearOfPublication() {
        return yearOfPublication;
    }
    public void setYearOfPublication(Integer yearOfPublication) {
        this.yearOfPublication = yearOfPublication;
    }

    public Integer getFirstVolumeInYear() {
        return firstVolumeInYear;
    }
    public void setFirstVolumeInYear(Integer firstVolumeInYear) {
        this.firstVolumeInYear = firstVolumeInYear;
    }

    public Integer getLastVolumeInYear() {
        return lastVolumeInYear;
    }
    public void setLastVolumeInYear(Integer lastVolumeInYear) {
        this.lastVolumeInYear = lastVolumeInYear;
    }
}
