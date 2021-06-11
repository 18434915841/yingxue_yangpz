package com.baizhi.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//自定义一个注解


@Target(ElementType.METHOD)     //设置注解使用的位置   可用于方法或类上
@Retention(RetentionPolicy.RUNTIME)    //运行时生效
public @interface AddLog {
    String value() default "";
}
