/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.negod.archetype;

import com.negod.archetype.entity.Account;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import org.junit.Test;

/**
 *
 * @author Joakim Johansson ( joakimjohansson@outlook.com )
 */
public class AnnotationTest {

    @Test
    public void test() {
        Field[] declaredFields = Account.class.getDeclaredFields();
        for (Field field : declaredFields) {
            System.out.println(field.getName());
            Annotation[] annotations = field.getAnnotations();
            for (Annotation annotation : annotations) {
                String annotationName = org.hibernate.search.annotations.Field.class.getName();
                if (annotation.annotationType().getName().equals(annotationName)) {
                    System.out.println("YEY");
                }
            }
        }
    }

}
