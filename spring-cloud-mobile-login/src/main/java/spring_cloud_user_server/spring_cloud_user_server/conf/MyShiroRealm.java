package spring_cloud_user_server.spring_cloud_user_server.conf;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import spring_cloud_user_server.spring_cloud_user_server.bean.User;
import spring_cloud_user_server.spring_cloud_user_server.service.UserService;

/**
 * 身份校验核心类;
 * 
 * @author Angel(QQ:412887952)
 * @version v.0.1
 */
public class MyShiroRealm extends AuthorizingRealm {

	private Logger logger = LoggerFactory.getLogger(MyShiroRealm.class);
	
	@Autowired
	private UserService userService;

	/**
	 * 认证信息.(身份验证) : Authentication 是用来验证用户身份
	 * 
	 * @param token
	 * @return
	 * @throws AuthenticationException
	 */
	@SuppressWarnings("unused")
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		System.out.println("MyShiroRealm.doGetAuthenticationInfo()");

		// 获取用户的输入的账号.
		String username = (String) token.getPrincipal();

		// 实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
		User user = userService.findByUsername(username);
		if (user == null) {
			return null;
		}
		if (user != null) {
			 Session session = SecurityUtils.getSubject().getSession();
			 session.setAttribute("user", user);//成功则放入session
			// 若存在，将此用户存放到登录认证info中，无需自己做密码对比，Shiro会为我们进行密码对比校验
			return new SimpleAuthenticationInfo(user,
					user.getPassword(), getName());
		}
		return null;
	}

	/**
	 * 此方法调用 hasRole,hasPermission的时候才会进行回调.
	 * 权限信息.(授权): 1、如果用户正常退出，缓存自动清空； 2、如果用户非正常退出，缓存自动清空；
	 * 3、如果我们修改了用户的权限，而用户不退出系统，修改的权限无法立即生效。 （需要手动编程进行实现；放在service进行调用）
	 * 在权限修改后调用realm中的方法，realm已经由spring管理，所以从spring中获取realm实例， 调用clearCached方法；
	 * :Authorization 是授权访问控制，用于对用户进行的操作授权，证明该用户是否允许进行当前操作，如访问某个链接，某个资源文件等。
	 * @param principals
	 * @return
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		/*
		 * 当没有使用缓存的时候，不断刷新页面的话，这个代码会不断执行， 当其实没有必要每次都重新设置权限信息，所以我们需要放到缓存中进行管理；
		 * 当放到缓存中时，这样的话，doGetAuthorizationInfo就只会执行一次了， 缓存过期之后会再次执行。
		 */
		System.out.println("权限配置-->MyShiroRealm.doGetAuthorizationInfo()");

		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		// UserInfo userInfo = (UserInfo)principals.getPrimaryPrincipal();

		// 实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
		// UserInfo userInfo = userInfoService.findByUsername(username)

		// 权限单个添加;
		// 或者按下面这样添加
		// 添加一个角色,不是配置意义上的添加,而是证明该用户拥有admin角色
		// authorizationInfo.addRole("admin");
		// 添加权限
		// authorizationInfo.addStringPermission("userInfo:query");

		// 设置权限信息.
		// authorizationInfo.setStringPermissions(getStringPermissions(userInfo.getRoleList()));

		return authorizationInfo;
	}

}
