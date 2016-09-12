package com.ssm.pt.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.log4j.Logger;

import com.ssm.pt.common.model.FiledBucket;

/**
 * 代码生成工具
 * @author asus
 *
 */
public final class CodeUtil {
	
	private static Logger logger = Logger.getLogger(CodeUtil.class); 
	
	
	private static final String FILE_SEPARATOR = File.separator + File.separator; // 文件分割符
	
	private static final String SYMBOL_TAB = "\t"; // 缩进
	private static final String SYMBOL_TAB_DOUBLE = "\t\t"; // 双重缩进
	private static final String SYMBOL_NEW_LINE_1 = "\r\n"; // 换行
	private static final String SYMBOL_NEW_LINE_2 = "\r\n\r\n"; // 换两行
	private static final String SYMBOL_BLANK = " ";
	private static final String SYMBOL_END = ";";
	private static final String SYMBOL_BRACKET_ANGLE_LEFT = "{"; 
	private static final String SYMBOL_BRACKET_ANGLE_RIGHT = "}";
	private static final String SYMBOL_BRACKET_LEFT = "("; 
	private static final String SYMBOL_BRACKET_RIGHT = ")";
	private static final String SYMBOL_POINT = ".";
	private static final String SYMBOL_SETTER_PREFIX = "set";
	private static final String SYMBOL_GETTER_PREFIX = "get";
	private static final String SYMBOL_EQUAL = "=";
	
	private static final String KEY_WORD_PACKEAGE = "package"; // 包关键字
	private static final String KEY_WORD_CLASS = "class"; // 类 关键字
	private static final String KEY_WORD_RANGE_PUBLIC = "public"; // 访问范围关键字
	private static final String KEY_WORD_RANGE_PRIVATE = "private"; // 访问范围关键字
	private static final String KEY_WORD_THIS = "this"; // this关键字
	private static final String KEY_WORD_RETURN = "return"; // this关键字
	private static final String KEY_WORD_RETURN_TYPE_VOID = "void"; // void返回类型

	private static final String defaultRootDir = "c:" + FILE_SEPARATOR + "codeCreator"; //默认的文件存储路径
	private static final String defaultSUFFIX = ".java"; //默认的文件类型
	
	/**
	 * 生成代码
	 * @param packageName
	 * @param fileName
	 * @param filedBuckets  
	 * @return  
	 * @author 曾声亮  	
	 * @date 2016年9月7日
	 * @version 1.0
	 */
	public static void generateCode(String projectContext, String packageName, String fileName, FiledBucket[] filedBuckets) {
		//文件名为空则不生成代码
		if(StringUtil.isEmpty(fileName)){
			return;
		}
		String path = generateDir(projectContext, packageName, fileName);
		File file = new File(path);
		//路径不存在则创建 
		if(!file.getParentFile().exists()){
			file.getParentFile().mkdirs();
		}
		// 文件不存在则新建
		if (!file.exists() ){
			try {
				file.createNewFile();
			} catch (Exception e) {
				logger.error("生成文件发生错误", e);
			}
		}
		
		FileWriter fw = null;
		try {
			fw = new FileWriter(file);
			// 将内容通过字符的形式写入文件中
			fw.write(generateCodeContent(packageName, fileName, filedBuckets));
			fw.flush();
			
		} catch (IOException e) {
			logger.error("生成代码文件发生错误",e);
		}finally {
			try {
				if(fw != null){
					fw.close();
				}
			} catch (IOException e) {
				logger.error("写入文件流关闭时发生错误",e);
			}
		}
	}
	
	/**
	 * 文件存储路径
	 * @param projectContext
	 * @param packageName
	 * @param fileName
	 * @return  
	 * @return  
	 * @author 曾声亮  	
	 * @date 2016年9月7日
	 * @version 1.0
	 */
	private static String generateDir(String projectContext, String packageName, String fileName) {
		
		//存储根路径
		String baseDir = StringUtil.isEmpty(projectContext) ? defaultRootDir : projectContext;
		StringBuilder fileDir = new StringBuilder();
		fileDir.append(baseDir);
		if(!StringUtil.isEmpty(packageName)){
			fileDir.append(FILE_SEPARATOR + convertPackage2Path(packageName));
		}
		fileDir.append(FILE_SEPARATOR + formatClassName(fileName) + defaultSUFFIX);	
		
		return fileDir.toString();
	}
	
