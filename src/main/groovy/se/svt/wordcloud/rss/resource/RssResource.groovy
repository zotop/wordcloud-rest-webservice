package se.svt.wordcloud.rss.resource

import groovy.json.JsonBuilder
import org.jsoup.Jsoup
import se.svt.wordcloud.rss.client.RssClient
import se.svt.wordcloud.util.TextAnalyser

import javax.ws.rs.DefaultValue
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;


@Path("rss")
public class RssResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String get(@QueryParam("url") String url, @DefaultValue("100") @QueryParam("limit") int limit) {
        try {
            def rssClient = new RssClient()
            def rssFeed = rssClient.fetchRssFeedFromUrl(url)
            def rssContent = rssClient.extractRssContent(rssFeed)
            def mostFrequentWordsMap = new TextAnalyser(text: rssContent).getMostFrequentWords(limit)
            new JsonBuilder(mostFrequentWordsMap).toPrettyString()
        } catch (Exception exception) {
            exception.printStackTrace()
            sendErrorMessage(exception)
        }
    }

    def sendErrorMessage(Exception exception) {
        def errorMessage = "Error occurred"
        if (exception.getMessage()) {
            errorMessage = exception.getMessage()
        }
        def errorJson = new JsonBuilder()
        errorJson { error errorMessage }
    }

}