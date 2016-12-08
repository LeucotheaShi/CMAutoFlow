package cmsz.autoflow.engine.access.mybatis;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//封装了mybatis 的 sqlSessionFactory
/**
 * 
 * @ClassName:cmsz.autoflow.engine.access.mybatis.MybatisSqlSessionFactory
 * @Description: TODO
 * @Date: 2016年12月7日
 * @Author: LeucotheaShi
 */
public class MybatisSqlSessionFactory {

	private static SqlSessionFactory sqlSessionFactory = null;

	private static final Logger logger = LoggerFactory.getLogger(MybatisSqlSessionFactory.class);

	@SuppressWarnings("unchecked")
	public static SqlSessionFactory getSqlSessionFactory() {
		if (sqlSessionFactory == null) {
			logger.error("SqlSessionFactory为空!工作流异常，请使用spring容器管理数据源。");

		}
		return sqlSessionFactory;
	}

	public static void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		MybatisSqlSessionFactory.sqlSessionFactory = sqlSessionFactory;
	}

	public static SqlSession openSession() {
		return getSqlSessionFactory().openSession();
	}

}
