package com.whc.service.impl;

import com.whc.dao.mapper.primary.StudentPrimaryMapper;
import com.whc.model.Student;
import com.whc.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by whc on 2017/4/11.
 */
@Service("studentMyBatisPrimaryService")
public class StudentMyBatisPrimaryServiceImpl implements StudentService {

    @Autowired
    private StudentPrimaryMapper studentPrimaryMapper;

    @Transactional(transactionManager = "primaryDataSourceTransactionManager", propagation = Propagation.REQUIRED)
    public void testTransaction(Student student) {
        studentPrimaryMapper.deleteByName(student.getName());
        studentPrimaryMapper.insert(student);
        studentPrimaryMapper.deleteByName(student.getName());
    }
}
