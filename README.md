![Java CI with Gradle](https://github.com/Abhay22/mybiddingapp/workflows/Java%20CI%20with%20Gradle/badge.svg?branch=master&event=push)
# Bidding Application
1. Core Language: Java
2. Framework: Spring Boot
3. Unit Test Language: Groovy
4. Unit testing done using spock framework.
5. H2 embedded database used.
6. Liquibase has been used to manage schema creation and data population.
7. @Version is used to imlement Concurrency in distributed env
8. API is secured with Oauth2
9. Gradle workflow enable for CI in github

# Steps To Run This Application
 * Download or clone the broject 
 * Traverse to `mybiddingapp`directory
 *  execute `./gradle clean build` (This will generate jar file under directory `mybiddingapp/build/libs/mybidding-0.0.1-SNAPSHOT.jar`)
 * After build successful
      * run application using `java -jar build/libs/mybidding-0.0.1-SNAPSHOT.jar`
  
 ##### API Details

 * Generating Bearer Token
    * http://localhost:8080/user?user=abhay&password=pass 
    
    Simple api is used to generate bearer token and for simplicity just passing user/id ad pass as url param which is ofcoure is not a secure way to do it.
    
 * Auction API: 
    * Authetication is disabled and it supports multiple URLs with different params
    * http://localhost:8080/auction    
    * http://localhost:8080/auction?status=RUNNING
    * http://localhost:8080/auction?status=RUNNING&pageNo=0&pageSize=4
    
 * Place bid API 
    * http://localhost:8080/placeBid       [authentication enabled]
    
#

    
 #### How to fetch all "Auctions" (Based upon page size)

   1. GET URL: http://localhost:8080/auction?status=RUNNING&pageNo=0&pageSize=4
   
   <img src="https://github.com/Abhay22/mybiddingapp/blob/master/image/auction.png" alt="drawing" width="1000" height="300"/> 
     
#

  #### How to place a bid
  
   1. * Generate a bearer token to call API
          Post URL: http://localhost:8080/user?user=abhay&password=pass
          
         <img src="https://github.com/Abhay22/mybiddingapp/blob/master/image/GetBearerToken.png" alt="drawing" width="1000" height="300"/> 
          
   2. post the bid request
       POST URL: https://github.com/Abhay22/mybiddingapp/blob/master/image/placebit.png

        * Header
           * Content-Type : application/json
        * Authorization
           * Type  - Bearer Token
           * Token - `Bearer eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiJBdWN0aW9uQXBwIiwic3ViIjoiYWJoYXkiLCJhdXRob3JpdGllcyI6WyJST0xFX1VTRVIiXSwidXNlcm5hbWUiOiJhYmhheSIsImlhdCI6MTU5OTE0ODU1NiwiZXhwIjoxNTk5MTQ5MTU2fQ.WvfqTe_6AokNBv4C-hNEmw2yCud3SkWisq12-Ntvmr6Ix8FkMHwhPyBXTPe69_yE6iLsgStKSAF3AP1es2Yp4w`
        
        * Body part
          ```
               {

               "itemCode": "Auction111",
               "bidAmount": "1200"

               }

         <img src="https://github.com/Abhay22/mybiddingapp/blob/master/image/placebit.png" alt="drawing" width="1000" height="300"/> 
          
          
