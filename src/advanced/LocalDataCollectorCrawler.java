package advanced;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

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

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Set;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.BinaryParseData;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;
import models.Node1;
import models.Node2;
import models.Node3;

public class LocalDataCollectorCrawler extends WebCrawler {

	private static String domainName = "http://www.cnn.com/";
	private static final Logger logger = LoggerFactory.getLogger(LocalDataCollectorCrawler.class);

	
	private static final Pattern filters = Pattern.compile(
			".*(\\.(css|js|mid|mp2|mp3|mp4|wav|avi|mov|mpeg|ram|m4v" + "|rm|smil|wmv|swf|wma|zip|rar|gz))$");

	private static final Pattern dotPatterns = Pattern.compile(".*(\\.[a-z]+)$");
	private static final Pattern allowPatterns = Pattern.compile(".*(\\.(html|htm|doc|pdf|bmp|gif|jpe?g|png|tiff?))$");

	CrawlStat myCrawlStat;

	public LocalDataCollectorCrawler() {
		myCrawlStat = new CrawlStat();
	}

	
	@Override
	public boolean shouldVisit(Page referringPage, WebURL url) {
		// filter illegal suffix
		String href = url.getURL().toLowerCase();
		if (filters.matcher(href).matches()) {
			return false;
		}
		// filter outside website
		if (!href.startsWith(domainName)) {
			return false;
		}
		// if have suffix, it must within the allow range
		String path = url.getPath().toLowerCase();
		if (dotPatterns.matcher(path).matches() && !allowPatterns.matcher(path).matches()) {
			return false;
		}

		return true;
	}

	
	
	@Override
	public void visit(Page page) {
		
		String url = page.getWebURL().getURL();
		int statusCode = page.getStatusCode();
		
		// 4.1 record all url and status code
		CrawlStat.recordToFetchList(new Node1(url, statusCode));
		
		// 4.2 the files it successfully downloads
		if (statusCode == 200) {
			int fileSize = page.getContentData().length;
			String contentType = page.getContentType();
			Set<WebURL> links = page.getParseData().getOutgoingUrls();
			
			CrawlStat.recordToVisitList(new Node2(url, fileSize, links.size(), contentType));
			
			// 4.3 record the URLs
			for (WebURL link: links) {
				boolean flag = link.getURL().toLowerCase().startsWith(domainName)? true: false;
				CrawlStat.recordToUrlList(new Node3(url, flag));
			}
		}
		
		
		
		
		
		if (page.getParseData() instanceof HtmlParseData || page.getParseData() instanceof BinaryParseData) {
			
			
		
		}
		
		

//		// General Stat
//		myCrawlStat.incProcessedPages();
//
//		if (page.getParseData() instanceof HtmlParseData) {
//			HtmlParseData parseData = (HtmlParseData) page.getParseData();
//			Set<WebURL> links = parseData.getOutgoingUrls();
//			myCrawlStat.incTotalLinks(links.size());
//			try {
//				myCrawlStat.incTotalTextSize(parseData.getText().getBytes("UTF-8").length);
//			} catch (UnsupportedEncodingException ignored) {
//				// Do nothing
//			}
//		}
		
	}

	
}
