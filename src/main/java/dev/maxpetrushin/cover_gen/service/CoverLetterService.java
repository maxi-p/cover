package dev.maxpetrushin.cover_gen.service;

import dev.maxpetrushin.cover_gen.model.CoverLetterRequest;

public interface CoverLetterService {
    String generateCoverLetterSource(CoverLetterRequest request);
    byte[] generateCoverLetter(CoverLetterRequest request);
}
