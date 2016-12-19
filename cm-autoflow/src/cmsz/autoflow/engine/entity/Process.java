/**
 * @Title: Process.java
 * @Description:
 * @Date:2016��12��6�� ����2:27:45
 * @Author:LeucotheaShi
 */
package cmsz.autoflow.engine.entity;

import java.io.Serializable;

import cmsz.autoflow.engine.model.ProcessModel;

/**
 * @ClassName:cmsz.autoflow.engine.entity.Process
 * @Description: ���̶����ʵ����
 * @Date: 2016��12��6��
 * @Author: LeucotheaShi
 */
public class Process implements Serializable {

	/**
	 * @Title: Process.java
	 * @Description:
	 * @Date:2016��12��6�� ����2:29:06
	 * @Author:LeucotheaShi
	 */
	private static final long serialVersionUID = -2130931056801022014L;

	private String id;
	private String name;
	private String status;

	private String createTime;
	private String updateTime;
	/**
	 * 流程定义xml
	 */
	private byte[] content;

	/**
	 * 流程定义模型
	 */
	private ProcessModel model;

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

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the createTime
	 */
	public String getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime
	 *            the createTime to set
	 */
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	/**
	 * @return the updateTime
	 */
	public String getUpdateTime() {
		return updateTime;
	}

	/**
	 * @param updateTime
	 *            the updateTime to set
	 */
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * @return the content
	 */
	public byte[] getContent() {
		return content;
	}

	/**
	 * @param content
	 *            the content to set
	 */
	public void setContent(byte[] content) {
		this.content = content;
	}

	/**
	 * @return the model
	 */
	public ProcessModel getModel() {
		return model;
	}

	/**
	 * @param model
	 *            the model to set
	 */
	public void setModel(ProcessModel model) {
		this.model = model;
	}

	/**
	 * 
	 * @Title: toString
	 * @Description:
	 * @return
	 * @Date:2016年12月11日 上午9:28:50
	 * @Author:LeucotheaShi
	 */
	@Override
	public String toString() {
		return "Process [id=" + id + ", name=" + name + ", status=" + status + ", content=" + content + ", createTime="
				+ createTime + ", updateTime=" + updateTime + "]";
	}

}// Process
