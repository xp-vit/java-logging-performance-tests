package com.patotski.performance.collections;

import com.patotski.performance.array.PrimitiveVsWrapperBenchmarks;
import com.patotski.performance.utils.BenchmarkUtils;
import org.openjdk.jmh.annotations.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
@BenchmarkMode({Mode.AverageTime})
@Warmup(iterations = 2, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 3, time = 5, timeUnit = TimeUnit.SECONDS)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Fork(1)
public class StreamVsLoopsBenchmark {
  private List<Integer> list;

  @Param({ "100000" })
  private int size;

  @Setup
  public void setup() {
    list = new ArrayList<>(size);
    for (int i = 0; i < size; i++) {
      list.add(i);
    }
  }

  @Benchmark
  public int stream() {
    return list.stream()
        .filter(i -> i == -1)
        .findAny()
        .orElse(0);
  }

  @Benchmark
  public int streamParallel() {
    return list.stream()
        .parallel()
        .filter(i -> i == -1)
        .findAny()
        .orElse(0);
  }

  @Benchmark
  public int loop() {
    for (Integer i : list) {
      if (i == -1) {
        return i;
      }
    }
    return 0;
  }

  @Benchmark
  public int iterator() {
    Iterator<Integer> iterator = list.iterator();
    while (iterator.hasNext()) {
      Integer i = iterator.next();
      if (i == -1) {
        return i;
      }
    }
    return 0;
  }

  @Benchmark
  public int oldSchoolForLoop() {
    int listSize = list.size();

    for (int i = 0; i < listSize; i++) {
      if (list.get(i) == -1) {
        return i;
      }
    }
    return 0;
  }


  public static void main(String[] args) throws Exception {
    BenchmarkUtils.runBenchmark(StreamVsLoopsBenchmark.class);
  }
}
