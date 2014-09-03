package com.whr.dms.web.form;

import com.whr.dms.models.SuggestionState;
import com.whr.dms.models.TSuggestion;

public class AssessForm {
	private boolean checked;
	private String reply;
	
	public AssessForm(){
		super();
	}
	
	public AssessForm(TSuggestion s) {
		super();
		if(SuggestionState.Public.equals(s.getState())) {
			this.checked=true;
		}
		else if(SuggestionState.Private.equals(s.getState())) {
			this.checked=false;
		}
		else {
			throw new RuntimeException("状态不正确");
		}
		
	}
	
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	public String getReply() {
		return reply;
	}
	public void setReply(String reply) {
		this.reply = reply;
	}
	
}
