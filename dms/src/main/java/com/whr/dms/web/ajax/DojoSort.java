package com.whr.dms.web.ajax;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;

public class DojoSort {
	private List<Order> orders = new ArrayList<Order>();
	
	public DojoSort(String sort){
		if(sort != null && !sort.isEmpty()){
			String[] sortArray = sort.split(",");
			for (int i = 0; i < sortArray.length; i++) {
				if(sortArray[i].startsWith("-")){
					orders.add(new Order(Direction.DESC,sortArray[i].substring(1)));
				}else{
					orders.add(new Order(Direction.ASC,sortArray[i].substring(1)));
				}
			}
		}
	}
	
	public Sort toSpringSort(){
		if(orders.size() == 0){
			return null;
		}
		return new Sort(orders);
	}

}
