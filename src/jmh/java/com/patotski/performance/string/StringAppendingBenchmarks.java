package com.patotski.performance.string;


import com.patotski.performance.utils.BenchmarkUtils;
import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
@BenchmarkMode({Mode.AverageTime})
@Warmup(iterations = 2, time = 3, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 10, timeUnit = TimeUnit.SECONDS)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Fork(1)
public class StringAppendingBenchmarks {

  @Param({"2", "3", "5", "10", "50", "100" })
  private int size;

  @Benchmark
  public String appendWithStringBuilder() {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < size; i++) {
      sb.append(i);
    }
    return sb.toString();
  }

  @Benchmark
  public String appendWithStringPlus() {
    String str = "";
    for (int i = 0; i < size; i++) {
      str += i;
    }
    return str;
  }

  @Benchmark
  public String appendWithStringBuffer() {
    StringBuffer sb = new StringBuffer();
    for (int i = 0; i < size; i++) {
      sb.append(i);
    }
    return sb.toString();
  }

  public static void main(String[] args) throws Exception {
    BenchmarkUtils.runBenchmark(StringAppendingBenchmarks.class);
  }
}
