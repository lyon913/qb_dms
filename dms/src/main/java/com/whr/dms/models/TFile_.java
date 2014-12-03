package com.whr.dms.models;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-12-03T11:03:38.822+0800")
@StaticMetamodel(TFile.class)
public class TFile_ extends BaseAuditEntity_ {
	public static volatile SingularAttribute<TFile, String> name;
	public static volatile SingularAttribute<TFile, String> description;
	public static volatile SingularAttribute<TFile, String> author;
	public static volatile SingularAttribute<TFile, String> authorDepart;
	public static volatile SingularAttribute<TFile, Long> authorId;
	public static volatile SingularAttribute<TFile, Long> parentId;
	public static volatile SingularAttribute<TFile, String> filePath;
	public static volatile SingularAttribute<TFile, Long> size;
}
