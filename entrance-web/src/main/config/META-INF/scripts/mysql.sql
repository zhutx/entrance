CREATE DATABASE edu_diamond;

CREATE TABLE `CONF_USER` (
  `ID` int(11) NOT NULL,
  `USER_CODE` varchar(32) DEFAULT NULL,
  `USER_NAME` varchar(32) NOT NULL,
  `PASSWORD` varchar(32) NOT NULL,
  `DELETE_FLAG` int(1) DEFAULT '0',
  `CREATE_TIME` datetime DEFAULT NULL,
  `UPDATE_TIME` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `CONF_PROJECT` (
  `ID` int(11) NOT NULL,
  `PROJ_CODE` varchar(32) DEFAULT NULL,
  `PROJ_NAME` varchar(32) DEFAULT NULL,
  `OWNER_ID` int(11) DEFAULT NULL COMMENT '椤圭洰鎷ユ湁鑰匢D',
  `DEVELOPMENT_VERSION` INT(11) DEFAULT 0  NULL,
  `PRODUCTION_VERSION` INT(11) DEFAULT 0  NULL,
  `TEST_VERSION` INT(11) DEFAULT 0  NULL,
  `DELETE_FLAG` int(1) DEFAULT '0',
  `CREATE_TIME` datetime DEFAULT NULL,
  `UPDATE_TIME` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `CONF_PROJECT_USER` (
  `PROJ_ID` int(11) NOT NULL,
  `USER_ID` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`PROJ_ID`,`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `CONF_PROJECT_MODULE` (
  `MODULE_ID` int(11) NOT NULL,
  `PROJ_ID` int(11) NOT NULL,
  `MODULE_NAME` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`MODULE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `CONF_PROJECT_USER_ROLE` (
  `PROJ_ID` int(11) NOT NULL,
  `USER_ID` int(11) NOT NULL,
  `ROLE_CODE` varchar(32) NOT NULL,
  PRIMARY KEY (`PROJ_ID`,`USER_ID`,`ROLE_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `CONF_PROJECT_CONFIG` (
  `CONFIG_ID` INT(11) NOT NULL,
  `CONFIG_KEY` VARCHAR(64) NOT NULL,
  `CONFIG_VALUE` VARCHAR(256) NOT NULL,
  `CONFIG_DESC` VARCHAR(256) DEFAULT NULL,
  `PROJECT_ID` INT(11) NOT NULL,
  `MODULE_ID` INT(11) NOT NULL,
  `DELETE_FLAG` INT(1) DEFAULT '0',
  `OPT_USER` VARCHAR(32) DEFAULT NULL,
  `OPT_TIME` DATETIME DEFAULT NULL,
  `PRODUCTION_VALUE` VARCHAR(256) NOT NULL,
  `PRODUCTION_USER` VARCHAR(32) DEFAULT NULL,
  `PRODUCTION_TIME` DATETIME DEFAULT NULL,
  `TEST_VALUE` VARCHAR(256) NOT NULL,
  `TEST_USER` VARCHAR(32) DEFAULT NULL,
  `TEST_TIME` DATETIME DEFAULT NULL,
  `BUILD_VALUE` VARCHAR(256) NOT NULL,
  `BUILD_USER` VARCHAR(32) DEFAULT NULL,
  `BUILD_TIME` DATETIME DEFAULT NULL,
  PRIMARY KEY (`CONFIG_ID`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;