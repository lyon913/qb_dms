package com.whr.dms.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.whr.dms.dao.TClickCountDao;
import com.whr.dms.models.ClickType;
import com.whr.dms.models.TClickCount;

@Service
public class ClickCountServiceImpl implements ClickCountService {

	@Resource
	TClickCountDao clickCountDao;
	@Override
	public TClickCount getClickCountById(ClickType clickType, long referencesId) {
		// 获取点击次数
		TClickCount cc = clickCountDao.getClickCount(clickType, referencesId);
		return cc;
	}
	@Override
	public Long saveClickCount(ClickType clickType, long referenceId) {
		TClickCount cc = clickCountDao.getClickCount(clickType, referenceId);
		if(cc==null) {
			cc = new TClickCount();
			cc.setClickType(clickType);
			cc.setReferenceId(referenceId);
		}
		Long counts = cc.getCounts();
		counts = counts +1;
		cc.setCounts(counts);
		clickCountDao.save(cc);
		return cc.getCounts();
	}



}
