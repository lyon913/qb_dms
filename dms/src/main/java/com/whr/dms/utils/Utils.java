package com.whr.dms.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.whr.dms.models.TFolder;
import com.whr.dms.web.form.FolderTreeNode;

public class Utils {
	
	private static Map<Integer,String> colorMap;
	static {
		colorMap = new HashMap<Integer, String>();
		colorMap.put(0, "#7cb5ec");
		colorMap.put(1, "#f7a35c");
		colorMap.put(2, "#90ee7e");
		colorMap.put(3, "#7798BF");
		colorMap.put(4, "#aaeeee");
		colorMap.put(5, "#ff0066");
		colorMap.put(6, "#eeaaee");
		colorMap.put(7, "#55BF3B");
		colorMap.put(8, "#DF5353");
		colorMap.put(9, "#7798BF");
	}
	
	public static Date getTodayDate() {
		try {
			DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			Date today = new Date();
			Date todayWithZeroTime =formatter.parse(formatter.format(today));
			return todayWithZeroTime;
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static Date getTomorrowDate() {
		try {
			DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			Calendar c = Calendar.getInstance();
			c.add(Calendar.DATE, 1);
			
			Date tomorrow = c.getTime();
			Date date =formatter.parse(formatter.format(tomorrow));
			return date;
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static List<FolderTreeNode> formatFolderList(List<TFolder> folders){
		return null;
	}
	
	public static String getColor(int index) {
		index = index%colorMap.size();
		return colorMap.get(index);
	}

}
