package com.cmsz.cmup.commons.jdbc.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.jdbc.support.rowset.SqlRowSetMetaData;
import org.springframework.transaction.annotation.Transactional;

import com.cmsz.cmup.commons.logging.system.SystemLogHandler;

/**
 * 通用jdbc工具类，对于特殊情况下，某些复杂的数据库操作，ORM框架不好实现时，可以使用工具类jdbcService直接执行sql
 * <p>提供了batchUpdate和doExecuteBatch两个方法可以实现事务的统一提交和回滚。
 * 只需要把sql放到list里面，统一用这两个方法中的任何一个执行即可实现事务控制</p>
 * <p>另外提供了不需要事务控制及忽略错误的方法:</p>
 * batchNoTransitionUpdate,doNoTransitionExecuteBatch
 * <p>具体使用请参考对应方法的注释</p>
 * @author JinChao
 * @see com.cmsz.cmup.commons.jdbc.service.JdbcService
 * 
 * @date 2015年11月18日 上午11:01:05
 *
 */
public class JdbcServiceImpl extends JdbcDaoSupport implements JdbcService {

	private static final SystemLogHandler logger = SystemLogHandler.getLogger(JdbcServiceImpl.class);

	public SqlRowSet query(String sql, Object[] args) {
		return (SqlRowSet) getJdbcTemplate().query(sql, args,new SqlRowResultSetExtractor());
	}

	public SqlRowSet query(String sql) {
		return (SqlRowSet) getJdbcTemplate().query(sql, new SqlRowResultSetExtractor());


	}

	public DataListEntity queryForDataListEntity(String sql, Object[] args, boolean calculateStatistics) {

		DataListEntity entity = new DataListEntity();

		SqlRowSet dataSet = (SqlRowSet) getJdbcTemplate().query(sql, args,
				new SqlRowResultSetExtractor());

		String[] columnNames = dataSet.getMetaData().getColumnNames();

		String[] nameTypes = new String[columnNames.length];

		for (int i = 1; i <= dataSet.getMetaData().getColumnCount(); i++) {
			nameTypes[i - 1] = dataSet.getMetaData().getColumnTypeName(i);
		}

		entity.setColumnNameTypes(nameTypes);

		// caculate statics
		if (calculateStatistics) {
			SqlRowSetMetaData meta = dataSet.getMetaData();
			StringBuilder sb = new StringBuilder();
			for (int i = 1; i <= meta.getColumnCount(); i++) {
				if ("NUMBER".equals(meta.getColumnTypeName(i))) {
					sb.append("sum(nvl(\"").append(meta.getColumnName(i)).append("\",0)) as \"").append(
							meta.getColumnName(i)).append("\",");
				}
			}
			if (sb.length() > 0) {
				String sumSql = new StringBuilder("select " ).append(sb.substring(0, sb.length() - 1)).append( " from ( " ).append( sql ).append( " )").toString();
				SqlRowSet sumSet = (SqlRowSet) getJdbcTemplate().query(sumSql, args,
						new SqlRowResultSetExtractor());
				Map<String, String> sumMap = new HashMap<String, String>();
				String[] names = sumSet.getMetaData().getColumnNames();
				while (sumSet.next()) {
					for (String name : names) {
						sumMap.put(name, sumSet.getString(name));
					}
				}
				entity.setSumMap(sumMap);
			}
		}

		List<Map<String, String>> dataList = new ArrayList<Map<String, String>>();
		while (dataSet.next()) {
			Map<String, String> dataMap = new HashMap<String, String>();
			for (String element : columnNames) {
				String ds = dataSet.getString(element);
				if ("SQLSTR".equals(element)) {
					if (ds != null) {
						ds = ds.replace("return false;", "");
					}
				}
				dataMap.put(element, ds);
			}
			dataList.add(dataMap);
		}

		entity.setColumnNames(columnNames);
		entity.setDataList(dataList);

		return entity;
	}

	public DataListEntity queryForDataListEntity(String sql) {

		SqlRowSet dataSet = (SqlRowSet) getJdbcTemplate().query(sql,
				new SqlRowResultSetExtractor());

		SqlRowSetMetaData meta = dataSet.getMetaData();

		String[] columnNames = new String[meta.getColumnCount()];

		for (int i = 1; i <= meta.getColumnCount(); i++) {
			columnNames[i - 1] = meta.getColumnName(i);
		}

		List<Map<String, String>> dataList = new ArrayList<Map<String, String>>();
		while (dataSet.next()) {
			Map<String, String> dataMap = new HashMap<String, String>();
			for (String element : columnNames) {
				dataMap.put(element, dataSet.getString(element));
			}
			dataList.add(dataMap);
		}

		DataListEntity entity = new DataListEntity();
		entity.setColumnNames(columnNames);
		entity.setDataList(dataList);

		return entity;
	}

