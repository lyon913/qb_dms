package com.whr.dms.models;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2012-09-05T10:52:47.785+0800")
@StaticMetamodel(TFile.class)
public class TFile_ extends BaseEntity_ {
	public static volatile SingularAttribute<TFile, String> name;
	public static volatile SingularAttribute<TFile, String> description;
	public static volatile SingularAttribute<TFile, String> author;
	public static volatile SingularAttribute<TFile, Long> authorId;
	public static volatile SingularAttribute<TFile, Date> createTime;
	public static volatile SingularAttribute<TFile, Long> parentId;
	public static volatile SingularAttribute<TFile, String> filePath;
	public static volatile SingularAttribute<TFile, Long> size;
}
