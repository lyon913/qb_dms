package com.whr.dms.web.propertyEditor;

import java.beans.PropertyEditorSupport;

import com.whr.dms.models.BaseEntity;

public class EntityPropertyEditor extends PropertyEditorSupport {
	private Class<? extends BaseEntity> clazz;

	public EntityPropertyEditor(Class<? extends BaseEntity> clazz) {
		this.clazz = clazz;
	}

	@Override
	public String getAsText() {
		if (this.getValue() instanceof BaseEntity) {
			BaseEntity c = (BaseEntity) this.getValue();
			Long id = c.getId();
			if (id == null) {
				return null;
			}
			return id.toString();
		}
		return super.getAsText();
	}

	@Override
	public void setAsText(String text)
			throws java.lang.IllegalArgumentException {
		if (text != null) {

			try {
				BaseEntity c = clazz.newInstance();
				c.setId(Long.parseLong(text));
				setValue(c);
				return;
			} catch (Exception e) {
				throw new RuntimeException(e.getMessage());
			}
		}
		setValue(null);
	}
}
