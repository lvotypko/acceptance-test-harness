package plugins;

import org.jenkinsci.test.acceptance.junit.AbstractJUnitTest;
import org.jenkinsci.test.acceptance.junit.WithPlugins;
import org.jenkinsci.test.acceptance.plugins.job_dsl.JobDslBuildStep;
import org.jenkinsci.test.acceptance.plugins.post_build_script.PostBuildScript;
import org.jenkinsci.test.acceptance.po.FreeStyleJob;
import org.jenkinsci.test.acceptance.po.ShellBuildStep;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Created by lvotypko on 10/18/18.
 */
@WithPlugins({
        "matrix-project", // JENKINS-37545
        "postbuildscript"
})
public class ReproduceCloseIssueTest extends AbstractJUnitTest {

    private FreeStyleJob j;

    @Before
    public void setUp() {
        j = jenkins.jobs.create();
    }

    @Test
    public void test_1() {
        j.configure();
        j.setLabelExpression("test");
        addMarkerPostBuildStep();
    }

    @Test
    public void test_2() {
        j.configure();
        j.setLabelExpression("test");
        addMarkerPostBuildStep();
    }

    @Test
    public void test_3() {
        j.configure();
        j.setLabelExpression("test");
        addMarkerPostBuildStep();
    }

    @Test
    public void test_4() {
        j.configure();
        j.setLabelExpression("test");
        addMarkerPostBuildStep();
    }

    @Test
    public void test_5() {
        j.configure();
        j.setLabelExpression("test");
        addMarkerPostBuildStep();
    }

    @Test
    public void test_6() {
        j.configure();
        j.setLabelExpression("test");
        addMarkerPostBuildStep();
    }

    @Test
    public void test_7() {
        j.configure();
        j.setLabelExpression("test");
        addMarkerPostBuildStep();
    }

    @Test
    public void test_8() {
        j.configure();
        j.setLabelExpression("test");
        addMarkerPostBuildStep();
    }

    @Test
    public void test_9() {
        j.configure();
        j.setLabelExpression("test");
        addMarkerPostBuildStep();
    }

    @Test
    public void test_10() {
        j.configure();
        j.setLabelExpression("test");
        addMarkerPostBuildStep();
    }

    @Test
    public void test_11() {
        j.configure();
        j.setLabelExpression("test");
        addMarkerPostBuildStep();
    }

    @Test
    public void test_12() {
        j.configure();
        j.setLabelExpression("test");
        addMarkerPostBuildStep();
    }

    @Test
    public void test_13() {
        j.configure();
        j.setLabelExpression("test");
        addMarkerPostBuildStep();
    }

    @Test
    public void test_14() {
        j.configure();
        j.setLabelExpression("test");
        addMarkerPostBuildStep();
    }

    @Test
    public void test_15() {
        j.configure();
        j.setLabelExpression("test");
        addMarkerPostBuildStep();
    }

    @Test
    public void test_16() {
        j.configure();
        j.setLabelExpression("test");
        addMarkerPostBuildStep();
    }

    @Test
    public void test_17() {
        j.configure();
        j.setLabelExpression("test");
        addMarkerPostBuildStep();
    }

    @Test
    public void test_18() {
        j.configure();
        j.setLabelExpression("test");
        addMarkerPostBuildStep();
    }

    @Test
    public void test_19() {
        j.configure();
        j.setLabelExpression("test");
        addMarkerPostBuildStep();
    }

    @Test
    public void test_20() {
        j.configure();
        j.setLabelExpression("test");
        addMarkerPostBuildStep();
    }

    @Test
    public void test_21() {
        j.configure();
        j.setLabelExpression("test");
        addMarkerPostBuildStep();
    }

    @Test
    public void test_22() {
        j.configure();
        j.setLabelExpression("test");
        addMarkerPostBuildStep();
    }

    @Test
    public void test_23() {
        j.configure();
        j.setLabelExpression("test");
        addMarkerPostBuildStep();
    }

    @Test
    public void test_24() {
        j.configure();
        j.setLabelExpression("test");
        addMarkerPostBuildStep();
    }

    @Test
    public void test_25() {
        j.configure();
        j.setLabelExpression("test");
        addMarkerPostBuildStep();
    }

    @Test
    public void test_26() {
        j.configure();
        j.setLabelExpression("test");
        addMarkerPostBuildStep();
    }

    @Test
    public void test_27() {
        j.configure();
        j.setLabelExpression("test");
        addMarkerPostBuildStep();
    }

    @Test
    public void test_28() {
        j.configure();
        j.setLabelExpression("test");
        addMarkerPostBuildStep();
    }

    @Test
    public void test_29() {
        j.configure();
        j.setLabelExpression("test");
        addMarkerPostBuildStep();
    }

    @Test
    public void test_30() {
        j.configure();
        j.setLabelExpression("test");
        addMarkerPostBuildStep();
    }

    @Test
    public void test_31() {
        j.configure();
        j.setLabelExpression("test");
        addMarkerPostBuildStep();
    }

    @Test
    public void test_32() {
        j.configure();
        j.setLabelExpression("test");
        addMarkerPostBuildStep();
    }

    @Test
    public void test_33() {
        j.configure();
        j.setLabelExpression("test");
        addMarkerPostBuildStep();
    }

    @Test
    public void test_34() {
        j.configure();
        j.setLabelExpression("test");
        addMarkerPostBuildStep();
    }

    @Test
    public void test_35() {
        j.configure();
        j.setLabelExpression("test");
        addMarkerPostBuildStep();
    }

    @Test
    public void test_36() {
        j.configure();
        j.setLabelExpression("test");
        addMarkerPostBuildStep();
    }

    @Test
    public void test_37() {
        j.configure();
        j.setLabelExpression("test");
        addMarkerPostBuildStep();
    }

    @Test
    public void test_38() {
        j.configure();
        j.setLabelExpression("test");
        addMarkerPostBuildStep();
    }

    @Test
    public void test_39() {
        j.configure();
        j.setLabelExpression("test");
        addMarkerPostBuildStep();
    }

    private PostBuildScript addMarkerPostBuildStep() {
        PostBuildScript post = j.addPublisher(PostBuildScript.class);
        j.control("/publisher[PostBuildScript]/repeatable-add[3]").click();
        post.addStep(ShellBuildStep.class).command("echo RUNNING_POST_BUILD_STEP");

        return post;
    }
}
