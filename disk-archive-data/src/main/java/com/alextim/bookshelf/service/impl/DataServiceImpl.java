package com.alextim.bookshelf.service.impl;

import java.io.File;

import com.alextim.bookshelf.datauploader.uploader.IUploaderStrategy;
import com.alextim.bookshelf.datauploader.uploader.impl.CsvFileUploaderStrategy;
import com.alextim.bookshelf.datauploader.uploader.impl.DummyUploaderStrategy;
import com.alextim.bookshelf.datauploader.uploader.impl.XlsFileUploaderStrategy;
import com.alextim.bookshelf.service.IDataService;

public class DataServiceImpl implements IDataService {
    private static final IDataService SERVICE = new DataServiceImpl();

    public static IDataService createService() {
        return SERVICE;
    }

    @Override
    public IUploaderStrategy createDummyInstance() {
        return new DummyUploaderStrategy();
    }

    @Override
    public IUploaderStrategy createCsvInstance(final File file) {
        return new CsvFileUploaderStrategy(file);
    }

    @Override
    public IUploaderStrategy createXslInstance(final File file) {
        return new XlsFileUploaderStrategy(file);
    }
}
