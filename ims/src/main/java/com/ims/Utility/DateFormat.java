/**
 * 
 */
package com.ims.Utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author rahul
 *
 */
public class DateFormat {
	
	/**
	 * @author rahul
	 * 
	 * @return
	 * @throws ParseException
	 */
	public static Date today() throws ParseException{
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		return format.parse(format.format(date));
	}
	public static Date yesterday() {
	    final Calendar cal = Calendar.getInstance();
	    cal.add(Calendar.DATE, -1);
	    return cal.getTime();
	}

}
