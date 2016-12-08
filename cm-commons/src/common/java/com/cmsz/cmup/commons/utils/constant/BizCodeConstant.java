package com.cmsz.cmup.commons.utils.constant;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;



/**
 * 返回业务线业务代码描述常量
 * 
 * @author zcb
 * 
 */
public class BizCodeConstant {
	
	public static Map<String,String> bizDescMap = new ConcurrentHashMap<String,String>();
	static{
		bizDescMap.put("012103_", "灵犀支付TOKEN");
		bizDescMap.put("012104_", "灵犀网关信息查询");
		bizDescMap.put("012105_", "灵犀充值");
	}



}
