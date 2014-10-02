package se.svt.wordcloud.util

import org.junit.Assert
import org.junit.Test


class TextAnalyserTest {

    @Test
    public void test_getWordFrequencyMap() {

        def emptyText = new TextAnalyser(text: "")
        assert [:] ==  emptyText.getWordFrequencyMap()

        def oneWordTwoTimes = new TextAnalyser(text: "word word")
        assert ["word":2] == oneWordTwoTimes.getWordFrequencyMap()

        def twoDifferentWords = new TextAnalyser(text: "word_A word_B")
        assert 2 == twoDifferentWords.getWordFrequencyMap().size()

    }
}
