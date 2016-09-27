package com.alextim.bookshelf.repository;

import com.alextim.entity.ApplicationSetting;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by admin on 20.09.2016.
 */
public interface SettingRepository extends JpaRepository<ApplicationSetting, Long> {
    ApplicationSetting findByProperty(String property);
}
