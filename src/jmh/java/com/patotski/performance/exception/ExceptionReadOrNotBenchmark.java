package com.patotski.performance.exception;

import com.patotski.performance.utils.BenchmarkUtils;
import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
@BenchmarkMode({Mode.AverageTime})
@Warmup(iterations = 2, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 3, time = 5, timeUnit = TimeUnit.SECONDS)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Fork(1)
public class ExceptionReadOrNotBenchmark {
  @Benchmark
  public String readEx() {
    try {
    throw new NullPointerException("test");
    } catch (NullPointerException e) {
      return e.getMessage();
    }
  }

  @Benchmark
  public String ignoreEx() {
    try {
      throw new NullPointerException("test");
    } catch (NullPointerException e) {
      return "";
    }
  }

  private Integer getInteger() {
    return null; //ThreadLocalRandom.current().nextBoolean()? null : 1;
  }

  public static void main(String[] args) throws Exception {
    BenchmarkUtils.runBenchmark(ExceptionReadOrNotBenchmark.class);
  }
}
