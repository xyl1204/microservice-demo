package com.xyl.microservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
/**
 * 该类为自定义Ribbon的配置类
 * 注意:该类不应该在主应用程序上下文的@ComponentScan中
 * @author xuelong
 *
 */
@Configuration
public class RibbonConfiguration {

	@Bean
	public IRule ribbonRule() {
		return new RandomRule();
	}
}
