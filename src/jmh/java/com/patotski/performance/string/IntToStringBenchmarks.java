package com.patotski.performance.string;

import org.openjdk.jmh.annotations.*;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import static com.patotski.performance.utils.BenchmarkUtils.runBenchmark;

@State(Scope.Benchmark)
@BenchmarkMode({Mode.AverageTime})
@Warmup(iterations = 2, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 3, time = 5, timeUnit = TimeUnit.SECONDS)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Fork(1)
public class IntToStringBenchmarks {
    @Benchmark
    public String toString() {
        return Integer.toString(ThreadLocalRandom.current().nextInt());
    }

    @Benchmark
    public String stringConcat() {
        return  "" + ThreadLocalRandom.current().nextInt();
    }

    @Benchmark
    public String stringBuilder() {
        return new StringBuilder().append(ThreadLocalRandom.current().nextInt()).toString();
    }

    @Benchmark
    public String stringValueOf() {
        return  String.valueOf(ThreadLocalRandom.current().nextInt());
    }

    public static void main(String[] args) throws Exception {
        runBenchmark(IntToStringBenchmarks.class);
    }
}