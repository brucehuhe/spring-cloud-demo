package org.spring.cloud.service.service;

import org.spring.cloud.service.util.Result;

public interface RoleService {
	
	public Result saveRole(String param);
	
	public Result findByRole(String param);
	
	public Result updateByRole(String param);
	
	public Result delByRole(String param);

}
