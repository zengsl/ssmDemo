package com.ssm.pt.util;


import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
/**
 * ת������Ĺ�����
 * @author ������
 *
 */
public final class ObjectDynamicCreator {
	
	private ObjectDynamicCreator(){
		
	}
	
    /**
     * �����ɶ��������Ϊkey,ֵΪmap��value��Map����
     * 
     * @param obj
     *            Object
     * @return mapValue Map<String,String>
     * @throws Exception
     */
    public static Map<String, String> getFieldVlaue(Object obj) throws Exception {
        Map<String, String> mapValue = new HashMap<String, String>();
        Class<?> cls = obj.getClass();
        Field[] fields = cls.getDeclaredFields();
        for (Field field : fields) {
            String name = field.getName();
            String strGet = "get" + name.substring(0, 1).toUpperCase() + name.substring(1, name.length());
            Method methodGet = cls.getDeclaredMethod(strGet);
            Object object = methodGet.invoke(obj);
            String value = object != null ? object.toString() : "";
            mapValue.put(name, value);
        }
        return mapValue;
    }

    /**
     * ������Map��key�����ԣ�value��Ӧֵ��ɵĶ�Ӧ
     * 
     * @param map
     *            Map<String,String>
     * @param cls
     *            Class
     * @return obj Object
     * @throws Exception
     */
    public static Object setFieldValue(Map<String, String> map, Class<?> cls) throws Exception {
        Field[] fields = cls.getDeclaredFields();
        Object obj = cls.newInstance();
        for (Field field : fields) {
            Class<?> clsType = field.getType();
            String name = field.getName();
            String strSet = "set" + name.substring(0, 1).toUpperCase() + name.substring(1, name.length());
            Method methodSet = cls.getDeclaredMethod(strSet, clsType);
            if (map.containsKey(name)) {
                Object objValue = typeConversion(clsType, map.get(name));
                methodSet.invoke(obj, objValue);
            }
        }
        return obj;
    }

    /**
     * ��Map����Ĳ���ֵͨ���������õ����ж�����ȥ
     * 
     * @param obj
     *            Object
     * @param data
     *            Map<String,String>
     * @return obj Object
     * @throws Exception
     */
    public static Object setObjectFileValue(Object obj, Map<String, String> data) throws Exception {
        Class<?> cls = obj.getClass();
        Field[] fields = cls.getDeclaredFields();
        for (Field field : fields) {
            Class<?> clsType = field.getType();
            String name = field.getName();
            String strSet = "set" + name.substring(0, 1).toUpperCase() + name.substring(1, name.length());
            Method methodSet = cls.getDeclaredMethod(strSet, clsType);
            if (data.containsKey(name)) {
                Object objValue = typeConversion(clsType, data.get(name));
                methodSet.invoke(obj, objValue);
            }
        }
        return obj;
    }

    /**
     * �Ѷ����ֵ��Map��Ӧװ����
     * 
     * @param map
     *            Map<String,String>
     * @param obj
     *            Object
     * @return ��������Զ�Ӧ��Map Map<String,String>
     */
    public static Map<String, String> compareMap(Map<String, String> map, Object obj) {
        Map<String, String> mapValue = new HashMap<String, String>();
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            String name = field.getName();
            if (map.containsKey(name)) {
                mapValue.put(name, map.get(name));
            }
        }
        return mapValue;
    }

    /**
     * ����ʱ�����ֵ���Ƶ��־û�������
     * 
     * @param oldObject
     *            Object �־û�����
     * @param newObject
     *            Object ��ʱ����
     * @return �־û�����
     * @throws Exception
     */
    public static Object mergedObject(Object oldObject, Object newObject) throws Exception {
        Class<?> cls = newObject.getClass();
        Field[] fields = cls.getDeclaredFields();
        for (Field field : fields) {
            Class<?> clsType = field.getType();
            String name = field.getName();
            String method = name.substring(0, 1).toUpperCase() + name.substring(1, name.length());
            String strGet = "get" + method;
            Method methodGet = cls.getDeclaredMethod(strGet);
            Object object = methodGet.invoke(newObject);
            if (object != null) {
                String strSet = "set" + method;
                Method methodSet = cls.getDeclaredMethod(strSet, clsType);
                Object objValue = typeConversion(clsType, object.toString());
                methodSet.invoke(oldObject, objValue);
            }
        }
        return oldObject;
    }

    public static Object typeConversion(Class<?> cls, String str) {
        Object obj = null;
        String nameType = cls.getSimpleName();
        if ("Integer".equals(nameType)) {
            obj = Integer.valueOf(str);
        }
        if ("String".equals(nameType)) {
            obj = str;
        }
        if ("Float".equals(nameType)) {
            obj = Float.valueOf(str);
        }
        if ("Double".equals(nameType)) {
            obj = Double.valueOf(str);
        }

        if ("Boolean".equals(nameType)) {
            obj = Boolean.valueOf(str);
        }
        if ("Long".equals(nameType)) {
            obj = Long.valueOf(str);
        }

        if ("Short".equals(nameType)) {
            obj = Short.valueOf(str);
        }

        if ("Character".equals(nameType)) {
            obj = str.charAt(1);
        }

        return obj;
    }
}