	/**
	 * 生成代码主体
	 * @param packageName
	 * @param fileName
	 * @param filedBuckets
	 * @return  
	 * @return  
	 * @author 曾声亮  	
	 * @date 2016年9月7日
	 * @version 1.0
	 */
	private static String generateCodeContent(String packageName, String fileName, FiledBucket[] filedBuckets){
		StringBuilder content = new StringBuilder();
		// 生成package行内容
		content.append(KEY_WORD_PACKEAGE + SYMBOL_BLANK + packageName + SYMBOL_END + SYMBOL_NEW_LINE_1);
		// classBody 生成类体
		content.append(generateClass(fileName, filedBuckets));
		
		return content.toString();
	}
	
	
	/**
	 * 生成类（包括类的声明和类体）
	 * @param fileName
	 * @return  
	 * @author 曾声亮  	
	 * @date 2016年9月8日
	 * @version 1.0
	 */
	private static String generateClass(String fileName, FiledBucket[] filedBuckets) {
		StringBuilder content = new StringBuilder();
		//格式化标准类名 ，首字母大写
		String className = formatClassName(fileName); 
		
		// 生成class头  如: public class Test {
		content.append(KEY_WORD_RANGE_PUBLIC);
		content.append(SYMBOL_BLANK);
		content.append(KEY_WORD_CLASS);
		content.append(SYMBOL_BLANK);
		content.append(className);
		content.append(SYMBOL_BLANK);
		content.append(SYMBOL_BRACKET_ANGLE_LEFT);
		content.append(SYMBOL_NEW_LINE_2);
		
		// 生产默认构造方法
		content.append(generateDefaultConstructor());
		
		// 生产类体的内容
		content.append(generateFiledsContent(filedBuckets));
		// 生成class结束符 "}"
		content.append(SYMBOL_BRACKET_ANGLE_RIGHT);
		
		return content.toString();
	}
	
	
	/**
	 * 生成类体的成员属性和成员方法
	 * @param filedBuckets
	 * @return  
	 * @author 曾声亮  	
	 * @date 2016年9月8日
	 * @version 1.0
	 */
	private static String generateFiledsContent(FiledBucket[] filedBuckets) {
		StringBuilder content = new StringBuilder();
		for(int i = 0,size = filedBuckets.length; i < size; i++) {
			content.append(generateFiledDefine(filedBuckets[i])).append(SYMBOL_NEW_LINE_1); // 增加属性定义
			content.append(generateGetter(filedBuckets[i])).append(SYMBOL_NEW_LINE_1); // 增加属性get方法
			content.append(generateSetter(filedBuckets[i])).append(SYMBOL_NEW_LINE_2); // 增加属性set方法
		}
		return content.toString();
	}
	
	/**
	 * 生成属性定义内容
	 * @return  
	 * @return  
	 * @author 曾声亮  	
	 * @date 2016年9月8日
	 * @version 1.0
	 */
	private static String generateFiledDefine(FiledBucket filedBucket) {
		String tmpFiledType = filedBucket.getFiledType();
		String filed = filedBucket.getFiledName();
		StringBuilder content = new StringBuilder();
		content.append(SYMBOL_TAB).append(KEY_WORD_RANGE_PRIVATE).append(SYMBOL_BLANK).append(tmpFiledType).append(SYMBOL_BLANK)
				.append(filed).append(SYMBOL_END);

		return content.toString();
	}
	
	/**
	 * 生产get方法
	 * @param filed
	 * 			属性名
	 * @return  
	 * @author 曾声亮  	
	 * @date 2016年9月8日
	 * @version 1.0
	 */
	private static String generateGetter(FiledBucket filedBucket) {
		String tmpFiledType = filedBucket.getFiledType();
		String filed = filedBucket.getFiledName();
		StringBuilder content = new StringBuilder();
		content.append(SYMBOL_TAB).append(KEY_WORD_RANGE_PUBLIC).append(SYMBOL_BLANK).append(tmpFiledType).append(SYMBOL_BLANK)
				.append(SYMBOL_GETTER_PREFIX).append(StringUtil.toUpperCase(0, 1, filed))
				.append(SYMBOL_BRACKET_LEFT).append(SYMBOL_BRACKET_RIGHT).append(SYMBOL_BLANK)
				.append(SYMBOL_BRACKET_ANGLE_LEFT).append(SYMBOL_NEW_LINE_1).append(SYMBOL_TAB_DOUBLE).append(KEY_WORD_RETURN).append(SYMBOL_BLANK)
				.append(filed).append(SYMBOL_END).append(SYMBOL_NEW_LINE_1).append(SYMBOL_TAB).append(SYMBOL_BRACKET_ANGLE_RIGHT);

		return content.toString();
	}
	
