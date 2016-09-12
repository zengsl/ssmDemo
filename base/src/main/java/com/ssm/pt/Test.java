package com.ssm.pt;

import java.util.ArrayList;
import java.util.List;


/**
 * @author asus
 *
 */
public class Test {
	
	/*@Resource(name="baseDao")
	private IBaseDao<People, String>baseDao ;*/
	public static void main(String[] args) {
		
		try {
			/*ApplicationContext ac = new FileSystemXmlApplicationContext("classpath:spring/applicationContext.xml"); 
			IBaseDao<People, String>baseDao= (IBaseDao<People, String>) ac.getBean("baseDao");
			//baseDao.findByPrimaryKey("1");
			
			People people = new People();
			people.setFdName("zsl");
			baseDao.add(people);*/
			
			List list = new ArrayList();
			list.add(1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
