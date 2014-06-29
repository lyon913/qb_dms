package com.whr.dms.models;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2012-08-23T20:58:51.767+0800")
@StaticMetamodel(TNoticeType.class)
public class TNoticeType_ extends BaseEntity_ {
	public static volatile SingularAttribute<TNoticeType, String> noticetype;
	public static volatile SingularAttribute<TNoticeType, String> noticememo;
	public static volatile SingularAttribute<TNoticeType, Long> noticeorder;
	public static volatile SingularAttribute<TNoticeType, Long> parentId;
}
