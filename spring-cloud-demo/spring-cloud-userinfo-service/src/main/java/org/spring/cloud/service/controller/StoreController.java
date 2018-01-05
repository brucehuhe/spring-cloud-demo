package org.spring.cloud.service.controller;

import java.util.List;

import org.spring.cloud.service.model.stockPo.Store;
import org.spring.cloud.service.service.StoreService;
import org.spring.cloud.service.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONObject;

/**
 * 用户访问层 ，swagger2 访问地址：http://localhost:8082/swagger-ui.html
 * @author Administrator
 *
 */
@RestController
@CrossOrigin
public class StoreController {
	
	@Autowired
	private StoreService storeservice ;
	
//	@Autowired
//    RedisTemplate redisTemplate;
//    
//    @Autowired
//    StringRedisTemplate stringRedisTemplate;
	
  		
	@ApiOperation(value = "1.1 获取所有商户信息 ", notes = "简单的请求")  
	@RequestMapping(value="/findByStore")
	@ResponseBody
	public Result findByStore(String param){
		return storeservice.findByStore(param);	
	}
	
	@ApiOperation(value = "1.3 新增商户信息", notes = "简单的请求")  
	@RequestMapping(value="/saveStore")
	@ResponseBody
	public Result saveStore(String param){
		return storeservice.saveStore(param);
	}

    
	@ApiOperation(value = "1.4 根据Id变更商户信息", notes = "简单的请求")  
	@RequestMapping(value="/updateStore")
	@ResponseBody
	public Result updateStore(String param){
		return storeservice.updateStore(param);			
	}
	
	@ApiOperation(value = "1.5 根据Id删除商户信息", notes = "简0单的请求")  
	@RequestMapping(value="/deleteStoreById")
	@ResponseBody
	public Result deleteStoreById(String param){
		return storeservice.deleteStoreById(param);
	}
}
