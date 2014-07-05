package com.whr.dms.web.ajax;


public class ContentRangeHeader {
	private int offset;
	private int count;
	private long total;
	
	public ContentRangeHeader(int offset, int count, long total){
		this.offset = offset;
		this.count = count;
		this.total = total;
	}
	
	@Override
	public String toString(){
		String result = "items ";
		result += offset + "-" + offset + count + "/" + total;
		
		return result;
	}

}
