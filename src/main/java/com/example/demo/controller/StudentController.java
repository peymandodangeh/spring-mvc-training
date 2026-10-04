package com.example.demo.controller;

import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/students")
public class StudentController {

    private static final Logger log = LoggerFactory.getLogger(StudentController.class);

    private static final String DUPLICATE_STUDENT_NUMBER = "این شماره دانشجویی قبلاً ثبت شده است.";

    private final StudentRepository studentRepository;

    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        // trim input and turn blank strings into null, so optional fields don't fail their patterns
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
        // never let a request overwrite the identifiers
        binder.setDisallowedFields("id", "publicId");
    }

    @GetMapping("/new")
    public String newForm(Model model) {
        model.addAttribute("student", new Student());
        return "students/form";
    }

    @PostMapping
    public String create(@Valid @ModelAttribute("student") Student student, BindingResult result) {
        if (!result.hasFieldErrors("studentNumber")
                && studentRepository.existsByStudentNumber(student.getStudentNumber(), null)) {
            result.rejectValue("studentNumber", "duplicate", DUPLICATE_STUDENT_NUMBER);
        }
        if (result.hasErrors()) {
            log.debug("Create student rejected: {} validation error(s)", result.getErrorCount());
            return "students/form";
        }
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
        markEditMode(model, publicId);
        return "students/form";
    }

    @PostMapping("/edit/{publicId}")
    public String update(@PathVariable("publicId") String publicId,
                         @Valid @ModelAttribute("student") Student student,
                         BindingResult result, Model model) {
        if (!result.hasFieldErrors("studentNumber")
                && studentRepository.existsByStudentNumber(student.getStudentNumber(), publicId)) {
            result.rejectValue("studentNumber", "duplicate", DUPLICATE_STUDENT_NUMBER);
        }
        if (result.hasErrors()) {
            log.debug("Update student publicId={} rejected: {} validation error(s)", publicId, result.getErrorCount());
            markEditMode(model, publicId);
            return "students/form";
        }
        log.info("Updating student publicId={}: fullName={}, studentNumber={}",
                publicId, student.getFullName(), student.getStudentNumber());
        if (!studentRepository.update(publicId, student)) {
            return "redirect:/students";
        }
        return "redirect:/students?updated=1";
    }

    @PostMapping("/delete/{publicId}")
    public String delete(@PathVariable("publicId") String publicId) {
        log.info("Deleting student publicId={}", publicId);
        boolean deleted = studentRepository.deleteByPublicId(publicId);
        return deleted ? "redirect:/students?deleted=1" : "redirect:/students";
    }

    /** The form decides between create/edit from these attributes, not from the (possibly re-bound) student. */
    private void markEditMode(Model model, String publicId) {
        model.addAttribute("editMode", true);
        model.addAttribute("studentPublicId", publicId);
    }
}
