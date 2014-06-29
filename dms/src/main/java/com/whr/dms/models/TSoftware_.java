package com.whr.dms.models;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2012-08-08T21:26:37.956+0800")
@StaticMetamodel(TSoftware.class)
public class TSoftware_ extends BaseEntity_ {
	public static volatile SingularAttribute<TSoftware, String> name;
	public static volatile SingularAttribute<TSoftware, String> description;
	public static volatile SingularAttribute<TSoftware, String> author;
	public static volatile SingularAttribute<TSoftware, Date> createTime;
	public static volatile SingularAttribute<TSoftware, Long> parentId;
	public static volatile SingularAttribute<TSoftware, String> filePath;
	public static volatile SingularAttribute<TSoftware, Long> size;
}
