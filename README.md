
# Image Scrapper using Multithreading 

I have used Spring Boot application for developing this Image Finder Application, which is
structured into controllers, services, utility, Exception, advice classes.
The steps you provided are a set of commands to build and run the application using Maven.
Here's a breakdown of my project structure:

## Controller:
### Location: 
src/main/java/com/imagefinder/controller
### MainUrlHandler:
Handles all kinds of requests (GET, POST, PUT, DELETE) on the "/main" URL.

## Service:
### Location: 
src/main/java/com/imagefinder/service
### MainUrlService 
Is an interface, and its implementation is in MainUrlServiceImpl located in the
'impl' folder.
MainUrlServiceImpl The class includes a method called doPostHandler responsible for
managing the business logic related to handling POST requests specifically for the "/main" URL.
In this class, I've opted for concurrent maps instead of synchronized maps. The choice of
concurrent maps is aimed at enhancing performance in a multi-threaded environment. Unlike
synchronized maps that lock the entire map during modifications, concurrent maps only lock
specific portions, allowing for concurrent modifications without blocking the entire map. This
approach contributes to better performance when working with threads.

## Util:
### Location: 
src/main/java/com/imagefinder/util Contains two classes:
### Domain Name: 
Responsible for returning the combined string of the protocol and host of the
URL sent from the frontend. This is used to check if the links extracted from the URL are from
the same domain.

### ScrapeData:
Responsible for scraping image links and href links found on the crawled URL.
### ExcecutorInstance: 
This class adheres to the singleton design pattern and gives access to the
executor instance. Essentially, this means that when the application begins, the executor is set
up and ready to allocate threads for tasks. However, we've added a unique feature: we've
organized an array of executors, breaking down the large collection of threads into smaller
segments. These segments are then assigned to different URLs using a straightforward hash
code algorithm. This way, a single task won't monopolize all the threads, and other tasks won't
suffer from a lack of resources due to a large thread pool. Each task uses only a specific part of
the threads, ensuring fair distribution and preventing resource shortages for other tasks.
Exception: This folder is responsible for having all the classes of exception faced
in these applications like DomainNotFoundException.

### Location: 
src/main/java/com/imagefinder/exception
### DominNotFoundExcpetion: 
This class extends Exception class to define the domain not found
exception.
Advice: This folder is responsible for the handling of these exception defined in
the exception folder means what action we should take when we face these
exceptions. It consists of Handler class.

### Location: 
src/main/java/com/imagefinder/advice
### Handler:
This class is responsible for having all functions which helps us to handle the
exceptions.


## To run the code: Open the terminal in your preferred IDE.
Execute the command "mvn clean install” to build the project.
After successful build, execute "mvn spring-boot: run" to start the Spring Boot application.
Make sure you have.
Maven installed and configured your system. Additionally, if you need any assistance please let
me know.
