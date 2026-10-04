package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.UUID;

@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Client-facing identifier (used in URLs) so the internal numeric id is never exposed. */
    @Column(name = "public_id", nullable = false, updatable = false, unique = true, length = 36)
    private String publicId = UUID.randomUUID().toString();

    @NotBlank(message = "نام و نام خانوادگی الزامی است.")
    @Size(max = 255, message = "نام و نام خانوادگی نباید بیش از ۲۵۵ نویسه باشد.")
    @Column(name = "full_name", nullable = false)
    private String fullName;

    @NotBlank(message = "شماره دانشجویی الزامی است.")
    @Pattern(regexp = "[0-9]{5,12}", message = "شماره دانشجویی باید ۵ تا ۱۲ رقم انگلیسی باشد.")
    @Column(name = "student_number", nullable = false, unique = true)
    private String studentNumber;

    @Pattern(regexp = "[0-9]{10}", message = "کد ملی باید ۱۰ رقم انگلیسی باشد.")
    @Column(name = "national_id")
    private String nationalId;

    @Size(max = 255, message = "رشته تحصیلی نباید بیش از ۲۵۵ نویسه باشد.")
    @Column(name = "major")
    private String major;

    @Email(regexp = ".+@.+\\..+", message = "ایمیل واردشده معتبر نیست.")
    @Size(max = 255, message = "ایمیل نباید بیش از ۲۵۵ نویسه باشد.")
    @Column(name = "email")
    private String email;

    @Pattern(regexp = "09[0-9]{9}", message = "شماره تماس باید مانند 09123456789 باشد.")
    @Column(name = "phone")
    private String phone;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPublicId() {
        return publicId;
    }

    public void setPublicId(String publicId) {
        this.publicId = publicId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
