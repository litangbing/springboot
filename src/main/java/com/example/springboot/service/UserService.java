package com.example.springboot.service;


import com.example.springboot.pojo.User;


import java.util.List;

public interface UserService {


   User findById(Integer id);

   List<User> findAll();



}
