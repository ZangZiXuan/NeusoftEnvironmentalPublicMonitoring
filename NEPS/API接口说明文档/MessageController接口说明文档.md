## `MessageController`接口说明文档

### 概述

此接口文档描述了`MessageController`中定义的API端点，用于处理空气质量监督信息的提交和查询功能。

### 接口列表

1. [创建消息](#创建消息)
2. [查看用户消息](#查看用户消息)

### 创建消息

#### 描述

创建一条新的空气质量监督信息。

#### URL

`/message/createMessage`

#### 方法

`POST`

#### 请求参数

| 参数名   | 类型   | 是否必填 | 说明                         |
| -------- | ------ | -------- | ---------------------------- |
| id       | String | 是       | 消息ID，由系统自动生成       |
| userId   | String | 是       | 用户ID                       |
| province | String | 是       | 省份                         |
| city     | String | 是       | 城市                         |
| level    | String | 是       | 空气质量等级                 |
| date     | Date   | 否       | 消息创建时间，由系统自动生成 |
| detail   | String | 是       | 详细信息                     |
| address  | String | 是       | 地址                         |

#### 请求示例

```json
{
  "userId": "user123",
  "province": "省份",
  "city": "城市",
  "level": "1",
  "detail": "详细信息",
  "address": "地址"
}
```

#### 响应参数

| 参数名  | 类型    | 说明           |
| ------- | ------- | -------------- |
| success | Boolean | 操作是否成功   |
| data    | Object  | 返回的消息对象 |
| message | String  | 操作结果信息   |

#### 响应示例

成功响应：

```json
{
  "success": true,
  "data": {
    "id": "自动生成的UUID",
    "userId": "user123",
    "province": "省份",
    "city": "城市",
    "level": "空气质量等级",
    "date": "当前日期和时间",
    "detail": "详细信息",
    "address": "地址"
  },
  "message": "提交空气质量监督信息成功"
}
```

失败响应：

```json
{
  "success": false,
  "data": null,
  "message": "提交空气质量监督信息失败"
}
```

### 查看用户消息

#### 描述

查询指定用户的所有空气质量监督信息提交记录。

#### URL

`/message/viewMyMessage/{userId}`

#### 方法

`GET`

#### 路径参数

| 参数名 | 类型   | 是否必填 | 说明   |
| ------ | ------ | -------- | ------ |
| userId | String | 是       | 用户ID |

#### 请求示例

`/message/viewMyMessage/user123`

#### 响应参数

| 参数名  | 类型    | 说明         |
| ------- | ------- | ------------ |
| success | Boolean | 操作是否成功 |
| data    | List    | 消息对象列表 |
| message | String  | 操作结果信息 |

#### 响应示例

成功响应：

```json
{
  "success": true,
  "data": [
    {
      "id": "消息ID",
      "userId": "user123",
      "province": "省份",
      "city": "城市",
      "level": "空气质量等级",
      "date": "日期和时间",
      "detail": "详细信息",
      "address": "地址"
    },
    ...
  ],
  "message": "查询当前用户的所有提交记录成功"
}
```

失败响应：

```json
{
  "success": false,
  "data": null,
  "message": "暂无任何提交记录"
}
```
