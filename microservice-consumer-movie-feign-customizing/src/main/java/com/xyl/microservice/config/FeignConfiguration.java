package com.xyl.microservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.Contract;
/**
 * 该类为自定义的Feign配置类
 * 注意: 该类不应该在主应用程序上下文的@componentScan中
 * @author xuelong
 *
 */
@Configuration
public class FeignConfiguration {

	/**
	 * 将契约改为feign原生的默认契约。这样就可以使用feign自带的注解了。
	 * @return
	 */
	@Bean
	public Contract feignContract() {
		return new feign.Contract.Default();
	}
}
