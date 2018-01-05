package spring_cloud_user_server.spring_cloud_user_server.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.sf.json.JSONObject;
import spring_cloud_user_server.spring_cloud_user_server.bean.UserInfo;
import spring_cloud_user_server.spring_cloud_user_server.mapper.UserInfoMapper;
import spring_cloud_user_server.spring_cloud_user_server.service.UserManageService;
import spring_cloud_user_server.spring_cloud_user_server.util.PageUtil;
import spring_cloud_user_server.spring_cloud_user_server.util.Result;
import spring_cloud_user_server.spring_cloud_user_server.util.Tools;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Service
@Transactional
public class UserManageServiceImpl implements UserManageService {

	@Autowired
	private UserInfoMapper userInfoMapper;

	/**
	 * 添加或修改用户基础信息
	 */
	@Override
	public Result saveOrUpdateUser(String param) {
		Result result = new Result();
		UserInfo rv = (UserInfo) JSONObject.toBean(JSONObject.fromObject(param), UserInfo.class);
		int i = 0;
		if (rv.getId() == null) {
			rv.setId(Tools.getId());
			rv.setCreateTime(Tools.getSortDate(new Date()));
			i = userInfoMapper.insertSelective(rv);
			if (i == 1) {
				result.setMessage("添加成功.");
				result.setState(i);
			} else {
				result.setState(i);
				result.setMessage("添加失败.");
			}
		} else {
			i = userInfoMapper.updateByPrimaryKeySelective(rv);
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

	/**
	 * 批量删除用户基础信息
	 */
	@Override
	public Result deleteUser(String param) {
		Result result = new Result();
		UserInfo rv = (UserInfo) JSONObject.toBean(JSONObject.fromObject(param), UserInfo.class);
		int i = userInfoMapper.deleteByPrimaryKey(rv.getId().split(";"));
		if (i > 1) {
			result.setState(1);
			result.setMessage("删除成功.");
		} else {
			result.setState(0);
			result.setMessage("删除失败.");
		}
		return result;
	}

	/**
	 * 分页查询用户信息
	 */
	@Override
	public Result findByConditionUser(String param,PageUtil pages) {
		Result result = new Result();
		Map<String, String> map = new HashMap<String, String>(); // 要带的条件参数
		UserInfo rv = (UserInfo) JSONObject.toBean(JSONObject.fromObject(param), UserInfo.class);
		if (rv.getName() == null) {
			map.put("name", null);
		} else {
			map.put("name", rv.getName());
		}
		if (rv.getState() == null) {
			map.put("state", rv.getState());
		} else {
			map.put("state", null);
		}
		if (rv.getCnName() == null) {
			map.put("cnName", null);
		} else {
			map.put("cnName", rv.getCnName());
		}
		// 分页起始页大小及条数
		PageHelper.startPage(pages.getPageNum(), pages.getPageSize());
		Page<UserInfo> page = userInfoMapper.findUserInfoByCondition(map);
		//下面是分页所需要的参数
		Long lg = (long) 0;
		if (page.getTotal() < pages.getPageSize()) {
			lg = (long) 1;
		} else {
			lg = page.getTotal() % pages.getPageSize() == 0 ? page.getTotal() / pages.getPageSize()
					: (page.getTotal() / pages.getPageSize()) + 1;
		}
		result.setState(1);
		result.setMessage("用户基本信息查询结果成功.");
		result.setMaxPage(lg);
		result.setTotalNum(page.getTotal());
		result.setPageNum(pages.getPageNum());
		result.setPageSize(pages.getPageSize());
		result.setObj(page);
		return result;
	}

}
