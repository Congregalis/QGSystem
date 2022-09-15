package com.xjtu.qgsystem.repository;

import com.xjtu.qgsystem.entity.MultiQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MultiQuestionRepository  extends JpaRepository<MultiQuestion, Long> {
    @Query(value = "select * from multiquestion where cId=?1 ", nativeQuery = true)
    List<MultiQuestion> findByCid(Long cid);
    @Query(value = "select * from multiquestion where qId=?1 ", nativeQuery = true)
    MultiQuestion findByQid(Long qid);
}
