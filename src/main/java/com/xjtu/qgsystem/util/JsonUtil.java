package com.xjtu.qgsystem.util;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONReader;
import com.xjtu.qgsystem.repository.ContextRepository;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@Component
public class JsonUtil {

    @Autowired
    ContextRepository contextRepository;

    public static void readBigJson() throws FileNotFoundException, SQLException {
        JSONReader jsonReader = new JSONReader(new FileReader("/Users/congregalis/Desktop/train_m.json"));

        // 先获取数据库连接，保持长连接避免开销
        Connection conn = JdbcUtil.getConnection();

        jsonReader.startArray();
        while (jsonReader.hasNext()) {
            JSONObject jsonObject = jsonReader.readObject(JSONObject.class);
            try {
                readAndStoreData(conn, jsonObject);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        jsonReader.endArray();
        jsonReader.close();
        // 关闭数据库连接
        conn.close();
    }

    public static void readSmallJson() throws IOException, SQLException {
        File file = new File("/Users/congregalis/Desktop/test.json");
        String rawData = FileUtils.readFileToString(file, "utf-8");
        JSONObject json = JSON.parseObject(rawData);

        JSONArray data = json.getJSONArray("data");
        System.out.println("data的大小：" + data.size());

        // 先获取数据库连接，保持长连接避免开销
        Connection conn = JdbcUtil.getConnection();

        for (int i = 0; i < data.size(); i++) {
            try {
                readAndStoreData(conn, data.getJSONObject(i));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        // 关闭数据库连接
        conn.close();
    }

    public static void readAndStoreData(Connection conn, JSONObject data) throws SQLException {

        String title = data.getString("title");
        JSONArray paragraphs = data.getJSONArray("paragraphs");
//        System.out.println("题目：" + title);

        for (int j = 0; j < paragraphs.size(); j++) {
            JSONObject paragraph = paragraphs.getJSONObject(j);
            String context = paragraph.getString("context");
//            context = context.replace("\n", "");
            // 存 context，并留下对应id
            Long contextId = JdbcUtil.insertContext(conn, context, title);

            JSONArray qas = paragraph.getJSONArray("qas");
            for (int k = 0; k < qas.size(); k++) {
//                System.out.println("问：" + qas.getJSONObject(k).getString("question"));
                // 存 qa 对
                JdbcUtil.insertQuestion(
                        conn,
                        qas.getJSONObject(k).getString("question"),
                        qas.getJSONObject(k).getJSONArray("answers").getJSONObject(0).getInteger("answer_start"),
                        qas.getJSONObject(k).getJSONArray("answers").getJSONObject(0).getString("text"),
                        contextId);

                /**
                 * 注：经测试，答案均只有 1 个，所以单个存入数据库即可
                 */

            }
        }
    }


    public static void main(String[] args) throws IOException, SQLException {
//        JsonUtil.readBigJson();
        JsonUtil.readSmallJson();
    }
}
