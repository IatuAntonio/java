package com.example.Homework.controller;

import com.example.Homework.dto.PreferenceRequestDTO;
import com.example.Homework.dto.PreferenceResponseDTO;
import com.example.Homework.repository.PreferenceRepository;
import com.example.Homework.service.PreferenceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/api/preferences",
        produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
public class PreferenceController {

    private final PreferenceService preferenceService;

    public PreferenceController(PreferenceService preferenceService) {
        this.preferenceService = preferenceService;
    }

    @Operation(summary = "Create or update student preference",
            description = "Creates or updates a student's preference for a course within a specific pack.")
    @ApiResponse(responseCode = "201", description = "Created")
    @PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<PreferenceResponseDTO> create(@Valid @RequestBody PreferenceRequestDTO dto) {
        PreferenceResponseDTO saved = preferenceService.create(dto);

        String etag = "\"" + saved.getVersion() + "\"";
        return ResponseEntity
                .created(URI.create("/api/preferences/" + saved.getId()))
                .eTag(etag)
                .body(saved);
    }

    @Operation(summary = "List preferences by student pack")
    @ApiResponse(responseCode = "304", description = "Preferences not modified (ETag matched)")
    @GetMapping
    public ResponseEntity<List<PreferenceResponseDTO>> list(
            @RequestParam Long studentId,
            @RequestParam Long packId,
            @RequestHeader(value = "If-None-Match", required = false) String ifNoneMatch) {

        List<PreferenceResponseDTO> list = preferenceService.listForStudentPack(studentId, packId);

        long ver = list.stream().mapToLong(PreferenceResponseDTO::getVersion).max().orElse(0L);
        String etag = "\"" + ver + "\"";

        if (ifNoneMatch != null && ifNoneMatch.equals(etag)) {
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).eTag(etag).build();
        }
        return ResponseEntity.ok().eTag(etag).body(list);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        preferenceService.delete(id);
    }

}
