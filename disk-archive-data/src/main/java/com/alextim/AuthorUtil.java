package com.alextim;

import java.util.ArrayList;

import com.alextim.diskarchive.entity.Author;

public class AuthorUtil {
    public static double compareAuthors(ArrayList<Author> names1, ArrayList<Author> names2) {
        ArrayList<String> diffAuthorsNamesArrayList = new ArrayList<>();

        for(int firstArrayIndex = 0; firstArrayIndex < names1.size(); firstArrayIndex++ ){
            String firstAuthorLastName = names1.get(firstArrayIndex).getLastName();

            for(int secondArrayIndex = 0; secondArrayIndex < names2.size(); secondArrayIndex++){
                String secondAuthorLastName = names2.get(secondArrayIndex).getLastName();

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
