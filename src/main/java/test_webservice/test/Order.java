package test_webservice.test;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by phillipellis on 9/21/16.
 */


@Retention(RetentionPolicy.RUNTIME)
public @interface Order {
    public int order() default 0;

}
