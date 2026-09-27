package com.example.demo.controller;

import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/students")
public class StudentController {

    private static final Logger log = LoggerFactory.getLogger(StudentController.class);

    private final StudentRepository studentRepository;

    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping("/new")
    public String newForm(Model model) {
        model.addAttribute("student", new Student());
        return "students/form";
    }

    @PostMapping
    public String create(@ModelAttribute Student student) {
        log.info("Registering new student: fullName={}, studentNumber={}",
                student.getFullName(), student.getStudentNumber());
        studentRepository.save(student);
        return "redirect:/students?created=1";
    }

    @GetMapping
    public String list(@RequestParam(value = "q", required = false) String q, Model model) {
        log.debug("Listing students, q={}", q);
        model.addAttribute("students", studentRepository.search(q));
        model.addAttribute("q", q);
        return "students/list";
    }
    @GetMapping("/edit/{publicId}")
    public String edit(@PathVariable("publicId") String publicId, Model model) {
        log.debug("Loading student publicId={} for edit", publicId);
        Student student = studentRepository.findByPublicId(publicId);
        if (student == null) {
            log.warn("No student found with publicId={}", publicId);
            return "redirect:/students";
        }
        model.addAttribute("student", student);
        return "students/form";
    }

    @PostMapping("/edit/{publicId}")
    public String update(@PathVariable("publicId") String publicId, @ModelAttribute Student student) {
        log.info("Updating student publicId={}: fullName={}, studentNumber={}",
                publicId, student.getFullName(), student.getStudentNumber());
        studentRepository.update(publicId, student);
        return "redirect:/students?updated=1";
    }

}
