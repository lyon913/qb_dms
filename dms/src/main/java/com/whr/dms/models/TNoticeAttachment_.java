package com.whr.dms.models;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-07-05T18:15:59.911+0800")
@StaticMetamodel(TNoticeAttachment.class)
public class TNoticeAttachment_ extends BaseAuditEntity_ {
	public static volatile SingularAttribute<TNoticeAttachment, Long> noticeId;
	public static volatile SingularAttribute<TNoticeAttachment, String> name;
	public static volatile SingularAttribute<TNoticeAttachment, String> path;
	public static volatile SingularAttribute<TNoticeAttachment, Long> size;
}
