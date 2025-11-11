package com.example.Homework.service;

import com.example.Homework.dto.PreferenceMapper;
import com.example.Homework.dto.PreferenceResponseDTO;
import com.example.Homework.dto.PreferenceRequestDTO;
import com.example.Homework.exception.InvalidPreferenceException;
import com.example.Homework.model.Course;
import com.example.Homework.model.Pack;
import com.example.Homework.model.Preference;
import com.example.Homework.model.Student;
import com.example.Homework.repository.CourseRepository;
import com.example.Homework.repository.PackRepository;
import com.example.Homework.repository.PreferenceRepository;
import com.example.Homework.repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.Homework.dto.PreferenceMapper.toDTO;


@Service
public class PreferenceService {

    private final PreferenceRepository preferenceRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final PackRepository packRepository;

    public PreferenceService(PreferenceRepository preferenceRepository,
                             StudentRepository studentRepository,
                             CourseRepository courseRepository,
                             PackRepository packRepository) {
        this.preferenceRepository = preferenceRepository;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        this.packRepository = packRepository;
    }

    @Transactional
    public PreferenceResponseDTO create(PreferenceRequestDTO dto) {
        Student s = studentRepository.findById(dto.getStudentId())
                .orElseThrow(() -> new InvalidPreferenceException("Student not found"));

        Course c = courseRepository.findById(dto.getCourseId())
                .orElseThrow(() -> new InvalidPreferenceException("Course not found"));

        Pack p = packRepository.findById(dto.getPackId())
                .orElseThrow(() -> new InvalidPreferenceException("Pack not found"));

        if (c.getPack() == null || !c.getPack().getId().equals(p.getId())) {
            throw new InvalidPreferenceException("Course does not belong to the specified pack");
        }

        if (s.getYear() != p.getYear()) {
            throw new InvalidPreferenceException("Student year does not match pack year");
        }

        Preference pref = preferenceRepository.findByStudent_IdAndCourse_Id(s.getId(), c.getId())
                .orElse(new Preference());

        pref.setStudent(s);
        pref.setCourse(c);
        pref.setPack(p);
        pref.setRank(dto.getRank());
        pref.setTieGroup(dto.getTieGroup());
        Preference savedPref = preferenceRepository.save(pref);
        return toDTO(savedPref);
    }

    public List<PreferenceResponseDTO> listForStudentPack(Long studentId, Long packId) {
        return preferenceRepository.findByStudent_IdAndPack_IdOrderByRankAsc(studentId, packId)
                .stream()
                .map(PreferenceMapper::toDTO)
                .toList();
    }

    @Transactional
    public void delete(Long id) {
        preferenceRepository.deleteById(id);
    }

}
