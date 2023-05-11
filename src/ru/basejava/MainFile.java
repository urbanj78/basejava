package ru.basejava;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class MainFile {
    public static void main(String[] args) {
        String filePath = ".\\.gitignore";

        File file = new File(filePath);
        try {
            System.out.println(file.getCanonicalPath());
        } catch (IOException e) {
            throw new RuntimeException("Error", e);
        }

        filesInDir("C:\\Users\\urbanj\\IdeaProjects\\basejava\\src");

        try (FileInputStream fis = new FileInputStream(filePath)) {
            System.out.println(fis.read());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void filesInDir(String path) {
        File dir = new File(path);
        File[] files = dir.listFiles();
        assert files != null;
        for (File file : files) {
            if (file.isDirectory()) {
                System.out.println();
            }
            System.out.println(file.getName());
            if (file.isDirectory()) {
                System.out.println();
                try {
                    filesInDir(file.getCanonicalPath());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}