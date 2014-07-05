package com.whr.dms.models;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-07-05T18:16:19.605+0800")
@StaticMetamodel(TReply.class)
public class TReply_ extends BaseAuditEntity_ {
	public static volatile SingularAttribute<TReply, Long> suggestionId;
	public static volatile SingularAttribute<TReply, Long> authorId;
	public static volatile SingularAttribute<TReply, Date> replyDate;
	public static volatile SingularAttribute<TReply, String> replyContent;
	public static volatile SingularAttribute<TReply, String> author;
}
