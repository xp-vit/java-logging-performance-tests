package com.patotski.performance.bigint;

import org.openjdk.jmh.annotations.*;

import java.math.BigInteger;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import static com.patotski.performance.utils.BenchmarkUtils.runBenchmark;

@State(Scope.Benchmark)
@BenchmarkMode({Mode.AverageTime})
@Warmup(iterations = 2, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 3, time = 5, timeUnit = TimeUnit.SECONDS)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Fork(1)
public class BigIntValidationBenchmarks {
    @State(Scope.Benchmark)
    public static class BigIntState {
        public String invalid = "424242424242boom";
        public String valid = "4242424242424242";

        public BigInteger bigInteger;

        public String stringPattern = "[0-9]+";

        public Pattern pattern = Pattern.compile(stringPattern);

        public String getString() {
            //5% of the time return invalid string
            if (ThreadLocalRandom.current().nextInt(20)==1) {
                return invalid;
            } else {
                return valid;
            }
        }
    }
    @Benchmark
    public String exceptionValidation(BigIntState state) {
        String stringToParse = state.getString();
        try {
            state.bigInteger = new BigInteger(stringToParse);
        } catch (NumberFormatException e) {
            return "invalid";
        }
        return "valid";
    }

   /* @Benchmark
    public String regExpValidation(BigIntState state) {
        String stringToParse = state.getString();
        if (stringToParse.matches(state.stringPattern)) {
            state.bigInteger = new BigInteger(stringToParse);
            return "valid";
        }
        return "invalid";
    }*/

    @Benchmark
    public String regExpCompiledPatternValidation(BigIntState state) {
        String stringToParse = state.getString();
        if (state.pattern.matcher(stringToParse).matches()) {
            state.bigInteger = new BigInteger(stringToParse);
            return "valid";
        }
        return "invalid";
    }

    public static void main(String[] args) throws Exception {
        runBenchmark(BigIntValidationBenchmarks.class);
    }
}