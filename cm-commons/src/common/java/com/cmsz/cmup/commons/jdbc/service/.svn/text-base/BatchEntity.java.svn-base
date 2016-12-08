package com.cmsz.cmup.commons.jdbc.service;

/**
 * 批处理sql脚本实体类，用于动态传参数的情形
 * <p>带?的sql放在前面，参数放在后面的args里面即可</p>
 * @author Administrator
 *
 */
public class BatchEntity {
	
	public BatchEntity(String sql,Object[] args){
		this.sql = sql;
		this.args = args;
	}
	public BatchEntity() {
	}

	/** sql 命令 */
	private String sql;
	
	/** 参数 */
	private Object[] args;

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public Object[] getArgs() {
		return args;
	}

	public void setArgs(Object[] args) {
		this.args = args;
	}
	
}
