package com.example.demo.dao;


import com.example.demo.mode.Msg;
import com.example.demo.mode.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;

@Mapper
public interface UserDao {

    @Insert({"insert into user(user_name,password) values(#{username},#{pswd})"})
//    @SelectKey(statement = "select last_insert_id()", keyProperty = "id", before = false, resultType = Integer.class)
    int addUser(User user);

    @Select("select user_name username, password pswd from user where user_name=#{name}")
    User getUserByName(String name);
}
