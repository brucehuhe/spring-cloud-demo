package org.spring.cloud.service.model.stockPo;

import java.io.Serializable;

public class User implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 6312493577385772431L;

	private String id;

    private String username;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }
}