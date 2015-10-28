package com.alextim.bookshelf.service.impl;

import java.io.File;

import com.alextim.bookshelf.datauploader.uploader.IUploader;
import com.alextim.bookshelf.datauploader.uploader.impl.DummyUploader;
import com.alextim.bookshelf.service.IDataService;

public class DataServiceImpl implements IDataService {
    private static final IDataService SERVICE = new DataServiceImpl();

    public static IDataService createService() {
        return SERVICE;
    }

    @Override
    public IUploader createDummyInstance() {
        return new DummyUploader();
    }

    @Override
    public IUploader createCsvInstance(final File file) {
        throw new UnsupportedOperationException("Not Implemented");
    }

    @Override
    public IUploader createXslInstance(final File file) {
        throw new UnsupportedOperationException("Not Implemented");
    }
}
