# Reinvent Ad Searching System 0602 


# Description

Ads Searching is a multi-billion dollar business. In this project, we will implement a simplified search ads stack which selects ads for a given query and returns sorted ads based on some ranking criteria.

Basic process flow: Query understanding -> Select Ads Candidates -> Rank Ads ->Filter Ads -> Pricing -> Allocate Ads

*This is an open-ended challenge. Do your best to come up with your own implementation


[Source Form](https://www.bittiger.io/microproject/KrPpRGNyDEpk4nSdn)


# Functionality
- User can input keywords and search whatever he want to search
- All ads should list in a the page and ranked

# Time Schedule
| Stage | Start  | End | Goals |
| ------------- | ------------- | ------------- | ------------- |
| 0 Week   | 12/29/16  | 01/11/17 | Finish the amazon crawler and user query/click log and stored crawlled data into mysql |
| 1st Week | 01/11/17  | 01/16/17 | Finish basic ads search server v1.0, including ads business logic, user query preprocessing, ads keywords inverted index, similarity algorithm| 
| 2nd Week | 09/12/16  | 09/18/16 | Finish front-end and start back-end |
| 3rd Week | 09/19/16  | 09/25/16 | Back-end and connection to twitter API  |
| 4th Week | 09/26/16  | 10/02/16 | Go over whole process and fix bugs |
| 5th Week | 10/03/16  | 10/09/16 | Fix bug and prepare presentation |


# Used Technology
- Front-end: AngularJS Bootstrap
- Backend: Spring MVC Jetty Lucene Memcached Mysql
- NLP algorithm

# Resource 
- Previous Project Example[link](https://github.com/BitTigerInst/ads-searching-system)
- Apache Lucene[link](http://www.wxdl.cn/index/lucene-source.html)
- Memcached Tutorial[link](http://www.tutorialspoint.com/memcached/)
- Spring in action[link](http://pdf.th7.cn/down/files/1508/Spring%20in%20Action,%204th%20Edition.pdf)
- Bittiger project tutorial[link](https://www.bittiger.io/classpage/w8pphM4Sahx54kAm3)
- Common logging[link](https://commons.apache.org/proper/commons-logging/)
- slf4j[link](http://www.slf4j.org/manual.html)
- jsoup[link](https://jsoup.org/)
- HttpComponents[link](https://hc.apache.org/)
- junit[link](http://junit.org/junit4/)
- commons-langlang3[link](https://commons.apache.org/proper/commons-lang/)


# License
See the [LICENSE](https://opensource.org/licenses/MIT) file for license rights and limitations (MIT).