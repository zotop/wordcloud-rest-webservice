package se.svt.wordcloud.twitter.client

import groovyx.net.http.RESTClient

class TwitterSearchClient {

    protected static final BASE_SEARCH_API_URL = "https://api.twitter.com/1.1/search/"
    def twitterRestClient

    TwitterSearchClient() {
        authorize()
    }

    def authorize() {
        twitterRestClient = new RESTClient(BASE_SEARCH_API_URL)
        def consumerKey = OAuthSettings.CONSUMER_KEY
        def consumerSecret = OAuthSettings.CONSUMER_SECRET
        def accessToken = OAuthSettings.ACCESS_TOKEN
        def secretToken = OAuthSettings.SECRET_TOKEN
        twitterRestClient.auth.oauth consumerKey, consumerSecret, accessToken, secretToken
    }

    def findTweetsByHashTag(String hashTagValue) {
        def response = twitterRestClient.get(path: 'tweets.json', query: [q: '#' + hashTagValue])
        if (response.status != 200) {
            throw new Exception("Failure to retrieve the tweets")
        }
        def tweetsConcatenation = new StringBuilder()
        response.responseData.statuses.each {
            tweetsConcatenation.append(it.text).append(" ")
        }
        tweetsConcatenation
    }

}
