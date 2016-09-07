package com.alextim.diskarchive.configuration;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Lazy;

import com.alextim.bookshelf.datauploader.uploader.IUploaderStrategy;
import com.alextim.bookshelf.datauploader.uploader.impl.UploaderContext;
import com.alextim.bookshelf.service.IDataService;
import com.alextim.bookshelf.service.impl.DataServiceImpl;
import org.springframework.core.io.InputStreamSource;

import javax.annotation.Resource;

@Configuration
@ImportResource({"classpath:data-factory-context.xml"})
public class DataFactoryConfiguration {
    @Resource( name = "csvSource" )
    private InputStreamSource csvSource;

    @Resource( name = "xlsSource" )
    private InputStreamSource xlsSource;

    @Bean
    public UploaderContext uploaderContext() {
        return new UploaderContext(xlsInstance());
    }

    @Bean
    @Lazy
    public IUploaderStrategy csvInstance() {
        return dataServiceFactory().createCsvInstance(csvSource);
    }

    @Bean
    @Lazy
    public IUploaderStrategy xlsInstance() {
        return dataServiceFactory().createXslInstance(xlsSource);
    }

    @Bean
    public IUploaderStrategy dummyInstance() {
        return dataServiceFactory().createDummyInstance();
    }

    private IDataService dataServiceFactory() {
        return DataServiceImpl.createService();
    }
}
