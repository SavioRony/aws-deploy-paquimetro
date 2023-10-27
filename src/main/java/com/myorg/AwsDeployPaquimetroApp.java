package com.myorg;

import software.amazon.awscdk.App;
import software.amazon.awscdk.Environment;
import software.amazon.awscdk.StackProps;

import java.util.Arrays;

public class AwsDeployPaquimetroApp {
    public static void main(final String[] args) {
        App app = new App();
        VpcStack vpcStack = new VpcStack(app, "Vpc");

        ClusterStack clusterStack = new ClusterStack(app, "Cluster", vpcStack.getVpc());
        clusterStack.addDependency(vpcStack);

        RdsStack rdsStack = new RdsStack(app, "Rds", vpcStack.getVpc());
        rdsStack.addDependency(vpcStack);

        Service01FargateStack service01 = new Service01FargateStack(app, "ServiceFiap", clusterStack.getCluster());
        service01.addDependency(clusterStack);
        service01.addDependency(rdsStack);

        app.synth();
    }
}

