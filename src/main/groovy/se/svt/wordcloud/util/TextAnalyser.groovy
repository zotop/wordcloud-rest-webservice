package se.svt.wordcloud.util

import java.util.regex.Matcher
import java.util.regex.Pattern;

public class TextAnalyser {

    def text = ""

    def getWordFrequencyMap() {
        def frequencyCounter = [:]
        Pattern pattern = Pattern.compile("[\\w']+");
        Matcher matcher = pattern.matcher(text);
        matcher.each {word ->
            def wordCounter = frequencyCounter[word] ?: 0
            frequencyCounter[word] = wordCounter + 1
        }
        frequencyCounter
    }
}
