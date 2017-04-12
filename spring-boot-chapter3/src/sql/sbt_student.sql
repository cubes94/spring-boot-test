-- ----------------------------
-- Table structure for sbt_student
-- ----------------------------
DROP TABLE IF EXISTS `sbt_student`;
CREATE TABLE `sbt_student` (
  `ID` int(5) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(255) NOT NULL,
  `AGE` int(2) DEFAULT NULL,
  `GENDER` int(1) NOT NULL DEFAULT '0' COMMENT '0-男，1-女',
  `CREATE_TIME` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `UPDATE_TIME` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;
