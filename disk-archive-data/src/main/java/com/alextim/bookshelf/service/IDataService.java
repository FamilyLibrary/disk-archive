package com.alextim.bookshelf.service;

import java.io.File;

import com.alextim.bookshelf.datauploader.uploader.IUploaderStrategy;

public interface IDataService {
    IUploaderStrategy createDummyInstance();
    IUploaderStrategy createCsvInstance(File file);
    IUploaderStrategy createXslInstance(File file);
}
