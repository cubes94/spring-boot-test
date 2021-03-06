package com.whc;

import com.whc.dao.mongo.StudentMongoRepository;
import com.whc.model.Student;
import com.whc.service.RedisCacheService;
import com.whc.service.StudentService;
import com.whc.web.HelloController;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Date;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class Chapter1ApplicationTest {

    private MockMvc mvc;

    @Autowired
    @Qualifier("studentJdbcTemplatePrimaryService")
    private StudentService studentJdbcTemplatePrimaryService;

    @Autowired
    @Qualifier("studentJdbcTemplateSecondaryService")
    private StudentService studentJdbcTemplateSecondaryService;

    @Autowired
    private RedisCacheService<String, String> redisCacheService;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private StudentMongoRepository studentMongoRepository;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(new HelloController()).build();
    }

    @Test
    public void getHello() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("Hello World")));
    }

    @Test
    public void testJDBC() {

        studentJdbcTemplatePrimaryService.deleteAll();
        studentJdbcTemplateSecondaryService.deleteAll();

        studentJdbcTemplatePrimaryService.create(new Student("张三", 20, 0, new Date(), new Date()));
        studentJdbcTemplatePrimaryService.create(new Student("李四", 20, 0, new Date(), new Date()));
        studentJdbcTemplatePrimaryService.create(new Student("王五", 20, 0, new Date(), new Date()));
        Assert.assertEquals(3, studentJdbcTemplatePrimaryService.getAll().size());

        studentJdbcTemplateSecondaryService.create(new Student("张三", 20, 0, new Date(), new Date()));
        studentJdbcTemplateSecondaryService.create(new Student("李四", 20, 0, new Date(), new Date()));
        studentJdbcTemplateSecondaryService.create(new Student("王五", 20, 0, new Date(), new Date()));
        Assert.assertEquals(3, studentJdbcTemplateSecondaryService.getAll().size());

        studentJdbcTemplateSecondaryService.deleteByName("张三");
        Assert.assertEquals(2, studentJdbcTemplateSecondaryService.deleteAll());
    }

    @Test
    public void testTransaction() {
        studentJdbcTemplatePrimaryService.testTransaction(new Student("张三", 20, 0, new Date(), new Date()));
        studentJdbcTemplateSecondaryService.testTransaction(new Student("张三", 20, null, new Date(), new Date()));
    }

    @Test
    public void testRedis() {
        redisCacheService.set("whc", "cubes_house");
        Assert.assertEquals("cubes_house", redisCacheService.get("whc"));

        Assert.assertEquals("cubes_house", redisTemplate.opsForValue().get("whc"));
    }

    @Test
    public void testMongo() {

        studentMongoRepository.deleteAll();
        studentMongoRepository.save(new Student(1, "张三", 20, 0, new Date(), new Date()));
        studentMongoRepository.save(new Student(2, "李四", 20, 0, new Date(), new Date()));
        studentMongoRepository.save(new Student(3, "王五", 20, 0, new Date(), new Date()));

        Assert.assertEquals(3, studentMongoRepository.findAll().size());

        Assert.assertEquals(2, studentMongoRepository.findByName("李四").getId().intValue());
    }

}
