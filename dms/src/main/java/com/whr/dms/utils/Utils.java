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
		colorMap.put(0, "#7CFC00");
		colorMap.put(1, "#FF7F50");
		colorMap.put(2, "#6495ED");
		colorMap.put(3, "#D2691E");
		colorMap.put(4, "#FF00FF");
		colorMap.put(5, "#DEB887");
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
