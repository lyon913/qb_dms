package com.whr.dms.models;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2012-07-17T09:20:43.866+0800")
@StaticMetamodel(TFunction.class)
public class TFunction_ extends BaseEntity_ {
	public static volatile SingularAttribute<TFunction, String> name;
	public static volatile SingularAttribute<TFunction, String> displayName;
	public static volatile SingularAttribute<TFunction, Integer> displayOrder;
	public static volatile SingularAttribute<TFunction, FunctionType> type;
	public static volatile SingularAttribute<TFunction, String> entryUrl;
	public static volatile SingularAttribute<TFunction, String> description;
}
