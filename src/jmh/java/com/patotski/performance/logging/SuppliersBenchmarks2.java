package com.patotski.performance.logging;

import org.openjdk.jmh.annotations.Benchmark;

import java.util.Optional;

public class SuppliersBenchmarks2 {

    //@Benchmark
    public void optionalWithOrElseSupplier() {
        Optional<String> optional = Optional.empty();
        String s = optional.orElseGet(() -> String.format("Hello %s, %s friend!", "world!", "Dear"));
        if (!s.isBlank()) {
            s = "";
        }
    }
}
