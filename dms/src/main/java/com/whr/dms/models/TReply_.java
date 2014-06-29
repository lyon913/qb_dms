package com.whr.dms.models;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2013-12-23T20:40:08.616+0800")
@StaticMetamodel(TReply.class)
public class TReply_ extends BaseEntity_ {
	public static volatile SingularAttribute<TReply, Long> suggestionId;
	public static volatile SingularAttribute<TReply, Long> authorId;
	public static volatile SingularAttribute<TReply, Date> replyDate;
	public static volatile SingularAttribute<TReply, String> replyContent;
	public static volatile SingularAttribute<TReply, String> author;
}
