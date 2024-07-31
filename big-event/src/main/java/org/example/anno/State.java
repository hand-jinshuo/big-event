package org.example.anno;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.example.validation.StateValidation;

import java.lang.annotation.*;

//元注解
@Documented
//指定校验类
@Constraint(
        validatedBy = {StateValidation.class}
)
//元注解 表示用在哪 FIELD用在属性上
@Target({ElementType.FIELD})
//元注解 保留到运行时阶段
@Retention(RetentionPolicy.RUNTIME)
public @interface State {

    //校验失败后的提示信息
    String message() default "state只能是已发布或者草稿";

    //指定分组
    Class<?>[] groups() default {};

    //负载 一般不用
    Class<? extends Payload>[] payload() default {};
}
