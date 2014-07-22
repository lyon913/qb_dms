package com.whr.dms.web.propertyEditor;

import java.beans.PropertyEditorSupport;

import com.whr.dms.models.TUserRole;
import com.whr.dms.security.RoleType;

public class RolePropertyEditor extends PropertyEditorSupport{
	
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		TUserRole ur = new TUserRole();
		ur.setRole(RoleType.valueOf(text));
		setValue(ur);
	}
	
	@Override
	public String getAsText() {
		TUserRole ur = (TUserRole) getValue();
		return ur.getRole().getName();
	}

}
