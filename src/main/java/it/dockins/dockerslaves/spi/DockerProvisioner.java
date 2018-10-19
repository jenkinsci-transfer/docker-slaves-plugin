package it.dockins.dockerslaves.spi;

import hudson.Launcher;
import hudson.Proc;
import hudson.model.TaskListener;
import it.dockins.dockerslaves.Container;
import it.dockins.dockerslaves.ContainersContext;
import it.dockins.dockerslaves.DockerComputer;

import java.io.IOException;

/**
 * @author <a href="mailto:nicolas.deloof@gmail.com">Nicolas De Loof</a>
 */
public abstract class DockerProvisioner {

    public abstract ContainersContext getContext();

    /**
     * Launch a container to host jenkins remoting agent and establish a channel as a Jenkins slave.
     */
    public abstract Container launchRemotingContainer(DockerComputer computer, TaskListener listener) throws IOException, InterruptedException;

    /**
     * Launch a container with adequate tools to run the SCM checkout build phase.
     */
    public abstract Container launchScmContainer(TaskListener listener) throws IOException, InterruptedException;

    /**
     * Launch build environment as defined by (@link Job}'s {@link it.dockins.dockerslaves.spec.ContainerSetDefinition}.
     */
    public abstract Container launchBuildContainers(Launcher.ProcStarter starter, TaskListener listener) throws IOException, InterruptedException;

    /**
     * Run specified process inside the main build container
     */
    public abstract Proc launchBuildProcess(Launcher.ProcStarter procStarter, TaskListener listener) throws IOException, InterruptedException;

    /**
     * Cleanup all allocated resources
     */
    public abstract void clean(TaskListener listener) throws IOException, InterruptedException;
}
