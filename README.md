# NeusoftEnvironmentalPublicMonitoring
大三下企业实训
## 使用的技术栈：Springboot + lombok + mysql + mybatis_plus
后端
数据库账户：root
密码：root
## 项目结构：
● NEPS端：公众监督员端。完成公众监督空气质量的全部业务流程。
● NEPG端：AQI检测网格员端。完成对公众监督员反馈的空气质量问题做AQI确认的全部业务流程。
● NEPM端：系统管理端。完成对公众监督员反馈的空气质量问题做指派处理，并对AQI检测网格员提交的AQI数据做统计处理，用于给决策者决策的依据。
● NEPV端：可视化大屏端。将系统管理端统计数据用可视化的方式展示给决策者。


#  接口说明文档

由于后端全部工作目前已经结束，现在所有模块均可运行

注意由于是chat生成后我整合的，如果有一些字段不同，按照实体类中的进行

## 实体类

```java
//根据代码中的
public class AQIDTO {
    private int aqiLevel;
    private String description;
    private int countNum;
}
public class MessageGriddlerDTO extends MessageGriddler {
    private String provinceId;
    private String provinceName;
    private String provinceShortTitle;
    private Integer soNum;
    private Integer coNum;
    private Integer pmNum;
    private Integer AQINum;

}
public class MessagePublicDTO {
    private Public aPublic;
    private MessagePublic messagePublic;
    private String provinceName;
    private String cityName;
    private String shortTitle;
}
public class PlaceDTO {
    private String shortTitle;
    private String provinceId;
    private String cityId;

}
public class AQI {

    private String aqiId;
    private String description;

    private String aqiDescription;
    private String color;
    private String healthImpact;
    private String measures;
    private int so2Min;
    private int so2Max;
    private int coMin;
    private int coMax;
    private int pmMin;
    private int pmMax;
    private String remarks;
}
public class City {
    private String id;
    private String cityId;
    private String cityName;
    private String provinceId;
    @TableField("is_capital_city")
    private int isCapitalCity;
    @TableField("is_big_city")
    private int isBigCity;
}
public class Griddler {
    private String id;
    private String name;
    private String code;
    private String password;
    private String provinceId;
    private String cityId;
    private String phone;
    /**
     * 网格员状态
     * 0：可工作状态
     * 1：临时抽调
     * 2：休假
     * 3：其他
     */
    private String statuses;
    private String remarks;
}
public class Manager {
    @TableId(value = "id",type = IdType.ASSIGN_UUID)
    private String id;
    private String managerCode;
    private String password;
}
public class MessageGriddler {
    @TableId(value = "id",type = IdType.ASSIGN_UUID)
    private String id;
    @TableField("message_public_id")
    private String messagePublicId;
    private String griddlerId;
    private int so2;
    private int co;
    private int pm;
    private Date time;
    /**
     * 待确认：0，已确认1
     */
    private int status;
    private int aqiLevel;
}
public class MessageManager {
    @TableId(value = "id",type = IdType.ASSIGN_UUID)
    private String id;
    private String messageId;
    private String griddlerId;
    private int statusManager;
    private String provinceId;
    private String cityId;
    private int status;
}
public class MessagePublic {
    @TableId(value = "id",type = IdType.ASSIGN_UUID)
    private String id;
    private String publicId;
    private String provinceId;
    private String cityId;
    private String address;
    private int level;

    private String description;
    private Date date;
    /**
     * 状态：
     * 待指派：0
     * 已指派：1
     */
    private int status;

    public MessagePublic(String id, String publicId, String provinceId, String cityId, String address) {
        this.id = id;
        this.publicId = publicId;
        this.provinceId = provinceId;
        this.cityId = cityId;
        this.address = address;
    }
}
public class PolicyMaker {
    @TableId(value = "id",type = IdType.ASSIGN_UUID)
    private String id;
    private String username;
    private String password;

}
public class Province {
    private String id;
    private String provinceId;

    private String provinceName;
    @TableField("province_abbreviation")
    private String shortTitle;
}
public class Public {
    @TableId(value = "id",type = IdType.ASSIGN_UUID)
    private String id;
    private String phone;
    private String name;
    private Date birthday;
    /**
     * 性别
     * 0：男
     * 1：女
     */
    private int gender;
    private String password;

}

```



