package com.whr.dms.ajax;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;


public class PageableRange implements Pageable{
	private int start;
	private int end;
	private Sort sort;
	
	public PageableRange(String range){
		range = range.replace("items=", "");
		String[] rangeArray = range.split("-");

		start = Integer.parseInt(rangeArray[0]);
		end = Integer.parseInt(rangeArray[1]);
	}
	
	public PageableRange(String range, Sort sort){
		range = range.replace("items=", "");
		String[] rangeArray = range.split("-");

		start = Integer.parseInt(rangeArray[0]);
		end = Integer.parseInt(rangeArray[1]);
		this.sort = sort;
	}
	
	@Override
	public int getPageNumber() {
		return 0;
	}

	@Override
	public int getPageSize() {
		return end - start + 1;
	}

	@Override
	public int getOffset() {
		return start;
	}

	@Override
	public Sort getSort() {
		return sort;
	}

	
	
	@Override
	public Pageable first() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasPrevious() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Pageable next() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pageable previousOrFirst() {
		// TODO Auto-generated method stub
		return null;
	}
}
