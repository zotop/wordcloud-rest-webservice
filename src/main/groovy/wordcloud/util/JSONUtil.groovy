package wordcloud.util

import groovy.json.JsonBuilder


class JSONUtil {

    def public static errorMessage(def exception) {
        def errorMessage = "Error occurred"
        if (exception.getMessage()) {
            errorMessage = exception.getMessage()
        }
        def errorJson = new JsonBuilder()
        errorJson { error errorMessage }
    }
}
