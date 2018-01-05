package org.spring.cloud.service.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spring.cloud.service.mapper.stockMapper.StoreMapper;
import org.spring.cloud.service.model.stockPo.Store;
import org.spring.cloud.service.service.StoreService;
import org.spring.cloud.service.util.Result;
import org.spring.cloud.service.util.Tools;
import org.spring.cloud.service.vo.StoreVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @author
 *
 */
@Service
@Transactional
public class StoreServiceImpl implements StoreService {

	private Logger log = LoggerFactory.getLogger(StoreServiceImpl.class);

	@Autowired
	private StoreMapper storeMapper;

	/**
	 * 分页且按条件查询出对应的仓库
	 */
	@Override
	public Result findByStore(String param) {
		Result result = new Result();
		List<StoreVo> list = new ArrayList<StoreVo>();
		StoreVo rv = (StoreVo) JSONObject.toBean(JSONObject.fromObject(param), StoreVo.class);
		Store store = getStore(rv);
		PageHelper.startPage(rv.getPageNum(), rv.getPageSize());
		Page<Store> page = storeMapper.findByStore(store);
		for (int i = 0; i < page.size(); i++) {
			Store st = page.get(i);
			StoreVo sv = getStoreVo(st);
			sv.setPageNum(rv.getPageNum());
			sv.setPageSize(rv.getPageSize());
			list.add(sv);
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
	 * 将实体转化成临时对象
	 * 
	 * @param st
	 * @return
	 */
	private StoreVo getStoreVo(Store st) {
		StoreVo storeVo = new StoreVo();
		storeVo.setAddress(st.getAddress());
		storeVo.setCode(st.getCode());
		storeVo.setCon(st.getCon());
		storeVo.setCreateTime(Tools.getDates(st.getCreateTime()));
		storeVo.setId(st.getId());
		storeVo.setName(st.getName());
		storeVo.setPicPath(st.getPicPath());
		storeVo.setState(st.getState());
		return storeVo;
	}

	/**
	 * 新增智能超市
	 */

	@Override
	public Result saveStore(String param) {
		Result result = new Result();
		StoreVo rv = (StoreVo) JSONObject.toBean(JSONObject.fromObject(param), StoreVo.class);
		Store store = getStore(rv);
		store.setCreateTime(Tools.getSortDate(new Date()));
		int i = storeMapper.insertSelective(store);
		if (i == 1) {
			log.info(rv.getName() + "==========店铺添加成功.");
			result.setMessage("店铺添加成功.");
			result.setState(1);
			result.setObj("");
		} else {
			log.info(rv.getName() + "==========店铺添加失败.");
			result.setMessage("店铺添加失败.");
			result.setState(0);
			result.setObj("");
		}
		return result;
	}

	/**
	 * 临时对象转化成实体对象
	 * 
	 * @param rv
	 * @return
	 */
	private Store getStore(StoreVo rv) {
		Store store = new Store();
		store.setAddress(rv.getAddress());
		store.setCode(rv.getCode());
		store.setCon(rv.getCon());
		store.setId(rv.getId() == null ? Tools.getId() : rv.getId());
		store.setName(rv.getName());
		store.setPicPath(rv.getPicPath());
		store.setState(rv.getState());
		return store;
	}

	/**
	 * 修改超市名称
	 */
	@Override
	public Result updateStore(String param) {
		Result result = new Result();
		StoreVo rv = (StoreVo) JSONObject.toBean(JSONObject.fromObject(param), StoreVo.class);
		Store store = getStore(rv);
		store.setCreateTime(Tools.getSortDate(new Date()));
		int i = storeMapper.updateByPrimaryKeySelective(store);
		if (i == 1) {
			log.info(rv.getName() + "==========店铺修改成功.");
			result.setMessage("店铺修改成功.");
			result.setState(1);
			result.setObj("");
		} else {
			log.info(rv.getName() + "==========店铺修改失败.");
			result.setMessage("店铺修改失败.");
			result.setState(0);
			result.setObj("");
		}
		return result;
	}

	/**
	 * 删除店铺名称
	 */
	@Override
	public Result deleteStoreById(String param) {
		Result result = new Result();
		StoreVo rv = (StoreVo) JSONObject.toBean(JSONObject.fromObject(param), StoreVo.class);
		String[] ids = rv.getId().split(",");
		for (String str : ids) {
			int i = storeMapper.deleteByPrimaryKey(str);
			if (i == 1) {
				log.info(str + "==========店铺删除成功.");
				result.setMessage("店铺删除成功.");
				result.setState(1);
				result.setObj("");
			} else {
				log.info(str + "==========店铺删除失败.");
				result.setMessage("店铺删除失败.");
				result.setState(0);
				result.setObj("");
			}
		}
		return result;
	}
}
