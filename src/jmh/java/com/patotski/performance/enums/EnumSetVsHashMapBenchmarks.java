package com.patotski.performance.enums;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.HashSet;

import static com.patotski.performance.enums.EnumSetVsHashMapBenchmarks.TestEnum.*;
import static com.patotski.performance.utils.BenchmarkUtils.runBenchmark;

public class EnumSetVsHashMapBenchmarks {

  public enum TestEnum {
    ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN
  }
  @State(Scope.Benchmark)
  public static class EnumHolder {
    public HashSet<TestEnum> hashSet;
    public EnumSet<TestEnum> enumSet;
  }
  @Benchmark
  public boolean hasSetContains(EnumHolder enumHolder) {
    enumHolder.hashSet = new HashSet<>(Arrays.asList(ONE, TWO, FOUR, SIX, EIGHT, TEN));
    return enumHolder.hashSet.contains(TestEnum.TWO);
  }

  @Benchmark
  public boolean enumSet(EnumHolder enumHolder) {
    enumHolder.enumSet = EnumSet.of(ONE, TWO, FOUR, SIX, EIGHT, TEN);
    return enumHolder.enumSet.contains(TestEnum.TWO);
  }
  public static void main(String[] args) throws Exception {
    runBenchmark(EnumSetVsHashMapBenchmarks.class);
  }
}
