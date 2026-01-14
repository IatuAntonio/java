package com.example.PrefSchedule;

import com.example.PrefSchedule.model.*;
import com.example.PrefSchedule.repository.CourseRepository;
import com.example.PrefSchedule.repository.UserRepository;
import com.example.PrefSchedule.service.CourseService;
import com.example.PrefSchedule.service.InstructorService;
import com.example.PrefSchedule.service.PackService;
import com.example.PrefSchedule.service.StudentService;
import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@SpringBootApplication
public class HomeworkApplication {

	public static void main(String[] args) {
		SpringApplication.run(HomeworkApplication.class, args);
	}

    @Bean
    CommandLineRunner run(StudentService studentService,
                          InstructorService instructorService,
                          PackService packService,
                          CourseService courseService,
                          CourseRepository courseRepository,
                          UserRepository userRepository,
                          PasswordEncoder passwordEncoder) {
        return args ->  {

            if (courseRepository.count() > 0) {
                System.out.println("=== Database already populated, skipping seeding ===");
                return;
            }

            Faker faker = new Faker(new Locale("en"));


            Person admin = new Person();
            admin.setName("System Admin");
            admin.setEmail("admin@test.com");
            admin.setPassword(passwordEncoder.encode("adminpass"));
            admin.setRole(Role.ADMIN);
            userRepository.save(admin);
            System.out.println("Created admin user: admin@test.com / adminpass");



            List<Instructor> instructors = new ArrayList<>();
            for (int i = 1; i <= 5; i++) {
                Instructor instructor = new Instructor();
                instructor.setName(faker.name().fullName());
                instructor.setEmail(faker.internet().emailAddress());

                instructor.setPassword(passwordEncoder.encode("pass123"));
                instructor.setRole(Role.INSTRUCTOR);

                instructors.add(instructorService.saveInstructor(instructor));
            }

            List<Pack> packs = new ArrayList<>();
            for (int i = 1; i <= 3; i++) {
                Pack pack = new Pack();
                pack.setName("Optional Pack - " + i);
                pack.setYear(2);
                pack.setSemester(i);
                packs.add(packService.savePack(pack));
            }

            for (int i = 0; i < 10; i++) {
                Course course = new Course();
                course.setType(faker.bool().bool() ? "optional" : "compulsory");
                course.setCode("C" + faker.number().digits(3));
                course.setAbbr(faker.letterify("CS?"));
                course.setName(faker.book().title());
                course.setDescription(faker.lorem().sentence(10));
                course.setGroupCount(faker.number().numberBetween(1, 5));
                course.setInstructor(faker.options().nextElement(instructors));
                course.setPack(faker.options().nextElement(packs));
                courseService.saveCourse(course);
            }

            for (int i = 0; i < 5; i++) {
                Student s = new Student();
                s.setCode("S" + faker.number().digits(3));
                s.setName(faker.name().fullName());
                s.setEmail(faker.internet().emailAddress());
                s.setYear(faker.number().numberBetween(1, 3));

                s.setPassword(passwordEncoder.encode("pass123"));
                s.setRole(Role.STUDENT);

                studentService.saveStudent(s);
            }

            System.out.println("=== Database populated ===");

            System.out.println("All course:");
            courseService.getAllCourses().forEach(c -> System.out.println("- " + c.getName() + " (" + c.getType() + ")"));

            System.out.println("\nOptional courses:");
            courseService.findByType("optional").forEach(c -> System.out.println("  * " + c.getName()));

            Course first = courseService.getAllCourses().getFirst();
            courseService.updateGroupCount(first.getId(), 10);
            System.out.println("\nUpdated group count for: " + first.getName());
        };
    }

}
