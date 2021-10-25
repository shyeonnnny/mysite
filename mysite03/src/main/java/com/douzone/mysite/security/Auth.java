package com.douzone.mysite.security;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Auth {
//	public String value() default "USER";
	public String role() default "USER";
//	public boolean test() default false;
	
//	@Auth(value = "/aa", test=true)
// String이 있어서 value값 입력가능, boolean이있어서 test=true가능
}