## GriddlerController 类：

### 接口说明文档

#### 查找网格员

**接口地址**: `/griddler/findGriddler`  
**请求方式**: POST  
**请求参数**:

| 参数名   | 类型   | 是否必填 | 说明                |
| -------- | ------ | -------- | ------------------- |
| username | String | 是       | 登录用户名          |
| password | String | 是       | 登录密码（MD5加密） |

**请求示例**:
```json
{
    "username": "exampleUser",
    "password": "examplePassword"
}
```

**响应示例**:
```json
{
    "data": {
        "id": 1,
        "code": "exampleUser",
        "name": "John Doe",
        "statuses": 1,
        "provinceId": 10,
        "cityId": 100
    },
    "success": true,
    "message": "登录者的身份是网格员"
}
```

**失败响应示例**:
```json
{
    "message": "登录者的身份不是网格员，或者账户或密码错误",
    "data": null,
    "success": false
}
```

#### 查看所有能够安排任务的网格员

**接口地址**: `/griddler/selectGriddlerStatus`  
**请求方式**: GET  
**请求参数**: 无

**请求示例**: 无

**响应示例**:
```json
{
    "data": [
        {
            "id": 1,
            "code": "exampleUser1",
            "name": "John Doe",
            "statuses": 1,
            "provinceId": 10,
            "cityId": 100
        },
        {
            "id": 2,
            "code": "exampleUser2",
            "name": "Jane Doe",
            "statuses": 1,
            "provinceId": 10,
            "cityId": 100
        }
    ],
    "success": true,
    "message": "查看所有可以使用的网格员（状态为1是正常工作的）"
}
```

**失败响应示例**:
```json
{
    "message": "没有可以使用的网格员",
    "data": null,
    "success": false
}
```

#### 查看某个省某个市的网格员（异地指派）

**接口地址**: `/griddler/selectPlaceGriddler`  
**请求方式**: POST  
**请求参数**:

| 参数名        | 类型 | 是否必填 | 说明         |
| ------------- | ---- | -------- | ------------ |
| offsiteStatus | int  | 是       | 是否异地指派 |
| provinceId    | int  | 是       | 省份ID       |
| cityId        | int  | 是       | 城市ID       |

**请求示例**:
```json
{
    "offsiteStatus": 1,
    "provinceId": 10,
    "cityId": 100
}
```

**响应示例**:
```json
{
    "data": [
        {
            "id": 1,
            "code": "exampleUser1",
            "name": "John Doe",
            "statuses": 0,
            "provinceId": 10,
            "cityId": 100
        },
        {
            "id": 2,
            "code": "exampleUser2",
            "name": "Jane Doe",
            "statuses": 0,
            "provinceId": 10,
            "cityId": 100
        }
    ],
    "success": true,
    "message": "筛选异地指派的所有可用的网格员"
}
```

**失败响应示例**:
```json
{
    "message": "没有符合条件的网格员",
    "data": null,
    "success": false
}
```

**非异地指派请求示例**:
```json
{
    "offsiteStatus": 0,
    "provinceId": 10,
    "cityId": 100
}
```

**非异地指派响应示例**:
```json
{
    "data": null,
    "success": false,
    "message": "不是异地指派不应该调用当前接口"
}
```

### MessageGriddlerController接口说明文档

#### 1. 接口说明：网格员提交数据

接口名称：网格员确认提交数据

请求 URL：`/messageGriddler/creatMessageGriddler`

请求方法：POST

请求参数：

| 参数名            | 类型   | 是否必填 | 说明        |
| ----------------- | ------ | -------- | ----------- |
| co                | int    | 是       | CO 指数     |
| pm                | int    | 是       | PM 指数     |
| so2               | int    | 是       | SO2 指数    |
| message_public_id | String | 是       | 消息发布 ID |
| aqi_level         | int    | 是       | AQI 等级    |
| griddler_id       | String | 是       | 网格员 ID   |

