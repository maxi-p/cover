package dev.maxpetrushin.cover_gen.service;

import dev.maxpetrushin.cover_gen.entity.Profile;

import java.util.List;

public interface ProfileService {
    boolean addProfile(Profile profile);
    Profile getProfile(int id);
    List<Profile> getAllProfiles();
}
