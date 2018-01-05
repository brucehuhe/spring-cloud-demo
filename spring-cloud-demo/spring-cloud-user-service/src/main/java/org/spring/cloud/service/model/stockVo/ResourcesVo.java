package org.spring.cloud.service.model.stockVo;

import java.util.List;

import org.spring.cloud.service.model.stockPo.Resources;

public class ResourcesVo {
	private String id;

	private String name;

	private String resurl;

	private String type;

	private String parentid;

	private String sort;

	private String createTime;

	private String btnName;
	
	private List<Resources> list;

	private int pageNum; // 分页开始

	private int pageSize; // 分页大小
	
	private String userId;//操作用户ID

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getResurl() {
		return resurl;
	}

	public void setResurl(String resurl) {
		this.resurl = resurl;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getParentid() {
		return parentid;
	}

	public void setParentid(String parentid) {
		this.parentid = parentid;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getBtnName() {
		return btnName;
	}

	public void setBtnName(String btnName) {
		this.btnName = btnName;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public List<Resources> getList() {
		return list;
	}

	public void setList(List<Resources> list) {
		this.list = list;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
