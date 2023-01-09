package com.patotski.performance.map;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.util.Utils;

import java.util.HashMap;
import java.util.Map;

import static com.patotski.performance.utils.BenchmarkUtils.runBenchmark;

public class MapEntrySetBenchmarks {
    @State(Scope.Benchmark)
    public static class MapHolder{
        public Map<String, String> map = new HashMap<>();

        public String concat;
        @Setup
        public void setup(){
            for (int i = 0; i < 100; i++) {
                map.put("key"+i, "value"+i);
            }
        }
    }
    @Benchmark
    public void mapEntrySet(MapHolder mapHolder) {
        for (Map.Entry<String, String> entry : mapHolder.map.entrySet()) {
            mapHolder.concat = entry.getKey() + entry.getValue();
        }
    }

    @Benchmark
    public void mapKeySet(MapHolder mapHolder) {
        for (String key : mapHolder.map.keySet()) {
            mapHolder.concat = mapHolder.map.get(key) + key;
        }
    }

    public static void main(String[] args) throws Exception {
        runBenchmark(MapEntrySetBenchmarks.class);
    }
}