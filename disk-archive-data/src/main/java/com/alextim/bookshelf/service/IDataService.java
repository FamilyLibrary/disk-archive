package com.alextim.bookshelf.service;

import java.io.File;

import com.alextim.bookshelf.datauploader.uploader.IUploader;

public interface IDataService {
    IUploader createDummyInstance();
    IUploader createCsvInstance(File file);
    IUploader createXslInstance(File file);
}
