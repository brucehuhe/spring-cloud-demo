package org.spring.cloud.service.service;

import org.spring.cloud.service.util.Result;

public interface StoreService {

	Result findByStore(String param);

	Result saveStore(String param);

	Result updateStore(String param);

	Result deleteStoreById(String param);
	
	   
}