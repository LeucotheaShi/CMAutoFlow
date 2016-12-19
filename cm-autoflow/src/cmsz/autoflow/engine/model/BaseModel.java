/**
 * @Title: BaseModel.java
 * @Description:
 * @Date:2016年12月8日 下午5:17:07
 * @Author:LeucotheaShi
 */
package cmsz.autoflow.engine.model;

import java.io.Serializable;

import cmsz.autoflow.engine.core.Execution;
import cmsz.autoflow.engine.handler.IHandler;

/**
 * @ClassName:cmsz.autoflow.engine.BaseModel
 * @Description: TODO
 * @Date: 2016年12月8日
 * @Author: LeucotheaShi
 */
public class BaseModel implements Serializable {

	/**
	 * @Title: BaseModel.java
	 * @Description:
	 * @Date:2016年12月8日 下午5:17:21
	 * @Author:LeucotheaShi
	 */
	private static final long serialVersionUID = 4775285765909683811L;

	private String name;

	/**
	 * 
	 * @Title: fire
	 * @Description:
	 * @param handler
	 * @param execution
	 * @return：void
	 * @Date:2016年12月8日 下午5:22:37
	 * @Author:LeucotheaShi
	 */
	protected void fire(IHandler handler, Execution execution) {
		handler.handle(execution);
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

}// BaseModel