	/**
	 * 生产set方法
	 * @param filed
	 * 			属性名
	 * @return  
	 * @author 曾声亮  	
	 * @date 2016年9月8日
	 * @version 1.0
	 */
	private static String generateSetter(FiledBucket filedBucket) {
		String tmpFiledType = filedBucket.getFiledType();
		String filed = filedBucket.getFiledName();
		StringBuilder content = new StringBuilder();
		content.append(SYMBOL_TAB).append(KEY_WORD_RANGE_PUBLIC).append(SYMBOL_BLANK).append(KEY_WORD_RETURN_TYPE_VOID)
				.append(SYMBOL_BLANK).append(SYMBOL_SETTER_PREFIX).append(StringUtil.toUpperCase(0, 1, filed))
				.append(SYMBOL_BRACKET_LEFT).append(tmpFiledType).append(SYMBOL_BLANK)
				.append(filed).append(SYMBOL_BRACKET_RIGHT).append(SYMBOL_BLANK).append(SYMBOL_BRACKET_ANGLE_LEFT)
				.append(SYMBOL_NEW_LINE_1).append(SYMBOL_TAB_DOUBLE).append(KEY_WORD_THIS).append(SYMBOL_POINT).append(filed).append(SYMBOL_BLANK)
				.append(SYMBOL_EQUAL).append(SYMBOL_BLANK).append(filed).append(SYMBOL_END).append(SYMBOL_NEW_LINE_1).append(SYMBOL_TAB)
				.append(SYMBOL_BRACKET_ANGLE_RIGHT);

		return content.toString();
	}
	
	private static String generateDefaultConstructor() {
		return "";

	}
	
	/**
	 * 将路径格式转换为包格式
	 * @param path
	 * 			文件相对存放路径
	 * 			如： WEB-INF\com\ssm\pt\test -> com.ssm.pt.test
	 * @return  packageName
	 * 			 包名	
	 * @author 曾声亮  	
	 * @date 2016年9月8日
	 * @version 1.0
	 */
	private static String convertPath2Package(String path) {
		
		String result = "";
		if(StringUtil.isEmpty(path))
			return result;
		
		String[] tmpArr = path.split("\\\\");
		if(ArrayUtils.isNotEmpty(tmpArr)) {
			StringBuilder packageName = new StringBuilder();
			for(int i = 0, size = tmpArr.length; i < size; i++) {
				if(StringUtil.isEmpty(tmpArr[i]))
					continue;
				
				if(i == 0) {
					packageName.append(tmpArr[i]);
				}else {
					packageName.append(SYMBOL_POINT + tmpArr[i]);
				}
			}
			result = packageName.toString();
		}
		
		return result;
	}

	/**
	 * 将包名转换为路径格式
	 * @param path
	 * 			文件相对存放路径
	 * 			如：com.ssm.pt.test -> com\ssm\pt\test
	 * @return  packageName
	 * 			 包名	
	 * @author 曾声亮  	
	 * @date 2016年9月8日
	 * @version 1.0
	 */
	private static String convertPackage2Path(String pacakageName) {
		
		String result = "";
		if(StringUtil.isEmpty(pacakageName))
			return result;
		
		String regexPacakage = "\\.";
		String[] tmpArr = pacakageName.split(regexPacakage);
		
		if(ArrayUtils.isNotEmpty(tmpArr)) {
			StringBuilder packageName = new StringBuilder();
			for(int i = 0, size = tmpArr.length; i < size; i++) {
				if(StringUtil.isEmpty(tmpArr[i]))
					continue;
				
				if(i == 0) {
					packageName.append(tmpArr[i]);
				}else {
					packageName.append(FILE_SEPARATOR  + tmpArr[i]);
				}
			}
			result = packageName.toString();
		}
		
		return result;
	}
	
	/**
	 * 生成包声明语句
	 * @param path
	 * @return  
	 * @author 曾声亮  	
	 * @date 2016年9月8日
	 * @version 1.0
	 */
	@SuppressWarnings("unused")
	private static String generatePackage(String path) {
		return KEY_WORD_PACKEAGE + SYMBOL_BLANK + convertPath2Package(path) + SYMBOL_END + SYMBOL_NEW_LINE_1;
	}
	
	/**
	 * 格式化类名
	 * @param className
	 * @return  
	 * @author 曾声亮  	
	 * @date 2016年9月8日
	 * @version 1.0
	 */
	private static String formatClassName(String className) {
		className = className.toLowerCase();
		className = StringUtil.toUpperCase(0, 1, className);
		return className;
	}
	
	private CodeUtil() {
		
	}
}
