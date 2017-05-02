package com.whc.dao.jpa.primary;

import com.whc.model.Student;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by whc on 2017/3/30.
 */
@CacheConfig(cacheNames = "students")
public interface StudentJpaPrimaryRepository extends JpaRepository<Student, Long> {

    @Cacheable(key = "#p0", condition = "#p0.length() < 3")    // 只有当第一个参数长度小于3时才会被缓存
    Student findByName(String name);

    @CachePut(key = "#p0.name")
    Student save(Student student);

    @Modifying
    @Query("delete from Student s where s.name = ?1")
    int deleteByName(String name);

}
