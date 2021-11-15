package com.patotski.performance.logging;

import org.openjdk.jmh.annotations.Benchmark;

import java.util.Optional;

public class SuppliersBenchmarks0 {

    //@Benchmark
    public void optionalWithOrElseValue() {
        Optional<String> optional = Optional.of("aaa");
        String s = optional.orElse(String.format("Hello %s, %s friend!", "world!", "Dear"));
        if (!s.isBlank()) {
            s = "";
        }
    }
}
