package com.alextim.bookshelf.datauploader.uploader.impl;

final class BookRow {
    private String author;
    private String name;
    private String volume;
    private String volumes;
    private String yearOfPublication;
    private String firstVolumeInYear;
    private String lastVolumeInYear;

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

    public String getVolume() {
        return volume;
    }
    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getVolumes() {
        return volumes;
    }
    public void setVolumes(String volumes) {
        this.volumes = volumes;
    }

    public String getYearOfPublication() {
        return yearOfPublication;
    }
    public void setYearOfPublication(String yearOfPublication) {
        this.yearOfPublication = yearOfPublication;
    }

    public String getFirstVolumeInYear() {
        return firstVolumeInYear;
    }
    public void setFirstVolumeInYear(String firstVolumeInYear) {
        this.firstVolumeInYear = firstVolumeInYear;
    }

    public String getLastVolumeInYear() {
        return lastVolumeInYear;
    }
    public void setLastVolumeInYear(String lastVolumeInYear) {
        this.lastVolumeInYear = lastVolumeInYear;
    }
}
