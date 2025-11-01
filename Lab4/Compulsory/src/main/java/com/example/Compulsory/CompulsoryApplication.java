package com.example.Compulsory;

import com.example.Compulsory.model.Student;
import com.example.Compulsory.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CompulsoryApplication {

	public static void main(String[] args) {
		SpringApplication.run(CompulsoryApplication.class, args);
	}

    @Bean
    CommandLineRunner run(StudentRepository studentRepository) {

        return args -> {
            Student student = new Student();
            student.setCode("s1235");
            student.setName("John Doe");
            student.setEmail("john.doe1@gmail.com");
            student.setYear(2);
            studentRepository.save(student);

            System.out.println("Student saved: " + student.getName());
        };

    }

}