	public DataListEntity queryForDataListEntityByPage(String sql, int pageIndex, int pageSize) {

		JdbcTemplate template = getJdbcTemplate();
		template.setFetchSize(pageSize);
		template.setMaxRows(pageIndex * pageSize);
		SqlRowSet dataSet = (SqlRowSet) template.query(sql, new SqlRowResultSetExtractor());

		SqlRowSetMetaData meta = dataSet.getMetaData();

		String[] columnNames = new String[meta.getColumnCount()];

		for (int i = 1; i <= meta.getColumnCount(); i++) {
			columnNames[i - 1] = meta.getColumnName(i);

		}

		List<Map<String, String>> dataList = new ArrayList<Map<String, String>>();
		while (dataSet.next()) {
			Map<String, String> dataMap = new HashMap<String, String>();
			for (String element : columnNames) {
				dataMap.put(element, dataSet.getString(element));
			}
			dataList.add(dataMap);
		}

		DataListEntity entity = new DataListEntity();
		entity.setColumnNames(columnNames);
		entity.setDataList(dataList);

		return entity;
	}

	/* (non-Javadoc)
	 * @see com.harbor.spring.service.JdbcService#queryForDataListEntityByPage(java.lang.String, java.lang.Object[], int, int, boolean)
	 */
	/* (non-Javadoc)
	 * @see com.harbor.spring.service.JdbcService#queryForDataListEntityByPage(java.lang.String, java.lang.Object[], int, int, boolean)
	 */
	public DataListEntity queryForDataListEntityByPage(String sql, Object[] args, int pageIndex, int pageSize,
			boolean calculateStatistics) {

		DataListEntity entity = new DataListEntity();

		String sql_temp = sql;

		int startRow = (pageIndex - 1) * pageSize;
		int endRow = pageIndex * pageSize;

		sql = this.getPageSQL(sql);

		Object[] new_args;
		if (args == null) {
			new_args = new Object[] { endRow, startRow };
		}
		else {
			new_args = new Object[args.length + 2];
			for (int i = 0; i < args.length; i++) {
				new_args[i] = args[i];
			}
			new_args[new_args.length - 2] = endRow;
			new_args[new_args.length - 1] = startRow;
		}

		JdbcTemplate template = getJdbcTemplate();

		SqlRowSet dataSet = (SqlRowSet) template.query(sql, new_args, new SqlRowResultSetExtractor());

		SqlRowSetMetaData meta = dataSet.getMetaData();

		String[] temp = meta.getColumnNames();

		List<String> nameTempList = new ArrayList<String>();

		for (String name : temp) {

			if ("ROWNO".equals(name)) {
				continue;
			}

			nameTempList.add(name);
		}

		String[] columnNames = new String[nameTempList.size()];

		for (int i = 0; i < nameTempList.size(); i++) {
			columnNames[i] = nameTempList.get(i);
		}

		// String[] nameTypes = new String[columnNames.length];
		List<String> nameTypeList = new ArrayList<String>(columnNames.length);
		for (int i = 1; i <= meta.getColumnCount(); i++) {
			// nameTypes[i-1] = meta.getColumnTypeName(i);
			if ("ROWNO".equals(meta.getColumnName(i))) {
				continue;
			}
			nameTypeList.add(meta.getColumnTypeName(i));
		}

		String[] nameTypes = new String[columnNames.length];

		for (int i = 0; i < nameTypeList.size(); i++) {
			nameTypes[i] = nameTypeList.get(i);
		}

		entity.setColumnNameTypes(nameTypes);

		// cacluate static
		if (calculateStatistics) {
			meta = dataSet.getMetaData();
			StringBuilder sb = new StringBuilder();
			for (int i = 1; i <= meta.getColumnCount(); i++) {

				if ("NUMBER".equals(meta.getColumnTypeName(i))) {

					if ("ROWNO".equals(meta.getColumnName(i))) {
						continue;
					}

					sb.append("sum(nvl(").append(meta.getColumnName(i)).append(",0)) as ")
							.append(meta.getColumnName(i)).append(",");
				}

			}

			if (sb.length() > 0) {
				String sumSql = new StringBuilder("select " ).append(sb.substring(0, sb.length() - 1)).append( " from ( " ).append( sql_temp ).append( " )").toString();
				SqlRowSet sumSet = (SqlRowSet) getJdbcTemplate().query(sumSql, args,
						new SqlRowResultSetExtractor());
				Map<String, String> sumMap = new HashMap<String, String>();
				String[] names = sumSet.getMetaData().getColumnNames();
				while (sumSet.next()) {
					for (String name : names) {
						sumMap.put(name, sumSet.getString(name));
					}
				}
				entity.setSumMap(sumMap);
			}
		}

		List<Map<String, String>> dataList = new ArrayList<Map<String, String>>();
		while (dataSet.next()) {
			Map<String, String> dataMap = new HashMap<String, String>();
			for (String element : columnNames) {
				dataMap.put(element, dataSet.getString(element));
			}
			dataList.add(dataMap);
		}
		/*int count = dataList.size();
		if (dataList.size() < 3) {
			for (int k = 0; k < 3 - count; k++) {
				dataList.add(new HashMap<String, String>());
			}
		}*/

		entity.setColumnNames(columnNames);
		entity.setDataList(dataList);

		return entity;
	}

