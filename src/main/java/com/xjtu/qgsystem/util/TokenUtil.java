package com.xjtu.qgsystem.util;

import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import com.xjtu.qgsystem.entity.User;
import com.xjtu.qgsystem.vo.UserVO;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class TokenUtil {
    private static final TokenUtil tokenUtil = new TokenUtil();

    private final String secret = "QG_SYSTEM_SERVER";

    private TokenUtil() {

    }

    public static TokenUtil getInstance() { return tokenUtil; }

    /**
     * 根据用户信息生成 token
     * @param user 用户信息
     * @return token
     */
    public String generateToken(User user) {
        Map<String, Object> userMap = new HashMap<String, Object>() {
            private static final long serialVersionUID = 1L;
            {
                put("name", user.getName());
                put("introduction", user.getIntroduction());
                put("avatar", user.getAvatar());
                put("id", user.getId());
            }
        };
        return JWTUtil.createToken(userMap, secret.getBytes());
    }

    /**
     * 验证 token 信息是否正确
     * @param token 信息
     * @return token 是否正确
     */
    public boolean checkToken(String token) {
        return JWTUtil.verify(token, secret.getBytes());
    }

    /**
     * 根据 token 获取用户信息，专门针对前端需求所做
     * @param token 信息
     * @return  前端需要的用户信息
     */
    public UserVO getInfoFromToken(String token) {
        final JWT jwt = JWTUtil.parseToken(token);

        // todo: 权限组功能暂未做
        return new UserVO(Collections.singletonList("admin"),
                (String) jwt.getPayload("introduction"),
                (String) jwt.getPayload("avatar"),
                (String) jwt.getPayload("name"));
    }

    /**
     * 根据 token 解析用户id
     * @param token 信息
     * @return uid
     */
    public long getUserIdFromToken(String token) {
        return ((Integer) JWTUtil.parseToken(token).getPayload("id")).longValue();
    }
}
