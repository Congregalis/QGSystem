package com.xjtu.qgsystem.repository;

import com.xjtu.qgsystem.entity.Context;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ContextRepository extends JpaRepository<Context, Long> {

    public Optional<Context> findById(Long id);

    public List<Context> findByTitle(String title);

    @Query(value = "select count(*) from context where language = ?1 and title= ?2 and subject= ?3 ;", nativeQuery = true)
    Long countContext(String s, String getcTitle, String getcSubject);

    @Query(value = "select * from context where if(?1 !='',language = ?1,1=1)  and if(?2 !='',title = ?2,1=1) and if(?3 !='',subject = ?3,1=1) and if(?4 !='',origin = ?4,1=1) order by id ;", nativeQuery = true)
    List<Context> findByCondition(String language, String title, String subject, String source);
}
