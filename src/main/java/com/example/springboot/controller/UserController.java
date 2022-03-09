package com.example.springboot.controller;


import com.example.springboot.config.Result;
import com.example.springboot.pojo.User;
import com.example.springboot.service.UserService;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/user")

public class UserController {
    @Autowired
    private UserService userService;



    @GetMapping({"","/"})

    public Result findAll(){
        List<User> users = userService.findAll();
        System.out.println("123456");
        return new Result(200,users,"查询成功");
    }

    @PostMapping("/{id}")
    @ApiOperation("根据id查询用户")
    public Result findById(@PathVariable("id") Integer id){

        User user = userService.findById(id);
        if(user==null){
            return new Result(400,user,"查询失败");
        }
        return new Result(200,user,"查询成功");

    }

    //http://localhost:8080/user/find?uid=2    @RequestParam("uid")  把get请求路径中参数uid映射给方法中的形参id
    @GetMapping("/find")
    public Result getUser(@RequestParam("uid") Integer id, HttpServletResponse response,
                          HttpServletRequest request){
        //存储在服务器的内存中，tomcat的StandardManager类将session存储在内存中，也可以持久化到file，数据库，memcache，redis等。
        // 客户端只保存sessionid到cookie中，而不会保存session，session销毁只能通过invalidate或超时，关掉浏览器并不会关闭session。
        request.getSession();//创建服务端session并生产sessionid给客户端存到Cookie中

        User user = userService.findById(id);
        return new Result(200,user,"post查询成功");
    }

    //示例把请求头（header）部分的 Accept-Encoding的值，绑定到了参数encoding上了
    @GetMapping("/get")
    public void  getUserHeader(@RequestHeader("Accept-Encoding") String encoding,
                               @RequestHeader("Cookie") String cookie,
                               HttpServletResponse response){
        //cookie 是一个非常具体的东西，指的就是浏览器里面能永久存储的一种数据，仅仅是浏览器实现的一种数据存储功能。
        //cookie由服务器生成，发送给浏览器，浏览器把cookie以kv形式保存到某个目录下的文本文件内，下一次请求同一网站时会把该cookie发送给服务器。
        // 由于cookie是存在客户端上的，所以浏览器加入了一些限制确保cookie不会被恶意使用，同时不会占据太多磁盘空间，所以每个域的cookie数量是有限的

        Cookie cookie2 =new Cookie("Status","200");
        response.addCookie(cookie2);

        System.out.println(cookie);
        System.out.println(encoding);

    }

    @GetMapping("/test")
      public String test(){

        return "你好2222333";
    }
    @RequestMapping(value = "/index")
    public String index(Model model) {
        model.addAttribute("data","Hello,SpringBoot 集成 Thymeleaf 模版");
        User user = new User();
        user.setId(10001);
        user.setName("王五");
        model.addAttribute("user",user);
        return "index"; }


}
