package org.spring.cloud.service.service;

import org.spring.cloud.service.util.Result;

public interface UserService {
	/**
	 * 保存仓库用户信息
	 * @author brucehu
	 * @param user
	 * void
	 * 2017年12月24日
	 */
	public Result saveUser(String user);
	/**
	 * 仓库用户登录信息
	 * @author brucehu
	 * @param user
	 * void
	 * 2017年12月23日
	 * @return 
	 */
	public Result loginUser(String param);
	/**
	 * 仓库用户退出登录
	 * @author brucehu
	 * @param param
	 * @return
	 * Result
	 * 2017年12月23日
	 */
	public Result loginOut(String param);
	/**
	 * 仓库用户重新设置密码
	 * @author brucehu
	 * @param para
	 * @return
	 * Result
	 * 2017年12月24日
	 */
	public Result setPassword(String para);
}
