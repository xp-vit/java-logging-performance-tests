package com.patotski.performance.bigint;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import java.math.BigInteger;
import java.util.regex.Pattern;

import static com.patotski.performance.utils.BenchmarkUtils.runBenchmark;

public class BigIntValidationBenchmarks {
    @State(Scope.Benchmark)
    public static class BigIntState {
        public String invalid = "424242424242boom";
        public String valid = "4242424242424242";

        public BigInteger bigInteger;

        public String stringPattern = "[0-9]+";

        public Pattern pattern = Pattern.compile(stringPattern);

        private boolean flag = true;
        public String getString() {
            if (flag) {
                flag = false;
                return invalid;
            } else {
                flag = true;
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

    @Benchmark
    public String regExpValidation(BigIntState state) {
        String stringToParse = state.getString();
        if (stringToParse.matches(state.stringPattern)) {
            state.bigInteger = new BigInteger(stringToParse);
            return "valid";
        }
        return "invalid";
    }

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