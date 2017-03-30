package com.whc.service;

import com.whc.model.Student;

import java.util.List;

/**
 * Created by whc on 2017/3/29.
 */
public interface StudentService {

    int create(Student s);

    int delete(int id);

    int deleteByName(String name);

    List<Student> getAll();

    int deleteAll();
}
