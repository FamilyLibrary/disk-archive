package com.alextim;

import java.util.ArrayList;
import java.util.List;

import com.alextim.diskarchive.entity.Author;

public class AuthorUtil {
    public static double compareAuthors(List<Author> firstArray, List<Author> secondArray) {
        List<String> diffAuthorsNamesArrayList = new ArrayList<>();

        for(Author author1 : firstArray){
            String firstAuthorLastName = author1.getLastName();

            for(Author author2 : secondArray){
                String secondAuthorLastName = author2.getLastName();

                boolean isEqual = firstAuthorLastName.equals(secondAuthorLastName);
                if (!isEqual){
                    diffAuthorsNamesArrayList.add(firstAuthorLastName);
                }
            }
        }
        if(firstArray.size() == 0){
            return 0;
        }
        return diffAuthorsNamesArrayList.size() * 100 / firstArray.size();
    }
}
