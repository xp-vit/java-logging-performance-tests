package com.patotski.performance.string;

import com.patotski.performance.utils.BenchmarkUtils;
import joptsimple.internal.Strings;
import org.openjdk.jmh.annotations.Benchmark;

public class StringEmptyComparisonBenchmark {

  @Benchmark
  public boolean testIsEmpty() {
    return "123==456".isEmpty();
  }

  @Benchmark
  public boolean testEqualsEmpty() {
    return "123==456".equals(Strings.EMPTY);
  }

  public static void main(String[] args) throws Exception {
    BenchmarkUtils.runBenchmark(StringEmptyComparisonBenchmark.class);
  }
}
