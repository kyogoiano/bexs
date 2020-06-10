First of all, thank you for the opportunity to do this exercise!

Design decisions: 

* Use Micronaut framework to expose the REST endpoints. 
I`ve never used that framework before... 
So for me this exercise was a complete task, 
including the decision to use one framework or not. Maybe with more time and using 
"Oracle JDK" it can be accomplished using an internal Htttp Server included on it. 
But instead to use the commonly used (even by me) Spring Boot, i've attained an harder way,
to use a new, compact and good framework called Micronaut, the micronaut was used not only to expose
the endpoint, but it includes an embedded web server called netty, in a future release of 
this test i'll include an reactive one, but for now it's configured on the old fashioned 
blocking way.
* Using Picocli integration with Micronaut, that way the same app can have an command line
 interface easily. 
* Use of lombok edge - first lombok is a great lib to create apps in java wit less time to market
(lombok currently does not support well jdk 14, but the cutting edge one
did a good job, looking for the next major release, (as an exercise i'd the idea to be 
familiar with new technologies))

Build requirements: 
    openjdk hotspot 14.0.1 
    Gradle 6.5 

Build instructions: 
    gradle clean build -x test
    ---Note: every time you build the app, it`ll run an integration test that will include an line on 
    input-file.txt 
Running with gradle:
    gradle run
Run tests with gradle: 
    gradle tests
Running outside (make sure your jdk is 14):
    Micronaut use a shadow packaging: 
        After build, the project generate at ../build/distributions files for distribute it, just extract
        the more appropriate file for you os, tar or zip, (both includes an .bat on an unix-like executable)
        on linux jus go the path and run: ./bexs

Using:
    After running it'll expose the endpoint and read the input file to ram memory, and give you the options to use the 
    command line interface or http request; 
Know Limitations and information:
    * You'll can use the cli only once every time you run the app, but from there you can use the exposed endpoints
     /route/best (get) -> media type plain text
     and /route/insert (post) -> consume a body with media type plain text
    * When you include a line on file by http rest request using insert it'll accept only the hyphen delimiter like: 
        "GRU-ORL-90", but it'll convert it to the original format before including it on file. 
        
Thank you!




 
 