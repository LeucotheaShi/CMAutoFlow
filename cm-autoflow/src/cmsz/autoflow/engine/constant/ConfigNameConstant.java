/**
 * @Title: ConfigNameConstant.java
 * @Description:
 * @Date:2016年12月8日 下午4:28:08
 * @Author:LeucotheaShi
 */
package cmsz.autoflow.engine.constant;

/**
 * @ClassName:cmsz.autoflow.engine.constant.ConfigNameConstant
 * @Description: 定义流程配置文件中个元素的命名
 * @Date: 2016年12月8日
 * @Author: LeucotheaShi
 */
public interface ConfigNameConstant {

	public static final String ATTR_NAME = "name";
	public static final String ATTR_ID = "id";
	public static final String ATTR_TO = "to";
	public static final String ATTR_LAUNCHER = "launcher";
	public static final String ATTR_REFBEAN = "refbean";
	public static final String ATTR_REFCLASS = "refclass";
	public static final String ATTR_FIELD = "field";
	public static final String ATTR_KEY = "key";
	public static final String ATTR_VALUE = "value";
	public static final String ATTR_EXCEPTION = "exception";
	public static final String ATTR_TYPE = "type";

	// 使用dubbo remote调用时使用
	public static final String ATTR_REFDUBBO = "refdubbo";
	public static final String ATTR_COMPONENT = "refcomponent";

	public static final String ELEM_START = "start";
	public static final String ELEM_TASK = "task";
	public static final String ELEM_END = "end";
	public static final String ELEM_TRAN = "transition";
	public static final String ELEM_FORK = "fork";
	public static final String ELEM_JOIN = "join";

	public static final String STATUS_START = "start";
	public static final String STATUS_ACTIVE = "active";
	public static final String STATUS_FINISH = "finish";

	// 流程或任务状态
	public static class Status {
		public static final String SUCCESS = "Success";
		public static final String FINISH = "Finish";

		public static final String START = "Start";
		public static final String RUNNING = "Running";
		public static final String ACTIVE = "Active";
		public static final String FAILED = "Failed";
		public static final String ERROR = "Error";
		public static final String EXCEPTION = "Exception";

	}

}// ConfigNameConstant
