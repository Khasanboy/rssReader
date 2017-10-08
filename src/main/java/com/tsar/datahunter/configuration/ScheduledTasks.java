package com.tsar.datahunter.configuration;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.tsar.datahunter.model.Feed;
import com.tsar.datahunter.service.FeedService;

@Component
public class ScheduledTasks {
	
	@Autowired
	FeedService feedService;
	
	private static final Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);
	
	SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
	
	//Url is defined in the application.properties file
	@Value("${url}")
	String url;
	
	@Scheduled(fixedRate = 30000)//Every 30 seconds
	public void scheduleTasksWithFixedRate(){
		List<Feed> feeds = feedService.readFeed("http://feeds.bbci.co.uk/news/world/rss.xml");
		
		for(Feed feed:feeds){
			
			if(feedService.isNew(feed)){
				feedService.editFeed(feed);
				feedService.addFeed(feed);
			}
		}
		
		logger.debug("-------------------- "+ format.format(new Date()) +" ------------------------------------");
	}
}
