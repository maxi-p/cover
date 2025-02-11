package dev.maxpetrushin.cover_gen.controller;

import dev.maxpetrushin.cover_gen.entity.Profile;
import dev.maxpetrushin.cover_gen.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/profile")
public class ProfileController {
    private ProfileService profileService;

    @Autowired
    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    public ResponseEntity<Boolean> addProfile(@RequestBody Profile profile) {
        Boolean result = profileService.addProfile(profile);
        return ResponseEntity.ok()
                .body(result);
    }
}