#### 请求示例：
```json
{
    "co": 10,
    "pm": 100,
    "so2": 200,
    "message_public_id": "abc123",
    "aqi_level": 3,
    "griddler_id": "grid123"
}
```

#### 响应示例：
```json
{
    "success": true,
    "message": "网格员端的提交实测数据添加成功",
    "data": 1
}
```

#### 状态码说明：
| 状态码 | 说明               |
| ------ | ------------------ |
| 200    | 请求成功           |
| 400    | 请求失败，参数错误 |
| 500    | 服务器内部错误     |

---

### 2. 接口说明：查看所有网格员确认信息
接口名称：查看网格员确认的所有信息

请求 URL：`/messageGriddler/viewAllMessageGriddler`

请求方法：GET

请求参数：无

请求示例：

无

#### 响应示例：
```json
{
    "success": true,
    "message": "查看所有的网格员端的提交实测数据成功",
    "data": [
        {
            "id": "uuid123",
            "messagePublicId": "abc123",
            "griddlerId": "grid123",
            "so2": 200,
            "co": 10,
            "pm": 100,
            "time": "2024-06-16 11:29:00",
            "status": 1,
            "aqiLevel": 3
        },
        ...
    ]
}
```

#### 状态码说明：
| 状态码 | 说明           |
| ------ | -------------- |
| 200    | 请求成功       |
| 500    | 服务器内部错误 |

---

#### 3.接口说明：查看省分组分项检查统计

接口名称：省分组分项检查统计

请求 URL：`/messageGriddler/viewProvinceSubgroup`

请求方法：GET

请求参数：无

请求示例：

无

响应示例：

```json
{
    "success": true,
    "message": "查看省分组分项检查统计数据成功",
    "data": {
        "provinceStats": [
            {
                "provinceId": "province123",
                "provinceName": "某省",
                "shortTitle": "某",
                "coNum": 5,
                "pmNum": 10,
                "soNum": 2,
                "AQINum": 10
            },
            ...
        ]
    }
}
```

#### 状态码说明：
| 状态码 | 说明           |
| ------ | -------------- |
| 200    | 请求成功       |
| 500    | 服务器内部错误 |

---

### 4. 接口说明：查看 AQI 等级分布
#### 接口名称：AQI 空气质量指数级别分布
#### 请求 URL：`/messageGriddler/viewAQILevel`
#### 请求方法：GET

#### 请求参数：无

#### 请求示例：
无

#### 响应示例：
```json
{
    "success": true,
    "message": "统计 AQI 空气质量指数级别分布成功",
    "data": [
        {
            "aqiLevel": 1,
            "description": "优",
            "countNum": 10
        },
        ...
    ],
    "result": {
        "one": 10,
        "two": 20,
        "three": 30,
        "four": 40,
        "five": 50,
        "six": 60
    }
}
```

#### 状态码说明：
| 状态码 | 说明           |
| ------ | -------------- |
| 200    | 请求成功       |
| 500    | 服务器内部错误 |

---

### 5. 接口说明：AQI 空气质量指数超标数量统计
#### 接口名称：AQI 空气质量指数超标数量统计表
#### 请求 URL：`/messageGriddler/AqiLevelOver`
#### 请求方法：GET

#### 请求参数：无

#### 请求示例：
无

#### 响应示例：
```json
{
    "success": true,
    "message": "获取到逐月的分组和统计成功",
    "data": {
        "2023-05": 5,
        "2023-06": 10,
        "2023-07": 15
    }
}
```

#### 状态码说明：
| 状态码 | 说明           |
| ------ | -------------- |
| 200    | 请求成功       |
| 500    | 服务器内部错误 |

---

### 6. 接口说明：获取其他统计信息
#### 接口名称：其他统计信息
#### 请求 URL：`/messageGriddler/elseData`
#### 请求方法：GET

