package com.whr.dms.models;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2015-01-22T10:22:06.830+0800")
@StaticMetamodel(TVote.class)
public class TVote_ extends BaseAuditEntity_ {
	public static volatile SingularAttribute<TVote, String> title;
	public static volatile SingularAttribute<TVote, String> description;
	public static volatile SingularAttribute<TVote, Long> authorId;
	public static volatile SingularAttribute<TVote, String> authorName;
	public static volatile SingularAttribute<TVote, Boolean> isMulti;
	public static volatile SingularAttribute<TVote, Boolean> isOpen;
	public static volatile SingularAttribute<TVote, Integer> maxVotes;
	public static volatile SingularAttribute<TVote, Date> endDate;
	public static volatile ListAttribute<TVote, TVoteOption> options;
}
