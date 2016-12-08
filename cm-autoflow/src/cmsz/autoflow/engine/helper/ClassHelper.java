package cmsz.autoflow.engine.helper;

public class ClassHelper {

	// 实例化传入的类
	public static <T> T instantiate(Class<T> clazz) {
		// 如果传入参数是个接口，则无法实例化
		if (clazz.isInterface()) {
			// log.error("所传递的class类型参数为接口，无法实例化");
			System.out.println("所传递的class类型参数为接口，无法实例化");
			return null;
		}
		try {
			return clazz.newInstance();
		} catch (Exception ex) {
			// log.error("检查传递的class类型参数是否为抽象类?", ex.getCause());
			System.out.println("检查传递的class类型参数是否为抽象类?");
		}
		return null;
	}

}
