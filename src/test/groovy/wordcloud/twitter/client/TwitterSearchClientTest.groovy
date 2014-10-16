package wordcloud.twitter.client

import groovyx.net.http.RESTClient
import org.junit.Test


class TwitterSearchClientTest {

    @Test
    public void test_authorize() {
        def twitter = new RESTClient(TwitterSearchClient.BASE_SEARCH_API_URL)
        def consumerKey = OAuthSettings.CONSUMER_KEY
        def consumerSecret = OAuthSettings.CONSUMER_SECRET
        def accessToken = OAuthSettings.ACCESS_TOKEN
        def secretToken = OAuthSettings.SECRET_TOKEN
        twitter.auth.oauth consumerKey, consumerSecret, accessToken, secretToken

        assert twitter.get(path : 'tweets.json', query : [q:'#almedalen'] ).status == 200
    }

}
