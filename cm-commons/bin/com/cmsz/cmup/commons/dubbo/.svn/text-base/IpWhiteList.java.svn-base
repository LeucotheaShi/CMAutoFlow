package com.cmsz.cmup.commons.dubbo;

import java.util.ArrayList;
import java.util.List;

/**
 * ip 白名单
 * 
 *
 * @author yaoQingCan
 * @version 创建时间：2016年1月14日 上午9:40:37
 */
public class IpWhiteList {
	
    /**
     * 白名单功能是否开启，ture 开启，false ：关闭。取自配置文件 
     */
	private String enabled;
   
    /**
     * 放到IP白名单中的IP，取自配置文件，多个IP中间有英文逗号分开
     */
    private String ips;
    
    
	/**
	 * ip 列表，从ips转换而来
	 */
	private List<String> ipList;


	public void init() {

		String[] ipArray=ips.split(",");
		ipList = new ArrayList<String>();
		for (String ip : ipArray) {
			ipList.add(ip);
		}

	}
	
	public String getEnabled() {
		return enabled;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}

	public List<String> getIpList() {
		return ipList;
	}

	public void setIpList(List<String> ipList) {
		this.ipList = ipList;
	}

    public String getIps() {
		return ips;
	}

	public void setIps(String ips) {
		this.ips = ips;
	}

    

}
