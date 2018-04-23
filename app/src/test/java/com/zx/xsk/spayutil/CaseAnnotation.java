package com.zx.xsk.spayutil;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by sjy on 2017/11/15.
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CaseAnnotation {
        public int mId();
        public String mDescription()
                default "no description";
}
