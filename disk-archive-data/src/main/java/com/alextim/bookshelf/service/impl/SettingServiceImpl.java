package com.alextim.bookshelf.service.impl;

import com.alextim.bookshelf.repository.SettingRepository;
import com.alextim.bookshelf.service.ISettingService;
import com.alextim.entity.ApplicationSetting;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by admin on 20.09.2016.
 */
@Service
public class SettingServiceImpl implements ISettingService{
    @Autowired
    private SettingRepository settingRepository;

    @Override
    public String findByProperty(String property) {
        if(StringUtils.isEmpty(property)){
            throw new IllegalArgumentException("property shouldn't be empty or null value");
        }
        ApplicationSetting applicationSetting = settingRepository.findByProperty(property);
        if (applicationSetting == null) {
            return "";
        }
        return applicationSetting.getValue();
    }
}
