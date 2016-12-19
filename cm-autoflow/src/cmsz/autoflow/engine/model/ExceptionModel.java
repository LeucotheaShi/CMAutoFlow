/**
 * @Title: ExceptionModel.java
 * @Description:
 * @Date:2016年12月9日 上午10:00:30
 * @Author:LeucotheaShi
 */
package cmsz.autoflow.engine.model;

import cmsz.autoflow.engine.core.Execution;

/**
 * @ClassName:cmsz.autoflow.engine.model.ExceptionModel
 * @Description: TODO
 * @Date: 2016年12月9日
 * @Author: LeucotheaShi
 */
public class ExceptionModel extends NodeModel {

	/**
	 * @Title: ExceptionModel.java
	 * @Description:
	 * @Date:2016年12月9日 上午10:00:37
	 * @Author:LeucotheaShi
	 */
	private static final long serialVersionUID = 79533855802758080L;

	private boolean enabled = false;
	private NodeModel source;
	private NodeModel target;
	private String to;
	private String type;

	/**
	 * @Title: exec
	 * @Description:
	 * @param execution
	 * @Date:2016年12月9日 上午10:00:30
	 * @Author:LeucotheaShi
	 */
	@Override
	protected void exec(Execution execution) {
		// TODO Auto-generated method stub

	}

	/**
	 * @return the enabled
	 */
	public boolean isEnabled() {
		return enabled;
	}

	/**
	 * @param enabled
	 *            the enabled to set
	 */
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	/**
	 * @return the source
	 */
	public NodeModel getSource() {
		return source;
	}

	/**
	 * @param source
	 *            the source to set
	 */
	public void setSource(NodeModel source) {
		this.source = source;
	}

	/**
	 * @return the target
	 */
	public NodeModel getTarget() {
		return target;
	}

	/**
	 * @param target
	 *            the target to set
	 */
	public void setTarget(NodeModel target) {
		this.target = target;
	}

	/**
	 * @return the to
	 */
	public String getTo() {
		return to;
	}

	/**
	 * @param to
	 *            the to to set
	 */
	public void setTo(String to) {
		this.to = to;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

}
