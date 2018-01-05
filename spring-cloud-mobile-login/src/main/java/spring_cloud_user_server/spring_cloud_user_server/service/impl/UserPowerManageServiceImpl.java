package spring_cloud_user_server.spring_cloud_user_server.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import net.sf.json.JSONObject;
import spring_cloud_user_server.spring_cloud_user_server.bean.Resources;
import spring_cloud_user_server.spring_cloud_user_server.bean.Role;
import spring_cloud_user_server.spring_cloud_user_server.bean.RoleResourcesKey;
import spring_cloud_user_server.spring_cloud_user_server.bean.UserInfo;
import spring_cloud_user_server.spring_cloud_user_server.bean.UserRole;
import spring_cloud_user_server.spring_cloud_user_server.mapper.ResourcesMapper;
import spring_cloud_user_server.spring_cloud_user_server.mapper.RoleMapper;
import spring_cloud_user_server.spring_cloud_user_server.mapper.RoleResourcesMapper;
import spring_cloud_user_server.spring_cloud_user_server.mapper.UserRoleMapper;
import spring_cloud_user_server.spring_cloud_user_server.service.UserPowerManageService;
import spring_cloud_user_server.spring_cloud_user_server.util.PageUtil;
import spring_cloud_user_server.spring_cloud_user_server.util.Result;
import spring_cloud_user_server.spring_cloud_user_server.util.Tools;

@Service
@Transactional
public class UserPowerManageServiceImpl implements UserPowerManageService {

	@Autowired
	private RoleMapper roleMapper;

	@Autowired
	private UserRoleMapper userRoleMapper;

	@Autowired
	private ResourcesMapper resourcesMapper;

	@Autowired
	private RoleResourcesMapper roleResourcesMapper;

	@Override
	public Result saveOrUpdateRole(String param) {
		Result result = new Result();
		Map<String, String> map = new HashMap<String, String>(); // 要带的条件参数
		Role role = (Role) JSONObject.toBean(JSONObject.fromObject(param), Role.class);
		if (role.getId() == null) {
			map.put("roledesc", role.getRoledesc());
			Page<Role> obj = roleMapper.findRoleByCondition(map);
			if (obj.size() == 0) {
				role.setId(Tools.getId());
				role.setCreateTime(Tools.getDates(new Date()));
				int i = roleMapper.insertSelective(role);
				if (i == 1) {
					result.setMessage("添加成功.");
					result.setState(i);
				} else {
					result.setState(i);
					result.setMessage("添加失败.");
				}
			} else {
				result.setState(0);
				result.setMessage("已存在.");
			}
		} else {
			int i = roleMapper.updateByPrimaryKeySelective(role);
			if (i == 1) {
				result.setState(i);
				result.setMessage("修改成功.");
			} else {
				result.setState(i);
				result.setMessage("修改失败.");
			}
		}
		return result;
	}

	@Override
	public Result deleteByRoleId(String param) {
		Result result = new Result();
		Role role = (Role) JSONObject.toBean(JSONObject.fromObject(param), Role.class);
		int i = roleMapper.deleteByPrimaryKey(role.getId().split(";"));
		if (i > 1) {
			result.setState(1);
			result.setMessage("删除成功.");
		} else {
			result.setState(0);
			result.setMessage("删除失败.");
		}
		return result;
	}

	@Override
	public Result findRoleByCondition(String param, PageUtil page) {
		Result result = new Result();
		Map<String, String> map = new HashMap<String, String>(); // 要带的条件参数
		Role role = (Role) JSONObject.toBean(JSONObject.fromObject(param), Role.class);
		if (role.getRoledesc() == null) {
			map.put("roledesc", null);
		} else {
			map.put("roledesc", role.getRoledesc());
		}
		if (role.getCreateTime() == null) {
			map.put("createTime", null);
		} else {
			map.put("createTime", role.getCreateTime());
		}
		// 分页起始页大小及条数
		PageHelper.startPage(page.getPageNum(), page.getPageSize());
		Page<Role> pages = roleMapper.findRoleByCondition(map);
		// 下面是分页所需要的参数
		Long lg = (long) 0;
		if (pages.getTotal() < page.getPageSize()) {
			lg = (long) 1;
		} else {
			lg = pages.getTotal() % page.getPageSize() == 0 ? pages.getTotal() / page.getPageSize()
					: (pages.getTotal() / page.getPageSize()) + 1;
		}
		result.setState(1);
		result.setMessage("用户基本信息查询结果成功.");
		result.setMaxPage(lg);
		result.setTotalNum(pages.getTotal());
		result.setPageNum(page.getPageNum());
		result.setPageSize(page.getPageSize());
		result.setObj(pages);
		return result;
	}

