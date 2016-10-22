package advanced;

/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

public class LocalDataCollectorController {
  
  private static final Logger logger = LoggerFactory.getLogger(LocalDataCollectorController.class);

  public static void main(String[] args) throws Exception {
	  
	logger.info("Welcome to RG's crawler");
 
	
    // Set root folder
    String rootFolder = "data/spider";
    int numberOfCrawlers = 1;

    /*
     *  Configuration
     */
    CrawlConfig config = new CrawlConfig();
    config.setCrawlStorageFolder(rootFolder); // Root folder
    config.setMaxPagesToFetch(150);    // Maximum Page # 10000
    config.setPolitenessDelay(1000);  // Request delay 2500 ms
    config.setMaxDepthOfCrawling(16); // Maximum Depth 16

    PageFetcher pageFetcher = new PageFetcher(config);
    RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
    RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
    CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer);

    // -------------------------------- Add seed here --------------------------------
    controller.addSeed("http://www.cnn.com/");
    controller.start(LocalDataCollectorCrawler.class, numberOfCrawlers);

  }
}
