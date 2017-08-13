package com.atjl.utilex;

import java.util.Collection;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * spring上下文助手
 */
@Component
public class ApplicationContextHepler implements ApplicationContextAware {

	private static final ApplicationContext[] applicationContext = new ApplicationContext[1];

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		ApplicationContextHepler.applicationContext[0] = applicationContext;
	}

	public static ApplicationContext getApplicationContext() {
		return applicationContext[0];
	}

	/**
	 * 方法说明：<br>
	 * 获取bean对象
	 * 
	 * @param name
	 * @param clazz
	 * @return
	 */
	public static <T> T getBean(String name, Class<T> clazz) {
		return applicationContext[0].getBean(name, clazz);
	}
	
	public static <T> T getBean(Class<T> clazz) {
		return applicationContext[0].getBean(clazz);
	}

	/**
	 * 根据接口类名获得唯一的实现类的实例 
	 * 没有发现接口的实现类返回null值
	 * 超过一个实现类,随机返回一个实现类的实例
	 * @param clazz
	 * @return
	 */
	public static <T> T getUniqueBean(Class<T> clazz) {
		Map<String, T> map = applicationContext[0].getBeansOfType(clazz);
		Collection<T> list = map.values();
		if (list.iterator().hasNext()) {
			return list.iterator().next();
		}
		return null;
	}

	public static Object getBeanByName(String name) {
		return applicationContext[0].getBean(name);
	}



}
