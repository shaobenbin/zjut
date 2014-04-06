package cn.edu.zjut.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.sevenstar.persistent.db.find.annotation.model.SSOneToMany;
import org.sevenstar.util.BeanHelper;

/**
 * 对象格式化的工具类
 * 
 * @author ben.s
 * @email shaobenbin@gmail.com
 * @date 2013.6.11
 * 
 */
public class ObjectFormat {

	/**
	 * 将对象中的基本类型的field设置成map格式.
	 * 
	 * @param obj
	 * @return
	 */
	public static Map object2Map(Object obj) {
		Field[] fields = BeanHelper.getFields(obj.getClass());
		Map map = new HashMap();
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			if (isBaseType(field.getType())) {
				map.put(field.getName(), BeanHelper.getPropertyValue(field
						.getName(), obj));
			}
		}
		return map;
	}

	public static Object setMapValue2Object(Class clazz, Map valueMap) {
		if (valueMap == null || valueMap.isEmpty()) {
			return null;
		}
		Field[] fields = BeanHelper.getFields(clazz);
		Object object = BeanHelper.newInstance(clazz);
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			if (isBaseType(field.getType())
					&& valueMap.get(field.getName()) != null) {
				BeanHelper.setPropertyValue(object, field.getName(), valueMap
						.get(field.getName()));
			} else if (field.getType().getName().equals(List.class.getName())) {
				List list = (List) valueMap.get(field.getName());
				String className = null;
				try {
					className = field.getAnnotation(SSOneToMany.class)
							.className();
				} catch (RuntimeException e) {
					continue;
				}
				Class className2Class = null;
				if (className != null) {

					try {
						className2Class = Class.forName(className);
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						continue;
					}

				}

				if (list == null || list.isEmpty()) {
					continue;
				}
				List setList = new ArrayList();
				for (int j = 0; j < list.size(); j++) {
					Object obj = list.get(j);
					if (className == null || obj == null
							|| !(obj instanceof Map)) {
						setList.add(obj);
					} else {
						setList.add(setMapValue2Object(className2Class,
								(Map) obj));
					}
				}
				BeanHelper.setPropertyValue(object, field.getName(), setList);
			} else if (!isJavaType(field.getType())
					&& valueMap.get(field.getName()) != null
					&& (valueMap.get(field.getName()) instanceof Map)) {
				// 如果是其它类型的话就单独处理
				BeanHelper.setPropertyValue(object, field.getName(),
						setMapValue2Object(field.getType(), (Map) valueMap
								.get(field.getName())));
			}
		}
		return object;
	}

	/**
	 * 判断是否是基本类型
	 * 
	 * @param clazz
	 * @return
	 */
	public static boolean isBaseType(Class clazz) {
		if (clazz.getName().equals(String.class.getName())
				|| clazz.getName().equals(Long.class.getName())
				|| clazz.getName().equals(Integer.class.getName())
				|| clazz.getName().equals(Short.class.getName())
				|| clazz.getName().equals(Float.class.getName())
				|| clazz.getName().equals(Double.class.getName())
				|| clazz.getName().equals(Date.class.getName())) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isJavaType(Class clazz) {
		if (isBaseType(clazz) || clazz.getName().equals(List.class.getName())
				|| clazz.getName().equals(Map.class.getName())) {
			return true;
		} else {
			return false;
		}
	}

}
