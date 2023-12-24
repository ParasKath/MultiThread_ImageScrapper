package com.imagefinder.controller;
import com.imagefinder.services.MainUrlService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// ToDo -  Role of this class is to handle all the request coming on "/main"

@Slf4j
@RestController
@AllArgsConstructor
public class MainUrlHandler {
    private MainUrlService mainUrlService;
    @PostMapping("/main")
    @ResponseBody
    public List<String> getUrl(@RequestParam(name = "url") String url) {
        try {
            // calling the service method which consist of the business logic
            return mainUrlService.doPostHandler(url);
        }catch (Exception error){
            log.error("Error Occured in the Main Url controller "+error);
            System.out.println("Error we get");
        }
        return null;
    }
}
