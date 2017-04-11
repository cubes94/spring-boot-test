package com.whc.dao.jpa.secondary;

import com.whc.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by whc on 2017/3/30.
 */
public interface StudentJpaSecondaryRepository extends JpaRepository<Student, Long> {

    Student findByName(String name);

    @Modifying
    @Query("delete from Student s where s.name = ?1")
    int deleteByName(String name);
}
