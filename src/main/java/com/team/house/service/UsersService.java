package com.team.house.service;

import com.github.pagehelper.PageInfo;
import com.team.house.entity.Users;
import com.team.house.utils.UserSearch;

public interface UsersService {
    public PageInfo<Users> getUsersByPage(UserSearch userSearch);

    public int getUsersByName(String name);

    public int addUser(Users users);

    public Users loginUser(String name,String password);
}
