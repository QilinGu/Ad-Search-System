package io.bittiger.ads.api;

import org.apache.lucene.analysis.CharArraySet;
import org.apache.lucene.analysis.StopFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.en.EnglishAnalyzer;
import org.apache.lucene.analysis.standard.StandardTokenizer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.util.AttributeFactory;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by ChenCheng on 10/11/2016.
 */
public class QueryProcessorImpl implements QueryProcessor {
    @Override
    public List<String> queryProcessor(String queryStr) {
        List<String> tokens = new ArrayList<>();
        AttributeFactory factory = AttributeFactory.DEFAULT_ATTRIBUTE_FACTORY;
        Tokenizer tokenizer = new StandardTokenizer(factory);
        tokenizer.setReader(new StringReader(queryStr));
        CharArraySet stop_words = EnglishAnalyzer.getDefaultStopSet();
        TokenStream ts = new StopFilter(tokenizer, stop_words);
        return NeededTokenMining(tokens, tokenizer, ts);
    }

    private List<String> NeededTokenMining(List<String> tokens, Tokenizer tokenizer, TokenStream ts) {

        CharTermAttribute cta = tokenizer.addAttribute(CharTermAttribute.class);

        try {
            ts.reset();
            TokenStreamHandle(ts, cta, tokens, tokenizer);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return deduplicate(tokens);
    }


    private void TokenStreamHandle(TokenStream ts, CharTermAttribute cta, List<String> tokens, Tokenizer tokenizer) {
        try {
            while(ts.incrementToken()){

                String term = cta.toString();

                tokens.add(term);
            }

            CloseEveryThing(tokenizer, ts);

        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    private void CloseEveryThing(Tokenizer tokenizer, TokenStream ts) {
        try {
            ts.end();

            ts.close();

            tokenizer.close();
        } catch (IOException e) {

            e.printStackTrace();
        }

    }

    private List<String> deduplicate(List<String> tokens) {
        Set<String> hs = new HashSet<>();

        List<String> result = new ArrayList<>();

        for (String str : tokens){
            if (hs.add(str)){
                result.add(str);
            }
        }

        return  result;
    }


}
