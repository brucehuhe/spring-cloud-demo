package org.spring.cloud.order.service.service.impl;

import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spring.cloud.order.service.mapper.PrintDeviceMapper;
import org.spring.cloud.order.service.model.PrintDevice;
import org.spring.cloud.order.service.service.PrintDeviceService;
import org.spring.cloud.order.service.util.Result;
import org.spring.cloud.order.service.util.Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import net.sf.json.JSONObject;

/**
 * 管理打印设备实现类
 * 
 * @author Administrator 2017年12月18日
 */
@Service
@Transactional
public class PrintDeviceServiceImpl implements PrintDeviceService {
	// 保存日志
	private Logger log = LoggerFactory.getLogger(PrintDeviceServiceImpl.class);
	// 保存数据到redis中
//	@Autowired
//	private RedisUtils redisUtils;
	// 调用数据库打印机接口
	@Autowired
	private PrintDeviceMapper printDeviceMapper;

	/**
	 * 新增打印设备
	 */
	@Override
	public Result savePrintDevice(String param) {
		Result rs = new Result();
		PrintDevice pdv = (PrintDevice) JSONObject.toBean(JSONObject.fromObject(param), PrintDevice.class);
		PrintDevice pd = printDeviceMapper.selectBySn(pdv.getPrintSn());
		if (pd == null) {
			PrintDevice pdm = new PrintDevice();
			pdm.setId(Tools.getId());
			pdm.setCreateTime(Tools.getSortDate(new Date()));
			int i = printDeviceMapper.insertSelective(pdm);
			if (i == 1) {
				rs.setState(1);
				rs.setMessage("数据添加成功.");
				rs.setObj(i);
				log.info("=================数据添加成功.===============");
			} else {
				rs.setState(0);
				rs.setMessage("数据添加失败.");
				rs.setObj(i);
				log.info("=================数据添加失败.===============");
			}
		} else {
			rs.setState(0);
			rs.setMessage("数据已存在，请重新填写!");
			rs.setObj(null);
			log.info("=============数据已存在，请重新填写!===============");
		}
		return rs;
	}

	/**
	 * 修改打印设备
	 */
	@Override
	public Result updatePrintDevice(String param) {
		Result rs = new Result();
		PrintDevice pdv = (PrintDevice) JSONObject.toBean(JSONObject.fromObject(param), PrintDevice.class);
		pdv.setCreateTime(Tools.getSortDate(new Date()));
		pdv.setState(pdv.getState());
		int i = printDeviceMapper.updateByPrimaryKey(pdv);
		if (i == 1) {
			rs.setState(1);
			rs.setMessage("数据修改成功.");
			rs.setObj(i);
			log.info("=================数据修改成功.===============");
		} else {
			rs.setState(0);
			rs.setMessage("数据修改失败.");
			rs.setObj(i);
			log.info("=================数据修改失败.===============");
		}
		return rs;
	}

	/**
	 * 删除打印设备
	 */
	@Override
	public Result deletePrintDevice(String param) {
		Result rs = new Result();
		PrintDevice pdv = (PrintDevice) JSONObject.toBean(JSONObject.fromObject(param), PrintDevice.class);
		int i = printDeviceMapper.deleteByPrimaryKey(pdv.getId());
		if (i == 1) {
			rs.setState(1);
			rs.setMessage("数据删除成功.");
			rs.setObj(i);
			log.info("=================数据删除成功.===============");
		} else {
			rs.setState(0);
			rs.setMessage("数据删除失败.");
			rs.setObj(i);
			log.info("=================数据删除失败.===============");
		}
		return rs;
	}

	/**
	 * 查询打印设备
	 */
	@Override
	public Result findPrintDevice(String param) {
		Result rs = new Result();
		PrintDevice pdv = (PrintDevice) JSONObject.toBean(JSONObject.fromObject(param), PrintDevice.class);
		List<PrintDevice> listPrintDevice = printDeviceMapper.findByPrintDeviceList(pdv);
		rs.setState(1);
		rs.setMessage("数据查询成功.");
		rs.setObj(listPrintDevice);
		log.info("=================数据查询成功.===============");
		return rs;
	}

}
