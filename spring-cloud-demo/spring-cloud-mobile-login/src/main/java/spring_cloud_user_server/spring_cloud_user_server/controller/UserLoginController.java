package spring_cloud_user_server.spring_cloud_user_server.controller;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import net.sf.json.JSONObject;
import spring_cloud_user_server.spring_cloud_user_server.bean.Resources;
import spring_cloud_user_server.spring_cloud_user_server.bean.User;
import spring_cloud_user_server.spring_cloud_user_server.service.UserService;
import spring_cloud_user_server.spring_cloud_user_server.util.Result;

@RestController
@CrossOrigin
public class UserLoginController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/login")
    @ResponseBody
	public Result login(String param){
		Result rs = new Result();
		JSONObject json = JSONObject.fromObject(param);
		String username = json.get("username").toString();
		String password = json.get("password").toString();
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token=new UsernamePasswordToken(username,password);
        try {
            subject.login(token);
            //获取资源文件
            User user=(User)subject.getPrincipal();
            List<Resources> list = userService.getResource(user);
            user.setList(list);
            rs.setState(1);
            rs.setMessage("登录成功");
            rs.setObj(user);
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
	
}
