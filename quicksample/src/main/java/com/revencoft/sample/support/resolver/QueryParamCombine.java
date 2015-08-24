package com.revencoft.sample.support.resolver;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.validation.annotation.Validated;

@Target(value={ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Validated
/**
 * 用于controller层，对方法参数进行查询参数绑定和数据校验
 * @author mengqingyan
 * @version
 */
public @interface QueryParamCombine {

}
