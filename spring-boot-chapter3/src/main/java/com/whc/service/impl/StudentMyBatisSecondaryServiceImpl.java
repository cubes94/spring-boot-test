package com.whc.service.impl;

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
@Service("studentMyBatisSecondaryService")
public class StudentMyBatisSecondaryServiceImpl implements StudentService {

    @Autowired
    private StudentSecondaryMapper studentSecondaryMapper;

    @Transactional(transactionManager = "secondaryDataSourceTransactionManager", propagation = Propagation.REQUIRED)
    public void testTransaction(Student student) {
        studentSecondaryMapper.deleteByName(student.getName());
        studentSecondaryMapper.insert(student);
        studentSecondaryMapper.deleteByName(student.getName());
    }
}
