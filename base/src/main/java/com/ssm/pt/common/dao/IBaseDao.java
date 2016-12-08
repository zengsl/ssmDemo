package com.ssm.pt.common.dao;

/**
 * @author asus
 *
 */
public interface IBaseDao<Entity, PK> {

	/**
	 * 通过主键查找
	 * 
	 * @param id
	 * @return Entity����
	 * @throws Exception
	 */
	public abstract Entity findByPrimaryKey(PK pk) throws Exception;
	
	/**
	 * 新增数据
	 * 
	 * @param entity
	 *            Entity����
	 * @throws Exception
	 */
	public abstract String add(Entity entity) throws Exception;
}
