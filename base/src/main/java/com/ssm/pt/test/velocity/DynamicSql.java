package com.ssm.pt.test.velocity;

import java.util.HashMap;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ssm.pt.builder.interfaces.IDynamicStatement;

/**
 * @author zengsl
 *
 */
public class DynamicSql {
	
private IDynamicStatement dynamicStatement;

/**
 * @param dynamicStatement the dynamicStatement to set
 */
public void setDynamicStatement(IDynamicStatement dynamicStatement) {
	this.dynamicStatement = dynamicStatement;
}

public static void main(String[] args) throws Exception {
	ApplicationContext  factory = new ClassPathXmlApplicationContext("classpath:spring/spring-core.xml");
	DynamicSql sql = (DynamicSql) factory.getBean("dynamicSql");
	
	try {
		System.out.println(sql.dynamicStatement.builder("test", new HashMap<String,Object>()));
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	/*System.setProperty("javax.xml.parsers.DocumentBuilderFactory", "com.sun.org.apache.xerces.internal.jaxp.DocumentBuilderFactoryImpl");
	ResourceLoader resourceLoader = new DefaultResourceLoader();  
	Resource resource = resourceLoader.getResource("classpath:spring/test.xml");
	DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder documentBuilder;
	documentBuilder = documentBuilderFactory.newDocumentBuilder();
	Document document = documentBuilder.parse(resource.getInputStream());
	Element root = document.getDocumentElement();*/
	
}
}
