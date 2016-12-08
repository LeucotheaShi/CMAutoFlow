package com.cmsz.cmup.commons.utils;

import java.io.File;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 系统初始化
 * 
 * @author jinchao wuhang
 */
public class SystemInitor {
	public static final String DEFAULT_LOG_FILE = "logback.xml";

	public static final String DEFAULT_CONFIG_DIR = "config";

	private static final SystemInitor instance = new SystemInitor();

	private String configDir;

	private SystemInitor() {
	}

	public static SystemInitor getInstance() {
		return instance;
	}

	public void init() throws Exception {
		this.init(null);
	}

	/**
	 * * 初始化系统
	 * 
	 * @param _configDir
	 * @throws Exception
	 */
	public void init(String _configDir) throws Exception {
		System.out.println("System.encoding="
				+ System.getProperty("file.encoding"));
		this.configDir = this.getConfigDir(_configDir);
		if (this.configDir == null) {
			System.out.println("Must set the config dir!");
		}
		if (!StringUtils.isEmpty(_configDir)) {
			this.initSpringIoc(_configDir);
		}else {
			this.initSpringIoc();
		}
		System.out.println("Start upay server ok, now ready service!");
	}

	public void printSystemSummary(boolean bool){
		
	}
	
	/**
	 * 初始化Spring IOC容器
	 * 
	 * @param configNames
	 */
	public void initSpringIoc() {
		try {
			ClassPathXmlApplicationContext xmlApplicationContext = new ClassPathXmlApplicationContext(new String[] {"classpath*:spring.xml"} );
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * 初始化Spring IOC容器
	 * 
	 * @param configNames
	 */
	public void initSpringIoc(String fileName) {
		try {
			ClassPathXmlApplicationContext xmlApplicationContext = new ClassPathXmlApplicationContext("classpath*:"+fileName);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}


	/**
	 * 获取配置文件目录 如果给定的配置文件不存在，将启用默认的
	 * 
	 * @param confDir
	 * @return
	 */
	public String getConfigDir(String confDir) {
		if (StringUtils.isBlank(confDir)){
			confDir = DEFAULT_CONFIG_DIR;
		}
		if (!this.checkFileExist(confDir)) {
			System.err.println("Config dir[" + confDir + "] not found!");
			return null;
		}

		return confDir;
	}

	private boolean checkFileExist(String file) {
		if (file != null && new File(file).exists()){
			return true;
		}
		return false;
	}

}
