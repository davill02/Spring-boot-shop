package com.spring.boot.phoneshop.annotaions;

import org.springframework.beans.BeansException;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

@Component
public class IncreasePriceProductBeanPostProcessor extends SimpleProductBeanPostProcessor {

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (nameBean2Bean.containsKey(beanName)) {
            Object realBean = nameBean2Bean.get(beanName);
            Object proxyInstance = Proxy.newProxyInstance(bean.getClass().getClassLoader(), bean.getClass().getInterfaces(), new IncreasePriceInvocationHandler(realBean, bean));
            return proxyInstance;
        }
        return bean;
    }

    @Override
    protected boolean isAnnotatedBy(Method method) {
        return findAnnotationByClass(method, IncreasePrice.class).isPresent();
    }
}
