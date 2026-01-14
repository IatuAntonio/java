package com.example.Compulsory.dto;

import com.example.Compulsory.model.Preference;

public class PreferenceMapper {

    public static PreferenceResponseDTO toDTO(Preference p) {
        PreferenceResponseDTO dto = new PreferenceResponseDTO();
        dto.setId(p.getId());
        dto.setStudentId(p.getStudent().getId());
        dto.setCourseId(p.getCourse().getId());
        dto.setPackId(p.getPack().getId());
        dto.setRank(p.getRank());
        dto.setTieGroup(p.getTieGroup());
        dto.setVersion(p.getVersion());
        return dto;
    }

}
