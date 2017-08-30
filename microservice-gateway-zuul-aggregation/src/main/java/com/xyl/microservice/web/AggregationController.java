package com.xyl.microservice.web;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import com.xyl.microservice.entity.User;
import com.xyl.microservice.service.AggregationService;

import rx.Observable;
import rx.Observer;

@RestController
public class AggregationController {

	
	private static final Logger LOGGER = LoggerFactory.getLogger(AggregationController.class);
	
	@Autowired
	private AggregationService aggregationService;
	
	
	
	@GetMapping("/aggregate/{id}")
	public DeferredResult<HashMap<String, User>> aggregate(@PathVariable Long id) {
		Observable<HashMap<String, User>> result = this.aggregateObServable(id);
		return this.toDeferredResult(result);
	}


	public Observable<HashMap<String, User>> aggregateObServable(Long id) {
		return Observable.zip(
				this.aggregationService.getUserId(id),
				this.aggregationService.getMovieUserByUserId(id),
				(user, movieUser) -> {
					HashMap<String, User> map = new HashMap<>();
					map.put("user", user);
					map.put("movieUser", movieUser);
					return map;
			});
	}
	
	
	public DeferredResult<HashMap<String, User>> toDeferredResult(Observable<HashMap<String, User>> details) {
		
		DeferredResult<HashMap<String, User>> result = new DeferredResult<>();
		details.subscribe(new Observer<HashMap<String, User>>() {

			@Override
			public void onCompleted() {
				LOGGER.info("完成...");
			}

			@Override
			public void onError(Throwable e) {
				LOGGER.error("发生错误...");
			}

			@Override
			public void onNext(HashMap<String, User> t) {
				result.setResult(t);
			}
			
		});
		
		return result;
	}

}
