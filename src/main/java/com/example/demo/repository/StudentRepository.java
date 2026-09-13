package com.example.demo.repository;

import com.example.demo.model.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class StudentRepository {

    private static final Logger log = LoggerFactory.getLogger(StudentRepository.class);

    private final SessionFactory sessionFactory;

    public StudentRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session currentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Transactional
    public void save(Student student) {
        currentSession().persist(student);
        log.info("Saved student id={} studentNumber={}", student.getId(), student.getStudentNumber());
    }

    @Transactional(readOnly = true)
    public List<Student> search(String search) {
        log.debug("Searching students with query='{}'", search);
        List<Student> results;
        if (search == null || search.isBlank()) {
            Query<Student> query = currentSession().createQuery(
                    "from Student order by id desc", Student.class);
            results = query.getResultList();
        } else {
            String like = "%" + search + "%";
            Query<Student> query = currentSession().createQuery(
                    "from Student where fullName like :like or studentNumber like :like " +
                            "or nationalId like :like or major like :like order by id desc",
                    Student.class);
            query.setParameter("like", like);
            results = query.getResultList();
        }
        log.debug("Found {} student(s) for query='{}'", results.size(), search);
        return results;
    }
}
