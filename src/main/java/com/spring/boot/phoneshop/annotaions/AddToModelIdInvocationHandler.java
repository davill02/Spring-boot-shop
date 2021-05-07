package com.spring.boot.phoneshop.annotaions;

import com.spring.boot.phoneshop.entities.Product;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

public class AddToModelIdInvocationHandler implements MethodInterceptor {
    private Object realBean;

    public AddToModelIdInvocationHandler(Object realBean) {
        this.realBean = realBean;
    }

    private Optional<AddIdToModelName> findAnnotationAddToModelId(Method method) {
        Optional<AddIdToModelName> addIdToModelName = Optional.empty();
        for (Annotation annotation : method.getAnnotations()) {
            if (annotation.getClass().equals(AddIdToModelName.class)) {
                addIdToModelName = Optional.of((AddIdToModelName) annotation);
                break;
            }
        }
        return addIdToModelName;
    }


    private Optional<Method> findRealBeanMethod(Method method) {
        return Arrays.stream(realBean.getClass().getMethods())
                .filter(method1 -> method1.getName().equals(method.getName()))
                .filter(method1 -> Arrays.equals(method1.getParameterTypes(), method.getParameterTypes()))
                .findAny();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        Optional<Method> realBeanMethod = findRealBeanMethod(method);
        Object result = methodProxy.invokeSuper(o, objects);
        if (realBeanMethod.isPresent() && findAnnotationAddToModelId(realBeanMethod.get()).isPresent()) {
            if (IncreasePriceProductBeanPostProcessor.isReturnCollectionsOfProducts(realBeanMethod.get())) {
                Collection<Product> products = (Collection<Product>) result;
                products.forEach(product -> product.setModel(product.getModel() + product.getId()));
            }
        }
        return result;
    }
}
