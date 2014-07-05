package com.whr.dms.models;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-07-05T18:15:31.581+0800")
@StaticMetamodel(TFolder.class)
public class TFolder_ extends BaseAuditEntity_ {
	public static volatile SingularAttribute<TFolder, String> name;
	public static volatile SingularAttribute<TFolder, String> description;
	public static volatile SingularAttribute<TFolder, String> author;
	public static volatile SingularAttribute<TFolder, Long> parentId;
}
