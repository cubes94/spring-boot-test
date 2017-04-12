package com.whc.service.impl;

import com.whc.dao.jpa.secondary.StudentJpaSecondaryRepository;
import com.whc.model.Student;
import com.whc.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by whc on 2017/4/11.
 */
@Service("studentJpaSecondaryService")
public class StudentJpaSecondaryServiceImpl implements StudentService {

    @Autowired
    private StudentJpaSecondaryRepository studentJpaSecondaryRepository;

    @Transactional(transactionManager = "transactionManagerSecondary", propagation = Propagation.REQUIRED)
    public void testTransaction(Student student) {
        studentJpaSecondaryRepository.deleteByName(student.getName());
        studentJpaSecondaryRepository.save(student);
        studentJpaSecondaryRepository.deleteByName(student.getName());
    }
}
