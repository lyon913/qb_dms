package com.whr.dms.models;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-07-05T18:13:47.378+0800")
@StaticMetamodel(BaseAuditEntity.class)
public class BaseAuditEntity_ extends BaseEntity_ {
	public static volatile SingularAttribute<BaseAuditEntity, Date> createTime;
	public static volatile SingularAttribute<BaseAuditEntity, String> creatorLoginName;
	public static volatile SingularAttribute<BaseAuditEntity, String> creatorName;
	public static volatile SingularAttribute<BaseAuditEntity, String> creatorIp;
	public static volatile SingularAttribute<BaseAuditEntity, Date> lastUpdate;
	public static volatile SingularAttribute<BaseAuditEntity, String> lastUpdaterLoginName;
	public static volatile SingularAttribute<BaseAuditEntity, String> lastUpdaterName;
	public static volatile SingularAttribute<BaseAuditEntity, String> lastUpdaterIp;
}
