package com.alextim.bookshelf.service;

import java.io.File;

import com.alextim.bookshelf.datauploader.uploader.IUploaderStrategy;
import org.springframework.core.io.InputStreamSource;

public interface IDataService {
    IUploaderStrategy createDummyInstance();
    IUploaderStrategy createCsvInstance(InputStreamSource csvStreamSource);
    IUploaderStrategy createXslInstance(InputStreamSource xlsStreamSource);
}
