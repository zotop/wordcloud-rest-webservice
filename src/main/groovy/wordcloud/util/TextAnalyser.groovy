package wordcloud.util

import java.util.regex.Matcher
import java.util.regex.Pattern;

public class TextAnalyser {

    def text = ""

    def getWordFrequencyMap() {
        def frequencyCounter = [:]
        Pattern pattern = Pattern.compile("[\\w']+");
        Matcher matcher = pattern.matcher(text)
        matcher.each {word ->
            word = word.toLowerCase()
            def wordCounter = frequencyCounter[word] ?: 0
            frequencyCounter[word] = wordCounter + 1
        }
        frequencyCounter
    }

    def getMostFrequentWords(def numberOfWords) {
        def wordFrequencyMap = getWordFrequencyMap()
        wordFrequencyMap = wordFrequencyMap.sort { -it.value } //sort by descending value
        def mostFrequentWords = [:]
        wordFrequencyMap.eachWithIndex { it, index ->
            if (index < numberOfWords) {
                mostFrequentWords.put(it.key, it.value)
            }
            else {
                return true //break from closure
            }
        }
        mostFrequentWords
    }
}
