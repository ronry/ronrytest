package com.ronrytest.mongo.util;

import java.beans.PropertyDescriptor;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.beanutils.PropertyUtils;



public class BeanDescription {
    private Map<String, PropertyDescriptor> description = new HashMap<String, PropertyDescriptor>();
    private Map<String, PropertyDescriptor> writeMethodToDescriptor = new HashMap<String, PropertyDescriptor>();
    private Map<String, PropertyDescriptor> readMethodToDescriptor = new HashMap<String, PropertyDescriptor>();
    private Map<String, String> writeMethodToPropertyName = new HashMap<String, String>();
    private Map<String, String> readMethodToPropertyName = new HashMap<String, String>();

    private BeanDescription() {
    }

    private static Map<String, BeanDescription> knownBeanDescriptions = new ConcurrentHashMap<String, BeanDescription>();

    public static BeanDescription describe(Class beanClass) {
        BeanDescription beanDescription = knownBeanDescriptions.get(beanClass.getName());
        if(beanDescription != null) {
            return beanDescription;
        }

        try {
            beanDescription = new BeanDescription();
            PropertyDescriptor[] descriptors = PropertyUtils.getPropertyDescriptors(beanClass);

            for(PropertyDescriptor descriptor : descriptors) {
                if(descriptor.getReadMethod() == null || descriptor.getWriteMethod() == null) {
                    continue;
                }

                beanDescription.description.put(descriptor.getName(), descriptor);

                beanDescription.readMethodToDescriptor.put(
                        descriptor.getReadMethod().getName(), descriptor);

                beanDescription.writeMethodToDescriptor.put(
                        descriptor.getWriteMethod().getName(), descriptor);

                beanDescription.readMethodToPropertyName.put(
                        descriptor.getReadMethod().getName(), descriptor.getName());

                beanDescription.writeMethodToPropertyName.put(
                        descriptor.getWriteMethod().getName(), descriptor.getName());
            }
            knownBeanDescriptions.put(beanClass.getName(), beanDescription);
            return beanDescription;
        } catch(Throwable t) {
            throw new RuntimeException(t);
        }
    }

    public PropertyDescriptor getPropertyDescriptor(String propertyName) {
        return description.get(propertyName);
    }

    public PropertyDescriptor getWriteMethodDescriptor(String writeMethodName) {
        return writeMethodToDescriptor.get(writeMethodName);
    }

    public PropertyDescriptor getReadMethodDescriptor(String readMethodName) {
        return readMethodToDescriptor.get(readMethodName);
    }

    public String getPropertyNameByReadMethod(String readMethodName) {
        return readMethodToPropertyName.get(readMethodName);
    }

    public String getPropertyNameByWriteMethod(String writeMethodName) {
        return writeMethodToPropertyName.get(writeMethodName);
    }

    public Set<String> getPropertyNames() {
        return description.keySet();
    }
}