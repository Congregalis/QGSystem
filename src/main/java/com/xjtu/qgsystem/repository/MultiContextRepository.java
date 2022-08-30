package com.xjtu.qgsystem.repository;

import com.xjtu.qgsystem.entity.MultiContext;
import org.hibernate.annotations.SQLInsert;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.awt.print.Pageable;
import java.util.List;

public interface MultiContextRepository extends JpaRepository<MultiContext, Long> {
    @Query(value = "select * from multicontext limit ?1,?2 ", nativeQuery = true)
    List<MultiContext> findAllContext(Integer pageNum, Integer pageLimit);
    @Transactional
    @Modifying
    @Query(value = "update  multicontext set cText=?2 where cId=?1 ", nativeQuery = true)
    void updateContext(long parseLong, String cText);
}
