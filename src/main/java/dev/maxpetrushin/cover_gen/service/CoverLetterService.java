package dev.maxpetrushin.cover_gen.service;

import dev.maxpetrushin.cover_gen.dto.CoverLetterRequest;

public interface CoverLetterService {
    String generateCoverLetterSource(CoverLetterRequest request);
    byte[] generateCoverLetter(CoverLetterRequest request);
}
