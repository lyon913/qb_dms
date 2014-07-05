package com.whr.dms.web.ajax;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class RangePage implements Pageable {
	
	private Sort sort;
	private int start;
	private int end;
	
	public RangePage(int start, int end){
		this.start = start;
		this.end = end;
	}
	
	public RangePage(int start, int end, Sort sort){
		this.start = start;
		this.end = end;
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
	public Pageable next() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pageable previousOrFirst() {
		// TODO Auto-generated method stub
		return null;
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

}
