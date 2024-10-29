package com.patotski.performance.bigdec;

import org.openjdk.jmh.annotations.*;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

import static com.patotski.performance.utils.BenchmarkUtils.runBenchmark;

@BenchmarkMode({Mode.AverageTime})
@Warmup(iterations = 2, time = 2, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 5, timeUnit = TimeUnit.SECONDS)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Fork(1)
public class BigDecDivideVsMove {
    public static final BigDecimal bigDecimal = BigDecimal.valueOf(12.3456);

    @Benchmark
    public BigDecimal divide() {
        return bigDecimal.divide(BigDecimal.valueOf(100));
    }

    @Benchmark
    public BigDecimal move2Left() {
        return bigDecimal.movePointLeft(2);
    }

    @Benchmark
    public BigDecimal scaleByPowerOfTen() {
        return bigDecimal.scaleByPowerOfTen(-2);
    }

    public static void main(String[] args) throws Exception {
        runBenchmark(BigDecDivideVsMove.class);
    }
}