	/**
	 * 生成分页查询语句
	 * @param sql
	 * @return
	 */
	private String getPageSQL(String sql) {

		// sql = sql.toUpperCase();

		int whereIndex = sql.toUpperCase().lastIndexOf("WHERE");
		int subQueryIndex = sql.toUpperCase().lastIndexOf(")");
		int funInex = sql.toUpperCase().lastIndexOf("(");
		int fromIndex = sql.toUpperCase().indexOf("FROM");
		int orderIndex = sql.toUpperCase().lastIndexOf("ORDER");

		if (orderIndex > -1) {
			logger.debug("SQL 有排序");
			return new StringBuilder("SELECT * FROM (SELECT TT.*, ROWNUM AS ROWNO FROM (" ).append(sql)
				.append( ") TT WHERE ROWNUM <= ? ) TABLE_ALIAS where TABLE_ALIAS.ROWNO > ? ").toString();
		}
		else {

			logger.debug("SQL 没有排序");

			String new_sql = "";
			boolean hasCondiction = false;

			/**
			if(sql.indexOf("where")==-1){
				hasCondiction = false;
			}else{
				if(sql.indexOf(")")==-1){
					//不带子查询
					hasCondiction = true;
				}else{
					//带子询
					String temp = sql.toLowerCase().substring(sql.lastIndexOf(")"), sql.length()-1);
					hasCondiction = temp.indexOf("where")!=-1;
				}
			}
			**/

			if ((subQueryIndex == -1) || (subQueryIndex < fromIndex) || (funInex > whereIndex)) {
				// 无子查询
				if (whereIndex == -1) {
					// 无查询条件
				}
				else {
					// 有查询条件
					hasCondiction = true;
				}

			}
			else {
				// 有子查询
				if ((whereIndex == -1) || (subQueryIndex > whereIndex)) {
					// 无查询条件
				}
				else {
					// 有查询条件
					hasCondiction = true;
				}
			}

			logger.debug("hasCondiction:" + hasCondiction);

			new_sql = new StringBuilder("SELECT * FROM ( " ).append( sql.replaceFirst("SELECT", "SELECT ROWNUM ROWNO,")
					).append( (hasCondiction ? " and " : " where ") ).append( " ROWNUM <= ? ) TABLE_ALIAS WHERE TABLE_ALIAS.ROWNO > ? ").toString();

			logger.debug("new_sql:\n" + new_sql);

			return new_sql;
		}
	}

	public int getRecordCount(String sql, Object[] args) {

		return this.getJdbcTemplate().queryForInt(new StringBuilder("select count(1) from (" ).append(sql ).append( ")").toString(), args);
	}

	public int getRecordCount(String sql) {

		return this.getJdbcTemplate().queryForInt(new StringBuilder("select count(1) from (" + sql + ")").toString());
	}

	public int queryForInt(String sql, Object[] params) {

		return this.getJdbcTemplate().queryForInt(sql, params);
	}

