package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class Main
{
    private static final Map<String, Integer> words = new HashMap<>(){};

    public static void main(String[] args)
    {
        String[] array = {"Начало", "Беда_одна_не_ходит", "Хэппи_Энд"};
        String text;

        for(int i = 0; i < 3; i++)
        {
            if(array[i].equals("Начало"))
            {
                text = getText("Data/" + array[i] + ".txt", Charset.forName("Windows-1251"));
                counter(text);
                continue;
            }

            text = getText("Data/" + array[i] + ".txt", StandardCharsets.UTF_8);
            counter(text);
        }

        show();
    }

    private static void show()
    {
        Map<String, Integer> nonUniqueWords = new HashMap<>();
        int size = words.size();
        int val;
        String key;

        System.out.println("\nUnique words: ");

        for(Map.Entry<String, Integer> el : words.entrySet())
        {
            val = el.getValue();
            key = el.getKey();

            if(val == 1)
            {
                System.out.println("\"" + key + "\"" + " = " + val);
                continue;
            }

            nonUniqueWords.put(key, val);
        }

        System.out.println("\nNon unique words: ");
        for(Map.Entry<String, Integer> el : nonUniqueWords.entrySet())
        {
            val = el.getValue();
            key = el.getKey();

            System.out.println("\"" + key + "\"" + " = " + val);
        }

        System.out.println("\nVarious words: " + size);
    }

    private static String getText(String path, Charset ch)
    {
        InputStream inputStream = Main.class
                .getClassLoader()
                .getResourceAsStream(path);

        if(inputStream == null)
        {
            System.out.println("Input stream is null");
            return null;
        }

        String line;
        StringBuilder result = new StringBuilder();

        try(InputStreamReader isr = new InputStreamReader(inputStream, ch);
            BufferedReader br = new BufferedReader(isr))
        {
            while ((line = br.readLine()) != null)
            {
                result.append(line);
            }
        }
        catch (IOException ex)
        {
            System.out.println(ex.getMessage());
            return null;
        }

        return result.toString();
    }

    private static void counter(String text)
    {
        String regexp = " ";
        Pattern pattern = Pattern.compile(regexp);
        String[] words_in_text = pattern.split(text);

        for (String word : words_in_text)
        {
            word = clearWord(word);

            if(word.isEmpty())
            {
                continue;
            }

            if(words.containsKey(word))
            {
                int oldValue = words.get(word);
                words.replace(word, oldValue + 1);
                continue;
            }

            words.put(word, 1);
        }
    }

    private static String clearWord(String w)
    {
        w = w.replace("\"", "");
        w = w.replace(".", "");
        w = w.replace(",", "");
        w = w.replace("?", "");
        w = w.replace("!", "");
        w = w.replace("-", "");
        w = w.replace(":", "");
        w = w.replace("\n", "");
        w = w.replace("\t", "");
        w = w.replace("\r", "");
        w = w.trim();
        return w.toLowerCase();

    }
}