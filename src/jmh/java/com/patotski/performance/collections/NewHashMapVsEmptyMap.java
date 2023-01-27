package com.patotski.performance.collections;

import org.openjdk.jmh.annotations.Benchmark;

import java.util.*;

import static com.patotski.performance.utils.BenchmarkUtils.runBenchmark;

public class NewHashMapVsEmptyMap {

  @Benchmark
  public Map emptyMap() {
    return Collections.emptyMap();
  }

  @Benchmark
  public Map newHashMap() {
    return new HashMap();
  }

  @Benchmark
  public Map newTreeMap() {
    return new TreeMap();
  }

  @Benchmark
  public Map mapOf() {
    return Map.of();
  }

  public static void main(String[] args) throws Exception {
    runBenchmark(NewHashMapVsEmptyMap.class);
  }
}
