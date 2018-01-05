package org.spring.cloud.service.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spring.cloud.service.mapper.stockMapper.ResourcesMapper;
import org.spring.cloud.service.mapper.stockMapper.RoleMapper;
import org.spring.cloud.service.model.stockPo.Resources;
import org.spring.cloud.service.model.stockPo.Role;
import org.spring.cloud.service.model.stockVo.RoleVo;
import org.spring.cloud.service.service.RoleService;
import org.spring.cloud.service.util.Result;
import org.spring.cloud.service.util.Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import net.sf.json.JSONObject;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

	private Logger log = LoggerFactory.getLogger(RoleServiceImpl.class);

	@Autowired
	private RoleMapper roleMapper;
	
	@Autowired
	private ResourcesMapper resourcesMapper;

	@SuppressWarnings("static-access")
	@Override
	public Result saveRole(String param) {
		Result result = new Result();
		RoleVo rv = (RoleVo) JSONObject.toBean(JSONObject.fromObject(param), RoleVo.class);
		Role rl = new Role();
		rl.setId(rv.getId() == null ? Tools.getId() : rv.getId());
		rl.setRoledesc(rv.getRoledesc());
		rl.setCreateTime(new Tools().getSortDate(new Date()));
		Role rr = roleMapper.findByName(rv.getRoledesc());
		if (rv.getId() == null) {
			if (rr == null) {
				int i = roleMapper.insertSelective(rl);
				if (i == 1) {
					log.info(rl.getRoledesc() + "==========角色添加成功.");
					result.setMessage("角色添加成功.");
					result.setState(1);
					result.setObj("");
				} else {
					log.info(rl.getRoledesc() + "==========角色添加失败.");
					result.setMessage("角色添加失败.");
					result.setState(0);
					result.setObj("");
				}
			} else {
				log.info(rl.getRoledesc() + "==========角色已存在.");
				result.setMessage("角色已存在.");
				result.setState(0);
				result.setObj("");
			}
		} else {
			if (rr == null) {
				int i = roleMapper.updateByPrimaryKeySelective(rl);
				if (i == 1) {
					log.info(rl.getRoledesc() + "==========角色修改成功.");
					result.setMessage("角色修改成功.");
					result.setState(1);
					result.setObj(JSONObject.fromObject(rl));
				} else {
					log.info(rl.getRoledesc() + "==========角色修改失败.");
					result.setMessage("角色修改失败.");
					result.setState(0);
					result.setObj(JSONObject.fromObject(rl));
				}
			} else {
				log.info(rl.getRoledesc() + "==========角色已存在.");
				result.setMessage("角色已存在.");
				result.setState(0);
				result.setObj("");
			}
		}
		return result;
	}

	@SuppressWarnings("static-access")
	@Override
	public Result findByRole(String param) {
		List<RoleVo> listRoleVo = new ArrayList<RoleVo>();
		Result result = new Result();
		RoleVo rv = (RoleVo) JSONObject.toBean(JSONObject.fromObject(param), RoleVo.class);
		Role rl = new Role();
		rl.setRoledesc(rv.getRoledesc() == null ? null : rv.getRoledesc());
		// 按时间和日期查询
		PageHelper.startPage(rv.getPageNum(), rv.getPageSize()); // 分页起始页大小及条数
		Page<Role> page = roleMapper.findByPageRole(rl.getRoledesc());
		for (int i = 0; i < page.size(); i++) {
			Role role = page.get(i);
			Map<String,String> map = new HashMap<String,String>();
			map.put("roleId", role.getId());
			String resources = resourcesMapper.findResourcesByRoleId(map);
			RoleVo r = new RoleVo();
			r.setId(role.getId());
			r.setRoledesc(role.getRoledesc());
			r.setCreateTime(new Tools().getDates(role.getCreateTime()));
			r.setResources(resources);
			r.setPageNum(rv.getPageNum());
			r.setPageSize(rv.getPageSize());
			listRoleVo.add(r);
		}
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
		result.setObj(listRoleVo);
		return result;
	}

	@SuppressWarnings("static-access")
	@Override
	public Result updateByRole(String param) {
		Result result = new Result();
		RoleVo rv = (RoleVo) JSONObject.toBean(JSONObject.fromObject(param), RoleVo.class);
		Role rl = new Role();
		rl.setId(rv.getId() == null ? Tools.getId() : rv.getId());
		rl.setRoledesc(rv.getRoledesc());
		rl.setCreateTime(new Tools().getSortDate(new Date()));
		int i = roleMapper.updateByPrimaryKeySelective(rl);
		if (i == 1) {
			log.info(rl.getRoledesc() + "==========角色修改成功.");
			result.setMessage("角色修改成功.");
			result.setState(1);
			result.setObj(JSONObject.fromObject(rl));
		} else {
			log.info(rl.getRoledesc() + "==========角色修改失败.");
			result.setMessage("角色修改失败.");
			result.setState(0);
			result.setObj(JSONObject.fromObject(rl));
		}
		return result;
	}

	@Override
	public Result delByRole(String param) {
		System.out.println("=================" + param + "******************");
		Result result = new Result();
		RoleVo rv = (RoleVo) JSONObject.toBean(JSONObject.fromObject(param), RoleVo.class);
		String[] ids = rv.getId().split(";");
		for (String str : ids) {
			Role role = roleMapper.selectByPrimaryKey(str);
			if (role.getRoledesc().equals("超级管理员")) {
				log.info(str + "==========超级管理员角色不能被删除.");
				result.setMessage("角色删除失败.");
				result.setState(0);
				result.setObj("");
			} else {
				int i = roleMapper.deleteByPrimaryKey(str);
				if (i == 1) {
					log.info(str + "==========id角色删除成功.");
					result.setMessage("角色删除成功.");
					result.setState(1);
					result.setObj("");
				} else {
					log.info(str + "==========id角色删除失败.");
					result.setMessage("角色删除失败.");
					result.setState(0);
					result.setObj("");
				}
			}
		}
		return result;
	}

}
