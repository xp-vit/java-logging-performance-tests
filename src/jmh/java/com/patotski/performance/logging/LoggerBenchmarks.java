package com.patotski.performance.logging;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

public class LoggerBenchmarks {
    static final Logger logger = LogManager.getLogger(LoggerBenchmarks.class);
    @State(Scope.Benchmark)
    public static class User{
        private String name = "John";
        private String surname = "Doe";

        public String getName() {
            return name;
        }

        public String getSurname() {
            return surname;
        }

        @Setup
        public void setup(){
            Configurator.setRootLevel(Level.ERROR);
        }
    }
    @Benchmark
    public void logStringFormatting(LoggerBenchmarks.User user) {
        logger.debug(String.format("User: %s and %s", user.getName(), user.getSurname()));
    }
    @Benchmark
    public void logStringConcat(LoggerBenchmarks.User user) {
        logger.debug("User: "+user.getName() + " and " + user.getSurname());
    }
    @Benchmark
    public void logStringParameterized(LoggerBenchmarks.User user) {
        logger.debug("User: {} and {}", user.getName(), user.getSurname());
    }

    @Benchmark
    public void logStringIfDebug(LoggerBenchmarks.User user) {
        if(logger.isDebugEnabled()){
            logger.debug("User: {} and {}", user.getName(), user.getSurname());
        }
    }
    @Benchmark
    public void logStringSupplier(LoggerBenchmarks.User user) {
        logger.debug(() -> "User: "+user.getName() + " and " + user.getSurname());
    }
}