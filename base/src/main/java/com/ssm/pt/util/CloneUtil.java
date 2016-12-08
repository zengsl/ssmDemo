package com.ssm.pt.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * 拷贝工具类
 * @author asus
 *
 */
public final class CloneUtil {
	
	private CloneUtil(){
		
	}
	/**
	 * 序列化拷贝（深拷贝）
	 * @param 目标对象
	 * @return  拷贝对象
	 * @author 曾声亮  	
	 * @date 2016年8月18日
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public static <T extends Serializable> T clone(T obj){
		
		T cloneObj = null;
		
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(obj);
			oos.close();
			
			ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
			ObjectInputStream ois = new ObjectInputStream(bais);
			cloneObj = (T)ois.readObject();
			ois.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cloneObj;
		//apache commons工具包中的SerializationUtils 序列化拷贝
	}
	
	
}
