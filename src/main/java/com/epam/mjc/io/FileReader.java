package com.epam.mjc.io;

import java.io.*;
import java.util.HashMap;
import java.util.Map;


public class FileReader {

    public Profile getDataFromFile(File file) {
        HashMap<String, String> map = new HashMap<>();

        try (FileInputStream fis = new FileInputStream(file);
             BufferedInputStream bis = new BufferedInputStream(fis)) {

            StringBuilder currentLine = new StringBuilder();
            int c;
            while((c = bis.read()) != -1) {
                if(c == '\n' || c == '\r') {
                    String line = currentLine.toString().trim();
                    String[] parts = line.split(": ");
                    if(parts.length == 2) {
                        map.put(parts[0],parts[1]);
                    }
                    currentLine.setLength(0);
                } else {
                    currentLine.append((char) c);
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        }

        String name = map.get("Name");
        Integer age = Integer.parseInt(map.get("Age"));
        String email = map.get("Email");
        Long phone = Long.parseLong(map.get("Phone"));

        return new Profile(name, age,email,phone);





    }
}
