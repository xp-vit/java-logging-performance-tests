package com.patotski.performance.random;

import com.patotski.performance.utils.BenchmarkUtils;
import org.openjdk.jmh.annotations.*;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
@BenchmarkMode({Mode.AverageTime})
@Warmup(iterations = 2, time = 2, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 5, timeUnit = TimeUnit.SECONDS)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Fork(1)
public class RandomBenchmark {


  @Param({ "10", "100", "1000" })
  private int size;

  Random reusedRandom;

  @Setup
  public void setup() {
    reusedRandom = new Random();
  }

  @Benchmark
  public long reusedRandom() {
    long result = 0;
    for (int i = 0; i < size; i++) {
      result+= reusedRandom.nextInt();
    }
    return result;
  }

  @Benchmark
  public long newRandom() {
    long result = 0;
    for (int i = 0; i < size; i++) {
      result += new Random().nextInt();
    }
    return result;
  }

  public static void main(String[] args) throws Exception {
    BenchmarkUtils.runBenchmark(RandomBenchmark.class);
  }
}