package com.patotski.performance.array;

import com.patotski.performance.utils.BenchmarkUtils;
import org.openjdk.jmh.annotations.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
@BenchmarkMode({Mode.AverageTime})
@Warmup(iterations = 2, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 3, time = 2, timeUnit = TimeUnit.SECONDS)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Fork(1)
public class VectorVsArrayListBenchmark {

  @Param({ "10", "100", "1000", "10000"})
  private int size;

  @Benchmark
  public Vector vector() {
    Vector<Integer> vector = new Vector<>();
    for (int i = 0; i < size; i++) {
      vector.add(i);
    }
    return vector;
  }

  @Benchmark
  public List arrayList() {
    List<Integer> list = new ArrayList<>();
    for (int i = 0; i < size; i++) {
      list.add(i);
    }
    return list;
  }

  public static void main(String[] args) throws Exception {
    BenchmarkUtils.runBenchmark(VectorVsArrayListBenchmark.class);
   /* Vector<Integer> vector = new Vector<>();
    int capacity = 0;
    for(int i = 0; i < 1000_000; i++) {
      vector.add(i);
      if (vector.capacity() != capacity) {
        capacity = vector.capacity();
        System.out.println(capacity);
      }
    }
    ArrayList<Integer> list = new ArrayList<>();
    capacity = 0;
    for(int i = 0; i < 1000_000; i++) {
      list.add(i);
      if (vector.capacity() != capacity) {
        capacity = vector.capacity();
        System.out.println(capacity);
      }
    }*/
  }
}
