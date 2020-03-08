package com.example.shortener.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Service
public class DynConfService {
    private Set<Object> beans = new HashSet<>();

    public void addBean(Object object) {
        beans.add(object);
    }

    public void onConfigChanged(Map<String, String> configs) {
        for (Object bean : beans) {
            try {
                onChange(bean, configs);
            } catch (Exception e) {
                throw new IllegalStateException("Cannot process config change for bean " + bean + ": " + e, e);
            }
        }
    }

    private void onChange(Object bean, Map<String, String> configs) {
        Field[] declaredFields = bean.getClass().getDeclaredFields();
        for (Field declaredField : declaredFields) {
            Value annotation = declaredField.getDeclaredAnnotation(Value.class);
            if (annotation != null) {
                String propName = annotation.value();
                configs.keySet().forEach(k->{
                    if (propName.contains(k)) {
                        try {
                            Method valueOf = declaredField.getType().getDeclaredMethod("valueOf", String.class);
                            Object newValueCasted = valueOf.invoke(null, configs.get(k));
                            declaredField.setAccessible(true);
                            declaredField.set(bean, newValueCasted);
                        } catch (Exception e) {
                            throw new IllegalStateException(e);
                        }
                    }
                });
            }
        }
    }
}
