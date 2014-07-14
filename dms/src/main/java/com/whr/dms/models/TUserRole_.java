package com.whr.dms.models;

import com.whr.dms.security.RoleType;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-07-14T17:15:10.157+0800")
@StaticMetamodel(TUserRole.class)
public class TUserRole_ extends BaseEntity_ {
	public static volatile SingularAttribute<TUserRole, TUser> user;
	public static volatile SingularAttribute<TUserRole, RoleType> role;
}
