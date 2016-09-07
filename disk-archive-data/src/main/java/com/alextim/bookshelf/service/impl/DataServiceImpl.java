package com.alextim.bookshelf.service.impl;

import java.io.File;

import com.alextim.bookshelf.datauploader.uploader.IUploaderStrategy;
import com.alextim.bookshelf.datauploader.uploader.impl.CsvFileUploaderStrategy;
import com.alextim.bookshelf.datauploader.uploader.impl.DummyUploaderStrategy;
import com.alextim.bookshelf.datauploader.uploader.impl.XlsFileUploaderStrategy;
import com.alextim.bookshelf.service.IDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamSource;

import javax.annotation.Resource;

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
    public IUploaderStrategy createCsvInstance(InputStreamSource csvStreamSource) {
        return new CsvFileUploaderStrategy(csvStreamSource);
    }

    @Override
    public IUploaderStrategy createXslInstance(InputStreamSource xlsStreamSource) {
        return new XlsFileUploaderStrategy(xlsStreamSource);
    }


}
