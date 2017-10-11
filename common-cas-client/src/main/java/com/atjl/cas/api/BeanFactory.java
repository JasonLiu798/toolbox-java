package com.atjl.cas.api;

import java.util.Collection;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import com.atjl.cas.domain.CasConfig;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;


public class BeanFactory implements ServletContextListener {

    private static final Log logger = LogFactory.getLog(BeanFactory.class);

    public static final Class<CasUserService> BEAN_CAS_USER_SERVICE = CasUserService.class;
    public static final Class<CasConfig> BEAN_CAS_CONFIG = CasConfig.class;
    
    private static WebApplicationContext context;

    public static <T> T getBean(String beanId, boolean throwIfNull) {
        validateBeanFactory();
		T t = (T) context.getBean(beanId);
        if (t == null && throwIfNull) {
        	  throw new CASBaseException("can't find bean [" + beanId + "].");
        }
        return t;
    }

    public static <T> T getBean(Class<T> clazz, boolean throwIfNull) {
        validateBeanFactory();
        T t = context.getBean(clazz);
        if (t == null && throwIfNull) {
        	  throw new CASBaseException("can't find bean [" + clazz + "].");
        }
        return t;
    }
    /**
	 * 根据接口类名获得唯一的实现类的实例 
	 * 没有发现接口的实现类返回null值
	 * 超过一个实现类,随机返回一个实现类的实例
	 * @param clazz
	 * @return
	 */
	public static <T> T getUniqueBean(Class<T> clazz) {
		Map<String, T> map = context.getBeansOfType(clazz);
		Collection<T> list = map.values();
		if (list.iterator().hasNext()) {
			return list.iterator().next();
		}
		return null;
	}

	private static void validateBeanFactory() {
		if (context == null) {
            if (logger.isDebugEnabled()) {
                logger.debug("please config web.xml, add listener:");
                logger.debug("<listener>");
                logger.debug("    <listener-class>com.atjl.integration.BeanFactory</listener-class>");
                logger.debug("</listener>");
            }
            throw new CASBaseException("can't find com.atjl.integration.BeanFactory, please config BeanFactory!");
        }
	}

	@Override
    public void contextInitialized(ServletContextEvent servletcontextevent) {
    	ServletContext servletContext = servletcontextevent.getServletContext();
    	try {
    		context = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
        } catch (Exception e) {
        	context = WebApplicationContextUtils.getWebApplicationContext(servletContext);
            if (context == null) {
                throw new CASBaseException(e);
            }
        }
    }

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// Do nothing because of not use.
	}
}
