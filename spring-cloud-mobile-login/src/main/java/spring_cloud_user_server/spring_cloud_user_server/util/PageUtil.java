package spring_cloud_user_server.spring_cloud_user_server.util;

/**
 * 接收前端传入的分页条件
 * @author Administrator
 *
 */
public class PageUtil {
	private Integer pageNum; //起始页
	private Integer pageSize;//页面大小
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
	
}
