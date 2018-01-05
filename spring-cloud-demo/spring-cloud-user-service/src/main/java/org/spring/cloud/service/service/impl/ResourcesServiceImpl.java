package org.spring.cloud.service.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spring.cloud.service.mapper.stockMapper.ResourcesMapper;
import org.spring.cloud.service.model.stockPo.Resources;
import org.spring.cloud.service.model.stockVo.ResourcesVo;
import org.spring.cloud.service.service.ResourcesService;
import org.spring.cloud.service.util.Result;
import org.spring.cloud.service.util.Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service
@Transactional
public class ResourcesServiceImpl implements ResourcesService {

	private Logger log = LoggerFactory.getLogger(ResourcesServiceImpl.class);

	@Autowired
	private ResourcesMapper resourcesMapper;

	/**
	 * 根据用户Id查询对应的资源权限按钮
	 */
	@Override
	public Result saveResource(String param) {
		Result result = new Result();
		ResourcesVo rv = (ResourcesVo) JSONObject.toBean(JSONObject.fromObject(param), ResourcesVo.class);
		Resources rc = getResource(rv);
		if (rv.getId() == null) {
			Resources rsc = resourcesMapper.findByName(rc);
			if (rsc == null) {
				int i = resourcesMapper.insertSelective(rc);
				if (i == 1) {
					log.info(rc.getName() + "==========资源添加成功.");
					result.setMessage("资源添加成功.");
					result.setState(1);
					result.setObj(JSONObject.fromObject(rc));
				} else {
					log.info(rc.getName() + "==========资源添加失败.");
					result.setMessage("资源添加失败.");
					result.setState(0);
					result.setObj(JSONObject.fromObject(rc));
				}
			} else {
				log.info(rc.getName() + "==========资源名称已存在.");
				result.setMessage("资源名称已存在.");
				result.setState(0);
				result.setObj(JSONObject.fromObject(rc));
			}
		} else {
			int i = resourcesMapper.updateByPrimaryKeySelective(rc);
			if (i == 1) {
				log.info(rc.getName() + "==========资源修改成功.");
				result.setMessage("资源修改成功.");
				result.setState(1);
				result.setObj("");
			} else {
				log.info(rc.getName() + "==========资源修改失败.");
				result.setMessage("资源修改失败.");
				result.setState(0);
				result.setObj("");
			}
		}
		return result;
	}

	/**
	 * 将临时对象转化成实体
	 * 
	 * @param rv
	 * @return
	 */
	@SuppressWarnings("static-access")
	private Resources getResource(ResourcesVo rv) {
		Resources rs = new Resources();
		rs.setId(rv.getId() == null ? Tools.getId() : rv.getId());
		rs.setCreateTime(new Tools().getSortDate(new Date()));
		rs.setName(rv.getName());
		rs.setParentid(rv.getParentid() == null ? "0" : rv.getParentid());
		rs.setType(Integer.valueOf(rv.getType()));

		return rs;
	}

	/**
	 * 组装实体数据到临时对象的方法
	 * 
	 * @param rc
	 * @return
	 */
	private ResourcesVo getResourcesVo(Resources rc) {
		ResourcesVo rv = new ResourcesVo();
		rv.setCreateTime(Tools.getDates(rc.getCreateTime()));
		rv.setId(rc.getId());
		rv.setName(rc.getName());
		rv.setParentid(rc.getParentid());
		rv.setType(String.valueOf(rc.getType()));
		return rv;
	}

	/**
	 * 根据用户ID查询对应权限的资源列表，同时将按钮权限组合显示
	 */
	@Override
	public Result findResourcesByUserId(String param) {
		Result rs = new Result();
		List<ResourcesVo> list = new ArrayList<ResourcesVo>();
		Page<Resources> listResources = null;
		JSONObject json = JSONObject.fromObject(param);
		Map<String, String> map = new HashMap<String, String>();
		if (!json.get("userId").equals("null")) {
			map.put("userId", json.get("userId").toString());
		}
		Integer pageNum = Integer.valueOf(json.get("pageNum").toString());
		Integer pageSize = Integer.valueOf(json.get("pageSize").toString());
		map.put("type", "1");
		PageHelper.startPage(pageNum, pageSize); // 分页起始页大小及条数
		if (map.get("userId").equals("1")) { // 超级管理员显示资源
			listResources = resourcesMapper.selectResourceByAdmin(map);
		} else {
			listResources = resourcesMapper.selectResourceBtnByUserId(map);
		}
		if (listResources != null) {
			for (int i = 0; i < listResources.size(); i++) {
				Resources re = listResources.get(i);
				ResourcesVo rv = getResourcesVo(re);
				map.put("resourceId", re.getId());
				String btnRs = resourcesMapper.selectBtnByResource(map);
				rv.setBtnName(btnRs);
				rv.setPageNum(pageNum);
				rv.setPageSize(pageSize);
				list.add(rv);
			}
		}
		log.info("=========" + JSONArray.fromObject(list));
		Long lg = (long) 0;
		if (listResources.getTotal() < pageSize) {
			lg = (long) 1;
		} else {
			lg = listResources.getTotal() % pageSize == 0 ? listResources.getTotal() / pageSize
					: (listResources.getTotal() / pageSize) + 1;
		}
		rs.setState(1);
		rs.setMaxPage(lg);
		rs.setTotalNum(listResources.getTotal());
		rs.setPageNum(pageNum);
		rs.setPageSize(pageSize);
		rs.setObj(list);
		return rs;
	}

