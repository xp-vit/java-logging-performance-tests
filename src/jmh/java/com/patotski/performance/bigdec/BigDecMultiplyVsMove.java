package com.patotski.performance.bigdec;

import org.openjdk.jmh.annotations.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import static com.patotski.performance.utils.BenchmarkUtils.runBenchmark;

@BenchmarkMode({Mode.AverageTime})
@Warmup(iterations = 2, time = 5, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 5, timeUnit = TimeUnit.SECONDS)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Fork(1)
public class BigDecMultiplyVsMove {
  public static final BigDecimal BIG_DECIMAL = BigDecimal.valueOf(12.3456);

  @Benchmark
  public BigDecimal multiply() {
    return BIG_DECIMAL.multiply(BigDecimal.valueOf(100));
  }

  @Benchmark
  public BigDecimal move2Right() {
    return BIG_DECIMAL.movePointRight(2);
  }

  @Benchmark
  public BigDecimal scaleByPowerOfTen() {
    return BIG_DECIMAL.scaleByPowerOfTen(2);
  }

  public static void main(String[] args) throws Exception {
    runBenchmark(BigDecMultiplyVsMove.class);
  }
}
