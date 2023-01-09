package com.patotski.performance.string;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

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

    public static void runBenchmark(Class<?> clazz) throws Exception {
//        String[] args = new String[]{
//                clazz.getSimpleName()
//        };
//        org.openjdk.jmh.Main.main(args);

        Options opt = new OptionsBuilder()
            .include(clazz.getSimpleName())
//                .shouldDoGC(true)
            .resultFormat(ResultFormatType.JSON)
            .result("reports/" + clazz.getSimpleName() + ".json")
//                .addProfiler(StackProfiler.class)
//                .addProfiler(CompilerProfiler.class)
            .jvmArgsAppend("-Djmh.stack.period=1")
                .warmupIterations(2)
                .measurementIterations(10)
                .forks(1)
            .build();

        new Runner(opt).run();
    }
}