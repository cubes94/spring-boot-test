package com.whc.dao.mongo;

import com.whc.model.Student;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by whc on 2017/4/6.
 */
public interface StudentMongoRepository extends MongoRepository<Student, Integer> {

    Student findByName(String name);
}
