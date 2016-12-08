package com.cmsz.cmup.commons.dubbo;

import com.alibaba.dubbo.rpc.Filter;
import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.Result;
import com.alibaba.dubbo.rpc.RpcContext;
import com.alibaba.dubbo.rpc.RpcException;
import com.cmsz.cmup.commons.logging.system.SystemLogHandler;

/**
 * 调用拦截扩展,ip拦截
 * 服务提供方对消费方IP的验证，验证通过的可以调用服务，否则不可以调用。
 *
 * @author yaoQingCan
 * @version 创建时间：2016年1月14日 上午9:21:42
 */
public class IpVerifyFilter implements Filter {
	
	private static SystemLogHandler syslog = SystemLogHandler.getLogger(IpVerifyFilter.class);
	
	private IpWhiteList ipWhiteList;
	
	@Override
	public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
		
		syslog.info("IpVerifyFilter enabled:"+ipWhiteList.getEnabled()+" : ips: "+ipWhiteList.getIps());


		//白名单功能关闭，不启用白名单,任何消费方都可以访问服务
		if (Boolean.FALSE.equals(Boolean.valueOf(ipWhiteList.getEnabled()))) {
			syslog.info("IpVerifyFilter : is closed!");
            return invoker.invoke(invocation);
        }
		String serviceName = invoker.getInterface().getName();
		String methodName = invocation.getMethodName();

        String clientIp = RpcContext.getContext().getRemoteHost();
        String localHost =RpcContext.getContext().getLocalHost();
        
    	StringBuilder stringBuilder = new StringBuilder();
    	stringBuilder.append(" invoke remote service: ").append(serviceName)
		        	.append(" method: ").append(methodName)
		        	.append( "() from consumer ").append(clientIp)
		        	.append(" to provider: ").append(localHost);
 
    	
    	//消费方和服务方在一台机器上，白名单不用配置，直接通过
    	if (clientIp.equals(localHost)) {

    		String logmessage = " clientIp equals localHost,allow invoke: "+stringBuilder.toString();
    		syslog.info(logmessage);
            
            return invoker.invoke(invocation);
		}

        //消费方IP不在白名单上，不让调用。
        if (!ipWhiteList.getIpList().contains(clientIp)) {
        	
        	String errorMesage =" Invalid clientIP,Forbid invoke: "+stringBuilder.toString();
    		syslog.info(errorMesage);
			throw new RpcException(errorMesage);
        }
        
		String logmessage = " valid clientIP,allow invoke: "+stringBuilder.toString();
		syslog.info(logmessage);
		
		return invoker.invoke(invocation);
	}

	public IpWhiteList getIpWhiteList() {
		return ipWhiteList;
	}
	public void setIpWhiteList(IpWhiteList ipWhiteList) {
		this.ipWhiteList = ipWhiteList;
	}

}
