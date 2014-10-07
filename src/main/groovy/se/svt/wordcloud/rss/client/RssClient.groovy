package se.svt.wordcloud.rss.client

import groovyx.net.http.RESTClient
import org.apache.commons.validator.routines.UrlValidator
import org.jsoup.Jsoup



class RssClient {

    def fetchRssFeedFromUrl(def url) {
        if (isValidUrl(url)) {
            new RESTClient(url).get([:])
        } else {
            throw new Exception("Url to Rss feed is invalid")
        }
    }

    def isValidUrl(String url) {
        String[] allowedSchemes = ["http", "https"]
        UrlValidator urlValidator = new UrlValidator(allowedSchemes)
        urlValidator.isValid(url)
    }

    /*
     *  Concatenates the text of all <description> elements
     */
    def extractRssContent(def rssFeed) {
        def rssContent = new StringBuilder()
        rssFeed.data.channel.item.description.each {
            def textWithoutHtmlTags = removeHtmlTagsFromText(it.text())
            rssContent.append(textWithoutHtmlTags).append(" ")
        }
        rssContent.toString()
    }

    def removeHtmlTagsFromText(def text) {
        Jsoup.parse(text).text()
    }
}
