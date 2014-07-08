package com.whr.dms.web.propertyEditor;

import java.beans.PropertyEditorSupport;

import com.whr.dms.models.TRole;

public class RolePropertyEditor extends PropertyEditorSupport{
	
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		Long id = Long.parseLong(text);
		TRole role = new TRole();
		role.setId(id);
		
		setValue(role);
	}

}
