Zatch's Http Service
===================

----------
A simple foray into implementing a RFC 2616 compliant webserver.

Building and Running
-------------

This project is built using the  [Maven Shade Plugin][1].  To build download the source, navigate to the root directory of the project and run:
```
mvn clean install
java -jar target/parent-0.0.1-SNAPSHOT-standalone.jar
```

This will package the jar into a runnable and then start it. It runs on port 8080 and logs to stout. The level and output location can be configured in logback.xml found in src/main/resources.

Unit tests can be found in the src/test/java package and are run every time the project is built with maven. Additionally you can see the test coverage in Eclipse using the EclEmma Java Code Coverage plugin by pointing it at the test package.

Performance tests can be run with the [Apache bench mark tool][2] using the following command.
```
ab -c 8 -n 10000 http://127.0.0.1:8080/<file to retreive>

This is ApacheBench, Version 2.3 <$Revision: 1663405 $>
Copyright 1996 Adam Twiss, Zeus Technology Ltd, http://www.zeustech.net/
Licensed to The Apache Software Foundation, http://www.apache.org/

Benchmarking 127.0.0.1 (be patient)
Completed 1000 requests
Completed 2000 requests
Completed 3000 requests
Completed 4000 requests
Completed 5000 requests
Completed 6000 requests
Completed 7000 requests
Completed 8000 requests
Completed 9000 requests
Completed 10000 requests
Finished 10000 requests


Server Software:        
Server Hostname:        127.0.0.1
Server Port:            8080

Document Path:          /file.txt
Document Length:        41 bytes

Concurrency Level:      8
Time taken for tests:   2.565 seconds
Complete requests:      10000
Failed requests:        0
Total transferred:      1570000 bytes
HTML transferred:       410000 bytes
Requests per second:    3897.94 [#/sec] (mean)
Time per request:       2.052 [ms] (mean)
Time per request:       0.257 [ms] (mean, across all concurrent requests)
Transfer rate:          597.63 [Kbytes/sec] received

Connection Times (ms)
              min  mean[+/-sd] median   max
Connect:        0    0   0.2      0      13
Processing:     0    2   4.2      1     143
Waiting:        0    2   4.2      1     143
Total:          0    2   4.2      2     143

Percentage of the requests served within a certain time (ms)
  50%      2
  66%      2
  75%      2
  80%      2
  90%      3
  95%      5
  98%      7
  99%      9
 100%    143 (longest request)
```



  [1]: https://maven.apache.org/plugins/maven-shade-plugin/
  [2]: https://httpd.apache.org/docs/2.2/programs/ab.html

