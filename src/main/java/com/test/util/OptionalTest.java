package com.test.util;

import java.util.Optional;

public class OptionalTest {
    public static void main(String[] args) {
        Optional<String> noVal = Optional.empty();
        System.out.println(noVal.orElse("xx"));
        

        Optional<String> hasVal = Optional.of("aaa");
        System.out.println(hasVal.orElse("xx"));
    }
}
