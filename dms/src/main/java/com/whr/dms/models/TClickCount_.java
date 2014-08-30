package com.whr.dms.models;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-08-30T14:48:24.823+0800")
@StaticMetamodel(TClickCount.class)
public class TClickCount_ extends BaseEntity_ {
	public static volatile SingularAttribute<TClickCount, ClickType> clickType;
	public static volatile SingularAttribute<TClickCount, Long> referenceId;
	public static volatile SingularAttribute<TClickCount, Long> counts;
}
