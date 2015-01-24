package com.whr.dms.models;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2015-01-24T21:50:18.906+0800")
@StaticMetamodel(TVoteRecord.class)
public class TVoteRecord_ extends BaseAuditEntity_ {
	public static volatile SingularAttribute<TVoteRecord, Long> voteId;
	public static volatile SingularAttribute<TVoteRecord, Long> optionId;
	public static volatile SingularAttribute<TVoteRecord, Long> userId;
	public static volatile SingularAttribute<TVoteRecord, String> userName;
}
