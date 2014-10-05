package se.svt.wordcloud.util

import org.junit.Test


class ConnectionFacadeTest {

    def sampleXmlUrl = "http://www.feedforall.com/sample.xml"

    @Test
    public void test_httpGet_of_xml() {

        def response = ConnectionFacade.httpGet(sampleXmlUrl)
        assert response.status == 200
        assert response.data.channel.item.description.size() == 9

    }
}
