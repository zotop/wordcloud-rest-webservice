package wordcloud.twitter.resource

import groovy.json.JsonBuilder
import wordcloud.twitter.client.TwitterSearchClient
import wordcloud.util.JSONUtil
import wordcloud.util.TextAnalyser

import javax.ws.rs.DefaultValue
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;


@Path("twitter")
public class TwitterResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String mostFrequentWords(@QueryParam("hashtag") String hashtag, @DefaultValue("100") @QueryParam("limit") int limit) {
        try {
            def tweetsByHashTag = new TwitterSearchClient().findTweetsByHashTag(hashtag)
            def mostFrequentWords = new TextAnalyser(text: tweetsByHashTag).getMostFrequentWords(limit)
            new JsonBuilder(mostFrequentWords).toPrettyString()
        } catch (Exception exception) {
            exception.printStackTrace()
            JSONUtil.errorMessage(exception)
        }
    }

}