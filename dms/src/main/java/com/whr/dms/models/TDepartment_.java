package com.whr.dms.models;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2012-08-07T13:38:51.964+0800")
@StaticMetamodel(TDepartment.class)
public class TDepartment_ extends BaseEntity_ {
	public static volatile SingularAttribute<TDepartment, String> name;
	public static volatile SingularAttribute<TDepartment, String> head;
	public static volatile SingularAttribute<TDepartment, String> telephone;
	public static volatile ListAttribute<TDepartment, TRole> roles;
}
