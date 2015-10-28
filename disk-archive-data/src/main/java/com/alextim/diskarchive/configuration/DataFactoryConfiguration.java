package com.alextim.diskarchive.configuration;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Lazy;

import com.alextim.bookshelf.datauploader.uploader.IUploader;
import com.alextim.bookshelf.service.IDataService;
import com.alextim.bookshelf.service.impl.DataServiceImpl;

@Configuration
@ImportResource({"classpath:data-factory-context.xml"})
public class DataFactoryConfiguration {
    @Autowired
    private File csvFile;

    @Autowired
    private File xlsFile;

    @Bean
    public IDataService dataServiceFactory() {
        return DataServiceImpl.createService();
    }

    @Bean
    @Lazy
    public IUploader csvInstance() {
        return dataServiceFactory().createCsvInstance(csvFile);
    }

    @Bean
    @Lazy
    public IUploader xlsInstance() {
        return dataServiceFactory().createXslInstance(xlsFile);
    }

    @Bean
    public IUploader dummyInstance() {
        return dataServiceFactory().createDummyInstance();
    }

}