#### 请求参数：无

#### 请求示例：
无

#### 响应示例：
```json
{
    "success": true,
    "message": "获取其他统计信息成功",
    "data": {
        "空气质量检测总数量": 100,
        "空气质量检测良好数量": 50,
        "省会城市网格覆盖范围": 0.5,
        "大城市覆盖范围": 0.7
    },
    "result": {
        "provinces": "50.00",
        "cities": "70.00"
    }
}
```

#### 状态码说明：
| 状态码 | 说明           |
| ------ | -------------- |
| 200    | 请求成功       |
| 500    | 服务器内部错误 |

---

### 7. 接口说明：获取处理进度信息
#### 接口名称：获取处理进度信息
#### 请求 URL：`/messageGriddler/getProcession`
#### 请求方法：GET

#### 请求参数：无

#### 请求示例：
无

#### 响应示例：
```json
{
    "success": true,
    "message": "get procession",
    "data": {
        "report": 100,
        "task": 80,
        "submission": 60
    }
}
```

#### 状态码说明：
| 状态码 | 说明           |
| ------ | -------------- |
| 200    | 请求成功       |
| 500    | 服务器内部错误 |

---

### 8. 接口说明：大屏展示网格员数据
#### 接口名称：大屏展示网格员数据
#### 请求 URL：`/messageGriddler/digitalScreenShowMessageGriddler`
#### 请求方法：GET

#### 请求参数：
| 参数名   | 类型    | 是否必填 | 说明               |
| -------- | ------- | -------- | ------------------ |
| limitNum | Integer | 是       | 限制返回的数据条数 |

#### 请求示例：
```
?limitNum=10
```

#### 响应示例：
```json
{
    "success": true,
    "message": "query",
    "data": [
        {
            "griddlerId": "grid123",
            "aqiLevel": 3,
            "time": "2024-06-16 11:29:00",
            "provinceName": "某省",
            "cityName": "某市",
            "address": "某地址"
        },
        ...
    ]
}
```

#### 状态码说明：
| 状态码 | 说明               |
| ------ | ------------------ |
| 200    | 请求成功           |
| 400    | 请求失败，参数错误 |
| 500    | 服务器内部错误     |

---

### 9. 接口说明：中国地图 AQI 分布
#### 接口名称：中国地图 AQI 分布
#### 请求 URL：`/messageGriddler/AQIRegionalDistributionChina`
#### 请求方法：GET

#### 请求参数：
| 参数名   | 类型   | 是否必填 | 说明                              |
| -------- | ------ | -------- | --------------------------------- |
| province | String | 是       | 省份名称，传 `china` 获取全国数据 |

#### 请求示例：
```
?province=china
```

#### 响应示例：
```json
{
    "success": true,
    "message": "get weekly air data",
    "data": [
        {
            "name": "某省",
            "value": 5
        },
        ...
    ]
}
```

#### 状态码说明：
| 状态码 | 说明           |
| ------ | -------------- |
| 200    | 请求成功       |
| 500    | 服务器内部错误 |

---

### 10. 接口说明：查看网格

员所有待做任务
#### 接口名称：查看所有待做任务列表
#### 请求 URL：`/messageGriddler/viewAllMessageGriddlerUndo`
#### 请求方法：GET

#### 请求参数：
| 参数名     | 类型   | 是否必填 | 说明      |
| ---------- | ------ | -------- | --------- |
| griddlerId | String | 是       | 网格员 ID |

#### 请求示例：
```
?griddlerId=grid123
```

#### 响应示例：
```json
{
    "success": true,
    "message": "查看所有的网格员端的待做实测数据成功",
    "data": [
        {
            "messagePublicId": "abc123",
            "griddlerId": "grid123",
            "so2": 200,
            "co": 10,
            "pm": 100,
            "time": "2024-06-16 11:29:00",
            "status": 0,
            "aqiLevel": 3
        },
        ...
    ]
}
```

