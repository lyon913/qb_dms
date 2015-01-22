package com.whr.dms.models;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-12-29T09:23:19.567+0800")
@StaticMetamodel(TVoteRecord.class)
public class TVoteRecord_ extends BaseAuditEntity_ {
	public static volatile SingularAttribute<TVoteRecord, Long> voteId;
	public static volatile SingularAttribute<TVoteRecord, Long> optionId;
	public static volatile SingularAttribute<TVoteRecord, Long> userId;
}
