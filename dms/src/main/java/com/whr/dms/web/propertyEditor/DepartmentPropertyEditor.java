package com.whr.dms.web.propertyEditor;

import java.beans.PropertyEditorSupport;

import com.whr.dms.models.TDepartment;

public class DepartmentPropertyEditor extends PropertyEditorSupport{
	
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		Long id = Long.parseLong(text);
		TDepartment depart = new TDepartment();
		depart.setId(id);
		
		setValue(depart);
	}

}
