package com.wekay.zookeeperspring;

import org.togglz.core.Feature;
import org.togglz.core.annotation.Label;
import org.togglz.core.context.FeatureContext;

public enum Features implements Feature {

    @Label("A feature 1")
    FEATURE_1,

    @Label("A feature 2")
    FEATURE_2;

    public boolean isActive() {
        return FeatureContext.getFeatureManager().isActive(this);
    }
}
