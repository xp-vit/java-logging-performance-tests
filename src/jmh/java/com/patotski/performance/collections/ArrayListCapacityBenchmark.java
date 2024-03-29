package com.patotski.performance.collections;

import org.openjdk.jmh.annotations.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.patotski.performance.utils.BenchmarkUtils.runBenchmark;


@State(Scope.Benchmark)
@BenchmarkMode({Mode.AverageTime})
@Warmup(iterations = 2, time = 3, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 2, time = 20, timeUnit = TimeUnit.SECONDS)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Fork(1)
public class ArrayListCapacityBenchmark {

 @Param({"10", "100", "1000", "10000", "100000", "1000000"})
 private int size;



  @Benchmark
  public ArrayList<Integer> withoutInitialCapacity() {
    final ArrayList<Integer> result = new ArrayList<>();
    for (int i = 0; i <= size; i++) {
      result.add(i);
    }
    return result;
  }

  @Benchmark
  public ArrayList<Integer> withInitialCapacity() {
    final ArrayList<Integer> result = new ArrayList<>(size);
    for (int i = 0; i <= size; i++) {
      result.add(i);
    }
    return result;
  }

  public static void main(String[] args) throws Exception {
    runBenchmark(ArrayListCapacityBenchmark.class);
  }
}
