package com.team.house.service;

import com.github.pagehelper.PageInfo;
import com.team.house.entity.Type;
import com.team.house.utils.Page;

import java.util.List;

public interface TypeService {
    public PageInfo<Type> getTypeByPage(Page page);

    public int addType(Type type);

    public int updateType(Type type);

    public int deleteType(Integer[] id);

    public List<Type> getTypeList();
}
