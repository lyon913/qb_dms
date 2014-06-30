package com.whr.dms.notice.service;

import javax.annotation.Resource;

import org.junit.Test;

import com.whr.dms.test.BaseTest;

public class NoticeServiceTest extends BaseTest{
	
	@Resource
	private NoticeService ns;
	
	@Test
	public void getLatestNoticeTest(){
		ns.getNoticeAttachments(1);
	}
}
