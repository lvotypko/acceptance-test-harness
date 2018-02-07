package org.jenkinsci.test.acceptance.docker.fixtures;

import org.jenkinsci.test.acceptance.docker.DockerContainer;
import org.jenkinsci.test.acceptance.docker.DockerFixture;

/**
 * Created by lvotypko on 1/30/18.
 */
@DockerFixture(id="package-install", ports=22)
public class PackageInstallationContainer extends SshAgentContainer {
}