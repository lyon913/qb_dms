package com.whr.dms.models;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-07-05T18:28:54.305+0800")
@StaticMetamodel(TLoginAudit.class)
public class TLoginAudit_ extends BaseEntity_ {
	public static volatile SingularAttribute<TLoginAudit, Long> userId;
	public static volatile SingularAttribute<TLoginAudit, String> loginName;
	public static volatile SingularAttribute<TLoginAudit, String> userName;
	public static volatile SingularAttribute<TLoginAudit, Date> longinTime;
	public static volatile SingularAttribute<TLoginAudit, String> ipAddress;
}
