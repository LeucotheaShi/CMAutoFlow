/**
 * @Title: TransitionModel.java
 * @Description:
 * @Date:2016年12月8日 下午5:35:42
 * @Author:LeucotheaShi
 */
package cmsz.autoflow.engine.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cmsz.autoflow.engine.core.Execution;

/**
 * @ClassName:cmsz.autoflow.engine.model.TransitionModel
 * @Description: TODO
 * @Date: 2016年12月8日
 * @Author: LeucotheaShi
 */
public class TransitionModel extends BaseModel implements Action {

	/**
	 * @Title: TransitionModel.java
	 * @Description:
	 * @Date:2016年12月8日 下午5:35:51
	 * @Author:LeucotheaShi
	 */
	private static final long serialVersionUID = 2912016797022697320L;

	private Logger logger = LoggerFactory.getLogger(getClass());

	private boolean enabled = false;
	private NodeModel source;
	private NodeModel target;
	private String to;

	/**
	 * @Title: execute
	 * @Description:
	 * @param execution
	 * @Date:2016年12月8日 下午5:35:42
	 * @Author:LeucotheaShi
	 */

	public void execute(Execution execution) {
		// TODO Auto-generated method stub

		if (!enabled) {
			return;
		} // if

	}// execute

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

}
