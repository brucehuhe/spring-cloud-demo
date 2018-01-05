package org.spring.cloud.order.service.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class Tools {
	
	@SuppressWarnings("static-access")
	public static void main(String[] args) throws ParseException {
//		System.out.println(new Date());
		System.out.println(new Tools().getSortDate(new Date()));
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dd = sdf.format(new Date());
		System.out.println(dd);
		System.out.println(sdf.parse(dd));
		
//		Date date = sdf.parse("2008-08-08 12:10:12");
//		System.out.println(date);
//		String str = sdf.format(date);
//		System.out.println(str);
	}
	
	/**
	 * 转化时间显示格式 为年-月-日 时：分：秒
	 * @author brucehu
	 * @param dt
	 * @return
	 * Date
	 * 2017年12月18日
	 * @throws ParseException 
	 */
	public static Date getSortDate(Date dt) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dd = sdf.format(new Date());
		try {
			return sdf.parse(dd);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String getDates(Date dt) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String str = sdf.format(dt);
			return str ;
	}
	
	/**
	 * 转化时间显示格式 为年-月-日 
	 * @author brucehu
	 * @param dt
	 * @return
	 * Date
	 * 2017年12月18日
	 * @throws ParseException 
	 */
	@SuppressWarnings("finally")
	public static Date getDate(Date dt){
		Date date = null ;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String str = sdf.format(new Date());
		try {
			date = sdf.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}finally {
			return date ;
		}
	}
	
	/**
	 * 生成ID主键
	 * @author brucehu
	 * @param dt
	 * @return
	 * 2017年12月18日
	 * @throws ParseException 
	 */
	public static String getId(){
		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		return uuid;
	}

}