	@Override
	public Result saveUserRole(String param) {
		Result result = new Result();
		UserRole ur = (UserRole) JSONObject.toBean(JSONObject.fromObject(param), UserRole.class);
		List<UserRole> listUserRole = userRoleMapper.findByUserId(ur.getUserid());
		if (listUserRole.size() == 0) {
			int i = userRoleMapper.insertSelective(ur);
			if (i == 1) {
				result.setMessage("添加成功.");
				result.setState(i);
			} else {
				result.setState(i);
				result.setMessage("添加失败.");
			}
		} else {
			int i = userRoleMapper.deleteByUserId(ur.getUserid());// 先进行删除再去添加
			i = userRoleMapper.insertSelective(ur);
			if (i == 1) {
				result.setMessage("添加成功.");
				result.setState(i);
			} else {
				result.setState(i);
				result.setMessage("添加失败.");
			}
		}
		return result;
	}

	@Override
	public Result saveOrUpdateResource(String param) {
		Result result = new Result();
		Map<String, String> map = new HashMap<String, String>(); // 要带的条件参数
		Resources resources = (Resources) JSONObject.toBean(JSONObject.fromObject(param), Resources.class);
		if (resources.getId() == null) {
			map.put("resourceName", resources.getName());
			Page<Resources> listResources = resourcesMapper.findByResources(map);
			if (listResources.size() == 0) {
				resources.setId(Tools.getId());
				resources.setCreateTime(Tools.getDates(new Date()));
				int i = resourcesMapper.insertSelective(resources);
				if (i == 1) {
					result.setMessage("添加成功.");
					result.setState(i);
				} else {
					result.setState(i);
					result.setMessage("添加失败.");
				}
			} else {
				result.setState(0);
				result.setMessage("已存在.");
			}
		} else {
			int i = resourcesMapper.updateByPrimaryKeySelective(resources);
			if (i == 1) {
				result.setState(i);
				result.setMessage("修改成功.");
			} else {
				result.setState(i);
				result.setMessage("修改失败.");
			}
		}
		return result;
	}

	@Override
	public Result deleteByResourceId(String param) {
		Result result = new Result();
		Resources resources = (Resources) JSONObject.toBean(JSONObject.fromObject(param), Resources.class);
		int i = resourcesMapper.deleteByPrimaryKey(resources.getId().split(";"));
		if (i > 1) {
			result.setState(1);
			result.setMessage("删除成功.");
		} else {
			result.setState(0);
			result.setMessage("删除失败.");
		}
		return result;
	}

	@Override
	public Result findResourceByCondition(String param, PageUtil page) {
		Result result = new Result();
		Map<String, String> map = new HashMap<String, String>(); // 要带的条件参数
		Resources resources = (Resources) JSONObject.toBean(JSONObject.fromObject(param), Resources.class);
		if (resources.getName() == null) {
			map.put("userId", null);
		} else {
			map.put("userId", resources.getUserId());
		}
		if (resources.getName() == null) {
			map.put("resourceName", null);
		} else {
			map.put("resourceName", resources.getName());
		}
		if (resources.getCreateTime() == null) {
			map.put("createTime", null);
		} else {
			map.put("createTime", resources.getCreateTime());
		}
		// 分页起始页大小及条数
		PageHelper.startPage(page.getPageNum(), page.getPageSize());
		Page<Resources> pages;
		if(map.get("userId") != null) {
			pages = resourcesMapper.findByResources(map);
		}else {
			pages = resourcesMapper.findResourcesByAdmin(map);
		}
		// 下面是分页所需要的参数
		Long lg = (long) 0;
		if (pages.getTotal() < page.getPageSize()) {
			lg = (long) 1;
		} else {
			lg = pages.getTotal() % page.getPageSize() == 0 ? pages.getTotal() / page.getPageSize()
					: (pages.getTotal() / page.getPageSize()) + 1;
		}
		result.setState(1);
		result.setMessage("资源信息查询结果成功.");
		result.setMaxPage(lg);
		result.setTotalNum(pages.getTotal());
		result.setPageNum(page.getPageNum());
		result.setPageSize(page.getPageSize());
		result.setObj(pages);
		return result;
	}

	@Override
	public Result saveRoleResource(String param) {
		Result result = new Result();
		int i = 0;
		RoleResourcesKey rr = (RoleResourcesKey) JSONObject.toBean(JSONObject.fromObject(param), RoleResourcesKey.class);
		List<RoleResourcesKey> list = roleResourcesMapper.findByRoleId(rr.getRoleid());
		String[] strs = rr.getResourcesid().split(";");
		if(list.size() == 0) {
			for(String str:strs) {
				RoleResourcesKey rrk = new RoleResourcesKey();
				rrk.setResourcesid(str);
				rrk.setRoleid(rr.getRoleid());
				i = roleResourcesMapper.insertSelective(rrk);
			}
		}else {
			roleResourcesMapper.deleteByRoleId(rr.getRoleid());
			for(String str:strs) {
				RoleResourcesKey rrk = new RoleResourcesKey();
				rrk.setResourcesid(str);
				rrk.setRoleid(rr.getRoleid());
				i = roleResourcesMapper.insertSelective(rrk);
			}
		}
		if (i == 1) {
			result.setState(1);
			result.setMessage("资源添加成功.");
		} else {
			result.setState(0);
			result.setMessage("资源添加失败.");
		}
		return result;
	}

}
