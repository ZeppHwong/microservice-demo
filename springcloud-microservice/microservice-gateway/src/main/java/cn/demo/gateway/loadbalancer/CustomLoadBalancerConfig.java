package cn.demo.gateway.loadbalancer;

import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClients;

@LoadBalancerClients(defaultConfiguration = RequestCountLoadBalancer.class)
public class CustomLoadBalancerConfig {

}