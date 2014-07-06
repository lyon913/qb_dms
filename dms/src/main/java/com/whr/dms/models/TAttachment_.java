package com.whr.dms.models;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-07-06T15:06:56.198+0800")
@StaticMetamodel(TAttachment.class)
public class TAttachment_ extends BaseAuditEntity_ {
	public static volatile SingularAttribute<TAttachment, String> foreignTable;
	public static volatile SingularAttribute<TAttachment, Long> foreignKey;
	public static volatile SingularAttribute<TAttachment, String> name;
	public static volatile SingularAttribute<TAttachment, String> path;
	public static volatile SingularAttribute<TAttachment, Long> size;
}
