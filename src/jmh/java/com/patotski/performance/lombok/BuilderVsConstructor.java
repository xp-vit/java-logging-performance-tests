package com.patotski.performance.lombok;

import org.openjdk.jmh.annotations.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.patotski.performance.utils.BenchmarkUtils.runBenchmark;


@BenchmarkMode({Mode.AverageTime})
@Warmup(iterations = 2, time = 2, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 10, time = 10, timeUnit = TimeUnit.SECONDS)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Fork(1)
public class BuilderVsConstructor {
  public static final BigDecimal ONE_HUNDRED = new BigDecimal(100);

  @Benchmark
  public BigDecimalTuple builder() {
    return BigDecimalTuple.builder()
        .first(BigDecimal.ZERO)
        .second(BigDecimal.ONE)
        .build();
  }

  @Benchmark
  public BigDecimalTuple constructor() {
    return new BigDecimalTuple(BigDecimal.ZERO, BigDecimal.ONE);
  }

  public static void main(String[] args) throws Exception {
    runBenchmark(BuilderVsConstructor.class);
  }
}
