package com.abhishek.practice;

import java.util.Arrays;
import java.util.List;

/**
 * @author abhishek ringsia (abhishek.ringsia@autodesk.com).
 * @since 19 Jan, 2019 12:17 PM
 */
public interface Interface1 {

    default void myDefaultMethod(){
        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7);
        list.stream().map((n) -> n*n  ).forEach((n) ->System.out.print(n));
    }
}
