package org.spring.cloud.img.server.conf;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="img.store")
public class SaveImgToServerConfig {
	private String savePath ;
	private String vistaPath;
	
	public String getSavePath() {
		return savePath;
	}
	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}
	public String getVistaPath() {
		return vistaPath;
	}
	public void setVistaPath(String vistaPath) {
		this.vistaPath = vistaPath;
	}
}
