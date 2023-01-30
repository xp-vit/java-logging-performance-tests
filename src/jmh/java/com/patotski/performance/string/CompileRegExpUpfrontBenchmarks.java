package com.patotski.performance.string;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import java.util.regex.Pattern;

import static com.patotski.performance.utils.BenchmarkUtils.runBenchmark;

public class CompileRegExpUpfrontBenchmarks {
  @State(Scope.Benchmark)
  public static class InputStringHolder {
    public String input = "4242424242424242";

    public Pattern pattern = Pattern.compile("\\d{16}");
  }
  @Benchmark
  public boolean compileOnSpot(InputStringHolder strHolder) {
    return strHolder.pattern.matcher(strHolder.input).matches();
  }

  @Benchmark
  public boolean preCompiledRegExp(InputStringHolder strHolder) {
    return strHolder.pattern.matcher(strHolder.input).matches();
  }
  public static void main(String[] args) throws Exception {
    runBenchmark(CompileRegExpUpfrontBenchmarks.class);
  }
}
