package com.study.api;


import com.study.entiy.Settings;

/**
 * 通用设置
 */
public interface SettingsApi {

    //根据用户id查询
    Settings findByUserId(Long userId);

    //保存
    void save(Settings settings);

    //更新
    void update(Settings settings);
}
