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

        public String stringPattern = "\\d+";

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
        try {
            state.bigInteger = new BigInteger(state.getString());
        } catch (NumberFormatException e) {
            return "invalid";
        }
        return "valid";
    }

    @Benchmark
    public String regExpValidation(BigIntState state) {
        if (state.getString().matches(state.stringPattern)) {
            state.bigInteger = new BigInteger(state.getString());
            return "valid";
        }
        return "invalid";
    }

    @Benchmark
    public String regExpCompiledPatternValidation(BigIntState state) {
        if (state.pattern.matcher(state.getString()).matches()) {
            state.bigInteger = new BigInteger(state.getString());
            return "valid";
        }
        return "invalid";
    }

    public static void main(String[] args) throws Exception {
        runBenchmark(BigIntValidationBenchmarks.class);
    }
}