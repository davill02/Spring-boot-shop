package com.spring.boot.phoneshop.annotaions;

import com.spring.boot.phoneshop.entities.Product;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

public class IncreasePriceInvocationHandler implements InvocationHandler {
    private final Object bean;
    private Object pr;

    public IncreasePriceInvocationHandler(Object bean, Object pr) {
        this.bean = bean;
        this.pr = pr;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Optional<Method> methodOptional = findRealBeanMethod(method);
        Object result = method.invoke(pr, args);
        if (methodOptional.isPresent()
                && IncreasePriceProductBeanPostProcessor.isAnnotatedIncreasePrice(methodOptional.get())) {
            if (IncreasePriceProductBeanPostProcessor.isReturnCollectionsOfProducts(methodOptional.get())) {
                Collection<Product> products = (Collection<Product>) result;
                increasePrice(methodOptional.get(), products);
            } else {
                if (Product.class.isAssignableFrom(((Class<?>) methodOptional.get().getGenericReturnType()))) {
                    Product product = (Product) result;
                    increaseProductPrice(methodOptional.get(), product);
                }
                if (IncreasePriceProductBeanPostProcessor.isOptionalOfProduct(methodOptional.get())) {
                    Optional<Product> productOptional = (Optional<Product>) result;
                    productOptional.ifPresent(product -> increaseProductPrice(methodOptional.get(), product));
                }
            }
        }
        return result;
    }

    private void increaseProductPrice(Method method, Product product) {
        if (product != null && product.getPrice() != null) {
            BigDecimal addingPrice = BigDecimal.valueOf(IncreasePriceProductBeanPostProcessor.getValueAnnotationIncreasePrice(method));
            product.setPrice(product.getPrice().add(addingPrice));
        }
    }

    private Optional<Method> findRealBeanMethod(Method method) {
        return Arrays.stream(bean.getClass().getMethods())
                .filter(method1 -> method1.getName().equals(method.getName()))
                .filter(method1 -> Arrays.equals(method1.getParameterTypes(), method.getParameterTypes()))
                .findAny();
    }

    private void increasePrice(Method method, Collection<Product> products) {
        products.forEach(product -> increaseProductPrice(method, product));
    }
}
