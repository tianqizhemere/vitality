package top.tianqi.vitality.tools.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 获取Spring容器对象
 * @Author wkh
 * @Date 2020/8/6 9:30
 */
public class SpringUtil implements ApplicationContextAware {

    /** 应用上下文对象 */
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (SpringUtil.applicationContext == null) {
            SpringUtil.applicationContext = applicationContext;
        }
    }

    /**
     * 根据class类型来获取bean
     * @param clazz 字节码对象
     * @return
     */
    public static <T> T getBean(Class<T> clazz) {
        return applicationContext.getBean(clazz);
    }

    /**
     * 获取applicationContext上下文对象
     * @return
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 通过name获取spring容器对象
     * @param name
     * @return
     */
    public static Object getName(String name) {
        return applicationContext.getBean(name);
    }


    /**
     * 获取spring容器对象
     * @param name spring对象bean名称
     * @param clazz class类型
     * @return
     */
    public static <T> T getBean(String name, Class<T> clazz) {
        return applicationContext.getBean(name, clazz);
    }
}
