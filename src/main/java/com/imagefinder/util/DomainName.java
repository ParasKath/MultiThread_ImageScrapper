package com.imagefinder.util;

import java.net.URI;
import java.net.URISyntaxException;

import com.imagefinder.exception.DomainNotFoundException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DomainName {

	// Todo - From the given Url we are extracting the protocol and host
	public static String getDomainName(String url) throws DomainNotFoundException {
		
		try {
	          URI uri = new URI(url);
			  // extracting the host and protocol from the given url
	          String protocol = uri.getScheme();
	          String host = uri.getHost();

	          if (protocol != null && host != null) {
				  return protocol+"://"+host;
	          }
			  else
			  {
				  throw new DomainNotFoundException("Domain not found util from else clause");
			  }
	      } catch (URISyntaxException e) {
	    	  log.error("Issue Caused while Extracting the domain Name");
			  throw new DomainNotFoundException("Domain from the domain name util class not found");
	      }
	}

}
