package com.simba.elasticsearch.controller;

import com.simba.elasticsearch.domain.ProfileDocument;
import com.simba.elasticsearch.service.ProfileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author <a href="mailto:ElHadjiOmar.DIONE@orange-sonatel.com">podisto</a>
 * @since 2019-09-16
 */
@RestController
@RequestMapping(value = "/api/v1/profiles")
@Slf4j
public class ProfileController {
    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @PostMapping
    public ResponseEntity<String> createProfile(@RequestBody ProfileDocument profileDocument) {
        log.info("--- creating profile with request = {} --- ", profileDocument);
        String response = profileService.createProfile(profileDocument);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfileDocument> getDocument(@PathVariable String id) {
        ProfileDocument document = profileService.findById(id);
        return ResponseEntity.ok(document);
    }
}
