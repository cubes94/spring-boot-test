package com.whc;

import com.whc.dao.jpa.primary.StudentJpaPrimaryRepository;
import com.whc.dao.jpa.secondary.StudentJpaSecondaryRepository;
import com.whc.model.Student;
import com.whc.service.StudentService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;


@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class Chapter2ApplicationTest {

    @Autowired
    private StudentJpaPrimaryRepository studentJpaPrimaryRepository;

    @Autowired
    private StudentJpaSecondaryRepository studentJpaSecondaryRepository;

    @Autowired
    @Qualifier("studentJpaPrimaryService")
    private StudentService studentJpaPrimaryService;

    @Autowired
    @Qualifier("studentJpaSecondaryService")
    private StudentService studentJpaSecondaryService;

    @Autowired
    private CacheManager cacheManager;

    @Test
    @Transactional("transactionManagerSecondary")
    public void testJPA() {
        studentJpaPrimaryRepository.deleteAll();
        studentJpaSecondaryRepository.deleteAll();

        studentJpaPrimaryRepository.save(new Student("张三", 20, 0, new Date(), new Date()));
        studentJpaPrimaryRepository.save(new Student("李四", 20, 0, new Date(), new Date()));
        studentJpaPrimaryRepository.save(new Student("王五", 20, 0, new Date(), new Date()));
        Assert.assertEquals(3, studentJpaPrimaryRepository.findAll().size());

        studentJpaSecondaryRepository.save(new Student("张三", 20, 0, new Date(), new Date()));
        studentJpaSecondaryRepository.save(new Student("李四", 20, 0, new Date(), new Date()));
        studentJpaSecondaryRepository.save(new Student("王五", 20, 0, new Date(), new Date()));
        Assert.assertEquals(3, studentJpaSecondaryRepository.findAll().size());

        studentJpaSecondaryRepository.deleteByName("张三");
        Assert.assertEquals(2, studentJpaSecondaryRepository.findAll().size());
    }

    @Test
    public void testTransaction() {
        studentJpaPrimaryService.testTransaction(new Student("张三", 20, 0, new Date(), new Date()));
        studentJpaSecondaryService.testTransaction(new Student("张三", 20, null, new Date(), new Date()));
    }

    @Test
    public void testCache() {
        studentJpaPrimaryRepository.deleteAll();
        studentJpaSecondaryRepository.deleteAll();

        studentJpaPrimaryRepository.save(new Student("张三", 20, 0, new Date(), new Date()));

        Student s1 = studentJpaPrimaryRepository.findByName("张三");
        System.out.println(s1);
        Student s2 = studentJpaPrimaryRepository.findByName("张三");
        System.out.println(s2);
        s1.setAge(30);
        studentJpaPrimaryRepository.save(s1);
        Student s3 = studentJpaPrimaryRepository.findByName("张三");
        System.out.println(s3);
    }
}
