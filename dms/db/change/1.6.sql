
ALTER TABLE `dms`.`tclickcount` 
ADD INDEX `clickType` (`clickType` ASC),
ADD INDEX `referenceId` (`referenceId` ASC);


ALTER TABLE `dms`.`tnotice` 
ADD INDEX `parentNoticeTypeId` (`parentNoticeTypeId` ASC),
ADD INDEX `noticeTypeId` (`noticetypeId` ASC),
ADD INDEX `noticeDate` (`noticeDate` DESC),
ADD INDEX `title` (`title` ASC),
ADD INDEX `publishDate` (`publishDate` DESC),
ADD INDEX `published` (`published` ASC);

ALTER TABLE `dms`.`tnotice_tdepartment` 
ADD INDEX `departmentId` (`departmentId` ASC),
ADD INDEX `noticeId` (`noticeId` ASC);

ALTER TABLE `dms`.`tnoticeattachment` 
ADD INDEX `noticeId` (`noticeId` ASC);

ALTER TABLE `dms`.`tnoticetype` 
ADD INDEX `parentId` (`parentId` ASC);

ALTER TABLE `dms`.`tfile` 
ADD INDEX `name` (`name` ASC),
ADD INDEX `parentId` (`parentId` ASC);

ALTER TABLE `dms`.`tfile_tdepartment` 
ADD INDEX `departmentId` (`departmentId` ASC),
ADD INDEX `fileId` (`fileId` ASC);

ALTER TABLE `dms`.`tloginaudit` 
ADD INDEX `loginTime` (`loginTime` DESC);

ALTER TABLE `dms`.`tsoftware` 
ADD INDEX `name` (`name` ASC),
ADD INDEX `parentId` (`parentId` ASC);

ALTER TABLE `dms`.`tsuggestion` 
ADD INDEX `authorId` (`authorId` ASC),
ADD INDEX `title` (`suggestionTitle` ASC),
ADD INDEX `suggDate` (`suggestionDate` DESC),
ADD INDEX `type` (`type` ASC),
ADD INDEX `state` (`state` ASC);

ALTER TABLE `dms`.`treply` 
ADD INDEX `suggId` (`suggestionId` ASC),
ADD INDEX `replyDate` (`replyDate` DESC);

insert into TDatabaseChange(majorVersion,minorVersion,fixVersion,fileName) values(1,6,0,'1.6.sql',now());