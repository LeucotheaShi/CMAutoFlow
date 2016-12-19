/**
 * @Title: FieldModel.java
 * @Description:
 * @Date:2016年12月9日 上午9:59:01
 * @Author:LeucotheaShi
 */
package cmsz.autoflow.engine.model;

import cmsz.autoflow.engine.core.Execution;

/**
 * @ClassName:cmsz.autoflow.engine.model.FieldModel
 * @Description: TODO
 * @Date: 2016年12月9日
 * @Author: LeucotheaShi
 */
public class FieldModel extends NodeModel {

	/**
	 * @Title: FieldModel.java
	 * @Description:
	 * @Date:2016年12月9日 上午9:59:07
	 * @Author:LeucotheaShi
	 */
	private static final long serialVersionUID = 2402152048957468509L;

	private String key;
	private String value;

	/**
	 * @Title: exec
	 * @Description:
	 * @param execution
	 * @Date:2016年12月9日 上午9:59:01
	 * @Author:LeucotheaShi
	 */
	@Override
	protected void exec(Execution execution) {
		// TODO Auto-generated method stub

	}

	/**
	 * @return the key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * @param key
	 *            the key to set
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value
	 *            the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

}
