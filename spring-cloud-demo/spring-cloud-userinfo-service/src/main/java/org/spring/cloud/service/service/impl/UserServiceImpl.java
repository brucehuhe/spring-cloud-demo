package org.spring.cloud.service.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spring.cloud.service.mapper.stockMapper.RoleResourcesMapper;
import org.spring.cloud.service.mapper.stockMapper.UserInfoMapper;
import org.spring.cloud.service.mapper.stockMapper.UserMapper;
import org.spring.cloud.service.mapper.stockMapper.UserRoleMapper;
import org.spring.cloud.service.model.stockPo.RoleResourcesKey;
import org.spring.cloud.service.model.stockPo.User;
import org.spring.cloud.service.model.stockPo.UserInfo;
import org.spring.cloud.service.model.stockPo.UserRole;
import org.spring.cloud.service.service.UserService;
import org.spring.cloud.service.util.EncryptUtil;
import org.spring.cloud.service.util.Result;
import org.spring.cloud.service.util.Tools;
import org.spring.cloud.service.vo.UserInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	private Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserInfoMapper userInfoMapper;

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private UserRoleMapper userRoleMapper;

	@Autowired
	private RoleResourcesMapper roleResourcesMapper;

	/**
	 * 根据分页条件查询出当前用户信息
	 */
	@Override
	public Result findByUserInfos(String param) {
		Result result = new Result();
		List<UserInfoVo> list = new ArrayList<UserInfoVo>();
		UserInfoVo rv = (UserInfoVo) JSONObject.toBean(JSONObject.fromObject(param), UserInfoVo.class);
		UserInfo ui = getUserInfo(rv);
		PageHelper.startPage(rv.getPageNum(), rv.getPageSize()); // 分页起始页大小及条数
		Page<UserInfo> page = userInfoMapper.findByUsers(ui);
		for (int i = 0; i < page.size(); i++) {
			UserInfo uf = page.get(i);
			UserInfoVo uv = getUserInfoVo(uf);
			uv.setPageNum(rv.getPageNum());
			uv.setPageSize(rv.getPageSize());
			list.add(uv);
		}
		log.info("=========" + JSONArray.fromObject(list));
		Long lg = (long) 0;
		if (page.getTotal() < rv.getPageSize()) {
			lg = (long) 1;
		} else {
			lg = page.getTotal() % rv.getPageSize() == 0 ? page.getTotal() / rv.getPageSize()
					: (page.getTotal() / rv.getPageSize()) + 1;
		}
		result.setState(1);
		result.setMaxPage(lg);
		result.setTotalNum(page.getTotal());
		result.setPageNum(rv.getPageNum());
		result.setPageSize(rv.getPageSize());
		result.setObj(list);
		return result;
	}

	/**
	 * 将实体转化为临时对象
	 * 
	 * @param uf
	 * @return
	 */
	private UserInfoVo getUserInfoVo(UserInfo uf) {
		UserInfoVo uv = new UserInfoVo();
		uv.setCode(uf.getCode());
		uv.setConInfo(uf.getConInfo());
		uv.setId(uf.getId() == null ? Tools.getId() : uf.getId());
		uv.setCreateTime(Tools.getDates(uf.getCreateTime()));
		uv.setName(uf.getName());
		uv.setSex(uf.getSex());
		uv.setState(uf.getState());
		uv.setUserId(uf.getUserId());
		return uv;
	}

	/**
	 * 实体与临时对象的转化
	 * 
	 * @param rv
	 * @return
	 */
	private UserInfo getUserInfo(UserInfoVo rv) {
		UserInfo ui = new UserInfo();
		ui.setCode(rv.getCode());
		ui.setConInfo(rv.getConInfo());
		ui.setName(rv.getName());
		ui.setSex(rv.getSex());
		ui.setState(rv.getState());
		ui.setUserId(rv.getUserId());
		ui.setId(rv.getId() == null ? Tools.getId() : rv.getId());
		return ui;
	}

	/**
	 * 保存用户详细信息
	 */
	@Override
	public Result saveUserInfo(String param) {
		Result result = new Result();
		UserInfoVo rv = (UserInfoVo) JSONObject.toBean(JSONObject.fromObject(param), UserInfoVo.class);
		Map<String, String> map = getMap(rv);
		if (rv.getId() == null) {
			UserInfo uif = userInfoMapper.selectByUser(map);
			if (uif == null) {
				UserInfo ui = getUserInfo(rv);
				ui.setCreateTime(Tools.getSortDate(new Date()));
				int i = userInfoMapper.insertSelective(ui);
				if (i == 1) {
					log.info(ui.getName() + "==========仓库用户添加成功.");
					result.setMessage("仓库用户添加成功.");
					result.setState(1);
					result.setObj("");
				} else {
					log.info(ui.getName() + "==========仓库用户添加失败.");
					result.setMessage("仓库用户添加失败.");
					result.setState(0);
					result.setObj("");
				}
			} else {
				log.info(rv.getName() + "==========仓库用户已存在.");
				result.setMessage("用户已存在.");
				result.setState(0);
				result.setObj("");
			}
		} else {
			UserInfo ui = getUserInfo(rv);
			int i = userInfoMapper.updateByPrimaryKeySelective(ui);
			if (i == 1) {
				User user = userMapper.selectByPrimaryKey(rv.getId());
				if (user != null) {
					user.setUsername(ui.getName());
					userMapper.updateByPrimaryKeySelective(user);
				}
				log.info(ui.getName() + "==========仓库用户修改成功.");
				result.setMessage("仓库用户修改成功.");
				result.setState(1);
				result.setObj("");
			} else {
				log.info(ui.getName() + "==========仓库用户修改失败.");
				result.setMessage("仓库用户修改失败.");
				result.setState(0);
				result.setObj("");
			}
		}
		return result;
	}

	/**
	 * 组装查询参数
	 * 
	 * @param rv
	 * @return
	 */
	private Map<String, String> getMap(UserInfoVo rv) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("code", rv.getCode());
		map.put("conInfo", rv.getConInfo());
		map.put("name", rv.getName());
		return map;
	}

	/**
	 * 修改仓库用户信息
	 */
	@Override
	public Result updateUserInfo(String param) {
		Result result = new Result();
		UserInfoVo rv = (UserInfoVo) JSONObject.toBean(JSONObject.fromObject(param), UserInfoVo.class);
		UserInfo ui = getUserInfo(rv);
		ui.setCreateTime(Tools.getSortDate(new Date()));
		int i = userInfoMapper.updateByPrimaryKeySelective(ui);
		if (i == 1) {
			log.info(ui.getName() + "==========仓库用户修改成功.");
			result.setMessage("仓库用户修改成功.");
			result.setState(1);
			result.setObj("");
		} else {
			log.info(ui.getName() + "==========仓库用户修改失败.");
			result.setMessage("仓库用户修改失败.");
			result.setState(0);
			result.setObj("");
		}
		return result;
	}

	/**
	 * 删除仓库人员信息
	 */
	@Override
	public Result deleteUserInfoById(String param) {
		Result result = new Result();
		UserInfoVo rv = (UserInfoVo) JSONObject.toBean(JSONObject.fromObject(param), UserInfoVo.class);
		String[] ids = rv.getId().split(";");
		for (String str : ids) {
			// userInfoMapper.deleteByUserRole(rv.getId()); // 删除用户与角色的关联
			if (str.equals("1")) {
				log.info(str + "==========id仓库超级管理员不能被删除.");
				result.setMessage("仓库超级管理员不能被删除.");
				result.setState(0);
				result.setObj("");
			} else {
				int i = userInfoMapper.deleteByPrimaryKey(str);
				if (i == 1) {
					log.info(str + "==========id仓库用户删除成功.");
					result.setMessage("仓库用户删除成功.");
					result.setState(1);
					result.setObj("");
				} else {
					log.info(str + "==========id仓库用户删除失败.");
					result.setMessage("仓库用户删除失败.");
					result.setState(0);
					result.setObj("");
				}
			}
		}
		return result;
	}

	/**
	 * 新增角色资源关联
	 */
	@Override
	public Result saveUserRoleResource(String param) {
		Result result = new Result();
		RoleResourcesKey rv = (RoleResourcesKey) JSONObject.toBean(JSONObject.fromObject(param),
				RoleResourcesKey.class);
		String[] strs = rv.getResourcesid().split(";");
		List<RoleResourcesKey> rrk = roleResourcesMapper.findByRoleId(rv.getRoleid());
		if (rrk.size() != 0) {
			// Map<String,String> map = new HashMap<String,String>();
			// map.put("roleId", rv.getRoleid());
			int i = roleResourcesMapper.deleteByRoleId(rv.getRoleid());
			for (String str : strs) {
				RoleResourcesKey rr = new RoleResourcesKey();
				System.out.println("**************"+str+"============");
				rr.setResourcesid(str);
				rr.setRoleid(rv.getRoleid());
				i = roleResourcesMapper.insertSelective(rr);
				if (i == 1) {
					log.info(rv.getResourcesid() + "==========资源分配成功.");
					result.setMessage("资源分配成功.");
					result.setState(1);
					result.setObj("");
				} else {
					log.info(rv.getResourcesid() + "==========资源分配失败.");
					result.setMessage("资源分配失败.");
					result.setState(0);
					result.setObj("");
				}
			}
		} else {
			for (String str : strs) {
				RoleResourcesKey rr = new RoleResourcesKey();
				rr.setResourcesid(str);
				rr.setRoleid(rv.getRoleid());
				int i = roleResourcesMapper.insertSelective(rr);
				if (i == 1) {
					log.info(rv.getResourcesid() + "==========资源分配成功.");
					result.setMessage("资源分配成功.");
					result.setState(1);
					result.setObj("");
				} else {
					log.info(rv.getResourcesid() + "==========资源分配失败.");
					result.setMessage("资源分配失败.");
					result.setState(0);
					result.setObj("");
				}
			}
		}
		return result;
	}

	/**
	 * 新增用户角色关联
	 */
	@Override
	public Result saveUserRole(String param) {
		Result result = new Result();
		UserRole rv = (UserRole) JSONObject.toBean(JSONObject.fromObject(param), UserRole.class);
		UserRole ur = userRoleMapper.findByUserRole(rv.getUserid());
		if (ur == null) {
			// 添加登录用户
			// String userId = Tools.getId();
			User user = new User();
			user.setId(rv.getUserid());
			user.setUsername(rv.getUsername());
			user.setEnable(Integer.valueOf(rv.getEnable()));
			userMapper.insertSelective(user);
			// 添加用户角色关联
			int i = userRoleMapper.insertSelective(rv);
			if (i == 1) {
				log.info(rv.getUserid() + "==========用户角色分配成功.");
				result.setMessage("用户角色分配成功.");
				result.setState(1);
				result.setObj("");
			} else {
				log.info(rv.getUserid() + "==========用户角色分配失败.");
				result.setMessage("用户角色分配失败.");
				result.setState(0);
				result.setObj("");
			}
		} else {
			// 这一步是确认其取消了用户的权限
			int i = userRoleMapper.deleteByUserId(rv.getUserid());
			if (i == 1) {
				i = userRoleMapper.insertSelective(rv);
				if (i == 1) {
					User user = userMapper.selectByPrimaryKey(rv.getUserid());
					if (user != null) {
						user.setEnable(Integer.valueOf(rv.getEnable()));
						user.setUsername(rv.getUsername());
						userMapper.updateByPrimaryKeySelective(user);
					}else {
						User users = new User();
						users.setEnable(Integer.valueOf(rv.getEnable()));;
						users.setUsername(rv.getUsername());
						String password = EncryptUtil.md5(rv.getPassword());
						users.setPassword(password);
						users.setId(rv.getUserid());
						userMapper.insertSelective(users);
					}
					log.info(rv.getUserid() + "==========用户角色分配成功.");
					result.setMessage("用户角色分配成功.");
					result.setState(1);
					result.setObj("");
				} else {
					log.info(rv.getUserid() + "==========用户角色分配失败.");
					result.setMessage("用户角色分配失败.");
					result.setState(0);
					result.setObj("");
				}
			} else {
				log.info(rv.getUserid() + "==========用户角色删除失败.");
				result.setMessage("用户角色删除失败.");
				result.setState(0);
				result.setObj("");
			}
		}
		return result;
	}

	@Override
	public Result findxUserRoleByUserId(String param) {
		Result result = new Result();
		UserRole rv = (UserRole) JSONObject.toBean(JSONObject.fromObject(param), UserRole.class);
		UserRole ur = userRoleMapper.findByUserRole(rv.getUserid());
		if (ur == null) {
			log.info(rv.getUserid() + "==========用户角色暂无数据.");
			result.setMessage("用户角色暂无数据.");
			result.setState(1);
			result.setObj("");
		} else {
			log.info(rv.getUserid() + "==========用户角色存在数据.");
			result.setMessage("用户角色存在数据.");
			result.setState(1);
			result.setObj(JSONObject.fromObject(ur));
		}
		return result;
	}

	@Override
	public Result findxByRoleResources(String param) {
		Result result = new Result();
		RoleResourcesKey rv = (RoleResourcesKey) JSONObject.toBean(JSONObject.fromObject(param),
				RoleResourcesKey.class);
		List<RoleResourcesKey> list = roleResourcesMapper.findByRoleId(rv.getRoleid());
		log.info(JSONArray.fromObject(list) + "==========用户角色存在数据.");
		result.setMessage("查询成功.");
		result.setState(1);
		result.setObj(JSONArray.fromObject(list));
		return result;
	}

}
