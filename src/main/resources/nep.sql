/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 80033
Source Host           : localhost:3306
Source Database       : nep

Target Server Type    : MYSQL
Target Server Version : 80033
File Encoding         : 65001

Date: 2024-07-01 19:02:09
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for aqi
-- ----------------------------
DROP TABLE IF EXISTS `aqi`;
CREATE TABLE `aqi` (
  `aqi_id` int NOT NULL AUTO_INCREMENT COMMENT '自增序列，一共六级',
  `description` varchar(10) DEFAULT NULL COMMENT '空气质量级别的汉字描述',
  `aqi_description` varchar(20) DEFAULT NULL,
  `color` varchar(7) DEFAULT NULL COMMENT '空气质量对应的颜色',
  `health_impact` varchar(500) DEFAULT NULL COMMENT '对健康的影响情况描述',
  `measures` varchar(500) DEFAULT NULL COMMENT '建议采取的措施',
  `so2_min` int DEFAULT NULL,
  `so2_max` int DEFAULT NULL,
  `co_min` int DEFAULT NULL,
  `co_max` int DEFAULT NULL,
  `pm_min` int DEFAULT NULL,
  `pm_max` int DEFAULT NULL,
  `remarks` int DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`aqi_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of aqi
-- ----------------------------
INSERT INTO `aqi` VALUES ('1', '优', '优', '#00E400', '空气质量令人满意，基本无空气污染，对健康无影响。', '继续保持良好的生活习惯。', '0', '35', '0', '5', '0', '35', null);
INSERT INTO `aqi` VALUES ('2', '良', '良', '#FFFF00', '空气质量可以接受，但某些污染物可能对极少数异常敏感人群健康有较轻微影响。', '极少数异常敏感人群应减少户外活动。', '36', '75', '6', '10', '36', '75', null);
INSERT INTO `aqi` VALUES ('3', '轻度污染', '轻度污染', '#FF7E00', '易感人群症状有轻度加剧，健康人群基本无影响。', '儿童、老年人及心脏病、呼吸系统疾病患者减少长时间、高强度户外锻炼。', '76', '185', '11', '35', '76', '115', null);
INSERT INTO `aqi` VALUES ('4', '中度污染', '中度污染', '#FF0000', '进一步加剧易感人群症状，可能对健康人群心脏、呼吸系统有影响。', '儿童、老年人及心脏病、呼吸系统疾病患者避免长时间、高强度户外锻炼，一般人群适量减少户外活动。', '186', '304', '36', '60', '116', '150', null);
INSERT INTO `aqi` VALUES ('5', '重度污染', '重度污染', '#99004C', '健康人群中有明显强烈症状，同时对其他人群健康影响较大。', '儿童、老年人及心脏、呼吸系统疾病患者避免户外活动，一般人群减少户外活动。', '305', '604', '61', '90', '151', '250', null);
INSERT INTO `aqi` VALUES ('6', '严重污染', '严重污染', '#7E0023', '健康影响极大，可能对整个人群的健康造成严重危害。', '所有人群应避免户外活动，建议打开空气净化器，确保室内空气质量。', '605', '1000', '91', '120', '251', '500', null);

-- ----------------------------
-- Table structure for cities
-- ----------------------------
DROP TABLE IF EXISTS `cities`;
CREATE TABLE `cities` (
  `id` int NOT NULL AUTO_INCREMENT,
  `city_id` varchar(10) NOT NULL,
  `city_name` varchar(50) NOT NULL,
  `province_id` varchar(10) NOT NULL,
  `is_capital_city` int DEFAULT '0' COMMENT '是否是省会城市\n0：否\n1：是',
  `is_big_city` int DEFAULT '0' COMMENT '是否是大城市\n是：1，否：0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=392 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of cities
-- ----------------------------
INSERT INTO `cities` VALUES ('1', '1-1', '北京市', '1', '1', '1');
INSERT INTO `cities` VALUES ('2', '2-1', '天津市', '2', '1', '1');
INSERT INTO `cities` VALUES ('3', '3-1', '石家庄市', '3', '1', '1');
INSERT INTO `cities` VALUES ('4', '3-2', '唐山市', '3', '0', '0');
INSERT INTO `cities` VALUES ('5', '3-3', '秦皇岛市', '3', '0', '0');
INSERT INTO `cities` VALUES ('6', '3-4', '邯郸市', '3', '0', '0');
INSERT INTO `cities` VALUES ('7', '3-5', '邢台市', '3', '0', '0');
INSERT INTO `cities` VALUES ('8', '3-6', '保定市', '3', '0', '0');
INSERT INTO `cities` VALUES ('9', '3-7', '张家口市', '3', '0', '0');
INSERT INTO `cities` VALUES ('10', '3-8', '承德市', '3', '0', '0');
INSERT INTO `cities` VALUES ('11', '3-9', '沧州市', '3', '0', '0');
INSERT INTO `cities` VALUES ('12', '3-10', '廊坊市', '3', '0', '0');
INSERT INTO `cities` VALUES ('13', '3-11', '衡水市', '3', '0', '0');
INSERT INTO `cities` VALUES ('14', '4-1', '太原市', '4', '1', '1');
INSERT INTO `cities` VALUES ('15', '4-2', '大同市', '4', '0', '0');
INSERT INTO `cities` VALUES ('16', '4-3', '阳泉市', '4', '0', '0');
INSERT INTO `cities` VALUES ('17', '4-4', '长治市', '4', '0', '0');
INSERT INTO `cities` VALUES ('18', '4-5', '晋城市', '4', '0', '0');
INSERT INTO `cities` VALUES ('19', '4-6', '朔州市', '4', '0', '0');
INSERT INTO `cities` VALUES ('20', '4-7', '晋中市', '4', '0', '0');
INSERT INTO `cities` VALUES ('21', '4-8', '运城市', '4', '0', '0');
INSERT INTO `cities` VALUES ('22', '4-9', '忻州市', '4', '0', '0');
INSERT INTO `cities` VALUES ('23', '4-10', '临汾市', '4', '0', '0');
INSERT INTO `cities` VALUES ('24', '4-11', '吕梁市', '4', '0', '0');
INSERT INTO `cities` VALUES ('25', '5-1', '呼和浩特市', '5', '1', '0');
INSERT INTO `cities` VALUES ('26', '5-2', '包头市', '5', '0', '0');
INSERT INTO `cities` VALUES ('27', '5-3', '乌海市', '5', '0', '0');
INSERT INTO `cities` VALUES ('28', '5-4', '赤峰市', '5', '0', '0');
INSERT INTO `cities` VALUES ('29', '5-5', '通辽市', '5', '0', '0');
INSERT INTO `cities` VALUES ('30', '5-6', '鄂尔多斯市', '5', '0', '0');
INSERT INTO `cities` VALUES ('31', '5-7', '呼伦贝尔市', '5', '0', '0');
INSERT INTO `cities` VALUES ('32', '5-8', '巴彦淖尔市', '5', '0', '0');
INSERT INTO `cities` VALUES ('33', '5-9', '乌兰察布市', '5', '0', '0');
INSERT INTO `cities` VALUES ('34', '5-10', '兴安盟', '5', '0', '0');
INSERT INTO `cities` VALUES ('35', '5-11', '锡林郭勒盟', '5', '0', '0');
INSERT INTO `cities` VALUES ('36', '5-12', '阿拉善盟', '5', '0', '0');
INSERT INTO `cities` VALUES ('37', '6-1', '沈阳市', '6', '1', '1');
INSERT INTO `cities` VALUES ('38', '6-2', '大连市', '6', '0', '1');
INSERT INTO `cities` VALUES ('39', '6-3', '鞍山市', '6', '0', '0');
INSERT INTO `cities` VALUES ('40', '6-4', '抚顺市', '6', '0', '0');
INSERT INTO `cities` VALUES ('41', '6-5', '本溪市', '6', '0', '0');
INSERT INTO `cities` VALUES ('42', '6-6', '丹东市', '6', '0', '0');
INSERT INTO `cities` VALUES ('43', '6-7', '锦州市', '6', '0', '0');
INSERT INTO `cities` VALUES ('44', '6-8', '营口市', '6', '0', '0');
INSERT INTO `cities` VALUES ('45', '6-9', '阜新市', '6', '0', '0');
INSERT INTO `cities` VALUES ('46', '6-10', '辽阳市', '6', '0', '0');
INSERT INTO `cities` VALUES ('47', '6-11', '盘锦市', '6', '0', '0');
INSERT INTO `cities` VALUES ('48', '6-12', '铁岭市', '6', '0', '0');
INSERT INTO `cities` VALUES ('49', '6-13', '朝阳市', '6', '0', '0');
INSERT INTO `cities` VALUES ('50', '6-14', '葫芦岛市', '6', '0', '0');
INSERT INTO `cities` VALUES ('51', '7-1', '长春市', '7', '1', '1');
INSERT INTO `cities` VALUES ('52', '7-2', '吉林市', '7', '0', '0');
INSERT INTO `cities` VALUES ('53', '7-3', '四平市', '7', '0', '0');
INSERT INTO `cities` VALUES ('54', '7-4', '辽源市', '7', '0', '0');
INSERT INTO `cities` VALUES ('55', '7-5', '通化市', '7', '0', '0');
INSERT INTO `cities` VALUES ('56', '7-6', '白山市', '7', '0', '0');
INSERT INTO `cities` VALUES ('57', '7-7', '松原市', '7', '0', '0');
INSERT INTO `cities` VALUES ('58', '7-8', '白城市', '7', '0', '0');
INSERT INTO `cities` VALUES ('59', '7-9', '延边朝鲜族自治州', '7', '0', '0');
INSERT INTO `cities` VALUES ('60', '8-1', '哈尔滨市', '8', '1', '1');
INSERT INTO `cities` VALUES ('61', '8-2', '齐齐哈尔市', '8', '0', '0');
INSERT INTO `cities` VALUES ('62', '8-3', '鸡西市', '8', '0', '0');
INSERT INTO `cities` VALUES ('63', '8-4', '鹤岗市', '8', '0', '0');
INSERT INTO `cities` VALUES ('64', '8-5', '双鸭山市', '8', '0', '0');
INSERT INTO `cities` VALUES ('65', '8-6', '大庆市', '8', '0', '0');
INSERT INTO `cities` VALUES ('66', '8-7', '伊春市', '8', '0', '0');
INSERT INTO `cities` VALUES ('67', '8-8', '佳木斯市', '8', '0', '0');
INSERT INTO `cities` VALUES ('68', '8-9', '七台河市', '8', '0', '0');
INSERT INTO `cities` VALUES ('69', '8-10', '牡丹江市', '8', '0', '0');
INSERT INTO `cities` VALUES ('70', '8-11', '黑河市', '8', '0', '0');
INSERT INTO `cities` VALUES ('71', '8-12', '绥化市', '8', '0', '0');
INSERT INTO `cities` VALUES ('72', '8-13', '大兴安岭地区', '8', '0', '0');
INSERT INTO `cities` VALUES ('73', '9-1', '上海市', '9', '1', '1');
INSERT INTO `cities` VALUES ('74', '10-1', '南京市', '10', '1', '1');
INSERT INTO `cities` VALUES ('75', '10-2', '无锡市', '10', '0', '1');
INSERT INTO `cities` VALUES ('76', '10-3', '徐州市', '10', '0', '0');
INSERT INTO `cities` VALUES ('77', '10-4', '常州市', '10', '0', '0');
INSERT INTO `cities` VALUES ('78', '10-5', '苏州市', '10', '0', '0');
INSERT INTO `cities` VALUES ('79', '10-6', '南通市', '10', '0', '0');
INSERT INTO `cities` VALUES ('80', '10-7', '连云港市', '10', '0', '0');
INSERT INTO `cities` VALUES ('81', '10-8', '淮安市', '10', '0', '0');
INSERT INTO `cities` VALUES ('82', '10-9', '盐城市', '10', '0', '0');
INSERT INTO `cities` VALUES ('83', '10-10', '扬州市', '10', '0', '0');
INSERT INTO `cities` VALUES ('84', '10-11', '镇江市', '10', '0', '0');
INSERT INTO `cities` VALUES ('85', '10-12', '泰州市', '10', '0', '0');
INSERT INTO `cities` VALUES ('86', '10-13', '宿迁市', '10', '0', '0');
INSERT INTO `cities` VALUES ('87', '11-1', '杭州市', '11', '1', '1');
INSERT INTO `cities` VALUES ('88', '11-2', '宁波市', '11', '0', '1');
INSERT INTO `cities` VALUES ('89', '11-3', '温州市', '11', '0', '0');
INSERT INTO `cities` VALUES ('90', '11-4', '嘉兴市', '11', '0', '0');
INSERT INTO `cities` VALUES ('91', '11-5', '湖州市', '11', '0', '0');
INSERT INTO `cities` VALUES ('92', '11-6', '绍兴市', '11', '0', '0');
INSERT INTO `cities` VALUES ('93', '11-7', '金华市', '11', '0', '0');
INSERT INTO `cities` VALUES ('94', '11-8', '衢州市', '11', '0', '0');
INSERT INTO `cities` VALUES ('95', '11-9', '舟山市', '11', '0', '0');
INSERT INTO `cities` VALUES ('96', '11-10', '台州市', '11', '0', '0');
INSERT INTO `cities` VALUES ('97', '11-11', '丽水市', '11', '0', '0');
INSERT INTO `cities` VALUES ('98', '12-1', '合肥市', '12', '1', '1');
INSERT INTO `cities` VALUES ('99', '12-2', '芜湖市', '12', '0', '0');
INSERT INTO `cities` VALUES ('100', '12-3', '蚌埠市', '12', '0', '0');
INSERT INTO `cities` VALUES ('101', '12-4', '淮南市', '12', '0', '0');
INSERT INTO `cities` VALUES ('102', '12-5', '马鞍山市', '12', '0', '0');
INSERT INTO `cities` VALUES ('103', '12-6', '淮北市', '12', '0', '0');
INSERT INTO `cities` VALUES ('104', '12-7', '铜陵市', '12', '0', '0');
INSERT INTO `cities` VALUES ('105', '12-8', '安庆市', '12', '0', '0');
INSERT INTO `cities` VALUES ('106', '12-9', '黄山市', '12', '0', '0');
INSERT INTO `cities` VALUES ('107', '12-10', '滁州市', '12', '0', '0');
INSERT INTO `cities` VALUES ('108', '12-11', '阜阳市', '12', '0', '0');
INSERT INTO `cities` VALUES ('109', '12-12', '宿州市', '12', '0', '0');
INSERT INTO `cities` VALUES ('110', '12-13', '六安市', '12', '0', '0');
INSERT INTO `cities` VALUES ('111', '12-14', '亳州市', '12', '0', '0');
INSERT INTO `cities` VALUES ('112', '12-15', '池州市', '12', '0', '0');
INSERT INTO `cities` VALUES ('113', '12-16', '宣城市', '12', '0', '0');
INSERT INTO `cities` VALUES ('114', '13-1', '福州市', '13', '1', '1');
INSERT INTO `cities` VALUES ('115', '13-2', '厦门市', '13', '0', '1');
INSERT INTO `cities` VALUES ('116', '13-3', '莆田市', '13', '0', '0');
INSERT INTO `cities` VALUES ('117', '13-4', '三明市', '13', '0', '0');
INSERT INTO `cities` VALUES ('118', '13-5', '泉州市', '13', '0', '0');
INSERT INTO `cities` VALUES ('119', '13-6', '漳州市', '13', '0', '0');
INSERT INTO `cities` VALUES ('120', '13-7', '南平市', '13', '0', '0');
INSERT INTO `cities` VALUES ('121', '13-8', '龙岩市', '13', '0', '0');
INSERT INTO `cities` VALUES ('122', '13-9', '宁德市', '13', '0', '0');
INSERT INTO `cities` VALUES ('123', '14-1', '南昌市', '14', '1', '1');
INSERT INTO `cities` VALUES ('124', '14-2', '景德镇市', '14', '0', '0');
INSERT INTO `cities` VALUES ('125', '14-3', '萍乡市', '14', '0', '0');
INSERT INTO `cities` VALUES ('126', '14-4', '九江市', '14', '0', '0');
INSERT INTO `cities` VALUES ('127', '14-5', '新余市', '14', '0', '0');
INSERT INTO `cities` VALUES ('128', '14-6', '鹰潭市', '14', '0', '0');
INSERT INTO `cities` VALUES ('129', '14-7', '赣州市', '14', '0', '0');
INSERT INTO `cities` VALUES ('130', '14-8', '吉安市', '14', '0', '0');
INSERT INTO `cities` VALUES ('131', '14-9', '宜春市', '14', '0', '0');
INSERT INTO `cities` VALUES ('132', '14-10', '抚州市', '14', '0', '0');
INSERT INTO `cities` VALUES ('133', '14-11', '上饶市', '14', '0', '0');
INSERT INTO `cities` VALUES ('134', '15-1', '济南市', '15', '1', '1');
INSERT INTO `cities` VALUES ('135', '15-2', '青岛市', '15', '0', '1');
INSERT INTO `cities` VALUES ('136', '15-3', '淄博市', '15', '0', '0');
INSERT INTO `cities` VALUES ('137', '15-4', '枣庄市', '15', '0', '0');
INSERT INTO `cities` VALUES ('138', '15-5', '东营市', '15', '0', '0');
INSERT INTO `cities` VALUES ('139', '15-6', '烟台市', '15', '0', '0');
INSERT INTO `cities` VALUES ('140', '15-7', '潍坊市', '15', '0', '0');
INSERT INTO `cities` VALUES ('141', '15-8', '济宁市', '15', '0', '0');
INSERT INTO `cities` VALUES ('142', '15-9', '泰安市', '15', '0', '0');
INSERT INTO `cities` VALUES ('143', '15-10', '威海市', '15', '0', '0');
INSERT INTO `cities` VALUES ('144', '15-11', '日照市', '15', '0', '0');
INSERT INTO `cities` VALUES ('145', '15-12', '临沂市', '15', '0', '0');
INSERT INTO `cities` VALUES ('146', '15-13', '德州市', '15', '0', '0');
INSERT INTO `cities` VALUES ('147', '15-14', '聊城市', '15', '0', '0');
INSERT INTO `cities` VALUES ('148', '15-15', '滨州市', '15', '0', '0');
INSERT INTO `cities` VALUES ('149', '15-16', '菏泽市', '15', '0', '0');
INSERT INTO `cities` VALUES ('150', '16-1', '郑州市', '16', '1', '1');
INSERT INTO `cities` VALUES ('151', '16-2', '开封市', '16', '0', '0');
INSERT INTO `cities` VALUES ('152', '16-3', '洛阳市', '16', '0', '0');
INSERT INTO `cities` VALUES ('153', '16-4', '平顶山市', '16', '0', '0');
INSERT INTO `cities` VALUES ('154', '16-5', '安阳市', '16', '0', '0');
INSERT INTO `cities` VALUES ('155', '16-6', '鹤壁市', '16', '0', '0');
INSERT INTO `cities` VALUES ('156', '16-7', '新乡市', '16', '0', '0');
INSERT INTO `cities` VALUES ('157', '16-8', '焦作市', '16', '0', '0');
INSERT INTO `cities` VALUES ('158', '16-9', '濮阳市', '16', '0', '0');
INSERT INTO `cities` VALUES ('159', '16-10', '许昌市', '16', '0', '0');
INSERT INTO `cities` VALUES ('160', '16-11', '漯河市', '16', '0', '0');
INSERT INTO `cities` VALUES ('161', '16-12', '三门峡市', '16', '0', '0');
INSERT INTO `cities` VALUES ('162', '16-13', '南阳市', '16', '0', '0');
INSERT INTO `cities` VALUES ('163', '16-14', '商丘市', '16', '0', '0');
INSERT INTO `cities` VALUES ('164', '16-15', '信阳市', '16', '0', '0');
INSERT INTO `cities` VALUES ('165', '16-16', '周口市', '16', '0', '0');
INSERT INTO `cities` VALUES ('166', '16-17', '驻马店市', '16', '0', '0');
INSERT INTO `cities` VALUES ('167', '16-18', '济源市', '16', '0', '0');
INSERT INTO `cities` VALUES ('168', '17-1', '武汉市', '17', '1', '1');
INSERT INTO `cities` VALUES ('169', '17-2', '黄石市', '17', '0', '0');
INSERT INTO `cities` VALUES ('170', '17-3', '十堰市', '17', '0', '0');
INSERT INTO `cities` VALUES ('171', '17-4', '宜昌市', '17', '0', '0');
INSERT INTO `cities` VALUES ('172', '17-5', '襄阳市', '17', '0', '0');
INSERT INTO `cities` VALUES ('173', '17-6', '鄂州市', '17', '0', '0');
INSERT INTO `cities` VALUES ('174', '17-7', '荆门市', '17', '0', '0');
INSERT INTO `cities` VALUES ('175', '17-8', '孝感市', '17', '0', '0');
INSERT INTO `cities` VALUES ('176', '17-9', '荆州市', '17', '0', '0');
INSERT INTO `cities` VALUES ('177', '17-10', '黄冈市', '17', '0', '0');
INSERT INTO `cities` VALUES ('178', '17-11', '咸宁市', '17', '0', '0');
INSERT INTO `cities` VALUES ('179', '17-12', '随州市', '17', '0', '0');
INSERT INTO `cities` VALUES ('180', '17-13', '恩施土家族苗族自治州', '17', '0', '0');
INSERT INTO `cities` VALUES ('181', '17-14', '仙桃市', '17', '0', '0');
INSERT INTO `cities` VALUES ('182', '17-15', '潜江市', '17', '0', '0');
INSERT INTO `cities` VALUES ('183', '17-16', '天门市', '17', '0', '0');
INSERT INTO `cities` VALUES ('184', '17-17', '神农架林区', '17', '0', '0');
INSERT INTO `cities` VALUES ('185', '18-1', '长沙市', '18', '1', '1');
INSERT INTO `cities` VALUES ('186', '18-2', '株洲市', '18', '0', '0');
INSERT INTO `cities` VALUES ('187', '18-3', '湘潭市', '18', '0', '0');
INSERT INTO `cities` VALUES ('188', '18-4', '衡阳市', '18', '0', '0');
INSERT INTO `cities` VALUES ('189', '18-5', '邵阳市', '18', '0', '0');
INSERT INTO `cities` VALUES ('190', '18-6', '岳阳市', '18', '0', '0');
INSERT INTO `cities` VALUES ('191', '18-7', '常德市', '18', '0', '0');
INSERT INTO `cities` VALUES ('192', '18-8', '张家界市', '18', '0', '0');
INSERT INTO `cities` VALUES ('193', '18-9', '益阳市', '18', '0', '0');
INSERT INTO `cities` VALUES ('194', '18-10', '郴州市', '18', '0', '0');
INSERT INTO `cities` VALUES ('195', '18-11', '永州市', '18', '0', '0');
INSERT INTO `cities` VALUES ('196', '18-12', '怀化市', '18', '0', '0');
INSERT INTO `cities` VALUES ('197', '18-13', '娄底市', '18', '0', '0');
INSERT INTO `cities` VALUES ('198', '18-14', '湘西土家族苗族自治州', '18', '0', '0');
INSERT INTO `cities` VALUES ('199', '19-1', '广州市', '19', '1', '1');
INSERT INTO `cities` VALUES ('200', '19-2', '韶关市', '19', '0', '0');
INSERT INTO `cities` VALUES ('201', '19-3', '深圳市', '19', '0', '1');
INSERT INTO `cities` VALUES ('202', '19-4', '珠海市', '19', '0', '0');
INSERT INTO `cities` VALUES ('203', '19-5', '汕头市', '19', '0', '0');
INSERT INTO `cities` VALUES ('204', '19-6', '佛山市', '19', '0', '1');
INSERT INTO `cities` VALUES ('205', '19-7', '江门市', '19', '0', '0');
INSERT INTO `cities` VALUES ('206', '19-8', '湛江市', '19', '0', '0');
INSERT INTO `cities` VALUES ('207', '19-9', '茂名市', '19', '0', '0');
INSERT INTO `cities` VALUES ('208', '19-10', '肇庆市', '19', '0', '0');
INSERT INTO `cities` VALUES ('209', '19-11', '惠州市', '19', '0', '0');
INSERT INTO `cities` VALUES ('210', '19-12', '梅州市', '19', '0', '0');
INSERT INTO `cities` VALUES ('211', '19-13', '汕尾市', '19', '0', '0');
INSERT INTO `cities` VALUES ('212', '19-14', '河源市', '19', '0', '0');
INSERT INTO `cities` VALUES ('213', '19-15', '阳江市', '19', '0', '0');
INSERT INTO `cities` VALUES ('214', '19-16', '清远市', '19', '0', '0');
INSERT INTO `cities` VALUES ('215', '19-17', '东莞市', '19', '0', '1');
INSERT INTO `cities` VALUES ('216', '19-18', '中山市', '19', '0', '0');
INSERT INTO `cities` VALUES ('217', '19-19', '潮州市', '19', '0', '0');
INSERT INTO `cities` VALUES ('218', '19-20', '揭阳市', '19', '0', '0');
INSERT INTO `cities` VALUES ('219', '19-21', '云浮市', '19', '0', '0');
INSERT INTO `cities` VALUES ('220', '20-1', '南宁市', '20', '1', '1');
INSERT INTO `cities` VALUES ('221', '20-2', '柳州市', '20', '0', '0');
INSERT INTO `cities` VALUES ('222', '20-3', '桂林市', '20', '0', '0');
INSERT INTO `cities` VALUES ('223', '20-4', '梧州市', '20', '0', '0');
INSERT INTO `cities` VALUES ('224', '20-5', '北海市', '20', '0', '0');
INSERT INTO `cities` VALUES ('225', '20-6', '防城港市', '20', '0', '0');
INSERT INTO `cities` VALUES ('226', '20-7', '钦州市', '20', '0', '0');
INSERT INTO `cities` VALUES ('227', '20-8', '贵港市', '20', '0', '0');
INSERT INTO `cities` VALUES ('228', '20-9', '玉林市', '20', '0', '0');
INSERT INTO `cities` VALUES ('229', '20-10', '百色市', '20', '0', '0');
INSERT INTO `cities` VALUES ('230', '20-11', '贺州市', '20', '0', '0');
INSERT INTO `cities` VALUES ('231', '20-12', '河池市', '20', '0', '0');
INSERT INTO `cities` VALUES ('232', '20-13', '来宾市', '20', '0', '0');
INSERT INTO `cities` VALUES ('233', '20-14', '崇左市', '20', '0', '0');
INSERT INTO `cities` VALUES ('234', '21-1', '海口市', '21', '1', '0');
INSERT INTO `cities` VALUES ('235', '21-2', '三亚市', '21', '0', '0');
INSERT INTO `cities` VALUES ('236', '21-3', '三沙市', '21', '0', '0');
INSERT INTO `cities` VALUES ('237', '21-4', '儋州市', '21', '0', '0');
INSERT INTO `cities` VALUES ('238', '21-5', '五指山市', '21', '0', '0');
INSERT INTO `cities` VALUES ('239', '21-6', '琼海市', '21', '0', '0');
INSERT INTO `cities` VALUES ('240', '21-7', '文昌市', '21', '0', '0');
INSERT INTO `cities` VALUES ('241', '21-8', '万宁市', '21', '0', '0');
INSERT INTO `cities` VALUES ('242', '21-9', '东方市', '21', '0', '0');
INSERT INTO `cities` VALUES ('243', '21-10', '定安县', '21', '0', '0');
INSERT INTO `cities` VALUES ('244', '21-11', '屯昌县', '21', '0', '0');
INSERT INTO `cities` VALUES ('245', '21-12', '澄迈县', '21', '0', '0');
INSERT INTO `cities` VALUES ('246', '21-13', '临高县', '21', '0', '0');
INSERT INTO `cities` VALUES ('247', '21-14', '白沙黎族自治县', '21', '0', '0');
INSERT INTO `cities` VALUES ('248', '21-15', '昌江黎族自治县', '21', '0', '0');
INSERT INTO `cities` VALUES ('249', '21-16', '乐东黎族自治县', '21', '0', '0');
INSERT INTO `cities` VALUES ('250', '21-17', '陵水黎族自治县', '21', '0', '0');
INSERT INTO `cities` VALUES ('251', '21-18', '保亭黎族苗族自治县', '21', '0', '0');
INSERT INTO `cities` VALUES ('252', '21-19', '琼中黎族苗族自治县', '21', '0', '0');
INSERT INTO `cities` VALUES ('253', '22-1', '重庆市', '22', '1', '1');
INSERT INTO `cities` VALUES ('254', '23-1', '成都市', '23', '1', '1');
INSERT INTO `cities` VALUES ('255', '23-2', '自贡市', '23', '0', '0');
INSERT INTO `cities` VALUES ('256', '23-3', '攀枝花市', '23', '0', '0');
INSERT INTO `cities` VALUES ('257', '23-4', '泸州市', '23', '0', '0');
INSERT INTO `cities` VALUES ('258', '23-5', '德阳市', '23', '0', '0');
INSERT INTO `cities` VALUES ('259', '23-6', '绵阳市', '23', '0', '0');
INSERT INTO `cities` VALUES ('260', '23-7', '广元市', '23', '0', '0');
INSERT INTO `cities` VALUES ('261', '23-8', '遂宁市', '23', '0', '0');
INSERT INTO `cities` VALUES ('262', '23-9', '内江市', '23', '0', '0');
INSERT INTO `cities` VALUES ('263', '23-10', '乐山市', '23', '0', '0');
INSERT INTO `cities` VALUES ('264', '23-11', '南充市', '23', '0', '0');
INSERT INTO `cities` VALUES ('265', '23-12', '眉山市', '23', '0', '0');
INSERT INTO `cities` VALUES ('266', '23-13', '宜宾市', '23', '0', '0');
INSERT INTO `cities` VALUES ('267', '23-14', '广安市', '23', '0', '0');
INSERT INTO `cities` VALUES ('268', '23-15', '达州市', '23', '0', '0');
INSERT INTO `cities` VALUES ('269', '23-16', '雅安市', '23', '0', '0');
INSERT INTO `cities` VALUES ('270', '23-17', '巴中市', '23', '0', '0');
INSERT INTO `cities` VALUES ('271', '23-18', '资阳市', '23', '0', '0');
INSERT INTO `cities` VALUES ('272', '23-19', '阿坝藏族羌族自治州', '23', '0', '0');
INSERT INTO `cities` VALUES ('273', '23-20', '甘孜藏族自治州', '23', '0', '0');
INSERT INTO `cities` VALUES ('274', '23-21', '凉山彝族自治州', '23', '0', '0');
INSERT INTO `cities` VALUES ('275', '24-1', '贵阳市', '24', '1', '1');
INSERT INTO `cities` VALUES ('276', '24-2', '六盘水市', '24', '0', '0');
INSERT INTO `cities` VALUES ('277', '24-3', '遵义市', '24', '0', '0');
INSERT INTO `cities` VALUES ('278', '24-4', '安顺市', '24', '0', '0');
INSERT INTO `cities` VALUES ('279', '24-5', '毕节市', '24', '0', '0');
INSERT INTO `cities` VALUES ('280', '24-6', '铜仁市', '24', '0', '0');
INSERT INTO `cities` VALUES ('281', '24-7', '黔西南布依族苗族自治州', '24', '0', '0');
INSERT INTO `cities` VALUES ('282', '24-8', '黔东南苗族侗族自治州', '24', '0', '0');
INSERT INTO `cities` VALUES ('283', '24-9', '黔南布依族苗族自治州', '24', '0', '0');
INSERT INTO `cities` VALUES ('284', '25-1', '昆明市', '25', '1', '1');
INSERT INTO `cities` VALUES ('285', '25-2', '曲靖市', '25', '0', '0');
INSERT INTO `cities` VALUES ('286', '25-3', '玉溪市', '25', '0', '0');
INSERT INTO `cities` VALUES ('287', '25-4', '保山市', '25', '0', '0');
INSERT INTO `cities` VALUES ('288', '25-5', '昭通市', '25', '0', '0');
INSERT INTO `cities` VALUES ('289', '25-6', '丽江市', '25', '0', '0');
INSERT INTO `cities` VALUES ('290', '25-7', '普洱市', '25', '0', '0');
INSERT INTO `cities` VALUES ('291', '25-8', '临沧市', '25', '0', '0');
INSERT INTO `cities` VALUES ('292', '25-9', '楚雄彝族自治州', '25', '0', '0');
INSERT INTO `cities` VALUES ('293', '25-10', '红河哈尼族彝族自治州', '25', '0', '0');
INSERT INTO `cities` VALUES ('294', '25-11', '文山壮族苗族自治州', '25', '0', '0');
INSERT INTO `cities` VALUES ('295', '25-12', '西双版纳傣族自治州', '25', '0', '0');
INSERT INTO `cities` VALUES ('296', '25-13', '大理白族自治州', '25', '0', '0');
INSERT INTO `cities` VALUES ('297', '25-14', '德宏傣族景颇族自治州', '25', '0', '0');
INSERT INTO `cities` VALUES ('298', '25-15', '怒江傈僳族自治州', '25', '0', '0');
INSERT INTO `cities` VALUES ('299', '25-16', '迪庆藏族自治州', '25', '0', '0');
INSERT INTO `cities` VALUES ('300', '26-1', '拉萨市', '26', '1', '0');
INSERT INTO `cities` VALUES ('301', '26-2', '日喀则市', '26', '0', '0');
INSERT INTO `cities` VALUES ('302', '26-3', '昌都市', '26', '0', '0');
INSERT INTO `cities` VALUES ('303', '26-4', '林芝市', '26', '0', '0');
INSERT INTO `cities` VALUES ('304', '26-5', '山南市', '26', '0', '0');
INSERT INTO `cities` VALUES ('305', '26-6', '那曲市', '26', '0', '0');
INSERT INTO `cities` VALUES ('306', '26-7', '阿里地区', '26', '0', '0');
INSERT INTO `cities` VALUES ('307', '27-1', '西安市', '27', '1', '1');
INSERT INTO `cities` VALUES ('308', '27-2', '铜川市', '27', '0', '0');
INSERT INTO `cities` VALUES ('309', '27-3', '宝鸡市', '27', '0', '0');
INSERT INTO `cities` VALUES ('310', '27-4', '咸阳市', '27', '0', '0');
INSERT INTO `cities` VALUES ('311', '27-5', '渭南市', '27', '0', '0');
INSERT INTO `cities` VALUES ('312', '27-6', '延安市', '27', '0', '0');
INSERT INTO `cities` VALUES ('313', '27-7', '汉中市', '27', '0', '0');
INSERT INTO `cities` VALUES ('314', '27-8', '榆林市', '27', '0', '0');
INSERT INTO `cities` VALUES ('315', '27-9', '安康市', '27', '0', '0');
INSERT INTO `cities` VALUES ('316', '27-10', '商洛市', '27', '0', '0');
INSERT INTO `cities` VALUES ('317', '28-1', '兰州市', '28', '1', '0');
INSERT INTO `cities` VALUES ('318', '28-2', '嘉峪关市', '28', '0', '0');
INSERT INTO `cities` VALUES ('319', '28-3', '金昌市', '28', '0', '0');
INSERT INTO `cities` VALUES ('320', '28-4', '白银市', '28', '0', '0');
INSERT INTO `cities` VALUES ('321', '28-5', '天水市', '28', '0', '0');
INSERT INTO `cities` VALUES ('322', '28-6', '武威市', '28', '0', '0');
INSERT INTO `cities` VALUES ('323', '28-7', '张掖市', '28', '0', '0');
INSERT INTO `cities` VALUES ('324', '28-8', '平凉市', '28', '0', '0');
INSERT INTO `cities` VALUES ('325', '28-9', '酒泉市', '28', '0', '0');
INSERT INTO `cities` VALUES ('326', '28-10', '庆阳市', '28', '0', '0');
INSERT INTO `cities` VALUES ('327', '28-11', '定西市', '28', '0', '0');
INSERT INTO `cities` VALUES ('328', '28-12', '陇南市', '28', '0', '0');
INSERT INTO `cities` VALUES ('329', '28-13', '临夏回族自治州', '28', '0', '0');
INSERT INTO `cities` VALUES ('330', '28-14', '甘南藏族自治州', '28', '0', '0');
INSERT INTO `cities` VALUES ('331', '29-1', '西宁市', '29', '1', '0');
INSERT INTO `cities` VALUES ('332', '29-2', '海东市', '29', '0', '0');
INSERT INTO `cities` VALUES ('333', '29-3', '海北藏族自治州', '29', '0', '0');
INSERT INTO `cities` VALUES ('334', '29-4', '黄南藏族自治州', '29', '0', '0');
INSERT INTO `cities` VALUES ('335', '29-5', '海南藏族自治州', '29', '0', '0');
INSERT INTO `cities` VALUES ('336', '29-6', '果洛藏族自治州', '29', '0', '0');
INSERT INTO `cities` VALUES ('337', '29-7', '玉树藏族自治州', '29', '0', '0');
INSERT INTO `cities` VALUES ('338', '29-8', '海西蒙古族藏族自治州', '29', '0', '0');
INSERT INTO `cities` VALUES ('339', '30-1', '银川市', '30', '1', '0');
INSERT INTO `cities` VALUES ('340', '30-2', '石嘴山市', '30', '0', '0');
INSERT INTO `cities` VALUES ('341', '30-3', '吴忠市', '30', '0', '0');
INSERT INTO `cities` VALUES ('342', '30-4', '固原市', '30', '0', '0');
INSERT INTO `cities` VALUES ('343', '30-5', '中卫市', '30', '0', '0');
INSERT INTO `cities` VALUES ('344', '31-1', '乌鲁木齐市', '31', '1', '1');
INSERT INTO `cities` VALUES ('345', '31-2', '克拉玛依市', '31', '0', '0');
INSERT INTO `cities` VALUES ('346', '31-3', '吐鲁番市', '31', '0', '0');
INSERT INTO `cities` VALUES ('347', '31-4', '哈密市', '31', '0', '0');
INSERT INTO `cities` VALUES ('348', '31-5', '昌吉回族自治州', '31', '0', '0');
INSERT INTO `cities` VALUES ('349', '31-6', '博尔塔拉蒙古自治州', '31', '0', '0');
INSERT INTO `cities` VALUES ('350', '31-7', '巴音郭楞蒙古自治州', '31', '0', '0');
INSERT INTO `cities` VALUES ('351', '31-8', '阿克苏地区', '31', '0', '0');
INSERT INTO `cities` VALUES ('352', '31-9', '克孜勒苏柯尔克孜自治州', '31', '0', '0');
INSERT INTO `cities` VALUES ('353', '31-10', '喀什地区', '31', '0', '0');
INSERT INTO `cities` VALUES ('354', '31-11', '和田地区', '31', '0', '0');
INSERT INTO `cities` VALUES ('355', '31-12', '伊犁哈萨克自治州', '31', '0', '0');
INSERT INTO `cities` VALUES ('356', '31-13', '塔城地区', '31', '0', '0');
INSERT INTO `cities` VALUES ('357', '31-14', '阿勒泰地区', '31', '0', '0');
INSERT INTO `cities` VALUES ('358', '31-15', '石河子市', '31', '0', '0');
INSERT INTO `cities` VALUES ('359', '31-16', '阿拉尔市', '31', '0', '0');
INSERT INTO `cities` VALUES ('360', '31-17', '图木舒克市', '31', '0', '0');
INSERT INTO `cities` VALUES ('361', '31-18', '五家渠市', '31', '0', '0');
INSERT INTO `cities` VALUES ('362', '31-19', '北屯市', '31', '0', '0');
INSERT INTO `cities` VALUES ('363', '31-20', '铁门关市', '31', '0', '0');
INSERT INTO `cities` VALUES ('364', '31-21', '双河市', '31', '0', '0');
INSERT INTO `cities` VALUES ('365', '31-22', '可克达拉市', '31', '0', '0');
INSERT INTO `cities` VALUES ('366', '31-23', '昆玉市', '31', '0', '0');
INSERT INTO `cities` VALUES ('367', '32-1', '香港岛', '32', '1', '0');
INSERT INTO `cities` VALUES ('368', '32-2', '九龙', '32', '0', '0');
INSERT INTO `cities` VALUES ('369', '32-3', '新界', '32', '0', '0');
INSERT INTO `cities` VALUES ('370', '33-1', '澳门半岛', '33', '1', '0');
INSERT INTO `cities` VALUES ('371', '33-2', '离岛', '33', '0', '0');
INSERT INTO `cities` VALUES ('372', '34-1', '台北市', '34', '1', '0');
INSERT INTO `cities` VALUES ('373', '34-2', '高雄市', '34', '0', '0');
INSERT INTO `cities` VALUES ('374', '34-3', '台南市', '34', '0', '0');
INSERT INTO `cities` VALUES ('375', '34-4', '台中市', '34', '0', '0');
INSERT INTO `cities` VALUES ('376', '34-5', '基隆市', '34', '0', '0');
INSERT INTO `cities` VALUES ('377', '34-6', '新竹市', '34', '0', '0');
INSERT INTO `cities` VALUES ('378', '34-7', '嘉义市', '34', '0', '0');
INSERT INTO `cities` VALUES ('379', '34-8', '新北市', '34', '0', '0');
INSERT INTO `cities` VALUES ('380', '34-9', '宜兰县', '34', '0', '0');
INSERT INTO `cities` VALUES ('381', '34-10', '桃园市', '34', '0', '0');
INSERT INTO `cities` VALUES ('382', '34-11', '新竹县', '34', '0', '0');
INSERT INTO `cities` VALUES ('383', '34-12', '苗栗县', '34', '0', '0');
INSERT INTO `cities` VALUES ('384', '34-13', '彰化县', '34', '0', '0');
INSERT INTO `cities` VALUES ('385', '34-14', '南投县', '34', '0', '0');
INSERT INTO `cities` VALUES ('386', '34-15', '云林县', '34', '0', '0');
INSERT INTO `cities` VALUES ('387', '34-16', '嘉义县', '34', '0', '0');
INSERT INTO `cities` VALUES ('388', '34-17', '屏东县', '34', '0', '0');
INSERT INTO `cities` VALUES ('389', '34-18', '台东县', '34', '0', '0');
INSERT INTO `cities` VALUES ('390', '34-19', '花莲县', '34', '0', '0');
INSERT INTO `cities` VALUES ('391', '34-20', '澎湖县', '34', '0', '0');

-- ----------------------------
-- Table structure for griddler
-- ----------------------------
DROP TABLE IF EXISTS `griddler`;
CREATE TABLE `griddler` (
  `id` varchar(255) NOT NULL,
  `name` varchar(20) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL COMMENT '登录编码',
  `password` varchar(255) DEFAULT NULL COMMENT '登录密码',
  `province_id` int DEFAULT NULL COMMENT '所在省份编号',
  `city_id` int DEFAULT NULL COMMENT '所在的市编号',
  `phone` varchar(20) DEFAULT NULL COMMENT '联系电话',
  `statuses` int DEFAULT NULL COMMENT '网格员状态：\n0：可工作状态\n1：临时抽调\n2：休假\n3：其他',
  `remarks` varchar(200) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='空气质量检测网格员表';

-- ----------------------------
-- Records of griddler
-- ----------------------------
INSERT INTO `griddler` VALUES ('1', '张三', 'zhangsan001', 'e10adc3949ba59abbe56e057f20f883e', '1', '101', '1234567890', '0', '可工作状态');
INSERT INTO `griddler` VALUES ('10', '陈十二', 'chenershi010', 'mima890', '2', '204', '0123456789', '1', '临时调离');
INSERT INTO `griddler` VALUES ('2', '李四', 'lisi002', 'mima456', '2', '202', '2345678901', '1', '临时抽调');
INSERT INTO `griddler` VALUES ('3', '王五', 'wangwu003', 'mima789', '3', '303', '3456789012', '2', '休假中');
INSERT INTO `griddler` VALUES ('4', '赵六', 'zhaoliu004', 'mima012', '4', '404', '4567890123', '3', '其他原因');
INSERT INTO `griddler` VALUES ('5', '孙七', 'sunqi005', 'mima345', '1', '102', '5678901234', '0', '准备工作');
INSERT INTO `griddler` VALUES ('6', '周八', 'zhouba006', 'mima678', '2', '203', '6789012345', '1', '抽调任务');
INSERT INTO `griddler` VALUES ('7', '吴九', 'wujie007', 'mima901', '3', '304', '7890123456', '2', '正在休假');
INSERT INTO `griddler` VALUES ('8', '郑十', 'zhengshi008', 'mima234', '4', '405', '8901234567', '3', '不可用');
INSERT INTO `griddler` VALUES ('9', '冯十一', 'fengshi009', 'mima567', '1', '103', '9012345678', '0', '正在工作');

-- ----------------------------
-- Table structure for manager
-- ----------------------------
DROP TABLE IF EXISTS `manager`;
CREATE TABLE `manager` (
  `id` varchar(255) NOT NULL,
  `manager_code` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of manager
-- ----------------------------
INSERT INTO `manager` VALUES ('1', 'admin', 'e10adc3949ba59abbe56e057f20f883e');

-- ----------------------------
-- Table structure for message_griddler
-- ----------------------------
DROP TABLE IF EXISTS `message_griddler`;
CREATE TABLE `message_griddler` (
  `id` varchar(255) NOT NULL,
  `message_public_id` varchar(255) DEFAULT NULL,
  `so2` int DEFAULT NULL,
  `co` int DEFAULT NULL,
  `pm` int DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  `status` int DEFAULT NULL COMMENT '待确认：0，已确认：1',
  `aqi_level` int DEFAULT NULL,
  `griddler_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of message_griddler
-- ----------------------------
INSERT INTO `message_griddler` VALUES ('1', '1', '50', '100', '30', '2024-07-01 14:07:16', '1', '4', '1');
INSERT INTO `message_griddler` VALUES ('G001', '45421', '10', '5', '20', '2024-07-01 12:34:56', '1', '5', 'GR001');
INSERT INTO `message_griddler` VALUES ('G002', '6442', '15', '7', '25', '2024-07-20 14:56:23', '1', '6', 'GR002');
INSERT INTO `message_griddler` VALUES ('G003', '686843', '20', '10', '30', '2024-06-30 18:12:34', '1', '3', 'GR003');
INSERT INTO `message_griddler` VALUES ('G004', '46485', '25', '12', '35', '2024-06-27 18:12:34', '1', '2', 'GR004');
INSERT INTO `message_griddler` VALUES ('G005', '5646', '30', '15', '40', '2024-06-28 19:45:12', '0', '3', 'GR005');

-- ----------------------------
-- Table structure for message_manager
-- ----------------------------
DROP TABLE IF EXISTS `message_manager`;
CREATE TABLE `message_manager` (
  `id` int NOT NULL,
  `message_id` varchar(255) DEFAULT NULL,
  `griddler_id` varchar(255) DEFAULT NULL COMMENT '分配给的网格员',
  `manager_id` varchar(50) DEFAULT NULL,
  `province_id` varchar(255) DEFAULT NULL,
  `city_id` varchar(255) DEFAULT NULL,
  `status_manager` int DEFAULT '0' COMMENT '是否异地指派\n0：否\n1：是',
  `status` int DEFAULT '0' COMMENT '未完成0，已完成1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of message_manager
-- ----------------------------
INSERT INTO `message_manager` VALUES ('1', '1', '1', '1', '1', '1-1', '0', '0');
INSERT INTO `message_manager` VALUES ('2', '2', '1', '1', '2', '2-3', '0', '0');
INSERT INTO `message_manager` VALUES ('3', '3', '1', '1', '2', '2-2', '0', '0');
INSERT INTO `message_manager` VALUES ('4', '4', '1', '1', '4', '4-1', '0', '0');
INSERT INTO `message_manager` VALUES ('5', '5', '1', '1', '5', '5-3', '0', '0');
INSERT INTO `message_manager` VALUES ('6', '6', '1', '1', '4', '4-2', '0', '0');
INSERT INTO `message_manager` VALUES ('7', '7', '1', '1', '11', '11-1', '0', '0');
INSERT INTO `message_manager` VALUES ('8', '8', '1', '1', '12', '12-5', '0', '0');
INSERT INTO `message_manager` VALUES ('9', '9', '1', '1', '30', '30-1', '0', '0');
INSERT INTO `message_manager` VALUES ('10', '10', '1', '1', '30', '30-5', '0', '0');

-- ----------------------------
-- Table structure for message_public
-- ----------------------------
DROP TABLE IF EXISTS `message_public`;
CREATE TABLE `message_public` (
  `id` varchar(255) NOT NULL,
  `public_id` varchar(255) DEFAULT NULL,
  `province_id` varchar(20) DEFAULT NULL,
  `city_id` varchar(20) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL COMMENT '详细的描述反馈信息',
  `date` datetime DEFAULT NULL,
  `status` int DEFAULT NULL COMMENT '状态：\n待指派：0\n已指派：1',
  `level` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of message_public
-- ----------------------------
INSERT INTO `message_public` VALUES ('1', '1', '1', '1-1', '朝阳区北京站', '空气质量令人满意，基本无空气污染，对健康无影响。', '2024-01-01 10:00:00', '1', '3');
INSERT INTO `message_public` VALUES ('45421', '1', '4', '4-2', '123 Street, City1', 'Description for public message 1', '2024-06-24 12:34:56', '0', '1');
INSERT INTO `message_public` VALUES ('46485', '1', '4', '4-4', '101 Circle, City4', 'Description for public message 4', '2024-06-27 18:12:34', '1', '3');
INSERT INTO `message_public` VALUES ('5646', '1', '4', '4-5', '202 Square, City5', 'Description for public message 5', '2024-06-28 19:45:12', '0', '2');
INSERT INTO `message_public` VALUES ('6442', '1', '4', '4-6', '456 Avenue, City2', 'Description for public message 2', '2024-06-25 14:56:23', '1', '2');
INSERT INTO `message_public` VALUES ('686843', '1', '4', '4-7', '789 Boulevard, City3', 'Description for public message 3', '2024-06-26 16:23:45', '1', '1');
INSERT INTO `message_public` VALUES ('m10', '10', '1', '4-8', '707 Poplar St', '空气质量可以接受，但某些污染物可能对极少数异常敏感人群健康有较轻微影响。', '2024-10-01 19:00:00', '1', '3');
INSERT INTO `message_public` VALUES ('m2', '2', '2', '4-9', '456 Elm St', '易感人群症状有轻度加剧，健康人群基本无影响。', '2024-02-01 11:00:00', '1', '2');
INSERT INTO `message_public` VALUES ('m3', '3', '2', '4-10', '789 Oak St', '进一步加剧易感人群症状，可能对健康人群心脏、呼吸系统有影响。', '2024-03-01 12:00:00', '0', '1');
INSERT INTO `message_public` VALUES ('m4', '4', '4', '4-11', '101 Pine St', '健康人群中有明显强烈症状，同时对其他人群健康影响较大。', '2024-04-01 13:00:00', '1', '3');
INSERT INTO `message_public` VALUES ('m5', '5', '5', '5-1', '202 Cedar St', '健康影响极大，可能对整个人群的健康造成严重危害。', '2024-05-01 14:00:00', '1', '2');
INSERT INTO `message_public` VALUES ('m6', '6', '4', '5-2', '303 Birch St', 'Concern about public safety', '2024-06-01 15:00:00', '1', '1');
INSERT INTO `message_public` VALUES ('m7', '7', '11', '5-3', '404 Maple St', 'Feedback on new policy', '2024-07-01 16:00:00', '1', '3');
INSERT INTO `message_public` VALUES ('m8', '8', '12', '5-4', '505 Walnut St', 'Suggestion for improvement', '2024-08-01 17:00:00', '1', '2');
INSERT INTO `message_public` VALUES ('m9', '9', '30', '5-5', '606 Cherry St', 'Inquiry about services', '2024-09-01 18:00:00', '0', '1');
INSERT INTO `message_public` VALUES ('string', 'string', '30', '30-5', 'sreing', 'string', '2024-06-27 00:00:00', '0', '1');

-- ----------------------------
-- Table structure for policymaker
-- ----------------------------
DROP TABLE IF EXISTS `policymaker`;
CREATE TABLE `policymaker` (
  `id` varchar(50) NOT NULL,
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of policymaker
-- ----------------------------
INSERT INTO `policymaker` VALUES ('dad4a2da2', 'ZangXinrui', '123456');

-- ----------------------------
-- Table structure for provinces
-- ----------------------------
DROP TABLE IF EXISTS `provinces`;
CREATE TABLE `provinces` (
  `id` int NOT NULL AUTO_INCREMENT,
  `province_id` varchar(10) NOT NULL,
  `province_name` varchar(50) NOT NULL,
  `province_abbreviation` varchar(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of provinces
-- ----------------------------
INSERT INTO `provinces` VALUES ('1', '1', '北京市', '京');
INSERT INTO `provinces` VALUES ('2', '2', '天津市', '津');
INSERT INTO `provinces` VALUES ('3', '3', '河北省', '冀');
INSERT INTO `provinces` VALUES ('4', '4', '山西省', '晋');
INSERT INTO `provinces` VALUES ('5', '5', '内蒙古自治区', '蒙');
INSERT INTO `provinces` VALUES ('6', '6', '辽宁省', '辽');
INSERT INTO `provinces` VALUES ('7', '7', '吉林省', '吉');
INSERT INTO `provinces` VALUES ('8', '8', '黑龙江省', '黑');
INSERT INTO `provinces` VALUES ('9', '9', '上海市', '沪');
INSERT INTO `provinces` VALUES ('10', '10', '江苏省', '苏');
INSERT INTO `provinces` VALUES ('11', '11', '浙江省', '浙');
INSERT INTO `provinces` VALUES ('12', '12', '安徽省', '皖');
INSERT INTO `provinces` VALUES ('13', '13', '福建省', '闽');
INSERT INTO `provinces` VALUES ('14', '14', '江西省', '赣');
INSERT INTO `provinces` VALUES ('15', '15', '山东省', '鲁');
INSERT INTO `provinces` VALUES ('16', '16', '河南省', '豫');
INSERT INTO `provinces` VALUES ('17', '17', '湖北省', '鄂');
INSERT INTO `provinces` VALUES ('18', '18', '湖南省', '湘');
INSERT INTO `provinces` VALUES ('19', '19', '广东省', '粤');
INSERT INTO `provinces` VALUES ('20', '20', '广西壮族自治区', '桂');
INSERT INTO `provinces` VALUES ('21', '21', '海南省', '琼');
INSERT INTO `provinces` VALUES ('22', '22', '重庆市', '渝');
INSERT INTO `provinces` VALUES ('23', '23', '四川省', '川');
INSERT INTO `provinces` VALUES ('24', '24', '贵州省', '黔');
INSERT INTO `provinces` VALUES ('25', '25', '云南省', '滇');
INSERT INTO `provinces` VALUES ('26', '26', '西藏自治区', '藏');
INSERT INTO `provinces` VALUES ('27', '27', '陕西省', '陕');
INSERT INTO `provinces` VALUES ('28', '28', '甘肃省', '甘');
INSERT INTO `provinces` VALUES ('29', '29', '青海省', '青');
INSERT INTO `provinces` VALUES ('30', '30', '宁夏回族自治区', '宁');
INSERT INTO `provinces` VALUES ('31', '31', '新疆维吾尔自治区', '新');
INSERT INTO `provinces` VALUES ('32', '32', '香港特别行政区', '港');
INSERT INTO `provinces` VALUES ('33', '33', '澳门特别行政区', '澳');
INSERT INTO `provinces` VALUES ('34', '34', '台湾省', '台');

-- ----------------------------
-- Table structure for public
-- ----------------------------
DROP TABLE IF EXISTS `public`;
CREATE TABLE `public` (
  `id` varchar(50) NOT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  `gender` int DEFAULT '0' COMMENT '性别：\n默认为男：0\n女：1',
  `password` varchar(255) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of public
-- ----------------------------
INSERT INTO `public` VALUES ('1', '13912345678', '张三', '0', 'password123', '1990-01-01');
INSERT INTO `public` VALUES ('10', '13012345678', '刘十二', '1', 'password012', '1999-10-10');
INSERT INTO `public` VALUES ('2', '13823456789', '李四', '1', 'password234', '1991-02-02');
INSERT INTO `public` VALUES ('3', '13734567890', '王五', '0', 'password345', '1992-03-03');
INSERT INTO `public` VALUES ('4', '13645678901', '赵六', '1', 'password456', '1993-04-04');
INSERT INTO `public` VALUES ('5', '13556789012', '孙七', '0', 'password567', '1994-05-05');
INSERT INTO `public` VALUES ('5fef032fc68dc0d617f0fcc0a36db855', '1234567890', 'testUser1', '0', 'e10adc3949ba59abbe56e057f20f883e', '2024-06-27');
INSERT INTO `public` VALUES ('6', '13467890123', '周八', '1', 'password678', '1995-06-06');
INSERT INTO `public` VALUES ('7', '13378901234', '吴九', '0', 'password789', '1996-07-07');
INSERT INTO `public` VALUES ('8', '13289012345', '郑十', '1', 'password890', '1997-08-08');
INSERT INTO `public` VALUES ('87dc03c9c46be44022b87f3323b17230', '1234567890', 'John Doe', '0', 'e10adc3949ba59abbe56e057f20f883e', '1990-01-01');
INSERT INTO `public` VALUES ('9', '13190123456', '钱十一', '0', 'password901', '1998-09-09');
