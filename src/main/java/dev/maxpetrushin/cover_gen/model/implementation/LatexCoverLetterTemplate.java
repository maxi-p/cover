package dev.maxpetrushin.cover_gen.model.implementation;

import dev.maxpetrushin.cover_gen.model.CoverLetterTemplate;
import dev.maxpetrushin.cover_gen.service.FileService;
import org.springframework.stereotype.Service;

import java.io.*;

@Service
public class LatexCoverLetterTemplate extends CoverLetterTemplate {
    private static final String LATEX_DIR = "src/main/resources/latex";
    private final FileService fileService;

    // Dependency injection
    public LatexCoverLetterTemplate(FileService fileService) {
        this.fileService = fileService;
    }

    @Override
    public void createTemplate(String type) {
        if (type.equals("default")) {
            this.template = this.fileService.readFile("latex/default.tex");
        }
    }

    @Override
    public byte[] generateCoverLetter(String template) {
        String filePath = fileService.writeFile(LATEX_DIR, "file.tex", template);
        File texFile = new File(filePath);
        if (!texFile.exists()) {
            System.out.println("TeX file not found: " + filePath);
            return null;
        }

        File workingDirectory = texFile.getParentFile();

        // Preparing pdflatex CLI
        ProcessBuilder processBuilder = new ProcessBuilder("pdflatex", texFile.getName());
        processBuilder.directory(workingDirectory);

        // Executing pdflatex CLI
        int exitCode = 0;
        try {
            Process process = processBuilder.start();
            exitCode = process.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        if (exitCode != 0) {
            System.out.println("Error occurred while generating the PDF. Exit code: " + exitCode);
            return null;
        }

        File pdfFile = new File(workingDirectory, texFile.getName().replace(".tex", ".pdf"));
        if (!pdfFile.exists()) {
            System.out.println("PDF not generated successfully. Check your Latex template");
            return null;
        }

        // Read the PDF file into a byte array
        try (FileInputStream fis = new FileInputStream(pdfFile);
             ByteArrayOutputStream baos = new ByteArrayOutputStream()) {

            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                baos.write(buffer, 0, bytesRead);
            }

            return baos.toByteArray();  // Return the PDF content as a byte array
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
