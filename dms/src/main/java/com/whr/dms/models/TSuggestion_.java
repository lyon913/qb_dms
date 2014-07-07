package com.whr.dms.models;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-07-07T11:25:12.257+0800")
@StaticMetamodel(TSuggestion.class)
public class TSuggestion_ extends BaseAuditEntity_ {
	public static volatile SingularAttribute<TSuggestion, String> suggestionTitle;
	public static volatile SingularAttribute<TSuggestion, Long> authorId;
	public static volatile SingularAttribute<TSuggestion, Date> suggestionDate;
	public static volatile SingularAttribute<TSuggestion, String> suggestionContent;
	public static volatile SingularAttribute<TSuggestion, String> author;
	public static volatile SingularAttribute<TSuggestion, SuggestionType> type;
	public static volatile SingularAttribute<TSuggestion, SuggestionState> state;
}
