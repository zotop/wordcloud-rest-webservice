wordcloud-rest-webservice
=========================

Groovy RESTful webservices using Jersey framework.

Twitter Service: Fetches statuses from Twitter containing an hashtag
and and generates a map of the most frequent words found.


RSS Service: Fetches an RSS feed from an url and generates a map
of the most frequent words found.


Herokufied demo
=========================

Heroku url: http://wordcloud-rest.herokuapp.com/

Twitter example using the Search API:

http://wordcloud-rest.herokuapp.com/twitter?hashtag=money&limit=10

Gives the 10 most frequent words on statuses containing the hashtag "money".

RSS example:

http://wordcloud-rest.herokuapp.com/rss?url=http://www.feedforall.com/sample-feed.xml

Gives the 100 (default) most frequent words in all the <description> elements.