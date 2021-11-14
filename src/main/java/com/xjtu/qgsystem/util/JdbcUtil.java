package com.xjtu.qgsystem.util;

import com.xjtu.qgsystem.entity.Question;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class JdbcUtil {

    private static String driver;
    private static String url;
    private static String user;
    private static String password;

    static{
        try{
            //1.新建属性集对象
            Properties properties = new Properties();
            //2通过反射，新建字符输入流，读取db.properties文件
            InputStream input = JdbcUtil.class.getClassLoader().getResourceAsStream("db.properties");
            //3.将输入流中读取到的属性，加载到properties属性集对象中
            properties.load(input);
            //4.根据键，获取properties中对应的值
            driver = properties.getProperty("driver");
            url = properties.getProperty("url");
            user = properties.getProperty("user");
            password = properties.getProperty("password");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    // 返回数据库连接
    public static Connection getConnection(){
        try{
            //注册数据库的驱动
            Class.forName(driver);
            //获取数据库连接（里面内容依次是：主机名和端口、用户名、密码）
            Connection connection = DriverManager.getConnection(url,user,password);
            //返回数据库连接
            return connection;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    // 插入一条 context
    public static Long insertContext(Connection connection, String text, String title) throws SQLException {
        String sql = "insert into context(id, text, title) values(?, ?, ?)";
        Long id = getAutoId("context", connection);

        PreparedStatement statement = connection.prepareCall(sql);
        statement.setLong(1, id);
        statement.setString(2, text);
        statement.setString(3, title);
        //执行sql语句（插入了几条记录，就返回几）
        int i = statement.executeUpdate();  //executeUpdate：执行并更新
//        System.out.println(i);

        //关闭jdbc连接
        statement.close();

        return id;
    }

    // 插入一个问答对
    public static Long insertQuestion(Connection connection, String text,
                                      int answerStart, String answerText, Long contextId) throws SQLException {
        String sql = "insert into question(id, text, answerStart, answerText, contextId) values(?, ?, ?, ?, ?)";
        Long id = getAutoId("question", connection);

        PreparedStatement statement = connection.prepareCall(sql);
        statement.setLong(1, id);
        statement.setString(2, text);
        statement.setInt(3, answerStart);
        statement.setString(4, answerText);
        statement.setLong(5, contextId);

        statement.executeUpdate();
        statement.close();

        return id;
    }

    // 自动获取主键
    public static Long getAutoId(String tableName, Connection connection) {
        Long lastId = 0L;
        String sql = "SELECT * FROM " + tableName + " ORDER BY id DESC LIMIT 1";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet res = statement.executeQuery();
            while(res.next()) {
                lastId = res.getLong("id");
            }

            //关闭jdbc连接
            statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return lastId + 1;
    }
}
