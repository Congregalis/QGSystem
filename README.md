#  问题生成数据评估系统后端

前后端分离项目，该仓库仅包含后端，前端仓库在[这里](https://github.com/Congregalis/QGSystem-Vue)

## 功能
```
- 用户管理
    - 登陆 / 登出
    - 权限管理
    - 我的评估
- 问题数据展示
    - 图表可视化展示
    - 全部数据展示
- 问题评估
    - 三大指标评估
    - 难度评估
    - 数据修改
- 数据添加
    - 手动添加
    - json文件导入（SQuAD 格式）
```

## 目录介绍
```
.
├── component // 自定义的 component
├── config // 配置类
├── controller 
├── entity // 实体类
├── exception // 处理异常
├── repository // data 层
│   └── redis // 缓存层
├── service
├── util // 工具类
│   └── result
└── vo // 返回前端的响应类
```

## Demo
[在线Demo](http://zscl.xjtudlc.com:8089)

## 使用了
- springboot
- jpa
- sa-token
- hutool