package org.spring.cloud.service.service.impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spring.cloud.service.mapper.stockMapper.UserMapper;
import org.spring.cloud.service.model.stockPo.User;
import org.spring.cloud.service.service.UserService;
import org.spring.cloud.service.util.EncryptUtil;
import org.spring.cloud.service.util.Result;
import org.spring.cloud.service.util.Tools;
import org.spring.cloud.service.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.sf.json.JSONObject;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	private Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	UserMapper userMapper;

	@SuppressWarnings("rawtypes")
	@Autowired
	RedisTemplate redisTemplate;

	@SuppressWarnings("static-access")
	@Override
	public Result saveUser(String param) {
		Result result = new Result();
		// 此步可以直接将字符串转化成对象
		User user = (User) JSONObject.toBean(JSONObject.fromObject(param), User.class);
		user.setLoginTime(new Tools().getSortDate(new Date()));
		user.setPassword(EncryptUtil.md5(user.getPassword()));
		User u = userMapper.selectByUserName(user.getUsername());
		if (user.getId()==null) {
			if (u == null) {
				int i = userMapper.insertSelective(user);
				if (i == 1) {
					log.info(user.getUsername() + "==========用户添加成功.");
					result.setMessage("用户添加成功.");
					result.setState(1);
					result.setObj(JSONObject.fromObject(user));
				} else {
					log.info(user.getUsername() + "==========用户添加失败.");
					result.setMessage("用户添加失败.");
					result.setState(0);
					result.setObj(JSONObject.fromObject(user));
				}
			} else {
				log.info(user.getUsername() + "==========用户存在,请重新添加.");
				result.setMessage("用户存在,请重新添加.");
				result.setState(0);
				result.setObj(JSONObject.fromObject(user));
			}
		} else {
			int i = userMapper.updateByPrimaryKeySelective(user);
			if (i == 1) {
				log.info(user.getUsername() + "==========用户修改成功.");
				result.setMessage("用户修改成功.");
				result.setState(1);
				result.setObj(JSONObject.fromObject(user));
			} else {
				log.info(user.getUsername() + "==========用户修改失败.");
				result.setMessage("用户修改失败.");
				result.setState(0);
				result.setObj(JSONObject.fromObject(user));
			}
		}
		return result;

	}

	/**
	 * 仓库用户登录信息
	 * 
	 * @return
	 */
	@SuppressWarnings("static-access")
	@Override
	public Result loginUser(String param) {
		Result rs = new Result();
		User user = (User) JSONObject.toBean(JSONObject.fromObject(param), User.class);
		User us = new User();
		us.setUsername(user.getUsername());
		String pass = user.getPassword();
		us.setPassword(EncryptUtil.md5(user.getPassword()));
		User u = userMapper.selectByUser(us);
		if (u != null) {
			if (u.getIsUse().equals(null)) {
				u.setIsUse("0");
			}
			if (u.getIsUse().equals("0")) {
				u.setIsUse("1");
				u.setLoginState(user.getLoginState()); // 此地要修复
				u.setLoginTime(new Tools().getSortDate(new Date()));
				userMapper.updateByPrimaryKeySelective(u);

				UserVo userVo = getUserVo(u, pass);

				rs.setState(1);
				rs.setMessage("登录成功.");
				rs.setObj(JSONObject.fromObject(userVo));
				log.info(u.getUsername() + "==========登录成功.");
			} else {
				if (u.getLoginState().contains(user.getLoginState())) {
					UserVo userVo = getUserVo(u, pass);
					rs.setState(0);
					rs.setMessage("已登录.");
					rs.setObj(JSONObject.fromObject(userVo));
					log.info(u.getUsername() + "==========已登录.");
				} else {
					u.setIsUse("1");
					if (u.getLoginState() == "0") {
						u.setLoginState(user.getLoginState());
					} else {
						u.setLoginState(u.getLoginState() + "," + user.getLoginState());
					}
					u.setLoginTime(new Tools().getSortDate(new Date()));
					userMapper.updateByPrimaryKeySelective(u);
					UserVo userVo = getUserVo(u, pass);
					rs.setState(1);
					rs.setMessage("登录成功.");
					rs.setObj(JSONObject.fromObject(userVo));
					log.info(u.getUsername() + "==========登录成功.");
				}
			}
		} else {
			rs.setState(0);
			rs.setMessage("用户已不存在，请确认后再登录.");
			rs.setObj(JSONObject.fromObject(null));
			log.info(user.getUsername() + "==========登录失败.");
		}
		return rs;
	}

	/**
	 * 共用用户VO
	 * 
	 * @author brucehu
	 * @param u
	 * @return UserVo 2017年12月25日
	 */
	@SuppressWarnings("static-access")
	private UserVo getUserVo(User u, String pass) {
		UserVo userVo = new UserVo();
		userVo.setId(u.getId());
		userVo.setPassword(pass);
		userVo.setEnable(String.valueOf(u.getEnable()));
		userVo.setIsUse(u.getIsUse());
		userVo.setLoginState(u.getLoginState());
		userVo.setLoginTime(new Tools().getDates(u.getLoginTime()));
		userVo.setUsername(u.getUsername());
		return userVo;
	}

	/**
	 * 仓库用户退出登录
	 */
	@Override
	public Result loginOut(String param) {
		Result result = new Result();
		User user = (User) JSONObject.toBean(JSONObject.fromObject(param), User.class);
		User us = userMapper.selectByPrimaryKey(user.getId());
		String rs = "";
		if (us.getLoginState().contains(",")) {
			String[] strs = us.getLoginState().split(",");
			for (String str : strs) {
				if (user.getLoginState().equals(str)) {
					rs = "";
					continue;
				} else {
					rs += str + ",";
				}
			}
		}
		if (!rs.contains("1") && !rs.contains("0")) {
			user.setLoginState("0");
			user.setIsUse("0");
		} else {
			user.setLoginState(rs);
		}
		int i = userMapper.updateByPrimaryKeySelective(user);
		if (i == 1) {
			log.info(user.getUsername() + "==========已退出登录.");
			result.setMessage("退出成功.");
			result.setState(1);
			result.setObj(user);
		} else {
			log.info(user.getUsername() + "==========退出失败.");
			result.setMessage("退出失败.");
			result.setState(0);
			result.setObj(user);
		}
		return result;
	}

	/**
	 * 仓库用户重新设置密码
	 */
	@Override
	public Result setPassword(String param) {
		Result rs = new Result();
		User user = (User) JSONObject.toBean(JSONObject.fromObject(param), User.class);
		user.setPassword(EncryptUtil.md5(user.getPassword()));
		int i = userMapper.updateByPrimaryKeySelective(user);
		if (i == 1) {
			rs.setState(1);
			rs.setMessage("密码设置成功.");
			rs.setObj(user.getUsername() + "密码设置成功.");
			log.info(user.getUsername() + "==========密码设置成功.");
		} else {
			rs.setState(0);
			rs.setMessage("密码设置失败.");
			rs.setObj("密码设置失败.");
			log.info(user.getUsername() + "==========密码设置失败.");
		}
		return rs;
	}

}
