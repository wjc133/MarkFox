package com.elite.tools.markfox.common.utils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class BeanUtils {
    /**
     * 将一个 JavaBean 对象转化为一个  Map
     *
     * @param bean 要转化的JavaBean 对象
     * @return 转化出来的  Map 对象
     * @throws IntrospectionException    如果分析类属性失败
     * @throws IllegalAccessException    如果实例化 JavaBean 失败
     * @throws InvocationTargetException 如果调用属性的 setter 方法失败
     */
    public static Map<String, Object> convertBean(Object bean)
            throws Exception {
        Class type = bean.getClass();
        Map<String, Object> returnMap = Maps.newHashMap();
        BeanInfo beanInfo = Introspector.getBeanInfo(type);

        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor descriptor : propertyDescriptors) {
            String propertyName = descriptor.getName();
            String finalName = propertyName;
            Field field = getClassField(type, propertyName);
            if (field != null) {
                Alias annotation = field.getAnnotation(Alias.class);
                if (annotation != null) {
                    finalName = annotation.value();
                }
            }
            if (!propertyName.equals("class")) {
                Method readMethod = descriptor.getReadMethod();
                Object result = readMethod.invoke(bean);
                if (result != null) {
                    if (result instanceof Date) {
                        returnMap.put(finalName, ((Date) result).getTime());
                    } else {
                        returnMap.put(finalName, result);
                    }
                } else {
                    returnMap.put(finalName, "");
                }
            }
        }
        return returnMap;
    }

    public static List<Map<String, Object>> batchConvertBean(List beans) throws Exception {
        List<Map<String, Object>> result = Lists.newArrayList();
        for (Object bean : beans) {
            Map<String, Object> map = convertBean(bean);
            result.add(map);
        }
        return result;
    }

    /**
     * 将Bean平铺在Map中
     * Bean中的Map将全部展开
     */
    // TODO: 2017/5/18 有时间把这个方法也加上Alias注解的解析
    public static Map<String, Object> tileOpenBean(Object bean)
            throws IntrospectionException, IllegalAccessException, InvocationTargetException {
        if (bean instanceof Map) {
            return (Map<String, Object>) bean;
        }
        Class type = bean.getClass();
        Map<String, Object> returnMap = Maps.newHashMap();
        BeanInfo beanInfo = Introspector.getBeanInfo(type);

        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor descriptor : propertyDescriptors) {
            String propertyName = descriptor.getName();
            if (!propertyName.equals("class")) {
                Method readMethod = descriptor.getReadMethod();
                Object result = readMethod.invoke(bean);
                if (result != null) {
                    if (result instanceof Map) {
                        returnMap.putAll((Map<? extends String, ?>) result);
                    } else {
                        returnMap.put(propertyName, result);
                    }
                } else {
                    returnMap.put(propertyName, "");
                }
            }
        }
        return returnMap;
    }

    private static Field getClassField(Class aClazz, String aFieldName) {
        Field[] declaredFields = aClazz.getDeclaredFields();
        for (Field field : declaredFields) {
            // 注意：这里判断的方式，是用字符串的比较。很傻瓜，但能跑。要直接返回Field。我试验中，尝试返回Class，然后用getDeclaredField(String fieldName)，但是，失败了
            if (field.getName().equals(aFieldName)) {
                return field;// define in this class
            }
        }

        Class superclass = aClazz.getSuperclass();
        if (!superclass.isAssignableFrom(Object.class)) {// 简单的递归一下
            return getClassField(superclass, aFieldName);
        }
        return null;
    }
}  
