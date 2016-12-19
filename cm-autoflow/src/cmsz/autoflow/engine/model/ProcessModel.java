/**
 * @Title: ProcessModel.java
 * @Description:
 * @Date:2016年12月8日 下午5:31:38
 * @Author:LeucotheaShi
 */
package cmsz.autoflow.engine.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName:cmsz.autoflow.engine.model.ProcessModel
 * @Description: 流程定义的模型
 * @Date: 2016年12月8日
 * @Author: LeucotheaShi
 */
public class ProcessModel extends BaseModel {

	/**
	 * @Title: ProcessModel.java
	 * @Description:
	 * @Date:2016年12月8日 下午5:32:22
	 * @Author:LeucotheaShi
	 */
	private static final long serialVersionUID = -2397540267543230485L;

	private String id;
	private List<NodeModel> nodes = new ArrayList<NodeModel>();
	private List<TaskModel> tasks = new ArrayList<TaskModel>();

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the nodes
	 */
	public List<NodeModel> getNodes() {
		return nodes;
	}

	/**
	 * @param nodes
	 *            the nodes to set
	 */
	public void setNodes(List<NodeModel> nodes) {
		this.nodes = nodes;
	}

	/**
	 * @return the tasks
	 */
	public List<TaskModel> getTasks() {
		return tasks;
	}

	/**
	 * @param tasks
	 *            the tasks to set
	 */
	public void setTasks(List<TaskModel> tasks) {
		this.tasks = tasks;
	}

	/**
	 * lock
	 */
	private Object lock = new Object();

	/**
	 * 获取开始节点
	 * 
	 * @return
	 */
	public StartModel getStart() {
		for (NodeModel node : nodes) {
			if (node instanceof StartModel)
				return (StartModel) node;
		}
		return null;
	}

	/**
	 * 返回指定名字的NodeModel
	 * 
	 * @param nodename
	 * @return
	 */

	public NodeModel getNode(String nodename) {
		for (NodeModel node : nodes) {
			if (node.getName().equals(nodename)) {
				return node;
			}
		}
		return null;
	}

	/**
	 * 根据指定的节点类型返回流程定义中所有模型对象
	 * 
	 * @param clazz
	 * @return
	 */

	public <T> List<T> getModels(Class<T> clazz) {
		List<T> models = new ArrayList<T>();
		buildModels(models, this.getStart().getNextModels(clazz), clazz);
		return null;
	}

	private <T> void buildModels(List<T> models, List<T> nextModels, Class<T> clazz) {
		for (T nextModel : nextModels) {
			if (!models.contains(nextModel)) {
				models.add(nextModel);
				buildModels(models, ((NodeModel) nextModel).getNextModels(clazz), clazz);
			}
		}
	}

}// ProcessModel
