package com.patotski.performance.array;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Random;

import static com.patotski.performance.utils.BenchmarkUtils.runBenchmark;

public class ArrayCopyBenchmarks {
    @State(Scope.Benchmark)
    public static class ArrState {
        public int[] arr = new int[3000];
        public int[] arr2 = new int[3000];

        @Setup
        public void setup() {
            Random random = new Random();
            Arrays.fill(arr, random.nextInt());
        }
    }
    //@Benchmark
    public int[] loopCopy(ArrState state) {
        int[] arr2 = new int[30];
        for (int i = 0; i < state.arr.length; i++) {
            arr2[i] = state.arr[i];
        }
        return arr2;
    }

    //@Benchmark
    public int[] systemArraysCopy(ArrState state) {
        int[] arr2 = new int[30];
        System.arraycopy(state.arr, 0, arr2, 0, state.arr.length);
        return arr2;
    }

    @Benchmark
    public int[] systemArraysCopyToExistingArray(ArrState state) {
        System.arraycopy(state.arr, 0, state.arr2, 0, state.arr.length);
        return state.arr2;
    }

    @Benchmark
    public int[] loopCopyToExistingArray(ArrState state) {
        for (int i = 0; i < state.arr.length; i++) {
            state.arr2[i] = state.arr[i];
        }
        return state.arr2;
    }

    //@Benchmark
    public int[] arraysCopy(ArrState state) {
        return Arrays.copyOf(state.arr, state.arr.length);
    }

    public static void main(String[] args) throws Exception {
        runBenchmark(ArrayCopyBenchmarks.class);
    }
}