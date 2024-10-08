package com.example.reproducer;

import io.vertx.core.*;

public class MainLauncher extends Launcher {

    public static void main(String[] args) {
        // run com.example.reproducer.MainVerticle
        // break point is:
        // 1. io.vertx.core.impl.launcher.commands.BareCommand.configureFromSystemProperties
        // 2. io/vertx/core/impl/launcher/commands/BareCommand.java:225
        new MainLauncher().dispatch(args);
    }

    @Override
    public void beforeStartingVertx(VertxOptions options) {
        System.out.println(options.toJson());
    }

    @Override
    public void beforeDeployingVerticle(DeploymentOptions deploymentOptions) {
        // ↓: I used this way to resolve my problem by hook deployment options
        // deploymentOptions.setThreadingModel(ThreadingModel.VIRTUAL_THREAD);
        System.out.println(deploymentOptions.toJson());
        System.out.println(deploymentOptions.toJson().getString("threadingModel"));
    }

    @Override
    public void afterStartingVertx(Vertx vertx) {
        super.afterStartingVertx(vertx);
    }
}
