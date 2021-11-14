package com.xjtu.qgsystem.service;

import com.xjtu.qgsystem.util.result.Result;
import com.xjtu.qgsystem.util.result.ResultUtil;
import com.xjtu.qgsystem.vo.UserVO;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;

@Service
public class UserService {

    public String login(String username, String password) {
        if (username.equals("admin") && password.equals("zsclqg")) {
            return "admin-token";
        } else {
            return "-1";
        }
    }

    public UserVO getInfo(String token) {
        if (token.equals("admin-token")) {
            return new UserVO(Collections.singletonList("admin"),
                    "I am a super administrator",
//                    "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif",
                    "https://img.zcool.cn/community/01e8fa5965991ba8012193a3195e5a.gif",
                    "Super Admin");
        } else return null;
    }

    public String logout() {
        return "success";
    }
}
