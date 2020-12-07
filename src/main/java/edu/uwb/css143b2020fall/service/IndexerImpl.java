package edu.uwb.css143b2020fall.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Arrays;


@Service
public class IndexerImpl implements Indexer {
    public Map<String, List<List<Integer>>> index(List<String> docs) {
        Map<String, List<List<Integer>>> indexes = new HashMap<>();
        for (int i = 0; i < docs.size(); i++) {
            if (!docs.get(i).isEmpty()) {
                String[] words = docs.get(i).trim().split("\\s+");
                for (int j = 0; j < words.length; j++) {
                    if (!indexes.containsKey(words[j])) {
                        indexes.put(words[j], new ArrayList<List<Integer>>());
                    }
                    if (indexes.get(words[j]).size() == 0) {
                        for (int z = 0; z < docs.size(); z++) {
                            indexes.get(words[j]).add(new ArrayList<>());
                        }
                    }
                    indexes.get(words[j]).get(i).add(j);
                }
            }
        }
        return indexes;
    }
}