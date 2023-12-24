package com.imagefinder.services.impl;

import com.imagefinder.services.MainUrlService;
import com.imagefinder.util.DomainName;
import com.imagefinder.util.ExecutorInstance;
import com.imagefinder.util.ScrapeData;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

@Service
@Slf4j
@AllArgsConstructor
public class MainUrlServiceImpl implements MainUrlService {

    public List<String> doPostHandler(String url){
        try{
            // Geeting the domain name used to check if the link that we get to crawl further belong to same domain or not
            String hostDomain = DomainName.getDomainName(url);

            // Getting the object for the scarpeData class to call scape function to scrap the required data from the given url
            ScrapeData scrapeDataObj = new ScrapeData();

            // Making two concurrent hashmaps to hold the final imageURl and vistedUrl
            ConcurrentHashMap<String, Boolean> visitedURLsMap = new ConcurrentHashMap<>();
            ConcurrentHashMap<String, Boolean> imageURLsMap = new ConcurrentHashMap<>();

            // Creating the list of the futures to hold the state of all the work submitted to the excutorservice
            List<Future<?>> futures = new CopyOnWriteArrayList<>();

            // Marking the url send from the user
            visitedURLsMap.put(url,true);

            // Creating the excutor service and assigning the pool size value from application.properties file located in resource folde
            ExecutorService executor = ExecutorInstance.getinstance();

            // Submittting the work to the excutor pool and also adding this to the future list
            futures.add(executor.submit(() -> scrapeDataObj.scrapeImagesAndLinks(url, imageURLsMap, visitedURLsMap, executor, hostDomain,futures)));

            // After all the task are completed check the whether all the work are completed or not
            boolean allTasksCompleted = false;
            while (!allTasksCompleted) {
                allTasksCompleted = true;
                for (Future<?> future : futures) {
                    if (!future.isDone()) {
                        allTasksCompleted = false;
                        break;
                    }
                }
            }

            // Now adding all the imageUrl links into this arraylist
            List<String> ImageUrl = new ArrayList<>();

            for(Map.Entry<String,Boolean> rvalue : imageURLsMap.entrySet()){
                ImageUrl.add(rvalue.getKey());
            }

            // Now sending back the imageurl list
            return ImageUrl;
        }
        catch(Exception error){
            log.error("An Error occured in Main Url Service -"+error);
        }

        return null;
    }
}
