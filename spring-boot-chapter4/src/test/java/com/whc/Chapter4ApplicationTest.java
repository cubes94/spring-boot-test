package com.whc;

import com.whc.dao.mapper.primary.StudentPrimaryMapper;
import com.whc.dao.mapper.secondary.StudentSecondaryMapper;
import com.whc.model.Student;
import com.whc.service.StudentService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class Chapter4ApplicationTest {

    @Autowired
    private StudentPrimaryMapper studentPrimaryMapper;

    @Autowired
    private StudentSecondaryMapper studentSecondaryMapper;

    @Autowired
    @Qualifier("studentService")
    private StudentService studentService;

    @Before
    public void before() {

        studentPrimaryMapper.deleteAll();
        studentSecondaryMapper.deleteAll();

        studentPrimaryMapper.insert(new Student("张三", 20, 0, new Date(), new Date()));
        studentSecondaryMapper.insert(new Student("张三", 20, 0, new Date(), new Date()));
    }

    @Test
    public void testMyBatis() {
        studentPrimaryMapper.deleteAll();
        studentSecondaryMapper.deleteAll();

        studentPrimaryMapper.insert(new Student("张三", 20, 0, new Date(), new Date()));
        studentPrimaryMapper.insert(new Student("李四", 20, 0, new Date(), new Date()));
        studentPrimaryMapper.insert(new Student("王五", 20, 0, new Date(), new Date()));
        Assert.assertEquals(3, studentPrimaryMapper.selectAll().size());

        studentSecondaryMapper.insert(new Student("张三", 20, 0, new Date(), new Date()));
        studentSecondaryMapper.insert(new Student("李四", 20, 0, new Date(), new Date()));
        studentSecondaryMapper.insert(new Student("王五", 20, 0, new Date(), new Date()));
        Assert.assertEquals(3, studentSecondaryMapper.selectAll().size());
    }

    @Test
    public void testTransaction() {
        studentService.testTransaction(new Student("张三", 20, 0, new Date(), new Date()));
    }
}
