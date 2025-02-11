package dev.maxpetrushin.cover_gen.dto.converter;

import dev.maxpetrushin.cover_gen.dto.ParagraphDTO;
import dev.maxpetrushin.cover_gen.entity.Paragraph;

import java.util.ArrayList;
import java.util.List;

public class ParagraphToParagraphDTO {
    private static ParagraphToParagraphDTO instance;

    private ParagraphToParagraphDTO() {
    }

    // Singleton
    public static ParagraphToParagraphDTO getInstance() {
        if (instance == null) {
            instance = new ParagraphToParagraphDTO();
        }
        return instance;
    }

    public ParagraphDTO paragraphToDTO(Paragraph paragraph) {
        ParagraphDTO paragraphDTO = new ParagraphDTO();
        paragraphDTO.setId(paragraph.getId());
        paragraphDTO.setContent(paragraph.getContent());
        paragraphDTO.setProfileId(paragraph.getProfileId());
        paragraphDTO.setPosition(paragraph.getPosition());
        return paragraphDTO;
    }

    public Paragraph dtoToParagraph(ParagraphDTO paragraphDTO) {
        Paragraph paragraph = new Paragraph();
        paragraph.setId(paragraphDTO.getId());
        paragraph.setContent(paragraphDTO.getContent());
        paragraph.setProfileId(paragraphDTO.getProfileId());
        paragraph.setPosition(paragraphDTO.getPosition());
        return paragraph;
    }

    public List<ParagraphDTO> paragraphListToDTOList(List<Paragraph> paragraphList) {
        List<ParagraphDTO> paragraphDTOList = new ArrayList<>();

        for (Paragraph paragraph : paragraphList) {
            paragraphDTOList.add(paragraphToDTO(paragraph));
        }

        return paragraphDTOList;
    }

    public List<Paragraph> dtoListToParagraphList(List<ParagraphDTO> paragraphDTOList) {
        List<Paragraph> paragraphList = new ArrayList<>();

        for (ParagraphDTO paragraphDTO : paragraphDTOList) {
            paragraphList.add(dtoToParagraph(paragraphDTO));
        }

        return paragraphList;
    }
}
