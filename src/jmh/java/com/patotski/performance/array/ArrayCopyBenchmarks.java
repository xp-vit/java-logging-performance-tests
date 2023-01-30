package com.patotski.performance.array;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import java.math.BigInteger;
import java.util.Arrays;

import static com.patotski.performance.utils.BenchmarkUtils.runBenchmark;

public class ArrayCopyBenchmarks {
    @State(Scope.Benchmark)
    public static class ArrState {
        public int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

    }
    @Benchmark
    public int[] loopCopy(ArrState state) {
        int[] arr2 = new int[10];
        for (int i = 0; i < state.arr.length; i++) {
            arr2[i] = state.arr[i];
        }
        return arr2;
    }

    @Benchmark
    public int[] systemArraysCopy(ArrState state) {
        int[] arr2 = new int[10];
        System.arraycopy(state.arr, 0, arr2, 0, state.arr.length);
        return arr2;
    }

    @Benchmark
    public int[] arraysCopy(ArrState state) {
        return Arrays.copyOf(state.arr, state.arr.length);
    }

    public static void main(String[] args) throws Exception {
        runBenchmark(ArrayCopyBenchmarks.class);
    }
}