package com.zhxin.common.utils;

import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

/**
 * @ClassName SpringUtil
 * @Description //Spring工具类,可以方便地把类委托给Spring容器管理
 * @Author singleZhang
 * @Email 405780096@qq.com
 * @Date 2020/11/13 0013 上午 9:56
 **/
@Component
public class SpringUtil implements BeanFactoryPostProcessor {
    /**
     * Spring应用上下文环境
     */
    private static ConfigurableListableBeanFactory beanFactory;

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory){
        SpringUtil.beanFactory = beanFactory;
    }

    /**
     * 获取对象
     */
    @SuppressWarnings("unchecked")
    public static <T> T getBean(String name){
        return (T) beanFactory.getBean(name);
    }

    /**
     * 获取类型为requiredType的对象
     */
    public static <T> T getBean(Class<T> clz){
        return beanFactory.getBean(clz);
    }

    /**
     * 如果BeanFactory包含一个与所给名称匹配的bean定义，则返回true
     */
    public static boolean containsBean(String name) {
        return beanFactory.containsBean(name);
    }

    /**
     * 判断以给定名字注册的bean定义是一个singleton还是一个prototype
     */
    public static boolean isSingleton(String name){
        return beanFactory.isSingleton(name);
    }

    /**
     * 获取注册对象的类型
     */
    public static Class<?> getType(String name){
        return beanFactory.getType(name);
    }

    /**
     * 如果给定的bean名字在bean定义中有别名，则返回这些别名
     */
    public static String[] getAliases(String name){
        return beanFactory.getAliases(name);
    }

    /**
     * 获取aop代理对象
     */
    @SuppressWarnings("unchecked")
    public static <T> T getAopProxy(T invoker){
        return (T) AopContext.currentProxy();
    }
}
