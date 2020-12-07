package edu.uwb.css143b2020fall.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
DO NOT CHANGE
 */

@Service
public class SearcherImpl implements Searcher {
    public List<Integer> search(String keyPhrase, Map<String, List<List<Integer>>> index) {
        List<Integer> result = new ArrayList<>();
        List<List<Integer>> var = index.get(keyPhrase);
        String[] words = keyPhrase.split("\\s+");
            if (var != null) {
                for (int i = 0; i < var.size(); i++) {
                    if (var.get(i).size() > 0) {
                        result.add(i);
                    }
                }
            }
            int num = index.get(words[0]).size();
            for (int i = 0; i < num; i++) {
                List<List<Integer>> wordList = new ArrayList<>(words.length);
                for (String word: words) {
                    wordList.add(index.get(word).get(num));
                }
            }
        return result;
    }
}