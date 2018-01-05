package org.spring.cloud.service.service;

import org.spring.cloud.service.util.Result;

public interface ResourcesService {
	
	Result findResourcesByUserId(String param);

	Result saveResource(String param);

	Result updateResource(String param);

	Result delResource(String param);

	Result findResourcesByRole(String param);

	Result findResourcesByType(String param);

	Result selectBtnByResource(String param);

}
