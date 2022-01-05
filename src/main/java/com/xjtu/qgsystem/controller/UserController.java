package com.xjtu.qgsystem.controller;

import com.xjtu.qgsystem.service.UserService;
import com.xjtu.qgsystem.util.result.Result;
import com.xjtu.qgsystem.util.result.ResultUtil;
import com.xjtu.qgsystem.vo.LoginVO;
import com.xjtu.qgsystem.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

//    @RequestMapping(value = "/login", method = RequestMethod.POST)
//    public Result login(@RequestParam(value = "username") String username,
//                        @RequestParam(value = "password") String password) {
//        String res = userService.login(username, password);
//        return res != "-1" ? ResultUtil.success(res) : ResultUtil.fail("登陆失败");
//    }
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public LoginVO login(@RequestParam(value = "username") String username,
                        @RequestParam(value = "password") String password) {
        String res = userService.login(username, password);
        return res.equals("-1") ? null : new LoginVO(200, res);
    }

    @RequestMapping(value = "/isLogin", method = RequestMethod.GET)
    public Result isLogin() {
        return ResultUtil.success(userService.isLogin());
    }

    @RequestMapping(value = "/{token}", method = RequestMethod.GET)
    public Result getUserInfo(@PathVariable("token") String token) {
        UserVO userVO = userService.getInfo(token);
        return userVO != null ? ResultUtil.success(userVO) : ResultUtil.fail("获取失败");
    }

    @RequestMapping(value = "logout", method = RequestMethod.POST)
    public Result logout() {
        return ResultUtil.success(userService.logout());
    }
}
