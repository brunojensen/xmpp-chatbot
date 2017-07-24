package br.com.bea.chatbot;

import java.text.Normalizer;
import java.util.Arrays;
import java.util.List;

import br.com.bea.chatbot.exception.BadWordException;

public class BadWords {

    private final static List<String> words = Arrays.asList(new String[] { "foda-se ", "fudido", "doido", "desprezivel",
            "nojento", "doido", "maluco", "doentil", "otario", "babaca", "merda", "feio", "pentelho", "tarado sexual",
            "merda", "cuzao", "puta", "vagabunda", "imbecil", "idiota", "cretino", "veado", "viado", "zona",
            "pe no saco", "Puxa saco", "barrigudo", "burro", "bicha", "bixa", "louca", "filho da mae", "vaca",
            "desgracado", "bosta", "canalha", "patife", "imbecil", "estupido", "idiota", "pau", "gay", "puto" });

    public static void check(final String phrase) throws BadWordException {
        if (null != phrase) {
            for (String word : phrase.replaceAll("\\?|!|,|\\.", "").split(" ")) {
                if (words.contains(Normalizer.normalize(word.trim().toLowerCase(), Normalizer.Form.NFD).replaceAll(
                        "[^\\p{ASCII}]", ""))) {
                    throw new BadWordException("User at whitelist: " + word);
                }
            }
        }
    }

}
