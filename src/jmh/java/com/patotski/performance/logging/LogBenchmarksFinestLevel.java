package com.patotski.performance.logging;

import org.openjdk.jmh.annotations.Benchmark;

import java.util.logging.Level;
import java.util.logging.Logger;

public class LogBenchmarksFinestLevel {

    static final Logger logger = Logger.getLogger(LogBenchmarksFinestLevel.class.getName());

    static final String PARAM_1 = "value 1";
    static final String PARAM_2 = "value 2";

    static {
        logger.setLevel(Level.WARNING);
    }

    @Benchmark
    public void logString() {
        logger.fine("Fine message");
    }

    @Benchmark
    public void logStringSupplier() {
        logger.fine(() -> "Fine message");
    }

    @Benchmark
    public void logFormatedString() {
        logger.fine(String.format("Fine message: %s and %s", PARAM_1, PARAM_2));
    }

    @Benchmark
    public void logFormatedStrinSupplier() {
        logger.fine(() -> String.format("Fine message: %s and %s", PARAM_1, PARAM_2));
    }
}
