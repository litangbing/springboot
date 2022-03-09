package com.example.springboot.mapper;

import com.example.springboot.pojo.User;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper {
   @Select("select * from user where id =#{id}")
   User selectById(Integer id);

   @Select("select *from user ")
   List<User> selectAll();


}




