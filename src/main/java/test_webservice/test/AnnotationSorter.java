package test_webservice.test;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by phillipellis on 9/21/16.
 */
public class AnnotationSorter {
    public static Field[] sort(Field[] fields){
        Arrays.sort(fields, new Comparator<Field>() {
            @Override
            public int compare(Field o1, Field o2) {
                Order or1 = o1.getAnnotation(Order.class);
                Order or2 = o2.getAnnotation(Order.class);
                // nulls last
                if (or1 != null && or2 != null) {
                    return or1.order() - or2.order();
                } else
                if (or1 != null && or2 == null) {
                    return -1;
                } else
                if (or1 == null && or2 != null) {
                    return 1;
                }
                return o1.getName().compareTo(o2.getName());
            }
        });
        return fields;
    }
}
