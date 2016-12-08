package com.ssm.pt.common.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.Session;

/**
 * @author zengsl
 */
public interface IDynamicSqlDao {
	
	public abstract Session getHibernateSession();
	
	public abstract List<Object[]> findData(String sqlName, Map<String, Object> params) throws Exception;

}
