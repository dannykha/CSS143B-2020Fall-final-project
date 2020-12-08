// Used https://www.baeldung.com/java-check-if-list-sorted algorithm in the Recursive approach
// to determine if a list is sorted in the way I want. Had to modify the algorithm to fit
// the index incrementing the way I wanted it though. But that is what I used for the isSorted portion.

package edu.uwb.css143b2020fall.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        String[] words = keyPhrase.trim().split("\\s+");

        if (!index.containsKey(words[0])) {
            return result;
        }

        if (words.length == 1) {
            for (int i = 0; i < var.size(); i++) {
                if (var.get(i).size() != 0) {
                    result.add(i);
                }
            }
        }

        if (words.length > 1) {
            for (int j = 0; j < index.get(words[0]).size(); j++) {
                List<List<Integer>> wordPos = new ArrayList<>(words.length);
                for (String word : words) {
                    wordPos.add(index.get(word).get(j));
                }
                if (inOrder(wordPos)) {
                    result.add(j);
                }
            }
        }
        return result;
    }

    private boolean inOrder(List<List<Integer>> wordPos) {
        for (int i = 0; i < wordPos.get(0).size(); i++) {
            return isSorted(wordPos, 1, wordPos.get(0).get(i));
        }
        return false;
    }

    private boolean isSorted(List<List<Integer>> wordPos, int wordIndex, int prevPos) {

        if (wordPos.size() <= wordIndex) {
            return true;
        }

        else {
            for (Integer place : wordPos.get(wordIndex)) { // if the word isn't next to each other it will break to false.
                if (place != prevPos + 1) {
                    continue;
                }
                return isSorted(wordPos, wordIndex + 1, prevPos + 1);
            }
        }
        return false;
    }
}





