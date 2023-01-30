package com.patotski.performance.string;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

import java.util.regex.Pattern;

import static com.patotski.performance.utils.BenchmarkUtils.runBenchmark;

public class StringBuilderBenchmarks {
  @State(Scope.Benchmark)
  public static class InputStringHolder {
    public StringBuilder sb;


    @Setup
    public void setup() {
      sb = new StringBuilder();
    }
  }
  @Benchmark
  public StringBuilder appendChar(InputStringHolder strHolder) {
    strHolder.sb.delete(0, strHolder.sb.length());
    return strHolder.sb.append('a');
  }

  @Benchmark
  public StringBuilder appendStr(InputStringHolder strHolder) {
    strHolder.sb.delete(0, strHolder.sb.length());
    return strHolder.sb.append("a");
  }

  public static void main(String[] args) throws Exception {
    runBenchmark(StringBuilderBenchmarks.class);
  }
}
