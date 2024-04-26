package cn.demo.jmh;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

/**
 * @State(Scope.Thread)：作用域为线程，可以理解为一个ThreadLocal变量
 * @State(Scope.Benchmark)：作用域为本次JMH测试，线程共享
 * @State(Scope.Group)：作用域为group，将在后文看到
 */
public class JMHSample_03_States {
    /*
     * Most of the time, you need to maintain some state while the benchmark is
     * running. Since JMH is heavily used to build concurrent benchmarks, we
     * opted for an explicit notion of state-bearing objects.
     *
     * Below are two state objects. Their class names are not essential, it
     * matters they are marked with @State. These objects will be instantiated
     * on demand, and reused during the entire benchmark trial.
     *
     * The important property is that state is always instantiated by one of
     * those benchmark threads which will then have the access to that state.
     * That means you can initialize the fields as if you do that in worker
     * threads (ThreadLocals are yours, etc).
     */

    @State(Scope.Benchmark)
    public static class BenchmarkState {
        volatile double x = Math.PI;
    }

    @State(Scope.Thread)
    public static class ThreadState {
        volatile double x = Math.PI;
    }

    /*
     * Benchmark methods can reference the states, and JMH will inject the
     * appropriate states while calling these methods. You can have no states at
     * all, or have only one state, or have multiple states referenced. This
     * makes building multi-threaded benchmark a breeze.
     *
     * For this exercise, we have two methods.
     */

    @Benchmark
    public void measureUnshared(ThreadState state) {
        // All benchmark threads will call in this method.
        //
        // However, since ThreadState is the Scope.Thread, each thread
        // will have it's own copy of the state, and this benchmark
        // will measure unshared case.
        state.x++;
    }

    @Benchmark
    public void measureShared(BenchmarkState state) {
        // All benchmark threads will call in this method.
        //
        // Since BenchmarkState is the Scope.Benchmark, all threads
        // will share the state instance, and we will end up measuring
        // shared case.
        state.x++;
    }

    /*
     * ============================== HOW TO RUN THIS TEST: ====================================
     *
     * You are expected to see the drastic difference in shared and unshared cases,
     * because you either contend for single memory location, or not. This effect
     * is more articulated on large machines.
     *
     * You can run this test:
     *
     * a) Via the command line:
     *    $ mvn clean install
     *    $ java -jar target/benchmarks.jar JMHSample_03 -t 4 -f 1
     *    (we requested 4 threads, single fork; there are also other options, see -h)
     *
     * b) Via the Java API:
     *    (see the JMH homepage for possible caveats when running from IDE:
     *      http://openjdk.java.net/projects/code-tools/jmh/)
     */

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(JMHSample_03_States.class.getSimpleName())
                .threads(4)
                .forks(1)
                .build();

        new Runner(opt).run();
    }

}
