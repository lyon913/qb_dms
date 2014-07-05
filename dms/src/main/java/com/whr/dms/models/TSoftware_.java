package com.whr.dms.models;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-07-05T18:58:19.244+0800")
@StaticMetamodel(TSoftware.class)
public class TSoftware_ extends BaseAuditEntity_ {
	public static volatile SingularAttribute<TSoftware, String> name;
	public static volatile SingularAttribute<TSoftware, String> description;
	public static volatile SingularAttribute<TSoftware, String> author;
	public static volatile SingularAttribute<TSoftware, Long> parentId;
	public static volatile SingularAttribute<TSoftware, String> filePath;
	public static volatile SingularAttribute<TSoftware, Long> size;
}
