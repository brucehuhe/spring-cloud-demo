package org.spring.cloud.service.util;

import java.io.Serializable;

/**
 * 返回前台对象
 * @author Administrator
 *
 */
public class Result implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4668797063052599341L;
	//状态
	private Integer state ;
	//返回消息
	private String message;
	//总条数
	private Long totalNum;
	//总页数
	private Long maxPage;
	//每页多少条
	private Integer pageSize;
	//当前页码
	private Integer pageNum;
	//结果集合
	private Object obj;
	//名称
	private String name ;
	
	public Result(Integer state, String message, Long totalNum, Long maxPage, Integer pageSize, Integer pageNum,
			Object obj,String name) {
		super();
		this.state = state;
		this.message = message;
		this.totalNum = totalNum;
		this.maxPage = maxPage;
		this.pageSize = pageSize;
		this.pageNum = pageNum;
		this.obj = obj;
		this.name = name ;
	}
	public Result() {
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getMessage() {
		if(message==null){
			message="";
		}
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Long getTotalNum() {
		if(totalNum==null){
			totalNum=1l;
		}
		return totalNum;
	}
	public void setTotalNum(Long totalNum) {
		this.totalNum = totalNum;
	}
	public Long getMaxPage() {
		return maxPage;
	}
	public void setMaxPage(Long maxPage) {
		this.maxPage = maxPage;
	}
	public Integer getPageSize() {
		if(pageSize==null){
			pageSize=0;
		}
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getPageNum() {
		if(pageNum==null){
			pageNum=0;
		}
		return pageNum;
	}
	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}
	public Object getObj() {
		if(obj==null){
			obj="";
		}
		return obj;
	}
	public void setObj(Object obj) {
		this.obj = obj;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}