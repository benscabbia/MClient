package MClient.models;

import java.io.File;

/**
 * Created by Ben on 26/05/2016.
 */
public  class FileOperator {

    public static boolean validateFile(String filePath){
        File file = new File(filePath);
        return file.exists() && file.canRead();
    }

    public static String[] getStringAndSearchTerm(String filePath) {
        String[] details = new String[2];

        if(!filePath.isEmpty()){
            int start = filePath.indexOf("["); //find the index of searchTerm
            int end = filePath.indexOf("]"); //find the index of searchTerm

            if(start <= 0 || end <= 0 || start>=end){
                //no input string or input string mistake, return just the path
                String cleanFilePath = filePath.replace("[", ""); //make sure there are no trailing brackets
                cleanFilePath = cleanFilePath.replace("]", "");

                details[0] = cleanFilePath;
                return details;
            }

            String searchKey = filePath.substring(start, end+1); //{searchKey}
            String replaced = filePath.replace(searchKey, "");
            searchKey = searchKey.substring(1, searchKey.length()-1); //searchKey
            details[0] = replaced;
            details[1] = searchKey;
            return details;
        }
        return null;
    }

}
