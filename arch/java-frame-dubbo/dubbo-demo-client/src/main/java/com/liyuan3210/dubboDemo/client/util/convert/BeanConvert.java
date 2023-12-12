package com.liyuan3210.dubboDemo.client.util.convert;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;

/**
 * @ClassName BeanConvert
 * @Description TODO
 * @Author <a href="mailto:franksoongyn@gmail.com">yexian</a>
 * @Date 2020/4/7 7:22 下午
 * @Version 1.0
 **/
public class BeanConvert {

    private static Log logger = LogFactory.getLog(BeanConvert.class);
    /**
     * 对象之间相互转换  只转换相同的属性  DO、DTO互转时使用
     *
     * @param source      来源实例  属性都有get方法
     * @param targetClass 待转换类型
     * @param <T>         返回类型
     * @return
     */
    public static <T> T bean2Object(Object source, Class<T> targetClass) {
        if (source == null) {
            return null;
        }
        try {
            T target = null;
            target = targetClass.newInstance();
            BeanUtils.copyProperties(source, target);
            return target;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("bean2Object Error: " + e.getMessage());
            //throw new UtilException("bean2Object Error: " + e.getMessage());
        }
        return null;
    }

    /**
     * 将beanList互转  注意  只转换bean对象
     * @param source       来源list
     * @param targetClass  待转换类型
     * @param <T>          返回类型
     * @param <U>          入参类型
     * @return
     */
    public static <T, U> List<T> beanList2ObjectList(List<U> source, Class<T> targetClass) {
        List<T> list = new ArrayList<>();
        source.forEach((d) -> {
            list.add(BeanConvert.bean2Object(d, targetClass));
        });
        return list;
    }

    /**
     * 对象转换为 map对象  此处转换为HashMap
     * @param source     源对象  属性都有get方法
     * @return           以属性名为key的map
     */
    public static Map<String, Object> bean2Map(Object source) {
        if (source == null) {
            return null;
        }

        try {
            Map<String, Object> map = new HashMap<String, Object>(16);
            BeanInfo beanInfo = Introspector.getBeanInfo(source.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();

                // 过滤class属性
                if (!key.equals("class")) {
                    // 得到property对应的getter方法
                    Method getter = property.getReadMethod();
                    Object value = getter.invoke(source);
                    if (null != value) {
                        map.put(key, value);
                    }
                }
            }
            return map;
        } catch (Exception e) {
//            logger.error("transBean2Map source: " +  JsonUtil.toJson(source) + ", Error: " + e.getMessage());
            //throw new UtilException("transBean2Map Error: " + e.getMessage());
        }
        return null;
    }

    /**
     * 转换为 TreeMap<String, String>
     * @param source 源对象  属性必须有get方法
     * @return
     */
    public static TreeMap<String, String> bean2TreeMap(Object source) {
        if (source == null) {
            return null;
        }
        TreeMap<String, String> map = new TreeMap<String, String>();
        bean2Map(source,map);
        return map;
    }


    /**
     * 转换为 HashMap<String, String>
     *
     * @param source 源对象  属性必须有get方法
     * @return
     */
    public static HashMap<String, String> bean2HashMap(Object source) {
        if (source == null) {
            return null;
        }
        HashMap<String, String> map = new HashMap<String, String>();
        bean2Map(source,map);
        return map;
    }


    private static void  bean2Map(Object source,Map<String,String> map){
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(source.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();

                // 过滤class属性
                if (!key.equals("class")) {
                    // 得到property对应的getter方法
                    Method getter = property.getReadMethod();
                    Object value = getter.invoke(source);
                    if (null != value) {
                        map.put(key, String.valueOf(value));
                    }

                }

            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("transBean2Map Error: " + e.getMessage());
            //throw new UtilException("transBean2Map Error: " + e.getMessage());
        }
    }
}
