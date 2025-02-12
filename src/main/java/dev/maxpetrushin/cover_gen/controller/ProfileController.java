package dev.maxpetrushin.cover_gen.controller;

import dev.maxpetrushin.cover_gen.entity.Profile;
import dev.maxpetrushin.cover_gen.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profile")
public class ProfileController {
    private final ProfileService profileService;

    @Autowired
    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @PostMapping
    public ResponseEntity<Boolean> addProfile(@RequestBody Profile profile) {
        boolean result = profileService.addProfile(profile);
        return ResponseEntity.ok()
                .body(result);
    }

    @GetMapping
    public ResponseEntity<List<Profile>> getAllProfiles() {
        List<Profile> profiles = profileService.getAllProfiles();
        return ResponseEntity.ok()
                .body(profiles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Profile> getProfileById(@PathVariable("id") int id) {
        Profile profile = profileService.getProfile(id);
        return ResponseEntity.ok()
                .body(profile);
    }
}
