package com.whr.dms.models;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2012-08-26T16:05:49.909+0800")
@StaticMetamodel(TNoticeAttachment.class)
public class TNoticeAttachment_ extends BaseEntity_ {
	public static volatile SingularAttribute<TNoticeAttachment, Long> noticeId;
	public static volatile SingularAttribute<TNoticeAttachment, String> name;
	public static volatile SingularAttribute<TNoticeAttachment, String> path;
	public static volatile SingularAttribute<TNoticeAttachment, Long> size;
}
