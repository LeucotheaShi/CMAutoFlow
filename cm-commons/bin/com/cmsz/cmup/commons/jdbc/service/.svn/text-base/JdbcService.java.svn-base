package com.cmsz.cmup.commons.jdbc.service;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.support.rowset.SqlRowSet;


/**
 * 通用jdbc工具类，对于特殊情况下，某些复杂的数据库操作，ORM框架不好实现时，可以使用工具类jdbcService直接执行sql
 * <p>提供了batchUpdate和doExecuteBatch两个方法可以实现事务的统一提交和回滚。
 * 只需要把sql放到list里面，统一用这两个方法中的任何一个执行即可实现事务控制</p>
 * <p>另外提供了不需要事务控制及忽略错误的方法:</p>
 * batchNoTransitionUpdate,batchNoTransationIgnoreErrorUpdate,doNoTransitionExecuteBatch,doNoTransationIgnoreErrorExecuteBatch
 * <p>具体使用请参考对应方法的注释</p>
 * @author JinChao
 * 
 * @date 2015年11月18日 上午11:01:05
 *
 */
public interface JdbcService {

	/**
	 * 根据参数查询,返回结果集
	 * @param sql
	 * @param args
	 * @return
	 */
	public SqlRowSet query(String sql, Object[] args);

	/**
	 * 不带参数的查询
	 * @param sql
	 * @return
	 */
	public SqlRowSet query(String sql);

	/**
	 * 不带参数的查询
	 * DataListEntity
	 * @param sql
	 * @return
	 */
	public DataListEntity queryForDataListEntity(String sql);

	/**
	 * 不带参数的分页查询
	 * @param sql
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public DataListEntity queryForDataListEntityByPage(String sql, int pageIndex, int pageSize);

	/**
	 * 带参数的查询
	 * @param sql
	 * @param args
	 * @param calculateStatistics 是否合计
	 * @return
	 */
	public DataListEntity queryForDataListEntity(String sql, Object[] args, boolean calculateStatistics);

	/**
	 * 带参数的分页查询
	 * @param sql
	 * @param args
	 * @param pageIndex 页码
	 * @param pageSize 小于1 表示不分页
	 * @param calculateStatistics 是否合计
	 * @return
	 */
	public DataListEntity queryForDataListEntityByPage(String sql, Object[] args, int pageIndex, int pageSize,
			boolean calculateStatistics);

	/**
	 * 取得记录总数，在分页查询中使用
	 * @param sql
	 * @param args
	 * @return
	 */
	public int getRecordCount(String sql, Object[] args);

	/**
	 * 取得记录总数，在分页查询中使用
	 * @param sql
	 * @return
	 */
	public int getRecordCount(String sql);

	/**
	 * 取得sql的统计数量
	 * @param sql (select count(1) from .....)
	 * @param params
	 * @return
	 */
	public int queryForInt(String sql, Object[] params);

	/**
	 * 读数据表结构信息
	 * @param tableName
	 * @return
	 */
	public Map<String, TableColumnEntity> getTableStructreInfo(String tableName);

	/**
	 * 取得序列的下一键值
	 * @param sequenceName
	 * @return
	 */
	public Long getSequenceValue(String sequenceName);

	/**
	 * 执行sql
	 * @param sql
	 * @param params
	 * @return
	 */
	public int doExecuteSQL(String sql, Object[] params);

	/**
	 * 读取系统参数配置
	 * @param paramName
	 * @return
	 */
	public String getSystemParamValue(String paramName);

	/**
	 * 执行sql
	 * @param sql
	 * @param params
	 * @return
	 */
	public void doExecute(String sql);

	/**
	 * 执行sql
	 * @param sql
	 * @param params
	 * @param argTypes 参数数据类型
	 * @return
	 */
	public int doExecute(String sql, Object[] params, int[] argTypes);

	/**
	 * 批量执行sql，可实现对批量事务的统一控制，要么sql全部执行失败，要么全部成功
	 * 此方法用在动态参数的情况下，带?的sql放在BatchEntity的sql字段，参数值放在args字段即可
	 * <p>BatchEntity有两个属性:</p>
	 *		<p>1)sql为执行的sql 如 insert into Test values(?,?)</p>
	 *		<p>2)args为执行的sql对应的参数值 如'user1','123'</p>
	 * @param list
	 */
	public void doExecuteBatch(List<BatchEntity> list);
	
	/**
	 * 批量执行sql，没有事务控制
	 * 此方法用在动态参数的情况下，带?的sql放在BatchEntity的sql字段，参数值放在args字段即可
	 * <p>BatchEntity有两个属性:</p>
	 *		<p>1)sql为执行的sql 如 insert into Test values(?,?)</p>
	 *		<p>2)args为执行的sql对应的参数值 如'user1','123'</p>
	 * @param list
	 */
	public void doNoTransitionExecuteBatch(List<BatchEntity> list);
	
	/**
	 * 没有办法在此方法中记录业务线等信息到日志记录中，不在提供此方法，如果用到类似功能请在业务层实现。－－－２０１６０６０１　yaoQingcan
	 * 批量执行sql，没有事务控制,忽略其中某几条sql的错误
	 * 此方法用在动态参数的情况下，带?的sql放在BatchEntity的sql字段，参数值放在args字段即可
	 * <p>BatchEntity有两个属性:</p>
	 *		<p>1)sql为执行的sql 如 insert into Test values(?,?)</p>
	 *		<p>2)args为执行的sql对应的参数值 如'user1','123'</p>
	 * @param list
	 
	public void doNoTransationIgnoreErrorExecuteBatch(List<BatchEntity> list);
	 */
	
	/**
	 * 批量执行sql，可实现对批量事务的统一控制，要么sql全部执行失败，要么全部成功
	 * @param List<String> sqlList
	 */
	public void batchUpdate(List<String> sqlList);
	
	/**
	 * 批量执行sql，无事务控制
	 * <p>此方法执行效率比batchUpdate(List<String> sqlList)要高一些
	 * @param String[] sqls
	 */
	public void batchUpdate(String[] sqls);
	
	/**
	 * 批量执行sql，没有事务控制
	 * @param List<String> sqlList
	 */
	public void batchNoTransitionUpdate(List<String> sqlList);
	
	/**
	 * 没有办法在此方法中记录业务线等信息到日志记录中，不在提供此方法，如果用到类似功能请在业务层实现。－－－２０１６０６０１　yaoQingcan
	 * 批量执行sql，没有事务控制，忽略其中某几条sql的错误
	 * @param List<String> sqlList
	 
	public void batchNoTransationIgnoreErrorUpdate(List<String> sqlList);
	*/
	
	/**
	 * 查询某表ID的下一个ID(max(id+1))
	 * @param searchCol 要查询的列
	 * @param table 要查询的表
	 * @return
	 */
	public Long getNextId(String searchCol,String table) ;
}
