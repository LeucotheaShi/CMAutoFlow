/**
 * 
 */
package com.cmsz.cmup.commons.utils;

import java.net.InetAddress;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.cmsz.cmup.commons.logging.system.SystemLogHandler;
import com.cmsz.cmup.commons.logging.utils.LogInitHandler;


/**
 * 路由表索引类
 * 
 * @author cmt
 * 
 */

@Component
public class HostMsgCache {
	private static SystemLogHandler systemLogHandler = SystemLogHandler.getLogger(LogInitHandler.class);
	
	private String hostIP4Part = "1";
	private static final String DEFAULT_HOSTIP4PART="1.0.0.0";

	/**
	 * 初始化数据字典
	 * 
	 * @author cmt
	 */
	@PostConstruct
	private void init() {
		try {
			String addr = InetAddress.getLocalHost().getHostAddress();
			systemLogHandler.info("本机ip地址是："+addr);
			
			if(addr==null){
				hostIP4Part = DEFAULT_HOSTIP4PART	;
			}
			else {
				hostIP4Part = addr.split("\\.")[3];
			}
			
		} catch (Exception e) {
			systemLogHandler.warn(e.getMessage());
		}
		
	}

	/**
	 * 重载数据字典
	 * 
	 * @author cmt
	 */
	public void reLoad() {
		init();
	}

	/**
	 * @return
	 */
	public String getHostIP4Part() {
		return hostIP4Part;
	}

}
