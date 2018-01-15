package com.mapper;

import com.po.SysMac;

public interface SysMacMapper {
    int deleteByPrimaryKey(Integer mac_id);

    int insert(SysMac record);

    int insertSelective(SysMac record);

    SysMac selectByPrimaryKey(Integer mac_id);

    int updateByPrimaryKeySelective(SysMac record);

    int updateByPrimaryKey(SysMac record);
}