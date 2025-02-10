package dev.maxpetrushin.cover_gen.service;

public interface FileService {
    String readFile(String file_name);
    String writeFile(String directory, String file_name, String content);
    String deleteFile(String file_name);
}
