package com.patotski.performance.logging;

import com.patotski.performance.utils.BenchmarkUtils;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;
import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

@BenchmarkMode({Mode.AverageTime})
@Warmup(iterations = 2, time = 2, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 5, timeUnit = TimeUnit.SECONDS)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Fork(1)
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

    public static void main(String[] args) throws Exception {
        BenchmarkUtils.runBenchmark(LoggerBenchmarks.class);
    }
}