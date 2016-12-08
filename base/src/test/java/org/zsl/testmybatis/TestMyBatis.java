package org.zsl.testmybatis;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.zsl.baseTest.SpringTestCase;

import com.ssm.pt.service.IUserService;

/**

 * @author asus

 *

 */
public class TestMyBatis extends SpringTestCase{

	private static Logger logger = Logger.getLogger(TestMyBatis.class);  
//  private ApplicationContext ac = null;  


    @Resource(name="userService") 
    private IUserService userService = null;  
  
//  @Before  


//  public void before() {  


//      ac = new ClassPathXmlApplicationContext("applicationContext.xml");  


//      userService = (IUserService) ac.getBean("userService");  


//  }  


  

    @Test  
    public void test1() {  
		/*User user = userService.getUserById(1);
		System.out.println("****:"+user.getUserName());
		logger.info("用户名：" + user.getUserName());
		logger.info(JSON.toJSONString(user));
		System.out.println("hello");*/
    	
    	/*IBaseDao<User, Integer> baseDao = new BaseDaoImp<User, Integer>();
    	try {
			baseDao.findByPrimaryKey(1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
    	
//    	Assert.assertEquals(-1%2, 1);
    }  
}