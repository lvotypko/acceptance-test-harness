package org.jenkinsci.test.acceptance.junit;

import org.jenkinsci.test.acceptance.controller.LocalController;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.Description;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;

import javax.annotation.Nonnull;
import javax.inject.Inject;
import java.io.File;


/**
 * Created by lvotypko on 8/10/17.
 */
public class JenkinsRuleInjectionFailureTest {
    @Test
    public void testControllerCleanup() throws Throwable {
        JenkinsAcceptanceTestRule rules = new JenkinsAcceptanceTestRule();
        FrameworkMethod method = new FrameworkMethod(this.getClass().getMethod("testControllerCleanup"));
        Statement s = rules.apply(new Statement() {
            @Override
            public void evaluate() throws Throwable {
                //do nothing
            }
        },method, this);
        Description description = Description.createTestDescription(this.getClass(), method.getName(), method.getAnnotations());
        StatementWithException statement = new StatementWithException(s, method, this);

        try {
            statement.evaluate();
        }
        catch(Exception e) {
            Throwable t = e;
            //find out if the cause is expected exception
            while(t.getCause()!=null){
                t = t.getCause();

            }
            if(t instanceof ExceptionInInitializerError) {
                if (statement.getTempFile().exists()) {
                    Assert.fail("Anytime controller is created, cleanup should be done.");
               }
            }
            else{
                t.printStackTrace(System.err);
                Assert.fail("Wrong exception was thrown - " + e.getClass() + ": " + e.getMessage());
            }
        }

    }

    public static class StatementWithException extends JenkinsAcceptanceTestRule.StatementImpl {

        @Inject @Nonnull
        ExceptionInConstructor notExistedIjection;

        public StatementWithException(Statement base, FrameworkMethod method, Object target){
            super(base, method, target);
        }

        public File getTempFile(){
            if(controller instanceof LocalController){
                return ((LocalController) controller).getJenkinsHome();
            }
            else{
                return null;
            }
        }

    }

    public static class ExceptionInConstructor {

        public ExceptionInConstructor() throws ExceptionInInitializerError {
            throw new ExceptionInInitializerError("simulate");
        }

    }
}
