package org.jenkinsci.test.acceptance.guice;

import org.junit.runners.model.Statement;

import java.io.Closeable;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.Callable;

/**
 * Performs clean-up tasks at the end of scope.
 *
 * Tests and their decorators can add stuff to this cleaner to ensure some cleanup operation
 * happens at the end of each test.
 *
 * @author Kohsuke Kawaguchi
 */
public class Cleaner {
    private final Deque<Statement> tasks = new ArrayDeque<>();

    public void addTask(Statement stmt) {
        tasks.push(stmt);
    }

    public void addTask(final Runnable r) {
        addTask(new Statement() {
            @Override
            public void evaluate() throws Throwable {
                r.run();
            }
        });
    }

    public void addTask(final Closeable c) {
        addTask(new Statement() {
            @Override
            public void evaluate() throws Throwable {
                c.close();
            }
        });
    }

    public void addTask(final Callable<?> c) {
        addTask(new Statement() {
            @Override
            public void evaluate() throws Throwable {
                c.call();
            }
        });
    }
    public void performCleanUp() {
        for (Statement task : tasks) {
            try {
                task.evaluate();
            } catch (Throwable t) {
                System.err.println("close vyhodil " + t);
                t.printStackTrace();
                throw new AssertionError(task+" failed",t);
            }
        }
        tasks.clear();
    }
}
