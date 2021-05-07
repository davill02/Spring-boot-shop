package com.spring.boot.phoneshop.annotaions;

import com.spring.boot.phoneshop.services.PhoneService;
import org.springframework.beans.BeansException;
import org.springframework.cglib.proxy.Enhancer;

import java.lang.reflect.Method;


public class AddIdToModelNameProductBeanPostProcessor extends SimpleProductBeanPostProcessor {


    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (nameBean2Bean.containsKey(beanName)) {
            Object realBean = nameBean2Bean.get(beanName);
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(PhoneService.class);
            enhancer.setCallback(new AddToModelIdInvocationHandler(realBean));
            return (PhoneService) enhancer.create();
        }
        return bean;
    }

    @Override
    protected boolean isAnnotatedBy(Method method) {
        return findAnnotationByClass(method, AddIdToModelName.class).isPresent();
    }
}
