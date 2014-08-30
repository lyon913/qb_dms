package com.whr.dms.service;

import com.whr.dms.models.ClickType;
import com.whr.dms.models.TClickCount;

public interface ClickCountService {

	/**
	 * 根据点击类型以及编号获得点击次数
	 * @param clickType
	 * @param referencesId
	 * @return
	 */
	public TClickCount getClickCountById(ClickType clickType,long referencesId);
	
	/**
	 * 保存点击次数
	 */
	public Long saveClickCount(ClickType clickType,long referencesId);

}
