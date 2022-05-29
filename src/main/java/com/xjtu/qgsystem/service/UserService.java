package com.xjtu.qgsystem.service;

import cn.dev33.satoken.stp.StpUtil;
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

import static com.xjtu.qgsystem.util.MD5Util.md5;

@Service
public class UserService {
    private final String secret = "QG_SYSTEM_SERVER";

    @Autowired
    private UserRepository userRepository;

    public String login(String username, String password) {
        Optional<User> byUsername = userRepository.findByUsername(username);
        if (!byUsername.isPresent()) return "-1";

        User user = byUsername.get();

        // md5方式存储在数据库
        if ((user.getPassword().equals(md5(password)))) {
            String token = TokenUtil.getInstance().generateToken(user);

            // 若该用户已在线上，则踢下线，使新登陆的客户端上线
            // (访客除外，所以 public 账号不用踢下线）
            if (!username.equals("public")) StpUtil.kickout(token);

            StpUtil.login(token);
            return token;
        } else {
            return "error token";
        }
    }

    public String isLogin() {
        System.out.println("tokenInfo:  " + StpUtil.getTokenInfo());
        return StpUtil.isLogin() ? "已登陆" : "未登陆";
    }

    public UserVO getInfo(String token) {
        if (TokenUtil.getInstance().checkToken(token)) {
            return TokenUtil.getInstance().getInfoFromToken(token);
        } else {
            return null;
        }
    }

    public String logout() {
        StpUtil.logout();
        return "success";
    }
}
