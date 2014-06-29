package com.whr.dms.models;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2013-12-23T20:40:56.098+0800")
@StaticMetamodel(TSuggestion.class)
public class TSuggestion_ extends BaseEntity_ {
	public static volatile SingularAttribute<TSuggestion, String> suggestionTitle;
	public static volatile SingularAttribute<TSuggestion, Long> authorId;
	public static volatile SingularAttribute<TSuggestion, Date> suggestionDate;
	public static volatile SingularAttribute<TSuggestion, String> suggestionContent;
	public static volatile SingularAttribute<TSuggestion, String> author;
}
