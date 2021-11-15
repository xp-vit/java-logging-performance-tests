package com.patotski.performance.logging;

import org.openjdk.jmh.annotations.Benchmark;

import java.util.Optional;

public class SuppliersBenchmarks3 {

    //@Benchmark
    public void optionalWithOrElseSuplierNotEmpty() {
        Optional<String> optional = Optional.of("aaa");
        String s = optional.orElseGet(() -> String.format("Hello %s, %s friend!", "world!", "Dear"));
        if (!s.isBlank()) {
            s = "";
        }
    }
}
