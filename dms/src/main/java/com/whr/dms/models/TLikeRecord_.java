package com.whr.dms.models;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2015-04-17T13:44:05.386+0800")
@StaticMetamodel(TLikeRecord.class)
public class TLikeRecord_ extends BaseAuditEntity_ {
	public static volatile SingularAttribute<TLikeRecord, Long> likeId;
	public static volatile SingularAttribute<TLikeRecord, Long> optionId;
	public static volatile SingularAttribute<TLikeRecord, Long> userId;
	public static volatile SingularAttribute<TLikeRecord, String> userName;
}
