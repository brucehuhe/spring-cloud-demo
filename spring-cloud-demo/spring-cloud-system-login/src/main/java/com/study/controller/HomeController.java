package com.study.controller;

import com.study.model.Resources;
import com.study.util.Result;
import com.vo.User;

import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * Created by bruce http://localhost:8080/swagger-ui.html
 */
@CrossOrigin
@RestController
public class HomeController {
	
	/**
	 * 用户登录
	 * @param param
	 * @return
	 */
	@RequestMapping(value="/login")
	public Result login(@RequestParam String param){
		Result rs = new Result();
		JSONObject json = JSONObject.fromObject(param);
		String username = json.get("username").toString();
		String password = json.get("password").toString();
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token=new UsernamePasswordToken(username,password);
        try {
            subject.login(token);
            //获取资源文件
            User uservo=(User)subject.getPrincipal();
            rs.setState(1);
            rs.setMessage("登录成功");
//            rs = getResource(uservo.getList());
            rs.setObj(uservo.getList());
            return rs;
        }catch (LockedAccountException lae) {
            token.clear();
            rs.setState(0);
            rs.setMessage("用户已经被锁定不能登录，请与管理员联系！");
            return rs;
        } catch (AuthenticationException e) {
            token.clear();
            rs.setState(0);
            rs.setMessage("用户或密码不正确！");
            return rs;
        }
    }
/**
 * 处理权限资源文件
 * @param list
 * @return
 */
private Result getResource(List<Resources> list) {
	Result rs = new Result();
	List<Resources> listRs = new ArrayList<Resources>();
	if(list != null) {
    	for(int i=0;i<list.size();i++) {
    		Resources rss = list.get(i);
    		if(rss.getType() == 0) {
    			rs.setName(rss.getName());
    			listRs.add(rss);
    			listRs = getResources(rss,list);
    		}
    	}
    }
	rs.setObj(listRs);
	return rs;
}
/**
 * 递归当前所有的父节点
 * @param rss
 * @param list
 * @return
 */
private List<Resources> getResources(Resources rss,List<Resources> list) {
	List<Resources> listRs = new ArrayList<Resources>();
	for(Resources r:list) {
		if(r.getType() == 1 && r.getParentid().equals(rss.getId())) {
			listRs.add(r);
			getResources(r,list);
		}
	}
	return listRs;
}
}
