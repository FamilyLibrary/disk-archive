package com.alextim.bookshelf.service.impl;

import com.alextim.bookshelf.repository.SettingRepository;
import com.alextim.bookshelf.service.ISettingService;
import com.alextim.entity.ApplicationSetting;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Created by admin on 21.09.2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class SettingServiceImplTest {
    private static final String VALUE = "111";
    private static final String PROPERTY = "222";
    @Mock
    ApplicationSetting applicationSetting;


    @Mock
    private SettingRepository settingRepository;

    @InjectMocks
    @Spy
    private ISettingService settingService = new SettingServiceImpl();

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentException(){
        settingService.findByProperty(null);
    }

    @Test
    public void shouldReturnValue(){
        when(settingRepository.findByProperty(PROPERTY)).thenReturn(applicationSetting);
        when(applicationSetting.getValue()).thenReturn("111");
        assertEquals(settingService.findByProperty(PROPERTY), VALUE);
    }
}
