package com.whr.dms.models;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-07-07T17:22:26.146+0800")
@StaticMetamodel(TUser.class)
public class TUser_ extends BaseAuditEntity_ {
	public static volatile SingularAttribute<TUser, String> loginName;
	public static volatile SingularAttribute<TUser, String> password;
	public static volatile SingularAttribute<TUser, String> name;
	public static volatile SingularAttribute<TUser, TDepartment> department;
	public static volatile ListAttribute<TUser, TRole> roles;
}
