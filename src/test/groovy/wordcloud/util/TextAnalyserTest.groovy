package wordcloud.util

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

    @Test
    public void test_getMostFrequentWords() {

        def text = new TextAnalyser(text: "a a b b b c")

        def noWords = text.getMostFrequentWords(-1)
        assert noWords == [:]

        def theMostFrequentWord = text.getMostFrequentWords(1)
        assert theMostFrequentWord == [b : 3]

        def threeMostFrequentWords = text.getMostFrequentWords(3)
        assert threeMostFrequentWords == [b : 3, a: 2, c:1]

    }
}
