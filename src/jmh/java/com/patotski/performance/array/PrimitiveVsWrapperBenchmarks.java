package com.patotski.performance.array;


import com.patotski.performance.utils.BenchmarkUtils;
import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
@BenchmarkMode({Mode.AverageTime})
@Warmup(iterations = 2, time = 2, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 5, timeUnit = TimeUnit.SECONDS)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Fork(1)
public class PrimitiveVsWrapperBenchmarks {
  @Param({"10", "100", "1000", "10000"})
  int size;

  @Benchmark
  public Integer[] arrayOfWrappers() {
    Integer[] array = new Integer[size];
    for (int i = 0; i < array.length; i++) {
      array[i] = i;
    }

    return array;
  }

  @Benchmark
  public int[] arrayOfPrimitives() {
    int[] array = new int[size];

    for (int i = 0; i < array.length; i++) {
      array[i] = i;
    }

    return array;
  }

  public static void main(String[] args) throws Exception {
    BenchmarkUtils.runBenchmark(PrimitiveVsWrapperBenchmarks.class);
  }
}
