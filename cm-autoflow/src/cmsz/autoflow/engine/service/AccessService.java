/**
 * @Title: AccessService.java
 * @Description:
 * @Date:2016年12月10日 下午5:50:43
 * @Author:LeucotheaShi
 */
package cmsz.autoflow.engine.service;

import cmsz.autoflow.engine.access.DBAccess;

/**
 * @ClassName:cmsz.autoflow.engine.service.AccessService
 * @Description: TODO
 * @Date: 2016年12月10日
 * @Author: LeucotheaShi
 */
public abstract class AccessService {

	protected DBAccess access;

	/**
	 * @return the access
	 */
	public DBAccess getAccess() {
		return access;
	}

	/**
	 * @param access
	 *            the access to set
	 */
	public void setAccess(DBAccess access) {
		this.access = access;
	}

}// AccessService
