package com.ssm.pt.util;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.ssm.pt.model.User;

/**
 * @author asus
 *
 */
public class ListUtil {
	/**
	 * 打印list中的元素
	 * 
	 * @param list
	 * @return
	 * @author 曾声亮
	 * @date 2016年9月9日
	 * @version 1.0
	 */
	public static <T> void printList(List<T> list) {
		if (null == list)
			list = new ArrayList<T>();
		list.stream().forEach(n -> System.out.println(n.toString()));
	}

	/**
	 * 从list中删除指定的元素 其他类需重写equals方法
	 * 
	 * @param list
	 * @param arg
	 *            要删除的元素
	 * @return 返回删除了指定元素的list
	 *         eg:list:[1,2,3,1]---removeElementFromList(list,1)---return
	 *         list:[2,3]
	 */
	public static <T> List<T> removeElementFromList(List<T> list, T arg) {
		if (null == list || list.isEmpty())
			return new ArrayList<T>();
		if (arg == null)
			return list;
		return list.stream().filter(n -> {
			return !n.equals(arg);
		}).collect(Collectors.toList());
	}

	/**
	 * list排序
	 * 
	 * @param list
	 * @param comparator
	 * @return 返回按comparator排好序的list eg:User:id name两个属性 List<User> userList =
	 *         new ArrayList<User>(); userList.add(new User(1,"abc"));
	 *         userList.add(new User(3, "ccd")); userList.add(new User(2,
	 *         "bde")); 1.按user名字排序 userList = ListUtils.sortList(userList, (p1,
	 *         p2) -> p1.getName().compareTo(p2.getName())); 2.按user Id排序
	 *         userList = ListUtils.sortList(userList, (p1, p2) ->
	 *         p1.getId()-p2.getId());
	 */
	public static <T> List<T> sortList(List<T> list, Comparator<? super T> comparator) {
		if (null == list || list.isEmpty())
			return new ArrayList<T>();
		if (null == comparator)
			return list;
		return list.stream().sorted(comparator).collect(Collectors.toList());

	}

	/**
	 * 判读list中的元素是不是全部满足 predicate的条件
	 * 
	 * @param list
	 * @param predicate
	 * @return 全部满足 true 有不满足的 false eg：判断list中的user的id是不是均小于4 List
	 *         <User> userList = new ArrayList<User>(); userList.add(new
	 *         User(1,"abc")); userList.add(new User(3, "ccd"));
	 *         userList.add(new User(2, "bde"));
	 *         System.out.println(ListUtils.isAllMatch(userList, u ->
	 *         u.getId()<4)); 输出 true
	 */
	public static <T> boolean isAllMatch(List<T> list, Predicate<? super T> predicate) {
		if (null == list || list.isEmpty())
			return false;
		if (null == predicate)
			return false;
		return list.stream().allMatch(predicate);
	}

	/**
	 * 只要有一个元素满足predicate的条件 返回true
	 * 
	 * @param list
	 * @param predicate
	 * @return eg：判断list中的user的id是不是有一个大于4 List<User> userList = new ArrayList
	 *         <User>(); userList.add(new User(1,"abc")); userList.add(new
	 *         User(3, "ccd")); userList.add(new User(2, "bde"));
	 *         System.out.println(ListUtils.isAllMatch(userList, u ->
	 *         u.getId()>4)); return false
	 */
	public static <T> boolean isAnyMatch(List<T> list, Predicate<? super T> predicate) {
		if (null == list || list.isEmpty())
			return false;
		if (null == predicate)
			return false;
		return list.stream().anyMatch(predicate);
	}

	/**
	 * list去重
	 * 
	 * @param list
	 * @return eg: list[1,2,2]---distinctList(list)---list[1,2]
	 */
	public static <T> List<T> distinctList(List<T> list) {
		if (null == list || list.isEmpty())
			return new ArrayList<T>();
		return list.stream().distinct().collect(Collectors.toList());
	}

	/**
	 * 递归获得多个list的笛卡尔积 eg[1],[8808],[1,2,3]-->[[1,8808,1],[1,8808,2]]
	 * 参考：http://stackoverflow.com/questions/714108/cartesian-product-of-
	 * arbitrary-sets-in-java
	 * 
	 * @param lists
	 * @return
	 */
	public static <T> List<List<T>> cartesianProduct(List<List<T>> lists) {
		List<List<T>> resultLists = new ArrayList<List<T>>();
		if (lists.size() == 0) {
			resultLists.add(new ArrayList<T>());
			return resultLists;
		} else {
			List<T> firstList = lists.get(0);
			List<List<T>> remainingLists = cartesianProduct(lists.subList(1, lists.size()));
			for (T condition : firstList) {
				for (List<T> remainingList : remainingLists) {
					ArrayList<T> resultList = new ArrayList<T>();
					resultList.add(condition);
					resultList.addAll(remainingList);
					resultLists.add(resultList);
				}
			}
		}
		return resultLists;
	}

	/**
	 * 将list转为map
	 *
	 * @param list
	 * @param predicate1
	 *            key
	 * @param predicate2
	 *            value
	 * @return
	 * 
	 *  * //省略list构造过程
	 *	Map<Integer, User> map = ListUtil.transformToMap(userList, p->p.getId(), p->p);
	 */
	public static <K, V, T> Map<K, V> transformToMap(List<T> list, Function<T, K> predicate1,
			Function<T, V> predicate2) {
		return list.stream().collect(Collectors.toMap(predicate1, predicate2));
	}
	
	/**
	 * 将list<T>转为Map<K,List<V>>
	 * @param list
	 * @param predicate1 map中的key
	 * @param predicate2 map中的list的元素
	 * @return
	 * Map<Integer, List<String>> deviceMap = ListUtil.transformToMapList(list, p->p.getDeviceType(), p->p.getDeviceId());
	 */
	public static <K,V,T> Map<K, List<V>> transformToMapList(List<T> list, Function<T, K> predicate1, Function<T,V> predicate2){
		return list.stream().collect(
				Collectors.groupingBy(predicate1, 
                Collectors.mapping(predicate2, 
                        Collectors.toList())));
	}
	
	public static void main(String[] args) {
		List<User> list = new ArrayList<User>();
		//省略list的构造
		Map<Integer, List<Integer>> deviceMap = ListUtil.transformToMapList(list, p->p.getAge(), p->p.getId());
	}
}
