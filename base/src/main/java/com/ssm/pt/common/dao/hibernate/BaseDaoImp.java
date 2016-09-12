package com.ssm.pt.common.dao.hibernate;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import com.ssm.pt.common.dao.IBaseDao;
import com.ssm.pt.model.User;

/**
 * @author asus
 *
 */
public class BaseDaoImp<Entity, PK extends Serializable> extends HibernateDaoSupport implements IBaseDao<Entity, PK> {
	
	/**
     * 操作的对象类型
     */
    private Class<Entity> entityClass;
    
	@SuppressWarnings("unchecked")
	public BaseDaoImp() {
		 	/**
	         *  获取实现接口的类型
	         * */
	        ParameterizedType type = (ParameterizedType) getClass()
	                .getGenericInterfaces()[0];
	        
	        /**
	         * 获取实现接口的第一个泛型参数
	         * */
	        entityClass = (Class<Entity>) type.getActualTypeArguments()[0].getClass();
	}
    
	public Entity findByPrimaryKey(PK pk) throws Exception {
		return getHibernateTemplate().get(entityClass, pk);
	}

	public String add(Entity entity) throws Exception {
		return (String) getHibernateTemplate().save(entity);
	}
	
	public static void main(String[] args) {
		
		IBaseDao<User, Integer> baseDao = new BaseDaoImp<User, Integer>();
		try {
			baseDao.findByPrimaryKey(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
