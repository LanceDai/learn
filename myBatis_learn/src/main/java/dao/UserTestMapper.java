package dao;

import model.UserTest;

import java.util.List;
import java.util.Map;

public interface UserTestMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(UserTest record);

    int insertSelective(UserTest record);

    UserTest selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(UserTest record);

    int updateByPrimaryKey(UserTest record);

    List<UserTest> selectAll();

    List<Map> selectAllWithMap();

    void deleteAll();
}