	/**
	 * 修改资源
	 */
	@Override
	public Result updateResource(String param) {
		Result result = new Result();
		ResourcesVo resourceVo = (ResourcesVo) JSONObject.toBean(JSONObject.fromObject(param), ResourcesVo.class);
		Resources resource = getResource(resourceVo);
		int i = resourcesMapper.updateByPrimaryKeySelective(resource);
		if (i == 1) {
			log.info(resource.getName() + "==========资源修改成功.");
			result.setMessage("资源修改成功.");
			result.setState(1);
			result.setObj(JSONObject.fromObject(resource));
		} else {
			log.info(resource.getName() + "==========资源修改失败.");
			result.setMessage("资源修改失败.");
			result.setState(0);
			result.setObj(JSONObject.fromObject(resource));
		}
		return result;
	}

	/**
	 * 删除资源
	 */
	@Override
	public Result delResource(String param) {
		Result result = new Result();
		ResourcesVo resourceVo = (ResourcesVo) JSONObject.toBean(JSONObject.fromObject(param), ResourcesVo.class);
		String[] ids = resourceVo.getId().split(",");
		for (String str : ids) {
			// 实际删除资源文件的节点
			int i = resourcesMapper.deleteByPrimaryKey(str);
			if (i == 1) {
				i = resourcesMapper.deleteByParentId(str);
				log.info(str + "==========资源删除成功.");
				result.setMessage("资源删除成功.");
				result.setState(1);
				result.setObj("");
			} else {
				log.info(str + "==========资源删除失败.");
				result.setMessage("资源删除失败.");
				result.setState(0);
				result.setObj("");
			}
		}
		return result;
	}

	/**
	 * 查询权限树形列表
	 */
	@Override
	public Result findResourcesByRole(String param) {
		Result rs = new Result();
		List<Resources> listResources = null;
		JSONObject json = JSONObject.fromObject(param);
		Map<String, String> map = new HashMap<String, String>();
		if (!json.get("userId").equals("null")) {
			map.put("userId", json.get("userId").toString());
		}
		map.put("type", "1");
		if (map.get("userId").equals("1")) { // 超级管理员显示资源
			listResources = resourcesMapper.selectResourceByAdmin(map);
		} else {
			listResources = resourcesMapper.selectResourceBtnByUserId(map);
		}
		rs = getResource(listResources); // 递归查出所有的当前资源节点
		log.info(rs + "==========资源列表成功.");
		rs.setMessage("资源列表查询结果成功.");
		rs.setTotalNum((long) listResources.size());
		rs.setState(1);
		return rs;
	}

	/**
	 * 处理权限资源文件
	 * 
	 * @param list
	 * @return
	 */
	private Result getResource(List<Resources> list) {
		Result rs = new Result();
		List<ResourcesVo> lists = new ArrayList<ResourcesVo>();
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				Resources rss = list.get(i);
				if (rss.getType() == 1) {
					ResourcesVo rv = new ResourcesVo();
					rv.setName(rss.getName());
					rv.setId(rss.getId());
					rv.setParentid(rss.getParentid());
					rv.setType(String.valueOf(rss.getType()));
					rv.setList(getResources(rss, list));
					lists.add(rv);
				}
			}
		}
		rs.setObj(lists);
		return rs;
	}

	/**
	 * 递归当前所有的父节点
	 * 
	 * @param rss
	 * @param list
	 * @return
	 */
	private List<Resources> getResources(Resources rss, List<Resources> list) {
		List<Resources> listRs = new ArrayList<Resources>();
		for (Resources r : list) {
			if (r.getType() == 2 && r.getParentid().equals(rss.getId())) {
				listRs.add(r);
				getResources(r, list);
			}
		}
		return listRs;
	}

	@Override
	public Result findResourcesByType(String param) {
		Result rs = new Result();
		List<Resources> listResources = null;
		JSONObject json = JSONObject.fromObject(param);
		Map<String, String> map = new HashMap<String, String>();
		if (!json.get("userId").equals("null")) {
			if (json.get("userId").toString().equals("1")) {
				map.put("userId", null);
			} else {
				map.put("userId", json.get("userId").toString());
			}
		}
		
		if(json.get("userId").toString().equals("1")) {
		listResources = resourcesMapper.selectResourceBtnByType(map);
		}else {
			listResources = resourcesMapper.selectResourceBtnByTypes(map);
		}
		log.info(rs + "==========资源列表成功.");
		rs.setMessage("资源列表查询结果成功.");
		rs.setTotalNum((long) listResources.size());
		rs.setState(1);
		rs.setObj(listResources);
		return rs;
	}

	/**
	 * 查询按钮资源
	 */
	@Override
	public Result selectBtnByResource(String param) {
		Result result = new Result();
		List<Resources> list = new ArrayList<Resources>();
		ResourcesVo resourceVo = (ResourcesVo) JSONObject.toBean(JSONObject.fromObject(param), ResourcesVo.class);
		Map<String, String> map = new HashMap<String, String>();
		map.put("userId", resourceVo.getUserId());
		map.put("resourceId", resourceVo.getId());
		if (resourceVo.getUserId().equals("1")) {
			list = resourcesMapper.selectBtnByAdmin(map);
		} else {
			list = resourcesMapper.selectBtnByUser(map);
		}
		log.info(result + "==========资源列表成功.");
		result.setMessage("资源列表查询结果成功.");
		result.setTotalNum((long) list.size());
		result.setState(1);
		result.setObj(list);
		return result;
	}

}
