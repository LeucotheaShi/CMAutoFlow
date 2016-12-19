/**
 * @Title: NodeModel.java
 * @Description:
 * @Date:2016年12月8日 下午5:33:44
 * @Author:LeucotheaShi
 */
package cmsz.autoflow.engine.model;

import java.util.ArrayList;
import java.util.List;

import cmsz.autoflow.engine.core.Execution;

/**
 * @ClassName:cmsz.autoflow.engine.model.NodeModel
 * @Description: TODO
 * @Date: 2016年12月8日
 * @Author: LeucotheaShi
 */
public abstract class NodeModel extends BaseModel implements Action {

	/**
	 * @Title: NodeModel.java
	 * @Description:
	 * @Date:2016年12月8日 下午5:34:05
	 * @Author:LeucotheaShi
	 */
	private static final long serialVersionUID = 2318797429979217671L;

	private List<TransitionModel> inputs = new ArrayList<TransitionModel>();
	private List<TransitionModel> outputs = new ArrayList<TransitionModel>();

	private List<ExceptionModel> inExceptions = new ArrayList<ExceptionModel>();
	private List<ExceptionModel> outExceptions = new ArrayList<ExceptionModel>();

	private List<FieldModel> fieldModels = new ArrayList<FieldModel>();

	private String refBean;
	private String refClass;
	private String refDubbo;
	private String refComponent;
	private String laucher;

	protected abstract void exec(Execution execution);

	public void execute(Execution execution) {
		exec(execution);
	}

	/**
	 * 
	 * @Title: runOutTransition
	 * @Description:run out this node's output transitions
	 * @param execution
	 * @return：void
	 * @Date:2016年12月15日 下午2:28:53
	 * @Author:LeucotheaShi
	 */
	protected void runOutTransition(Execution execution) {
		for (TransitionModel transitionModel : getOutputs()) {
			transitionModel.setEnabled(true);
			transitionModel.execute(execution);
		} // for
	}// runOutTransition

	/**
	 * 
	 * @Title: getNextModels
	 * @Description:
	 * @param clazz
	 * @return
	 * @return：List<T>
	 * @Date:2016年12月15日 下午3:42:42
	 * @Author:LeucotheaShi
	 */
	public <T> List<T> getNextModels(Class<T> clazz) {

		List<T> models = new ArrayList<T>();

		for (TransitionModel transitionModel : this.getOutputs()) {
			this.addNextModels(models, transitionModel, clazz);
		} // for

		return models;

	}// getNextModels

	/**
	 * 
	 * @Title: addNextModels
	 * @Description:
	 * @param models
	 * @param tm
	 * @param clazz
	 * @return：void
	 * @Date:2016年12月15日 下午3:08:43
	 * @Author:LeucotheaShi
	 */
	@SuppressWarnings("unchecked")
	protected <T> void addNextModels(List<T> models, TransitionModel tm, Class<T> clazz) {

		if (clazz.isInstance(tm.getTarget())) {
			models.add((T) tm.getTarget());
		} // if
		else {
			for (TransitionModel tm2 : tm.getTarget().getOutputs()) {
				this.addNextModels(models, tm2, clazz);
			} // for
		} // else

	}// addNextModels

	/**
	 * @return the inputs
	 */
	public List<TransitionModel> getInputs() {
		return inputs;
	}

	/**
	 * @param inputs
	 *            the inputs to set
	 */
	public void setInputs(List<TransitionModel> inputs) {
		this.inputs = inputs;
	}

	/**
	 * @return the outputs
	 */
	public List<TransitionModel> getOutputs() {
		return outputs;
	}

	/**
	 * @param outputs
	 *            the outputs to set
	 */
	public void setOutputs(List<TransitionModel> outputs) {
		this.outputs = outputs;
	}

	/**
	 * @return the refBean
	 */
	public String getRefBean() {
		return refBean;
	}

	/**
	 * @param refBean
	 *            the refBean to set
	 */
	public void setRefBean(String refBean) {
		this.refBean = refBean;
	}

	/**
	 * @return the refClass
	 */
	public String getRefClass() {
		return refClass;
	}

	/**
	 * @param refClass
	 *            the refClass to set
	 */
	public void setRefClass(String refClass) {
		this.refClass = refClass;
	}

	/**
	 * @return the refDubbo
	 */
	public String getRefDubbo() {
		return refDubbo;
	}

	/**
	 * @param refDubbo
	 *            the refDubbo to set
	 */
	public void setRefDubbo(String refDubbo) {
		this.refDubbo = refDubbo;
	}

	/**
	 * @return the refComponent
	 */
	public String getRefComponent() {
		return refComponent;
	}

	/**
	 * @param refComponent
	 *            the refComponent to set
	 */
	public void setRefComponent(String refComponent) {
		this.refComponent = refComponent;
	}

	/**
	 * @return the laucher
	 */
	public String getLaucher() {
		return laucher;
	}

	/**
	 * @param laucher
	 *            the laucher to set
	 */
	public void setLaucher(String laucher) {
		this.laucher = laucher;
	}

	/**
	 * @return the inExceptions
	 */
	public List<ExceptionModel> getInExceptions() {
		return inExceptions;
	}

	/**
	 * @param inExceptions
	 *            the inExceptions to set
	 */
	public void setInExceptions(List<ExceptionModel> inExceptions) {
		this.inExceptions = inExceptions;
	}

	/**
	 * @return the outExceptions
	 */
	public List<ExceptionModel> getOutExceptions() {
		return outExceptions;
	}

	/**
	 * @param outExceptions
	 *            the outExceptions to set
	 */
	public void setOutExceptions(List<ExceptionModel> outExceptions) {
		this.outExceptions = outExceptions;
	}

	/**
	 * @return the fieldModels
	 */
	public List<FieldModel> getFieldModels() {
		return fieldModels;
	}

	/**
	 * @param fieldModels
	 *            the fieldModels to set
	 */
	public void setFieldModels(List<FieldModel> fieldModels) {
		this.fieldModels = fieldModels;
	}

}// NodeModel
