package dev.maxpetrushin.cover_gen.service.implementation;

import dev.maxpetrushin.cover_gen.dto.ProfileDTO;
import dev.maxpetrushin.cover_gen.dto.converter.ProfileToProfileDTO;
import dev.maxpetrushin.cover_gen.entity.Profile;
import dev.maxpetrushin.cover_gen.repository.ProfileDAO;
import dev.maxpetrushin.cover_gen.repository.implementation.PostgresJDBCProfileDAO;
import dev.maxpetrushin.cover_gen.service.ProfileService;
import org.springframework.stereotype.Service;


@Service
public class PostgresJDBCProfileService implements ProfileService {
    private static ProfileDAO profileDAO;
    private static ProfileToProfileDTO profileToProfileDTO;

    public PostgresJDBCProfileService() {
        profileDAO = PostgresJDBCProfileDAO.getInstance();
        profileToProfileDTO = ProfileToProfileDTO.getInstance();
    }

    @Override
    public boolean addProfile(Profile profile) {
        ProfileDTO profileDTO = profileToProfileDTO.profileToDTO(profile);
        return profileDAO.saveProfile(profileDTO);
    }
}
