package com.example.muhammadhumayun.readqr.Validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)

/// <summary>
/// Defines Required as True for the Fields
/// </summary>
public @interface Required {
    public boolean value() default true;
}
