package com.whc.service.impl;

import com.whc.dao.jpa.primary.StudentJpaPrimaryRepository;
import com.whc.model.Student;
import com.whc.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by whc on 2017/4/11.
 */
@Service("studentJpaPrimaryService")
public class StudentJpaPrimaryServiceImpl implements StudentService {

    @Autowired
    private StudentJpaPrimaryRepository studentJpaPrimaryRepository;

    @Transactional(transactionManager = "transactionManagerPrimary", propagation = Propagation.REQUIRED)
    public void testTransaction(Student student) {
        studentJpaPrimaryRepository.deleteByName(student.getName());
        studentJpaPrimaryRepository.save(student);
        studentJpaPrimaryRepository.deleteByName(student.getName());
    }
}
