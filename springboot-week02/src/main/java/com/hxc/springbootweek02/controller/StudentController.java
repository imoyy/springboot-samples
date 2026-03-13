package com.hxc.springbootweek02.controller;

import com.hxc.springbootweek02.entity.Student;
import com.hxc.springbootweek02.services.StudentService;
import jakarta.annotation.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {

    @Resource
    private StudentService studentService;

    /**
     * 按ID查询
     *
     * @param id 学生ID
     * @return 学生信息
     */
    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable Long id) {
        return studentService.getStudentById(id);
    }

    /**
     * 按名查询
     *
     * @param name 学生姓名
     * @return 学生信息
     */
    @GetMapping(params = "name")
    public Student getStudentByName(@RequestParam String name) {
        return studentService.getStudentByName(name);
    }

    /**
     * 查询全部
     *
     * @return 学生列表
     */
    @GetMapping
    public Iterable<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    /**
     * 更新学生
     *
     * @param id      学生ID
     * @param student 学生数据
     * @return 更新结果
     */
    @PutMapping("/{id}")
    public Student updateStudentById(@PathVariable Long id, @RequestBody Student student) {
        student.setId(id);
        studentService.updateStudentById(id, student);
        return studentService.getStudentById(id);
    }

    /**
     * 删除学生
     *
     * @param id 学生ID
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteStudentById(@PathVariable Long id) {
        studentService.deleteStudentById(id);
    }

}
