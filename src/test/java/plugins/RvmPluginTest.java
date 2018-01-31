/*
 * The MIT License
 *
 * Copyright 2017 CloudBees, Inc.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package plugins;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import com.google.inject.Inject;
import org.jenkinsci.test.acceptance.docker.Docker;
import org.jenkinsci.test.acceptance.docker.DockerContainer;
import org.jenkinsci.test.acceptance.docker.DockerContainerHolder;
import org.jenkinsci.test.acceptance.docker.fixtures.JavaContainer;
import org.jenkinsci.test.acceptance.docker.fixtures.JavaGitContainer;
import org.jenkinsci.test.acceptance.docker.fixtures.PackageInstallationContainer;
import org.jenkinsci.test.acceptance.docker.fixtures.SshdContainer;
import org.jenkinsci.test.acceptance.junit.AbstractJUnitTest;
import org.jenkinsci.test.acceptance.junit.DockerTest;
import org.jenkinsci.test.acceptance.junit.WithDocker;
import org.jenkinsci.test.acceptance.junit.WithPlugins;
import org.jenkinsci.test.acceptance.plugins.rvm.Rvm;
import org.jenkinsci.test.acceptance.po.Build;
import org.jenkinsci.test.acceptance.po.FreeStyleJob;
import org.jenkinsci.test.acceptance.po.ShellBuildStep;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@WithPlugins("rvm")
@Category(DockerTest.class)
@WithDocker
public class RvmPluginTest extends AbstractJUnitTest {


    @Inject
    private DockerContainerHolder<PackageInstallationContainer> dockerContainer;

    @Test
    @WithDocker
    public void smokes() throws Exception {
        dockerContainer.get().assertRunning();
        dockerContainer.get().getLogfile();
        FreeStyleJob job = jenkins.jobs.create();
        job.configure();
        // Could also use "jruby", though it seems to be more intrusive (tries to install openjdk-jre-headless).
        job.addBuildWrapper(Rvm.class).implementation.set("2.4.2");
        job.addBuildStep(ShellBuildStep.class).command("ruby --version");
        job.save();
        Build build = job.startBuild();
        Thread.sleep((10000));
        System.err.println(build.getConsole());
        

        build.shouldSucceed();
        assertThat(build.getConsole(), containsString("ruby 2.4.2"));
    }

}
