package com.patotski.performance.utils;

import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.io.File;

public class BenchmarkUtils {

  public static void runBenchmark(Class<?> clazz) throws Exception {
    new File("reports").mkdirs();
    Options opt = new OptionsBuilder()
        .include(clazz.getSimpleName())
            .resultFormat(ResultFormatType.JSON)
            .shouldDoGC(true)
            .result("reports/" + clazz.getSimpleName() + ".json")
            .jvmArgsAppend("-Djmh.stack.period=1")
            //.warmupIterations(2)
            //.measurementIterations(10)
        .forks(1)
        .build();

    new Runner(opt).run();
  }
}
