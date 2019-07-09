package com.team.house.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.team.house.entity.Type;
import com.team.house.entity.TypeExample;
import com.team.house.mapper.TypeMapper;
import com.team.house.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {
    @Autowired
    private TypeMapper typeMapper;
    @Override
    public PageInfo<Type> getTypeByPage(com.team.house.utils.Page page) {
        PageHelper.startPage(page.getPage(),page.getRows());
        List<Type> list = typeMapper.selectByExample(null);
        PageInfo<Type> info=new PageInfo<>(list);
        return info;
    }

    @Override
    public int addType(Type type) {
        return typeMapper.insertSelective(type);
    }

    @Override
    public int updateType(Type type) {
        return typeMapper.updateByPrimaryKeySelective(type);
    }

    @Override
    public int deleteType(Integer[] id) {
        TypeExample example=new TypeExample();
        TypeExample.Criteria criteria = example.createCriteria();
        List<Integer> list = Arrays.asList(id);
        criteria.andIdIn(list);
        return typeMapper.deleteByExample(example);
    }

    @Override
    public List<Type> getTypeList() {
        return typeMapper.selectByExample(null);
    }
}
