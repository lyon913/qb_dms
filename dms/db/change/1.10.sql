ALTER TABLE `dms`.`tvoterecord` 
ADD INDEX `IDX_TVOTERECORD_VOTEID` (`voteId` ASC),
ADD INDEX `IDX_TVOTERECORD_USERID` (`userId` ASC),
ADD INDEX `IDX_TVOTERECORD_OPTIONID` (`optionId` ASC);

insert into TDatabaseChange(majorVersion,minorVersion,fixVersion,fileName,opdate) 
	values(1,10,0,'1.10.sql',now());