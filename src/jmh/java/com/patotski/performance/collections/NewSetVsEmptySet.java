package com.patotski.performance.collections;

import org.openjdk.jmh.annotations.Benchmark;

import java.util.*;

import static com.patotski.performance.utils.BenchmarkUtils.runBenchmark;

public class NewSetVsEmptySet {

  @Benchmark
  public Set emptySet() {
    return Collections.emptySet();
  }

  @Benchmark
  public Set newHashSet() {
    return new HashSet();
  }

  @Benchmark
  public Set newTreeSet() {
    return new TreeSet();
  }

  @Benchmark
  public Set setOf() {
    return Set.of();
  }

  public static void main(String[] args) throws Exception {
    runBenchmark(NewSetVsEmptySet.class);
  }
}
