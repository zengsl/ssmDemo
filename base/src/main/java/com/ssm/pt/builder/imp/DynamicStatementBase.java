package com.ssm.pt.builder.imp;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.lang3.Validate;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.ssm.pt.builder.exception.DynamicStatementException;
import com.ssm.pt.builder.interfaces.IDynamicStatement;

/**
 * @author zengsl
 *
 */
public class DynamicStatementBase implements IDynamicStatement, ResourceLoaderAware, InitializingBean {
	
	private String rootElement = "sql-query";
	
	protected Map<String, String> namedSQLQueries;
	protected String[] fileNames = new String[0];
	protected ResourceLoader resourceLoader;
	
	/**
	 * 查询语句名称缓存，不允许重复
	 */
	private Set<String> nameCache = new HashSet<String>();
	
	protected  String getStatement(String stmtName) {
		return namedSQLQueries.get(stmtName);
	}
	
	@Override
	public String builder(String stmtName, Map<String, Object> params) throws Exception {
		//初始化运行时引擎， 默认初始化   
		VelocityEngine ve = new VelocityEngine();
		ve.init();
        //建立context， 并放入数据  
        VelocityContext context = new VelocityContext(); 
        Set<String> keys = params.keySet();  
        for (Iterator<String> iterator = keys.iterator(); iterator.hasNext();) {  
            String key = iterator.next();  
            context.put(key, params.get(key));  
        } 
        StringWriter sql = new StringWriter();  
        ve.evaluate(context, sql, "", getStatement(stmtName));
		return sql.toString();
	}


	
	private void init() throws IOException {
		namedSQLQueries = new HashMap<String, String>();
		boolean flag = this.resourceLoader instanceof ResourcePatternResolver;
		for (String file : fileNames) {
			if (flag) {
				Resource[] resources = ((ResourcePatternResolver) this.resourceLoader)
						.getResources(file);
				buildMap(resources);
			} else {
				Resource resource = resourceLoader.getResource(file);
				buildMap(resource);
			}
		}
		nameCache.clear();
	}

	/**
	 * @param resources  
	 * @return  
	 * @author Zengsl 	
	 * @date 2016年12月7日
	 * @version 1.0 
	 */
	private void buildMap(Resource[] resources) {
		
		if (resources == null) {
			return;
		}
		for (Resource resource : resources) {
			buildMap(resource);
		}
	}

	/**
	 * @param resource  
	 * @return  
	 * @author Zengsl 	
	 * @date 2016年12月7日
	 * @version 1.0 
	 */
	private void buildMap(Resource resource) {
		try {
			Document document = createDoucument(resource);
			Element root = document.getDocumentElement();
			NodeList nodeList = root.getChildNodes();
			Node nodeTemp = null;
			Element elementTemp = null;
			for (int i = 0; i < nodeList.getLength(); i++)
			{
				nodeTemp = nodeList.item(i);
				if (!(nodeTemp instanceof Element))
					continue;
				elementTemp = (Element) nodeTemp;
				String elementName = elementTemp.getTagName();
				if (rootElement.equals(elementName))
				{
					putStatementToCacheMap(resource, elementTemp,
							namedSQLQueries);
				}
			}
		} catch (Exception e) {
			throw new DynamicStatementException(e);
		}
	}
	
	private void putStatementToCacheMap(Resource resource,
			final Element element, Map<String, String> statementMap)
			throws IOException
	{
		String sqlQueryName = element.getAttribute("name");
		Validate.notEmpty(sqlQueryName);
		if (nameCache.contains(sqlQueryName))
		{
			throw new DynamicStatementException(String.format(
					"重复的sql-query/hql-query语句定义在文件:[%s]中，必须保证name的唯一。",
					resource.getURI()));
		}
		nameCache.add(sqlQueryName);
		String queryText = element.getTextContent();
		statementMap.put(sqlQueryName, queryText);
	}
	
	private Document createDoucument(final Resource resource) throws Exception {
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
		return documentBuilder.parse(resource.getInputStream());
	}
	
	/**
	 * @return the namedSQLQueries
	 */
	public Map<String, String> getNamedSQLQueries() {
		return namedSQLQueries;
	}

	/**
	 * @param namedSQLQueries the namedSQLQueries to set
	 */
	public void setNamedSQLQueries(Map<String, String> namedSQLQueries) {
		this.namedSQLQueries = namedSQLQueries;
	}

	/**
	 * @param fileNames the fileNames to set
	 */
	public void setFileNames(String[] fileNames) {
		this.fileNames = fileNames;
	}

	/**
	 * @param resourceLoader the resourceLoader to set
	 */
	public void setResourceLoader(ResourceLoader resourceLoader) {
		this.resourceLoader = resourceLoader;
	}

	
	@Override
	public void afterPropertiesSet() throws Exception {
		init();
	}
}
