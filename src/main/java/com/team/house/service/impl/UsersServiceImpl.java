package com.team.house.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.team.house.entity.Users;
import com.team.house.entity.UsersExample;
import com.team.house.mapper.UsersMapper;
import com.team.house.service.UsersService;
import com.team.house.utils.MD5Utils;
import com.team.house.utils.UserSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersServiceImpl implements UsersService {
    @Autowired
    private UsersMapper usersMapper;
    @Override
    public PageInfo<Users> getUsersByPage(UserSearch userSearch) {
        PageHelper.startPage(userSearch.getPage(),userSearch.getRows());
        UsersExample example=new UsersExample();
        UsersExample.Criteria criteria = example.createCriteria();
        if (userSearch.getName()!=null){
            criteria.andNameLike("%"+userSearch.getName()+"%");
        }
        if (userSearch.getTelephone()!=null){
            criteria.andTelephoneLike("%"+userSearch.getTelephone()+"%");
        }
        criteria.andIsadminEqualTo(1);
        List<Users> list = usersMapper.selectByExample(example);
        PageInfo<Users> info=new PageInfo<>(list);
        return info;
    }

    @Override
    public int getUsersByName(String name) {
        UsersExample example=new UsersExample();
        UsersExample.Criteria criteria = example.createCriteria();
        criteria.andNameEqualTo(name);
        criteria.andIsadminEqualTo(0);
        List<Users> users = usersMapper.selectByExample(example);
        return users.size();
    }

    @Override
    public int addUser(Users users) {
        users.setIsadmin(0);
        String password = users.getPassword();
        String s = MD5Utils.md5Encrypt(password);
        users.setPassword(s);
        int i = usersMapper.insertSelective(users);
        return i;
    }

    @Override
    public Users loginUser(String name, String password) {
        UsersExample example=new UsersExample();
        UsersExample.Criteria criteria = example.createCriteria();
        criteria.andNameEqualTo(name);
        criteria.andPasswordEqualTo(MD5Utils.md5Encrypt(password));
        List<Users> users = usersMapper.selectByExample(example);
        if (users.size()>0){
            return users.get(0);
        }else {
            return null;
        }
    }
}
