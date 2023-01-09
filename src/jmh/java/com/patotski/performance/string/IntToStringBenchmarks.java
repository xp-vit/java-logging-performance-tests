package com.patotski.performance.string;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import static com.patotski.performance.utils.BenchmarkUtils.runBenchmark;

public class IntToStringBenchmarks {
    @State(Scope.Benchmark)
    public static class IntHolder {
        public int i = 1234;
    }
    @Benchmark
    public String toString(IntHolder mapHolder) {
        return Integer.toString(mapHolder.i);
    }

    @Benchmark
    public String stringConcat(IntHolder mapHolder) {
        return  "" + mapHolder.i;
    }

    @Benchmark
    public String stringValueOf(IntHolder mapHolder) {
        return  String.valueOf(mapHolder.i);
    }

    public static void main(String[] args) throws Exception {
        runBenchmark(IntToStringBenchmarks.class);
    }
}