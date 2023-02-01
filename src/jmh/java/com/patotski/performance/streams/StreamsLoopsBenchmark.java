package com.patotski.performance.streams;

import com.patotski.performance.utils.BenchmarkUtils;
import org.openjdk.jmh.annotations.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
@BenchmarkMode({Mode.Throughput})
@Warmup(iterations = 2, time = 2, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 5, timeUnit = TimeUnit.SECONDS)
@Fork(1)
public class StreamsLoopsBenchmark {
  private List<Integer> list;

  @Param({ "10", "1000", "10000", "100000", "1000000" })
  private int size;

  @Setup
  public void setup() {
    list = new ArrayList<>(size);
    for (int i = 0; i < size; i++) {
      list.add(i);
    }
  }

  @Benchmark
  public int parallel_stream() {
    return list.parallelStream()
        .filter(i -> i == -1)
        .findAny()
        .orElse(0);
  }

  @Benchmark
  public int stream() {
    return list.stream()
        .filter(i -> i == -1)
        .findAny()
        .orElse(0);
  }

  public static void main(String[] args) throws Exception {
    BenchmarkUtils.runBenchmark(StreamsLoopsBenchmark.class);
  }
}