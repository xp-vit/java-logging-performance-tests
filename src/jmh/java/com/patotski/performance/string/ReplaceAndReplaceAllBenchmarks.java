package com.patotski.performance.string;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import static com.patotski.performance.utils.BenchmarkUtils.runBenchmark;

public class ReplaceAndReplaceAllBenchmarks {
  @State(Scope.Benchmark)
  public static class InputStringHolder {
    public String input = "4242424242424242";
  }
  @Benchmark
  public String replace(InputStringHolder strHolder) {
    return strHolder.input.replace("42", "4422");
  }

  @Benchmark
  public String replaceAll(InputStringHolder strHolder) {
    return strHolder.input.replaceAll("42", "4422");
  }
  public static void main(String[] args) throws Exception {
    runBenchmark(ReplaceAndReplaceAllBenchmarks.class);
  }
}
