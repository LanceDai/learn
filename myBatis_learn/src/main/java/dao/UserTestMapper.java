package dao;

import model.UserTest;

public interface UserTestMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(UserTest record);

    int insertSelective(UserTest record);

    UserTest selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(UserTest record);

    int updateByPrimaryKey(UserTest record);
}