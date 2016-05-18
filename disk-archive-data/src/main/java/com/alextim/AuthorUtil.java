package com.alextim;

import java.util.ArrayList;
import java.util.List;

import com.alextim.diskarchive.entity.Author;

public class AuthorUtil {
    public static double compareAuthors(List<Author> names1, List<Author> names2) {
        List<String> diffAuthorsNamesArrayList = new ArrayList<>();

        for(Author author1 : names1){
            String firstAuthorLastName = author1.getLastName();

            for(Author author2 : names2){
                String secondAuthorLastName = author2.getLastName();

                boolean isEqual = firstAuthorLastName.equals(secondAuthorLastName);
                if (!isEqual){
                    diffAuthorsNamesArrayList.add(firstAuthorLastName);
                }
            }
        }
        if(names1.size() == 0){
            return 0;
        }
        double result =  diffAuthorsNamesArrayList.size() * 100 / names1.size();
        return result;
    }
}
