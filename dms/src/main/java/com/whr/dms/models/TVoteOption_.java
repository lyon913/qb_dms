package com.whr.dms.models;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2015-01-21T14:18:59.540+0800")
@StaticMetamodel(TVoteOption.class)
public class TVoteOption_ extends BaseAuditEntity_ {
	public static volatile SingularAttribute<TVoteOption, String> title;
	public static volatile SingularAttribute<TVoteOption, String> description;
	public static volatile SingularAttribute<TVoteOption, TVote> vote;
}
