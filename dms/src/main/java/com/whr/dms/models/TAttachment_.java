package com.whr.dms.models;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-09-16T22:20:23.361+0800")
@StaticMetamodel(TAttachment.class)
public class TAttachment_ extends BaseAuditEntity_ {
	public static volatile SingularAttribute<TAttachment, AttachmentType> foreignTable;
	public static volatile SingularAttribute<TAttachment, Long> foreignKey;
	public static volatile SingularAttribute<TAttachment, String> name;
	public static volatile SingularAttribute<TAttachment, String> path;
	public static volatile SingularAttribute<TAttachment, Long> size;
}
