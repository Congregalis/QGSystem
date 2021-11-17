package com.xjtu.qgsystem.service;

import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import com.xjtu.qgsystem.entity.User;
import com.xjtu.qgsystem.repository.UserRepository;
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
            String introduction = user.getIntroduction();
            String avatar = user.getAvatar();
            String name = user.getName();

            Map<String, Object> userMap = new HashMap<String, Object>() {
                private static final long serialVersionUID = 1L;
                {
                    put("name", name);
                    put("introduction", introduction);
                    put("avatar", avatar);
                }
            };
            return JWTUtil.createToken(userMap, secret.getBytes());
        } else {
            return "-1";
        }
    }

    public UserVO getInfo(String token) {
        if (JWTUtil.verify(token, secret.getBytes())) {
            final JWT jwt = JWTUtil.parseToken(token);
            // todo: 权限组功能暂未做
            return new UserVO(Collections.singletonList("admin"),
                    (String) jwt.getPayload("introduction"),
                    (String) jwt.getPayload("avatar"),
                    (String) jwt.getPayload("name"));
        } else {
            return null;
        }
    }

    public String logout() {
        return "success";
    }
}
