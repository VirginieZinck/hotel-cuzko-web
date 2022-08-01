package com.example.hotelcuzcoweb.Business.Services;

import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@Warmup(iterations = 1000, time = 2)
public class StringConcat {

    @Benchmark
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public String concat() {
        String a = "mlkmklj";
        String b = "mlkjmlkjmlkjmlkjm";
        return a.concat(b);
    }
}
