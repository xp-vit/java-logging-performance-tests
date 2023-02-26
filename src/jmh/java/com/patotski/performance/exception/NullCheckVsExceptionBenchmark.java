package com.patotski.performance.exception;

import com.patotski.performance.utils.BenchmarkUtils;
import org.openjdk.jmh.annotations.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
@BenchmarkMode({Mode.AverageTime})
@Warmup(iterations = 2, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 3, time = 5, timeUnit = TimeUnit.SECONDS)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Fork(1)
public class NullCheckVsExceptionBenchmark {
  @Benchmark
  public String nullCheck() {
    Integer i = getInteger();
    if (i == null) {
      return "I is null.";
    } else {
      return i.toString();
    }
  }

  @Benchmark
  public String npeExCatch() {
    Integer i = getInteger();
    try {
      return i.toString();
    } catch (NullPointerException e) {
      return e.getMessage();
    }
  }

  private Integer getInteger() {
    return ThreadLocalRandom.current().nextBoolean()? null : 1;
  }

  public static void main(String[] args) throws Exception {
    BenchmarkUtils.runBenchmark(NullCheckVsExceptionBenchmark.class);
  }
}
