package se.svt.wordcloud.util

import groovyx.net.http.ContentType
import groovyx.net.http.HTTPBuilder
import groovyx.net.http.RESTClient


class ConnectionFacade {

    public static httpGet(String url) {
        def restClient = new RESTClient(url)
        restClient.get([:])
    }
}
