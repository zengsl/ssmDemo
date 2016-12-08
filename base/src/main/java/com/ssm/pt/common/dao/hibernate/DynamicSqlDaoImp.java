package com.ssm.pt.common.dao.hibernate;

import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import com.ssm.pt.common.dao.IDynamicSqlDao;

/**
 * @author zengsl
 *
 */
public class DynamicSqlDaoImp extends HibernateDaoSupport implements IDynamicSqlDao {
	
	protected String getSql(String sqlName, Map<String, Object> params) {
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> findData(String sqlName, Map<String, Object> params) throws Exception {
		return getHibernateSession().createSQLQuery(getSql(sqlName, params)).list();
	}

	
	@Override
	public Session getHibernateSession() {
		
		return getSessionFactory().getCurrentSession();
	}

}
