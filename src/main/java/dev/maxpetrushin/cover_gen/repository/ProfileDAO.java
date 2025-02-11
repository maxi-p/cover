package dev.maxpetrushin.cover_gen.repository;

import dev.maxpetrushin.cover_gen.dto.ProfileDTO;

import java.util.List;

public interface ProfileDAO {
    boolean saveProfile(ProfileDTO profile);

    List<ProfileDTO> getAllProfiles();

    ProfileDTO getProfileById(int id);
}
