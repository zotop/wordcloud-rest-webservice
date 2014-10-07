package se.svt.wordcloud.rss.client

import groovyx.net.http.RESTClient
import org.junit.Test


class RssClientTest {

    def sampleXmlUrl = "http://www.feedforall.com/sample.xml"

    @Test
    public void test_isValidUrl() {

        assert new RssClient().isValidUrl("ijoi%jgr@feioge") == false
        assert new RssClient().isValidUrl(sampleXmlUrl) == true
    }

    @Test
    public void test_fetchRssFeedFromUrl() {

        def response = new RssClient().fetchRssFeedFromUrl(sampleXmlUrl)
        assert response.status == 200
        assert response.data.channel.item.description.size() > 0

    }

    @Test
    public void test_removeHtmlTagsFromText() {

        def textWithoutHtmlTags = new RssClient().removeHtmlTagsFromText("<h1>Hello</h1>")
        assert textWithoutHtmlTags == "Hello"
    }
}
