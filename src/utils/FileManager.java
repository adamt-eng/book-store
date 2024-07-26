package utils;

import java.io.*;
import java.util.*;

public class FileManager
{
    public static boolean fileExists(String filePath)
    {
        return new File(filePath).exists();
    }

    public static List<String> readFile(String filePath)
    {
        List<String> lines = new ArrayList<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(filePath)))
        {
            String line;
            
            while ((line = br.readLine()) != null) lines.add(line);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return lines;
    }

    public static void writeFile(String filePath, List<String> lines)
    {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath)))
        {
            for (String line : lines)
            {
                bw.write(line);
                bw.newLine();
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}