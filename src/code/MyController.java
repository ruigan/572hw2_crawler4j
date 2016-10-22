package code;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

public class MyController {
	
	public static void main(String[] args) throws Throwable {
		
		String crawlStorageFolder = "data/crawl";
		int numberOfCrawlers = 7;
		
		// config
		CrawlConfig config = new CrawlConfig();
		config.setCrawlStorageFolder(crawlStorageFolder);
		
		config.setMaxPagesToFetch(10000); // Maximum Page # 10000
        config.setPolitenessDelay(2500);  // Request delay 2500 ms
        config.setMaxDepthOfCrawling(16); // Maximum Depth 16
        
        /*
         * Do you want crawler4j to crawl also binary data ?
         * example: the contents of pdf, or the metadata of images etc
         */
        config.setIncludeBinaryContentInCrawling(true); // 
   
		
		
        /*
         * Instantiate the controller for this crawl.
         */
		PageFetcher pageFetcher = new PageFetcher(config);
		RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
		RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
		CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer);
		
		
		/*
		* For each crawl, you need to add some seed urls. These are the first
		* URLs that are fetched and then the crawler starts following links * which are found in these pages
		*/
		controller.addSeed("http://www.viterbi.usc.edu/");
		
		
		/*
		* Start the crawl. This is a blocking operation, meaning that your code
		* will reach the line after this only when crawling is finished.
		*/
		controller.start(MyCrawler.class, numberOfCrawlers);
		
	}
}
