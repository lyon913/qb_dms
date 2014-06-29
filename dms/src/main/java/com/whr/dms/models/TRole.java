package com.whr.dms.models;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import org.springframework.security.core.GrantedAuthority;

@Entity
public class TRole extends BaseEntity implements GrantedAuthority{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7538438255663226132L;
	@NotNull
	private String name;
	
	@NotNull
	private String displayName;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	@Override
	public String getAuthority() {
		return name;
	}


}
