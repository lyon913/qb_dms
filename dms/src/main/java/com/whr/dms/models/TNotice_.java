package com.whr.dms.models;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2012-08-26T17:01:34.428+0800")
@StaticMetamodel(TNotice.class)
public class TNotice_ extends BaseEntity_ {
	public static volatile SingularAttribute<TNotice, String> title;
	public static volatile SingularAttribute<TNotice, String> content;
	public static volatile SingularAttribute<TNotice, Date> createTime;
	public static volatile SingularAttribute<TNotice, String> author;
	public static volatile SingularAttribute<TNotice, Long> noticetypeId;
	public static volatile SingularAttribute<TNotice, Boolean> published;
	public static volatile SingularAttribute<TNotice, Date> publishDate;
	public static volatile SingularAttribute<TNotice, Date> noticeDate;
	public static volatile SingularAttribute<TNotice, Long> parentNoticeTypeId;
}
