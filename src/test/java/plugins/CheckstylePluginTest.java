package plugins;

import org.jenkinsci.test.acceptance.junit.AbstractJUnitTest;
import org.jenkinsci.test.acceptance.junit.WithPlugins;
import org.jenkinsci.test.acceptance.plugins.checkstyle.CheckstyleAction;
import org.jenkinsci.test.acceptance.plugins.checkstyle.CheckstylePublisher;
import org.jenkinsci.test.acceptance.po.Build;
import org.jenkinsci.test.acceptance.po.FreeStyleJob;
import org.jenkinsci.test.acceptance.po.Jenkins;
import org.junit.Test;

import javax.inject.Inject;

import static org.hamcrest.CoreMatchers.*;
import static org.jenkinsci.test.acceptance.Matchers.*;

/**
 * Feature: Allow publishing of Checkstyle report
   In order to be able to check code style of my project
   As a Jenkins user
   I want to be able to publish Checkstyle report
 */
@WithPlugins("checkstyle")
public class CheckstylePluginTest extends AbstractJUnitTest {
    @Inject
    Jenkins j;

    /**
     * Scenario: Record Checkstyle report
         Given I have installed the "checkstyle" plugin
         And a job
         When I configure the job
         And I copy resource "checkstyle_plugin/checkstyle-result.xml" into workspace
         And I add "Publish Checkstyle analysis results" post-build action
         And I set up "checkstyle-result.xml" as the Checkstyle results
         And I save the job
         And I build the job
         Then the build should have "Checkstyle Warnings" action
         And the job should have "Checkstyle Warnings" action
     */
    @Test
    public void record_checkstyle_report() {
        FreeStyleJob job = setupJob();
        Build b = job.queueBuild().waitUntilFinished().shouldSucceed();

        assertThat(b, hasAction("Checkstyle Warnings"));
        assertThat(job, hasAction("Checkstyle Warnings"));
    }

    /**
     *   Scenario: View Checkstyle report
         Given I have installed the "checkstyle" plugin
         And a job
         When I configure the job
         And I copy resource "checkstyle_plugin/checkstyle-result.xml" into workspace
         And I add "Publish Checkstyle analysis results" post-build action
         And I set up "checkstyle-result.xml" as the Checkstyle results
         And I save the job
         And I build the job
         Then the build should succeed
         When I visit Checkstyle report
         Then I should see there are 776 warnings
         And I should see there are 776 new warnings
         And I should see there are 0 fixed warnings
         And I should see there are 776 high priority warnings
         And I should see there are 0 normal priority warnings
         And I should see there are 0 low priority warnings
     */
    @Test
    public void view_checkstyle_report() {
        FreeStyleJob job = setupJob();
        Build b = job.queueBuild().waitUntilFinished().shouldSucceed();

        CheckstyleAction ca = new CheckstyleAction(job);
        assertThat(ca.getWarningNumber(), is(776));
        assertThat(ca.getNewWarningNumber(), is(776));
        assertThat(ca.getFixedWarningNumber(), is(0));
        assertThat(ca.getHighWarningNumber(), is(776));
        assertThat(ca.getNormalWarningNumber(), is(0));
        assertThat(ca.getLowWarningNumber(), is(0));
    }

    private FreeStyleJob setupJob() {
        FreeStyleJob job = j.createJob();
        job.configure();
        job.copyResource(resource("/checkstyle_plugin/checkstyle-result.xml"), "checkstyle-result.xml");
        job.addPublisher(CheckstylePublisher.class)
            .pattern.sendKeys("checkstyle-result.xml");
        job.save();
        return job;
    }
}
