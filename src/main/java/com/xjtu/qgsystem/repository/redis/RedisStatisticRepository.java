package com.xjtu.qgsystem.repository.redis;

//import com.sun.corba.se.spi.ior.ObjectKey;
import org.hibernate.annotations.Check;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
public class RedisStatisticRepository {

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    static final String DistributionByTitle = "distribution_by_title";
    static final String DistributionByDifficulty = "distribution_by_difficulty";
    static final String CheckedNum = "QG_checked_num";
    static final String UnCheckedNum = "QG_unchecked_num";

    /**
     * 指定缓存失效时间
     *
     * @param key  键
     * @param time 时间(秒)
     * @return
     */
    public boolean expire(String key, long time) {
        try {
            if (time > 0) {
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean saveDistributionByTitle(List<Map<String, Object>> distribution) {
       Map<String, Object> map = new HashMap<>();

       for (Map<String, Object> tmp : distribution) {
           map.put((String) tmp.get("title"), tmp.get("COUNT(*)"));
       }

        try {
            redisTemplate.opsForHash().putAll(DistributionByTitle, map);
            expire(DistributionByTitle, 24 * 60 * 60);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Map<String, Object>> getDistributionByTitle() {
        List<Map<String, Object>> res = new ArrayList<>();
        Map<Object, Object> tmp = redisTemplate.opsForHash().entries(DistributionByTitle);

        if (tmp.size() == 0) return null;

        for (Map.Entry<Object, Object> entry : tmp.entrySet()) {
            Map<String, Object> map = new HashMap<>();
            map.put("title", entry.getKey());
            map.put("COUNT(*)", entry.getValue());

            res.add(map);
        }

        return res;
    }

    public boolean saveDistributionByDifficulty(List<Map<String, Object>> distribution) {
        Map<String, Object> map = new HashMap<>();

        for (Map<String, Object> tmp : distribution) {
            map.put((String) String.valueOf(tmp.get("difficulty")), tmp.get("COUNT(*)"));
        }

        try {
            redisTemplate.opsForHash().putAll(DistributionByDifficulty, map);
            expire(DistributionByTitle, 24 * 60 * 60);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Map<String, Object>> getDistributionByDifficulty() {
        List<Map<String, Object>> res = new ArrayList<>();
        Map<Object, Object> tmp = redisTemplate.opsForHash().entries(DistributionByDifficulty);

        if (tmp.size() == 0) return null;

        for (Map.Entry<Object, Object> entry : tmp.entrySet()) {
            Map<String, Object> map = new HashMap<>();
            map.put("difficulty", entry.getKey());
            map.put("COUNT(*)", entry.getValue());

            res.add(map);
        }

        return res;
    }

    public boolean saveCheckedInfo(int checkedNum, int uncheckedNum) {
        try {
            redisTemplate.opsForValue().set(CheckedNum, checkedNum);
            redisTemplate.opsForValue().set(UnCheckedNum, uncheckedNum);
            expire(CheckedNum, 24 * 60 * 60);
            expire(UnCheckedNum, 24 * 60 * 60);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public int[] getCheckedInfo() {
        return new int[] {
                (int) redisTemplate.opsForValue().get(CheckedNum),
                (int) redisTemplate.opsForValue().get(UnCheckedNum)
        };
    }
}
