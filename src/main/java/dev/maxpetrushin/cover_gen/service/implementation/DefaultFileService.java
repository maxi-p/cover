package dev.maxpetrushin.cover_gen.service.implementation;

import dev.maxpetrushin.cover_gen.service.FileService;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.charset.StandardCharsets;

@Service
public class DefaultFileService implements FileService {
    private static long id = 1;

    public String readFile(String file_name) {
        Resource resource = new ClassPathResource(file_name);
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8))) {
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line).append(System.lineSeparator());
            }
            return stringBuilder.toString();
        } catch (IOException e) {
            System.out.println("Failed to read file: " + file_name + e.toString());
            return "";
        }
    }

    @Override
    public String writeFile(String directory, String file_name, String content) {
        File texDir = new File(directory);
        if (!texDir.exists()) {
            boolean dirsCreated = texDir.mkdirs(); // Create the directory if it doesn't exist
            if (!dirsCreated) {
                System.out.println("Failed to create directory: \" + LATEX_DIR");
                return null;
            }
        }

        file_name = id + file_name;
        File texFile = new File(texDir, file_name);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(texFile))) {
            writer.write(content);
        }
        catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        id++;

        return texFile.getAbsolutePath();
    }

    @Override
    public String deleteFile(String file_name) {
        return "";
    }

}
