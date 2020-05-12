package com.wekay.zookeeperspring;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryOneTime;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.togglz.core.manager.EnumBasedFeatureProvider;
import org.togglz.core.repository.StateRepository;
import org.togglz.core.spi.FeatureProvider;
import org.togglz.zookeeper.ZookeeperStateRepository;

@Configuration
public class FeatureToggleConfiguration {
    private static final String ZOOKEEPER_CONNECTION_STRING = "localhost:2181";
    private static final String TOGGLE_NODE = "/toggle";

    @Bean
    public StateRepository stateRepository() throws Exception {
        final CuratorFramework client = CuratorFrameworkFactory
                .builder()
                .connectString(ZOOKEEPER_CONNECTION_STRING)
                .retryPolicy(new RetryOneTime(2000))
                .zk34CompatibilityMode(true)
                .build();

        client.start();

        return ZookeeperStateRepository.newBuilder(client, TOGGLE_NODE).build();
    }

    @Bean
    public FeatureProvider featureProvider() {
        return new EnumBasedFeatureProvider(Features.class);
    }

}