#### 状态码说明：
| 状态码 | 说明           |
| ------ | -------------- |
| 200    | 请求成功       |
| 500    | 服务器内部错误 |

---

### 11. 接口说明：统计空气质量最好的城市
#### 接口名称：统计空气质量最好的城市
#### 请求 URL：`/messageGriddler/selectTopCity`
#### 请求方法：GET

#### 请求参数：
| 参数名   | 类型    | 是否必填 | 说明               |
| -------- | ------- | -------- | ------------------ |
| limitNum | Integer | 是       | 限制返回的数据条数 |

#### 请求示例：
```
?limitNum=10
```

#### 响应示例：
```json
{
    "success": true,
    "message": "统计空气质量最好的城市成功",
    "data": [
        {
            "cityName": "某市",
            "goodDays": 300
        },
        ...
    ]
}
```

#### 状态码说明：
| 状态码 | 说明           |
| ------ | -------------- |
| 200    | 请求成功       |
| 500    | 服务器内部错误 |

### MessageManagerController接口说明文档

### 1. 接口说明：管理员分配任务给网格员
#### 接口名称：管理员分配任务
#### 请求 URL：`/messageManager/creatAssignedMessageManager`
#### 请求方法：POST

#### 请求参数：
| 参数名      | 类型    | 是否必填 | 说明      |
| ----------- | ------- | -------- | --------- |
| id          | String  | 否       | 任务 ID   |
| griddlerId  | String  | 是       | 网格员 ID |
| messageId   | String  | 是       | 消息 ID   |
| status      | Integer | 是       | 状态      |
| createdTime | Date    | 否       | 创建时间  |
| updatedTime | Date    | 否       | 更新时间  |

#### 请求示例：
```json
{
    "griddlerId": "grid123",
    "messageId": "msg123",
    "status": 0
}
```

#### 响应示例：
```json
{
    "success": true,
    "message": "公众管理员端的提交添加成功",
    "data": 1
}
```

#### 状态码说明：
| 状态码 | 说明               |
| ------ | ------------------ |
| 200    | 请求成功           |
| 400    | 请求失败，参数错误 |
| 500    | 服务器内部错误     |

---

### 2. 接口说明：查看所有已分配任务
#### 接口名称：查看所有已分配任务
#### 请求 URL：`/messageManager/viewAllAssignedMessageManager`
#### 请求方法：GET

#### 请求参数：
| 参数名 | 类型    | 是否必填 | 说明 |
| ------ | ------- | -------- | ---- |
| status | Integer | 是       | 状态 |

#### 请求示例：
```
?status=0
```

#### 响应示例：
```json
{
    "success": true,
    "message": "查看所有的公众管理员端的提交成功",
    "data": [
        {
            "id": "uuid123",
            "griddlerId": "grid123",
            "messageId": "msg123",
            "status": 0,
            "createdTime": "2024-06-16 11:29:00",
            "updatedTime": "2024-06-16 11:29:00"
        },
        ...
    ]
}
```

#### 状态码说明：
| 状态码 | 说明               |
| ------ | ------------------ |
| 200    | 请求成功           |
| 400    | 请求失败，参数错误 |
| 500    | 服务器内部错误     |

---

### 3. 接口说明：查看某网格员的待做任务
#### 接口名称：查看某网格员的待做任务
#### 请求 URL：`/messageManager/viewOneGriddlerAssigned/{griddlerId}`
#### 请求方法：GET

#### 请求参数：
| 参数名     | 类型   | 是否必填 | 说明      |
| ---------- | ------ | -------- | --------- |
| griddlerId | String | 是       | 网格员 ID |

#### 请求示例：
```
/messageManager/viewOneGriddlerAssigned/grid123
```

#### 响应示例：
```json
{
    "success": true,
    "message": "查看待做任务成功",
    "data": [
        {
            "provinceName": "某省",
            "cityName": "某市",
            "level": 3,
            "address": "某地址",
            "date": "2024-06-16 11:29:00"
        },
        ...
    ]
}
```

