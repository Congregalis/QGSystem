package com.xjtu.qgsystem.repository;

import com.xjtu.qgsystem.entity.Context;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ContextRepository extends JpaRepository<Context, Long> {

    public Optional<Context> findById(Long id);

    public List<Context> findByTitle(String title);
    @Query(value = "select * from context where language = ?3 and title= ?4 and subject= ?5 limit ?1,?2 ;", nativeQuery = true)
    List<Context> findByCondition(Integer start, Integer pagesize, String getcLanguage, String getcTitle, String getcSubject);
}
