package com.whr.dms.models;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-07-05T18:59:46.461+0800")
@StaticMetamodel(TNotice.class)
public class TNotice_ extends BaseAuditEntity_ {
	public static volatile SingularAttribute<TNotice, String> title;
	public static volatile SingularAttribute<TNotice, String> content;
	public static volatile SingularAttribute<TNotice, String> author;
	public static volatile SingularAttribute<TNotice, Long> noticetypeId;
	public static volatile SingularAttribute<TNotice, Boolean> published;
	public static volatile SingularAttribute<TNotice, Date> publishDate;
	public static volatile SingularAttribute<TNotice, Date> noticeDate;
	public static volatile SingularAttribute<TNotice, Long> parentNoticeTypeId;
}
