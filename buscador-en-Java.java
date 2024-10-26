//BUSCADOR DE ZIPS RAR Y 7Z EN LA UNIDAD. EN JAVA
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileSearch {

    public static List<File> searchFiles(String directoryPath, String[] extensions) {
        List<File> resultList = new ArrayList<>();
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            System.out.println("Directory does not exist.");
            return resultList;
        }
        searchFilesInDirectory(directory, extensions, resultList);
        return resultList;
    }

    private static void searchFilesInDirectory(File directory, String[] extensions, List<File> resultList) {
        File[] files = directory.listFiles();
        if (files == null) {
            return;
        }
        for (File file : files) {
            if (file.isDirectory()) {
                searchFilesInDirectory(file, extensions, resultList);
            } else {
                for (String extension : extensions) {
                    if (file.getName().endsWith("." + extension)) {
                        resultList.add(file);
                        break;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        String directoryPath = "C:/folderPath";
        String[] extensions = {"zip", "rar", "7z"};
        List<File> searchResult = searchFiles(directoryPath, extensions);

        if (searchResult.isEmpty()) {
            System.out.println("No files found with the specified extensions.");
        } else {
            System.out.println("Files found: ");
            for (File file : searchResult) {
                System.out.println(file.getAbsolutePath());
            }
        }
    }
