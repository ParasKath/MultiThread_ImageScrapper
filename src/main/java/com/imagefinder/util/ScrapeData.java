package com.imagefinder.util;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

// Todo - Scarping the ImageURls and href links from the websites
@Slf4j
public class ScrapeData {

	public void scrapeImagesAndLinks(String url,ConcurrentHashMap<String, Boolean> imageURLsMap ,
			ConcurrentHashMap<String, Boolean> visitedURLsMap,ExecutorService executor, String topdomain,
			List<Future<?>> futures) {

		try {

			//With this we can simply avoid being blocked by manually setting these HTTP headers.
			Document doc = Jsoup.connect(url).userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/108.0.0.0 Safari/537.36")
					.header("Accept-Language", "*").get();

			// extracting all the img tags
			Elements images = doc.getElementsByTag("img");

			// extracting all the links from the page
			Elements links = doc.getElementsByTag("a");

			// collected images are added to imageUrl map but before adding we are checking whether we have that value in map or not
			for (Element image : images) {
				String imageUrl = image.attr("abs:src"); // Get the absolute URL of the image
				if(imageUrl.length()>0 && imageURLsMap.containsKey(imageUrl)==false) {
					imageURLsMap.put(imageUrl,true);
				}
			}

			// collected links are added to visitedUrl map but before adding we are checking whether we have that value in map or not
			// And also submitting the undiscovered url to the excutor pool to execute
			for (Element link : links) {
				String href = link.attr("abs:href"); // Absolute URL

				if (href.startsWith(topdomain) && href.endsWith("#")==false && visitedURLsMap.containsKey(href)==false) {
					visitedURLsMap.put(href,true);
					futures.add(executor.submit(() -> scrapeImagesAndLinks(href,imageURLsMap,visitedURLsMap,executor,topdomain,futures)));
				}
			}

		} catch (Exception e) {
			log.error("Could not Run this URl because of 404 status code "+url);
		}
	}
}
