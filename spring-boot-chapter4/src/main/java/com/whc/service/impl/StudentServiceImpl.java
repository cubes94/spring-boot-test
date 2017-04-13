package com.whc.service.impl;

import com.whc.dao.mapper.primary.StudentPrimaryMapper;
import com.whc.dao.mapper.secondary.StudentSecondaryMapper;
import com.whc.model.Student;
import com.whc.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by whc on 2017/4/11.
 */
@Service("studentService")
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentPrimaryMapper studentPrimaryMapper;

    @Autowired
    private StudentSecondaryMapper studentSecondaryMapper;

    @Transactional(propagation = Propagation.REQUIRED)
    public void testTransaction(Student student) {
        studentPrimaryMapper.deleteByName(student.getName());
        studentPrimaryMapper.insert(student);
        studentPrimaryMapper.deleteByName(student.getName());

        student.setGender(null);
        studentSecondaryMapper.deleteByName(student.getName());
        studentSecondaryMapper.insert(student);
        studentSecondaryMapper.deleteByName(student.getName());
    }
}
