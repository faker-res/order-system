package com.tuniu.ord.core.init;

import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Spring容器应用上下文代理工具类
 * 
 * @author fangzhongwei
 * 
 */
public class ApplicationContextProxy implements ApplicationContextAware {

    private static ApplicationContext ac;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ac = applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
        return ac;
    }

    public static <T> T getBean(Class<T> requiredType) {
        return ac.getBean(requiredType);
    }

    public static <T> T getBean(Class<T> requiredType, Object... args) {
        return ac.getBean(requiredType, args);
    }

    public static Object getBean(String name) {
        return ac.getBean(name);
    }

    public static Object getBean(String name, Object... args) {
        return ac.getBean(name, args);
    }

    public static <T> T getBean(String name, Class<T> requiredType) {
        return ac.getBean(name, requiredType);
    }

    public static <T> Map<String, T> getBeanOfType(Class<T> type) {
        return ac.getBeansOfType(type);
    }
}
