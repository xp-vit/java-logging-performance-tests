package com.patotski.performance.collections;

import org.openjdk.jmh.annotations.Benchmark;

import java.util.*;

import static com.patotski.performance.utils.BenchmarkUtils.runBenchmark;

public class NewListVsEmptyList {

  @Benchmark
  public List emptyList() {
    return Collections.emptyList();
  }

  @Benchmark
  public List newArrayList() {
    return new ArrayList();
  }

  @Benchmark
  public List newLinkedList() {
    return new LinkedList();
  }

  public static void main(String[] args) throws Exception {
    runBenchmark(NewListVsEmptyList.class);
  }
}
