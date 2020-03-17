package com.example.demo.dao;

import com.example.demo.mode.Msg;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MsgDao {

    @Insert("insert into msg(user_id,content) values(" +
            "(select user_id from user where user_name=#{username}),#{content})")
    int addMsg(Msg msg);

    @Select("select u.user_name username, m.content, m.datetime from user u, msg m " +
            "where m.user_id=u.user_id\n" +
            "order by datetime desc limit 0,#{number}")
    List<Msg> selectMsgs(int number);
}
