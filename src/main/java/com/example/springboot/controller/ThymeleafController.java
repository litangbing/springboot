package com.example.springboot.controller;


import com.example.springboot.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ThymeleafController {

    @RequestMapping("/abc/index")
    public String index(Model model){

        model.addAttribute("data","模板整合成功");
        return "index";
    }

    @RequestMapping("/each/list")
    public String eachList(Model model) {
        List<User> userList = new ArrayList<User>();
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setId(100 + i);
            user.setName("张"+i);
            user.setAge(20+i);
            userList.add(user);
            model.addAttribute("userList", userList);
        }
        return "each";
    }
}