#### 状态码说明：
| 状态码 | 说明               |
| ------ | ------------------ |
| 200    | 请求成功           |
| 400    | 请求失败，参数错误 |
| 500    | 服务器内部错误     |

### MessagePublicController接口说明文档

### 接口说明文档

### 1. 接口说明：公众监督员端的提交
#### 接口名称：提交反馈信息
#### 请求 URL：`/messagePublic/submitMessagePublic`
#### 请求方法：POST

#### 请求参数：
| 参数名      | 类型    | 是否必填 | 说明          |
| ----------- | ------- | -------- | ------------- |
| id          | String  | 否       | 消息 ID       |
| publicId    | String  | 是       | 公众监督员 ID |
| provinceId  | String  | 是       | 省份 ID       |
| cityId      | String  | 是       | 城市 ID       |
| level       | Integer | 是       | 等级          |
| address     | String  | 是       | 地址          |
| date        | Date    | 是       | 日期          |
| description | String  | 否       | 描述          |
| status      | Integer | 是       | 状态          |
| createdTime | Date    | 否       | 创建时间      |
| updatedTime | Date    | 否       | 更新时间      |

#### 请求示例：
```json
{
    "publicId": "pub123",
    "provinceId": "prov123",
    "cityId": "city123",
    "level": 3,
    "address": "某地址",
    "date": "2024-06-16T11:29:00",
    "description": "描述信息",
    "status": 0
}
```

#### 响应示例：
```json
{
    "success": true,
    "message": "公众监督员端的提交添加成功",
    "data": 1
}
```

#### 状态码说明：
| 状态码 | 说明               |
| ------ | ------------------ |
| 200    | 请求成功           |
| 400    | 请求失败，参数错误 |
| 500    | 服务器内部错误     |

---

### 2. 接口说明：查找特定 ID 的本人提交的反馈信息
#### 接口名称：查看本人提交的反馈信息
#### 请求 URL：`/messagePublic/ViewMyMessagePublic/{publicId}`
#### 请求方法：GET

#### 请求参数：
| 参数名   | 类型   | 是否必填 | 说明          |
| -------- | ------ | -------- | ------------- |
| publicId | String | 是       | 公众监督员 ID |

#### 请求示例：
```
/messagePublic/ViewMyMessagePublic/pub123
```

#### 响应示例：
```json
{
    "success": true,
    "message": "查询当前公众监督员的提交记录成功",
    "data": [
        {
            "public": {
                "id": "pub123",
                "name": "公众监督员名称"
            },
            "messagePublic": {
                "id": "msg123",
                "provinceId": "prov123",
                "cityId": "city123",
                "level": 3,
                "address": "某地址",
                "date": "2024-06-16T11:29:00",
                "description": "描述信息",
                "status": 0
            },
            "provinceName": "某省",
            "cityName": "某市",
            "shortTitle": "简短标题"
        },
        ...
    ]
}
```

#### 状态码说明：
| 状态码 | 说明               |
| ------ | ------------------ |
| 200    | 请求成功           |
| 400    | 请求失败，参数错误 |
| 500    | 服务器内部错误     |

---

### 3. 接口说明：查看所有用户的反馈信息
#### 接口名称：查看所有反馈信息
#### 请求 URL：`/messagePublic/viewAllMessagePublic`
#### 请求方法：POST

#### 请求示例：
无

#### 响应示例：
```json
{
    "success": true,
    "message": "查询所有的公众监督员的提交记录成功",
    "data": [
        {
            "public": {
                "id": "pub123",
                "name": "公众监督员名称"
            },
            "messagePublic": {
                "id": "msg123",
                "provinceId": "prov123",
                "cityId": "city123",
                "level": 3,
                "address": "某地址",
                "date": "2024-06-16T11:29:00",
                "description": "描述信息",
                "status": 0
            },
            "provinceName": "某省",
            "cityName": "某市",
            "shortTitle": "简短标题"
        },
        ...
    ]
}
```

