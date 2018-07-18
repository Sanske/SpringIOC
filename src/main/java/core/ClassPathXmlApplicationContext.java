package core;

import config.Bean;
import config.Property;
import config.XmlConfig;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @author sanske
 * @date 2018/7/16 下午5:01
 * 将在 Bean里面的值实例化IOC容器中
 **/
public class ClassPathXmlApplicationContext implements BeanFactory {
    //定义一个IOC容器
    private Map<String, Object> IOC;
    private Map<String, Bean> config;

    public ClassPathXmlApplicationContext(String path) {
        IOC = new HashMap<String, Object>(16);
        config = XmlConfig.getConfig(path);
        if (config != null) {
            for (Entry<String, Bean> beanEntry : config.entrySet()) {
                String beanId = beanEntry.getKey();
                Bean bean = beanEntry.getValue();

                //bean对象是一个引用，需要赋值；
                Object obj = createBean(bean);
                IOC.put(beanId, obj);
            }
        }
    }

    /**
     * 根据对象生成实例，根据反射来生成实例
     *
     * @param bean
     * @return
     */
    private Object createBean(Bean bean) {
        String beanId = bean.getId();
        String className = bean.getClassName();
        Class c = null;
        Object obj = null;
        try {
            c = Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("您的 class 不合法" + className);
        }

        try {
            obj = c.newInstance();
        } catch (Exception e) {
            throw new RuntimeException("类名实例化错误" + className);
        }

        try {
            if (bean.getProperties() != null) {
                for (Property p : bean.getProperties()) {
                    // 分别对2种不同的情况进行处理
                    if (p.getValue() != null) {
                        Field field = c.getDeclaredField(p.getName());
                        field.setAccessible(true);
                        field.set(obj, p.getValue());
                    }

                    if (p.getRef() != null) {
                        Field field = c.getDeclaredField(p.getName());
                        field.setAccessible(true);
                        Object object = IOC.get(p.getRef());
                        field.set(obj, object);
                    }
                }
            }

        } catch (Exception e) {
            throw new RuntimeException("解析property错误");
        }
        return obj;
    }


    @Override
    public Object getBean(String beanName) {
        return IOC.get(beanName);
    }
}
