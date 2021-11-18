package com.xjtu.qgsystem.service;

import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import com.xjtu.qgsystem.entity.User;
import com.xjtu.qgsystem.repository.UserRepository;
import com.xjtu.qgsystem.util.TokenUtil;
import com.xjtu.qgsystem.util.result.Result;
import com.xjtu.qgsystem.util.result.ResultUtil;
import com.xjtu.qgsystem.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import cn.hutool.*;

@Service
public class UserService {
    private final String secret = "QG_SYSTEM_SERVER";

    @Autowired
    private UserRepository userRepository;

    public String login(String username, String password) {
        Optional<User> byUsername = userRepository.findByUsername(username);
        if (!byUsername.isPresent()) return "-1";

        User user = byUsername.get();

        // todo: 密码暂时先用明文存在数据库中，后续更换更安全待方式
        if (user.getPassword().equals(password)) {
            return TokenUtil.getInstance().generateToken(user);
        } else {
            return "error token";
        }
    }

    public UserVO getInfo(String token) {
        if (TokenUtil.getInstance().checkToken(token)) {
            return TokenUtil.getInstance().getInfoFromToken(token);
        } else {
            return null;
        }
    }

    public String logout() {
        return "success";
    }
}