#### 状态码说明：
| 状态码 | 说明               |
| ------ | ------------------ |
| 200    | 请求成功           |
| 400    | 请求失败，参数错误 |
| 500    | 服务器内部错误     |

---

### 4. 接口说明：根据特定条件查询反馈信息
#### 接口名称：根据条件查询反馈信息
#### 请求 URL：`/messagePublic/viewSomeMessagePublic`
#### 请求方法：GET

#### 请求参数：
| 参数名     | 类型    | 是否必填 | 说明    |
| ---------- | ------- | -------- | ------- |
| provinceId | String  | 是       | 省份 ID |
| cityId     | String  | 是       | 城市 ID |
| level      | Integer | 是       | 等级    |
| date       | Date    | 是       | 日期    |
| status     | Integer | 是       | 状态    |

#### 请求示例：
```
?provinceId=prov123&cityId=city123&level=3&date=2024-06-16&status=0
```

#### 响应示例：
```json
{
    "success": true,
    "message": "用特定条件查询特定的公众监督员的提交记录成功",
    "data": [
        {
            "public": {
                "id": "pub123",
                "name": "公众监督员名称"
            },
            "messagePublic": {
                "id": "msg123",
                "provinceId": "prov123",
                "cityId": "city123",
                "level": 3,
                "address": "某地址",
                "date": "2024-06-16T11:29:00",
                "description": "描述信息",
                "status": 0
            },
            "provinceName": "某省",
            "cityName": "某市",
            "shortTitle": "简短标题"
        },
        ...
    ]
}
```

#### 状态码说明：
| 状态码 | 说明               |
| ------ | ------------------ |
| 200    | 请求成功           |
| 400    | 请求失败，参数错误 |
| 500    | 服务器内部错误     |

---

### 5. 接口说明：查询特定消息 ID 的反馈信息
#### 接口名称：查询特定消息 ID 的反馈信息
#### 请求 URL：`/messagePublic/selectMessagePublic`
#### 请求方法：POST

#### 请求参数：
| 参数名    | 类型   | 是否必填 | 说明    |
| --------- | ------ | -------- | ------- |
| messageId | String | 是       | 消息 ID |

#### 请求示例：
```json
{
    "messageId": "msg123"
}
```

#### 响应示例：
```json
{
    "success": true,
    "message": "某个 id 的公众监督员记录",
    "data": {
        "id": "msg123",
        "publicId": "pub123",
        "provinceId": "prov123",
        "cityId": "city123",
        "level": 3,
        "address": "某地址",
        "date": "2024-06-16T11:29:00",
        "description": "描述信息",
        "status": 0
    }
}
```

#### 状态码说明：
| 状态码 | 说明               |
| ------ | ------------------ |
| 200    | 请求成功           |
| 400    | 请求失败，参数错误 |
| 500    | 服务器内部错误     |

---

### 6. 接口说明：统计反馈信息来自的地区
#### 接口名称：统计反馈信息来自的地区
#### 请求 URL：`/messagePublic/findAllAddressByCityId`
#### 请求方法：GET

#### 响应示例：
```json
[
    "city123",
    "city456",
    ...
]
```

#### 状态码说明：
| 状态码 | 说明               |
| ------ | ------------------ |
| 200    | 请求成功           |
| 400    | 请求失败，参数错误 |
| 500    | 服务器内部错误     |

---

### 7. 接口说明：全部的信息记录条数
#### 接口名称：统计所有记录条数
#### 请求 URL：`/messagePublic/countMessagePublic`
#### 请求方法：GET

#### 响应示例：
```json
{
    "count": 100
}
```

#### 状态码说明：
| 状态码 | 说明               |
| ------ | ------------------ |
| 200    | 请求成功           |
| 400    | 请求失败，参数错误 |
| 500    | 服务器内部错误     |

---

### 8. 接口说明：未指派的信息记录条数
接口名称：统计未指派记录条数
#### 请求 URL：`/messagePublic/countUnGriddler`
#### 请求方法：GET

#### 响应示例：
```json
{
    "count": 50
}
```

