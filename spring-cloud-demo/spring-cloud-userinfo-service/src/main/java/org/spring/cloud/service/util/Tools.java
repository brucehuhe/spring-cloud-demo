package org.spring.cloud.service.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class Tools {

	/**
	 * 转化时间显示格式 为年-月-日 时：分：秒
	 * 
	 * @author brucehu
	 * @param dt
	 * @return Date 2017年12月18日
	 * @throws ParseException
	 */
	@SuppressWarnings("finally")
	public static Date getSortDate(Date dt) {
		Date date = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String str = sdf.format(new Date());
		try {
			date = sdf.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		} finally {
			return date;
		}
	}

	/**
	 * 将日期转化为字符串
	 * 
	 * @param dt
	 * @return
	 */
	public static String getDates(Date dt) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String str = sdf.format(dt);
		return str;
	}

	public static Date getString(String dt) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date date = null;
		try {
			date = sdf.parse(dt);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 转化时间显示格式 为年-月-日
	 * 
	 * @author brucehu
	 * @param dt
	 * @return Date 2017年12月18日
	 * @throws ParseException
	 */
	@SuppressWarnings("finally")
	public static Date getDate(Date dt) {
		Date date = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String str = sdf.format(new Date());
		try {
			date = sdf.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		} finally {
			return date;
		}
	}

	/**
	 * 生成ID主键
	 * 
	 * @author brucehu
	 * @param dt
	 * @return 2017年12月18日
	 * @throws ParseException
	 */
	public static String getId() {
		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		return uuid;
	}

}