	public Map<String, TableColumnEntity> getTableStructreInfo(String tableName) {

		// tableName = "MW_WORKITEM";

		String sql = new StringBuilder("SELECT  COLUMN_NAME,DATA_TYPE,DATA_LENGTH,DATA_PRECISION,DATA_SCALE FROM USER_TAB_COLUMNS "
				).append( "WHERE TABLE_NAME NOT IN (SELECT VIEW_NAME FROM USER_VIEWS) and table_name = upper( ? ) ").toString();
		SqlRowSet set = this.query(sql, new Object[] { tableName });
		Map<String, TableColumnEntity> map = new HashMap<String, TableColumnEntity>();
		while (set.next()) {
			TableColumnEntity entity = new TableColumnEntity();
			entity.setColumnName(set.getString("COLUMN_NAME"));
			entity.setDataLength(set.getString("DATA_LENGTH"));
			entity.setDataPrecision(set.getString("DATA_PRECISION"));
			entity.setDataType(set.getString("DATA_TYPE"));
			entity.setDataScale(set.getString("DATA_SCALE"));
			map.put(entity.getColumnName(), entity);
		}
		return map;
	}

	public Long getSequenceValue(String sequenceName) {

		String sql = new StringBuilder("select  " ).append( sequenceName ).append( ".nextval from dual ").toString();
		return new Long(this.getJdbcTemplate().queryForInt(sql));
	}

	public int doExecuteSQL(String sql, final Object[] params) {

		int i = this.getJdbcTemplate().update(sql, params);
		return i;
		// return 0;
	}

	public String getSystemParamValue(String paramName) {

		String sql = " SELECT paras FROM mw_paras WHERE name= ? ";
		String value = "";
		SqlRowSet set = this.query(sql, new Object[] { paramName });
		if (set.next()) {
			value = set.getString("paras");
		}
		return value;
	}

	public void doExecute(String sql) {

		logger.debug("doExecute2 sql:\n" + sql);
		JdbcTemplate jdbcTemplate = this.getJdbcTemplate();
		jdbcTemplate.execute(sql);
	}

	public int doExecute(String sql, Object[] params, int[] argTypes) {

		logger.debug("exec sql:\n" + sql);
		return this.getJdbcTemplate().update(sql, params, argTypes);
	}

	@Transactional
	public void doExecuteBatch(List<BatchEntity> list) {

		for (BatchEntity item : list) {
			this.getJdbcTemplate().update(item.getSql(), item.getArgs());
		}
	}

	@Transactional
	public void batchUpdate(List<String> sqlList) {

		if ((null != sqlList) && (sqlList.size() > 0)) {
			String sqls[] = new String[sqlList.size()];
			for (int i = 0; i < sqlList.size(); i++) {
				String sql = sqlList.get(i);
				sqls[i] = sql;
			}
			this.getJdbcTemplate().batchUpdate(sqls);
		}
	}
	
	public void batchUpdate(String[] sqls) {
		this.getJdbcTemplate().batchUpdate(sqls);
	}
	
	/*
	public void doNoTransationIgnoreErrorExecuteBatch(List<BatchEntity> list) {
		for (BatchEntity item : list) {
			String sql = item.getSql();
			Object[] args = item.getArgs();
			try {
				this.getJdbcTemplate().update(sql, args);
			} catch (Exception e) {
				String message = new StringBuilder("批量执行sql时，部分sql执行出错,sql:").append(sql).append(",args:").append(args).toString();
				logger.error(message,e);
			}
		}
	}
	*/
	
	public void doNoTransitionExecuteBatch(List<BatchEntity> list) {

		for (BatchEntity item : list) {
			this.getJdbcTemplate().update(item.getSql(), item.getArgs());
		}
	}

	public void batchNoTransitionUpdate(List<String> sqlList) {
		if ((null != sqlList) && (sqlList.size() > 0)) {
			String sqls[] = new String[sqlList.size()];
			for (int i = 0; i < sqlList.size(); i++) {
				String sql = sqlList.get(i);
				sqls[i] = sql;
			}
			this.getJdbcTemplate().batchUpdate(sqls);
		}
	}
	
	/*
	public void batchNoTransationIgnoreErrorUpdate(List<String> sqlList) {
		if ((null != sqlList) && (sqlList.size() > 0)) {
			for (int i = 0; i < sqlList.size(); i++) {
				String sql = sqlList.get(i);
				try {
					this.getJdbcTemplate().update(sql);
				} catch (Exception e) {
					String message = new StringBuilder("批量执行sql时，部分sql执行出错,sql:").append(sql).toString();
					logger.error(message,e);
				}
				
			}
		}
	}
   */
	
	public Long getNextId(String column,String talbe) {
		StringBuilder sql=new StringBuilder("select max(").append(column).append(")+1 from ").append(talbe);
		return new Long(this.getJdbcTemplate().queryForInt(sql.toString()));
	}
}
