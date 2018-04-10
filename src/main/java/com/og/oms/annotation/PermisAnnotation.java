package com.og.oms.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 权限注解用于指定方法权限和验证权限
 *
 * @author jeff
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface PermisAnnotation {

    /**
     * 流水id
     */
    int id();

    /**
     * 显示名称
     */
    String name();
}

