package com.whr.dms.models;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2012-08-02T13:26:05.127+0800")
@StaticMetamodel(TFolder.class)
public class TFolder_ extends BaseEntity_ {
	public static volatile SingularAttribute<TFolder, String> name;
	public static volatile SingularAttribute<TFolder, String> description;
	public static volatile SingularAttribute<TFolder, Date> createTime;
	public static volatile SingularAttribute<TFolder, String> author;
	public static volatile SingularAttribute<TFolder, Long> parentId;
}
