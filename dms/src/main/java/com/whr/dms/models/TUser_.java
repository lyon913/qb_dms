package com.whr.dms.models;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2012-08-07T13:31:36.302+0800")
@StaticMetamodel(TUser.class)
public class TUser_ extends BaseEntity_ {
	public static volatile SingularAttribute<TUser, String> loginName;
	public static volatile SingularAttribute<TUser, String> password;
	public static volatile SingularAttribute<TUser, String> name;
	public static volatile SingularAttribute<TUser, TDepartment> department;
}
