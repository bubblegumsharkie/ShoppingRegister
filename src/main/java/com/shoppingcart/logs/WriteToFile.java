package com.shoppingcart.logs;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class WriteToFile {

    public static void write(String text)
    {
        Path FILE_PATH = Paths.get("D:/", "botLog.txt");
        text = text +"\n";

        //Writing to the file temp.txt
        try (BufferedWriter writer = Files.newBufferedWriter(FILE_PATH, StandardCharsets.UTF_8, StandardOpenOption.APPEND)) {
            writer.write(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeFull(String text)
    {
        Path FILE_PATH = Paths.get("D:/", "botLogFull.txt");
        text = text +"\n";

        //Writing to the file temp.txt
        try (BufferedWriter writer = Files.newBufferedWriter(FILE_PATH, StandardCharsets.UTF_8, StandardOpenOption.APPEND)) {
            writer.write(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
