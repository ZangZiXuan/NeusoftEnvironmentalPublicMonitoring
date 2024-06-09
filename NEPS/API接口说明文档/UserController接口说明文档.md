## `UserController`接口说明文档

### 概述

此接口文档描述了`UserController`中定义的API端点，用于处理用户的注册和登录功能。

### 接口列表

1. [创建用户](#创建用户)
2. [用户登录](#用户登录)

### 创建用户

#### 描述

创建一个新的用户账号。

#### URL

`/user/creatUser`

#### 方法

`POST`

#### 请求参数

| 参数名   | 类型    | 是否必填 | 说明       |
| -------- | ------- | -------- | ---------- |
| phone    | String  | 是       | 用户手机号 |
| age      | Integer | 是       | 用户年龄   |
| sex      | String  | 是       | 用户性别   |
| password | String  | 是       | 用户密码   |

#### 请求示例

```json
{
  "phone": "1234567890",
  "age": 30,
  "sex": "男",
  "password": "password123"
}
```

#### 响应参数

| 参数名  | 类型    | 说明           |
| ------- | ------- | -------------- |
| success | Boolean | 操作是否成功   |
| data    | Object  | 返回的用户对象 |
| message | String  | 操作结果信息   |

#### 响应示例

成功响应：

```json
{
  "success": true,
  "data": {
    "phone": "1234567890",
    "age": 30,
    "sex": "男",
    "password": "password123"
  },
  "message": "注册成功"
}
```

失败响应：

```json
{
  "success": false,
  "data": null,
  "message": "该号码已经注册"
}
```

### 用户登录

#### 描述

用户登录验证。

#### URL

`/user/loginUser`

#### 方法

`GET`

#### 请求参数

| 参数名   | 类型   | 是否必填 | 说明       |
| -------- | ------ | -------- | ---------- |
| phone    | String | 是       | 用户手机号 |
| password | String | 是       | 用户密码   |

#### 请求示例

`/user/loginUser?phone=1234567890&password=password123`

#### 响应参数

| 参数名  | 类型    | 说明           |
| ------- | ------- | -------------- |
| success | Boolean | 操作是否成功   |
| data    | Object  | 返回的用户对象 |
| message | String  | 操作结果信息   |

#### 响应示例

成功响应：

```json
{
  "success": true,
  "data": {
    "id": "用户ID",
    "phone": "1234567890",
    "age": 30,
    "sex": "男",
    "password": "加密后的密码"
  },
  "message": "登录成功"
}
```

失败响应：

```json
{
  "success": false,
  "data": null,
  "message": "登录失败，用户名与密码不匹配"
}
```