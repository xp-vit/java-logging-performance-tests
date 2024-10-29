package com.patotski.performance.bigdec;

import org.openjdk.jmh.annotations.*;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

import static com.patotski.performance.utils.BenchmarkUtils.runBenchmark;

@BenchmarkMode({Mode.AverageTime})
@Warmup(iterations = 2, time = 3, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 10, timeUnit = TimeUnit.SECONDS)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Fork(1)
public class BigDecConstructor {
  public static final BigDecimal BIG_DECIMAL = BigDecimal.valueOf(12.3456);

  @Benchmark
  public BigDecimal string() {
    return new BigDecimal("12.3456");

  }

  @Benchmark
  public BigDecimal doubleCreation() {
    return new BigDecimal(12.3456);
  }

  public static void main(String[] args) throws Exception {
    runBenchmark(BigDecConstructor.class);
  }
}
