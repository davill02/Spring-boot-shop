package com.spring.boot.phoneshop.annotaions;

import com.spring.boot.phoneshop.entities.Product;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

abstract class SimpleProductBeanPostProcessor implements BeanPostProcessor {
    protected Map<String, Object> nameBean2Bean = new HashMap<>();

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        for (Method method : bean.getClass().getMethods()) {
            if (isAnnotatedBy(method)) {
                addToMapIfReturnTypeProductCollection(bean, beanName, method);
                addToMapIfReturnTypeProduct(bean, beanName, method);
                addToMapIfReturnOptionalProduct(bean, beanName, method);
            }
        }
        return bean;
    }

    abstract protected boolean isAnnotatedBy(Method method);

    private void addToMapIfReturnTypeProductCollection(Object bean, String beanName, Method method) {
        if (isReturnCollectionsOfProducts(method)) {
            nameBean2Bean.put(beanName, bean);
        }
    }

    private void addToMapIfReturnOptionalProduct(Object bean, String beanName, Method method) {
        if (isOptionalOfProduct(method)) {
            nameBean2Bean.put(beanName, bean);
        }
    }

    private void addToMapIfReturnTypeProduct(Object bean, String beanName, Method method) {
        if (method.getGenericReturnType() instanceof Product) {
            nameBean2Bean.put(beanName, bean);
        }
    }

    public static Integer getValueAnnotationIncreasePrice(Method methodWithAnnotation) {
        int result = 0;
        Optional<Annotation> increasePrice = findAnnotationByClass(methodWithAnnotation, IncreasePrice.class);
        if (increasePrice.isPresent()) {
            result = ((IncreasePrice) increasePrice.get()).value();
        }
        return result;
    }

    public static Optional<Annotation> findAnnotationByClass(Method method, Class<?> annotationClass) {
        Optional<Annotation> result = Optional.empty();
        for (Annotation annotation : method.getAnnotations()) {
            if (annotation.annotationType().equals(annotationClass)) {
                result = Optional.of(annotation);
                break;
            }
        }
        return result;
    }


    public static boolean isReturnCollectionsOfProducts(Method method) {
        boolean result = false;
        if (method.getGenericReturnType() instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) method.getGenericReturnType();
            if (Collection.class.isAssignableFrom(((Class<?>) parameterizedType.getRawType()))
                    && Product.class.isAssignableFrom((Class<?>) parameterizedType.getActualTypeArguments()[0])) {
                result = true;
            }
        }
        return result;
    }

    public static boolean isAnnotatedIncreasePrice(Method method) {
        return findAnnotationByClass(method, IncreasePrice.class).isPresent();
    }

    public static boolean isOptionalOfProduct(Method method) {
        boolean result = false;
        if (method.getGenericReturnType() instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) method.getGenericReturnType();
            if (Optional.class.isAssignableFrom((Class<?>) parameterizedType.getRawType())
                    && Product.class.isAssignableFrom((Class<?>) parameterizedType.getActualTypeArguments()[0])) {
                result = true;
            }
        }
        return result;
    }

}
