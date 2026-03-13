package com.hxc.springbootweek02.services;

import com.hxc.springbootweek02.entity.Student;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class StudentService {
    private static final Map<Long, Student> STUDENT_DATA = new ConcurrentHashMap<>();

    static {
        Student student1 = Student.builder().id(1L).name("张三").build();
        Student student2 = Student.builder().id(2L).name("李四").build();
        STUDENT_DATA.put(student1.getId(), student1);
        STUDENT_DATA.put(student2.getId(), student2);
    }

    public Student getStudentById(Long id) {
        return STUDENT_DATA.get(id);
    }

    public Student getStudentByName(String name) {
        return STUDENT_DATA.values().stream()
                .filter(student -> student.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    public Iterable<Student> getAllStudents() {
        return STUDENT_DATA.values();
    }

    public void updateStudentById(Long id, Student student) {
        STUDENT_DATA.put(id, student);
    }

    public void deleteStudentById(Long id) {
        STUDENT_DATA.remove(id);
    }

}
