package cf.lihao.testlib;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@Documented

public @interface TestLibAnnotation {
	String key();
	String author() default "";
	String[] keyDescription() default {"the author is too lazy , no description writed"} ;
	
}