#### 状态码说明：
| 状态码 | 说明               |
| ------ | ------------------ |
| 200    | 请求成功           |
| 400    | 请求失败，参数错误 |
| 500    | 服务器内部错误     |

---

### 9. 接口说明：等待指派的信息记录条数
#### 接口名称：统计等待指派记录条数
#### 请求 URL：`/messagePublic/countAlreadyAssigned`
#### 请求方法：GET

#### 响应示例：
```json
{
    "count": 30
}
```

#### 状态码说明：
| 状态码 | 说明               |
| ------ | ------------------ |
| 200    | 请求成功           |
| 400    | 请求失败，参数错误 |
| 500    | 服务器内部错误     |

---

### 10. 接口说明：选择数字屏的反馈信息
#### 接口名称：选择数字屏反馈信息
#### 请求 URL：`/messagePublic/selectMessagePublicDigitalScreen`
#### 请求方法：GET

#### 请求参数：
| 参数名   | 类型    | 是否必填 | 说明       |
| -------- | ------- | -------- | ---------- |
| limitNum | Integer | 是       | 返回的条数 |

#### 请求示例：
```
/messagePublic/selectMessagePublicDigitalScreen?limitNum=5
```

#### 响应示例：
```json
{
    "status": 200,
    "message": "query ",
    "data": [
        {
            "provinceName": "某省",
            "cityName": "某市",
            "address": "某地址",
            "date": "2024-06-16T11:29:00",
            "publicId": "pub123",
            "aqiLevel": 3,
            "description": "描述信息"
        },
        ...
    ]
}
```

#### 状态码说明：
| 状态码 | 说明               |
| ------ | ------------------ |
| 200    | 请求成功           |
| 400    | 请求失败，参数错误 |
| 500    | 服务器内部错误     |

### Public接口说明文档

#### 1. 公众监督员登录

**接口地址**: `/public/findPublic`  
**请求方式**: POST  
**请求参数**:

| 参数名   | 类型   | 是否必填 | 说明             |
| -------- | ------ | -------- | ---------------- |
| username | String | 是       | 用户名（手机号） |
| password | String | 是       | 密码             |

**请求示例**:
```json
{
    "username": "12345678901",
    "password": "password123"
}
```

**响应示例**:
```json
{
    "success": true,
    "message": "登录者的身份是管理员",
    "data": {
        "id": "1",
        "name": "公众监督员姓名",
        "phone": "12345678901",
        // 其他公众监督员信息
    }
}
```

**失败响应示例**:
```json
{
    "success": false,
    "message": "登录者的身份不是公众监督员，或者如果他是公众监督员的话账户或者密码错误了",
    "data": null
}
```

#### 2. 注册公众监督员

**接口地址**: `/public/addPublic`  
**请求方式**: POST  
**请求参数**:

| 参数名   | 类型   | 是否必填 | 说明                           |
| -------- | ------ | -------- | ------------------------------ |
| id       | String | 否       | 公众监督员唯一标识（自动生成） |
| name     | String | 是       | 公众监督员姓名                 |
| phone    | String | 是       | 手机号                         |
| password | String | 是       | 密码                           |

**请求示例**:
```json
{
    "name": "公众监督员姓名",
    "phone": "12345678901",
    "password": "password123"
}
```

**响应示例**:
```json
{
    "success": true,
    "message": "注册公众监督员成功"
}
```

**失败响应示例**:
```json
{
    "success": false,
    "message": "注册公众监督员失败"
}
```

#### 3. 获取公众监督员详细信息

**接口地址**: `/public/publicDetail`  
**请求方式**: GET  
**请求参数**:

| 参数名   | 类型   | 是否必填 | 说明               |
| -------- | ------ | -------- | ------------------ |
| publicId | String | 是       | 公众监督员唯一标识 |

**请求示例**:
```json
{
    "publicId": "1"
}
```

**响应示例**:
```json
{
    "id": "1",
    "name": "公众监督员姓名",
    "phone": "12345678901",
    // 其他公众监督员信息
}
```
