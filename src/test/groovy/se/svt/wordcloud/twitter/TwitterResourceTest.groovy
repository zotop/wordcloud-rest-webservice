package se.svt.wordcloud.twitter

import groovyx.net.http.RESTClient
import org.junit.Test
import se.svt.wordcloud.resource.twitter.OAuthSettings

class TwitterResourceTest {

    @Test
    public void test_authentication() {
        def twitter = new RESTClient( 'https://api.twitter.com/1.1/search/' )
        def consumerKey = OAuthSettings.CONSUMER_KEY
        def consumerSecret = OAuthSettings.CONSUMER_SECRET
        def accessToken = OAuthSettings.ACCESS_TOKEN
        def secretToken = OAuthSettings.SECRET_TOKEN

        twitter.auth.oauth consumerKey, consumerSecret, accessToken, secretToken

        assert twitter.get(path : 'tweets.json', query : [q:'almedalen'] ).status == 200
    }
}
