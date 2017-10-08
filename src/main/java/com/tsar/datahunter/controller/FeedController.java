package com.tsar.datahunter.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tsar.datahunter.model.Feed;
import com.tsar.datahunter.service.FeedService;

@RestController
@RequestMapping("/api/feeds")
public class FeedController {
	
	@Autowired
	FeedService feedService;
	
	private final Logger logger = LoggerFactory.getLogger(FeedController.class);
	
	@RequestMapping("/")
	public List<Feed> readFeed(){
		
		 System.out.println(feedService.getAllFeeds());
		 return feedService.getAllFeeds();
		
	}
	
	@RequestMapping("/last10")
	public List<Feed> getLastTen(){
		System.out.println(feedService.getLastTen());
		return feedService.getLastTen();
	}

}
