package com.study.util;

public class GetParameters {
	
	private String id ; //主键
	private String obj ; //存放集合或多参数对象
	private String param ; //参数
	private Integer pageNum; //当前页
	private Integer pageSize; //页面大小
	private String vesionCode ;//记录App端的版本号
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getObj() {
		return obj;
	}
	public void setObj(String obj) {
		this.obj = obj;
	}
	public String getParam() {
		return param;
	}
	public void setParam(String param) {
		this.param = param;
	}
	public Integer getPageNum() {
		return pageNum;
	}
	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public String getVesionCode() {
		return vesionCode;
	}
	public void setVesionCode(String vesionCode) {
		this.vesionCode = vesionCode;
	}

}
