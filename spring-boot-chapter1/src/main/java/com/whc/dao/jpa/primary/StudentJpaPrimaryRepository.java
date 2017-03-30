package com.whc.dao.jpa.primary;

import com.whc.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by whc on 2017/3/30.
 */
public interface StudentJpaPrimaryRepository extends JpaRepository<Student, Long> {

    Student findByName(String name);

    @Modifying
    @Query("delete from Student s where s.name = ?1")
    @Transactional
    int deleteByName(String name);
}
