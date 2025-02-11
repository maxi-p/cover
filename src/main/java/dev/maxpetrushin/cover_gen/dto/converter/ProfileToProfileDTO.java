package dev.maxpetrushin.cover_gen.dto.converter;

import dev.maxpetrushin.cover_gen.dto.ParagraphDTO;
import dev.maxpetrushin.cover_gen.dto.ProfileDTO;
import dev.maxpetrushin.cover_gen.entity.Paragraph;
import dev.maxpetrushin.cover_gen.entity.Profile;

import java.util.ArrayList;
import java.util.List;

public class ProfileToProfileDTO {
    private static ProfileToProfileDTO instance;
    private final ParagraphToParagraphDTO paragraphToParagraphDTO = ParagraphToParagraphDTO.getInstance();

    private ProfileToProfileDTO() {
    }

    // Singleton
    public static ProfileToProfileDTO getInstance() {
        if (instance == null) {
            instance = new ProfileToProfileDTO();
        }
        return instance;
    }

    public ProfileDTO profileToDTO(Profile profile) {
        ProfileDTO profileDTO = new ProfileDTO();
        profileDTO.setId(profile.getId());
        profileDTO.setProfileName(profile.getProfileName());
        profileDTO.setDefaultName(profile.getDefaultName());
        profileDTO.setDefaultTemplateSource(profile.getDefaultTemplateSource());
        profileDTO.setDefaultGreeting(profile.getDefaultGreeting());
        profileDTO.setDefaultEmail(profile.getDefaultEmail());
        profileDTO.setDefaultPhone(profile.getDefaultPhone());
        profileDTO.setDefaultWebsite(profile.getDefaultWebsite());
        profileDTO.setDefaultGithub(profile.getDefaultGithub());
        profileDTO.setDefaultWebsite(profile.getDefaultWebsite());
        profileDTO.setDefaultSignatureUrl(profile.getDefaultSignatureUrl());
        profileDTO.setDefaultComplimentaryClose(profile.getDefaultComplimentaryClose());

        List<ParagraphDTO> paragraphDTOList = new ArrayList<>();
        profileDTO.setParagraphs(paragraphDTOList);

        for (Paragraph paragraph : profile.getParagraphs()) {
            paragraphDTOList.add(paragraphToParagraphDTO.paragraphToDTO(paragraph));
        }

        return profileDTO;
    }

    public Profile profileDTOToProfile(ProfileDTO profileDTO) {
        Profile profile = new Profile();
        profile.setId(profileDTO.getId());
        profile.setProfileName(profileDTO.getProfileName());
        profile.setDefaultName(profileDTO.getDefaultName());
        profile.setDefaultTemplateSource(profileDTO.getDefaultTemplateSource());
        profile.setDefaultGreeting(profileDTO.getDefaultGreeting());
        profile.setDefaultEmail(profileDTO.getDefaultEmail());
        profile.setDefaultPhone(profileDTO.getDefaultPhone());
        profile.setDefaultWebsite(profileDTO.getDefaultWebsite());
        profile.setDefaultGithub(profileDTO.getDefaultGithub());
        profile.setDefaultWebsite(profileDTO.getDefaultWebsite());
        profile.setDefaultSignatureUrl(profileDTO.getDefaultSignatureUrl());
        profile.setDefaultComplimentaryClose(profileDTO.getDefaultComplimentaryClose());
        List<Paragraph> paragraphList = new ArrayList<>();
        profile.setParagraphs(paragraphList);

        for (ParagraphDTO paragraphDTO : profileDTO.getParagraphs()) {
            paragraphList.add(paragraphToParagraphDTO.dtoToParagraph(paragraphDTO));
        }
        return profile;
    }

    public List<ProfileDTO> profileDTOListToProfileDTOList(List<Profile> profiles) {
        List<ProfileDTO> profileDTOList = new ArrayList<>();
        for (Profile profile : profiles) {
            profileDTOList.add(profileToDTO(profile));
        }
        return profileDTOList;
    }

    public List<Profile> profileDTOListToProfileList(List<ProfileDTO> profileDTOList) {
        List<Profile> profileList = new ArrayList<>();
        for (ProfileDTO profileDTO : profileDTOList) {
            profileList.add(profileDTOToProfile(profileDTO));
        }
        return profileList;
    }

}
