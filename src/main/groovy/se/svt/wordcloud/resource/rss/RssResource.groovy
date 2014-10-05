package se.svt.wordcloud.resource.rss

import groovy.json.JsonBuilder
import se.svt.wordcloud.util.ConnectionFacade
import se.svt.wordcloud.util.TextAnalyser

import javax.ws.rs.DefaultValue
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.apache.commons.validator.routines.UrlValidator;



@Path("rss")
public class RssResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String get(@QueryParam("url") String url, @DefaultValue("100") @QueryParam("limit") int limit) {
        try {
            if (isValidUrlToRssFeed(url)) {
                def xmlResponse = ConnectionFacade.httpGet(url)
                String rssContent = extractRssContent(xmlResponse)
                def mostFrequentWordsMap = new TextAnalyser(text: rssContent).getMostFrequentWords(limit)
                return new JsonBuilder(mostFrequentWordsMap)
            } else {
                throw new Exception("Url to Rss feed is invalid")
            }
        } catch (Exception exception) {
            JsonBuilder errorJson = buildErrorJson(exception)
            return errorJson
        }

    }

    private JsonBuilder buildErrorJson(Exception exception) {
        def errorMessage = "Error occurred"
        if (exception.getMessage()) {
            errorMessage = exception.getMessage()
        }
        def errorJson = new JsonBuilder()
        errorJson {
            error errorMessage
        }
        errorJson
    }

    /*
     *  Fetches the text of all <description> elements
     */
    private String extractRssContent(xmlResponse) {
        def rssContent = new StringBuilder()
        xmlResponse.data.channel.item.description.each {
            rssContent.append(it.text()).append(" ")
        }
        rssContent.toString()
    }

    private boolean isValidUrlToRssFeed(String url) {
         def allSchemesAllowed = UrlValidator.ALLOW_ALL_SCHEMES
         UrlValidator urlValidator = new UrlValidator(allSchemesAllowed)
         urlValidator.isValid(url) && url.endsWith(".xml")
    }
}