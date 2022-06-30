package com.xjtu.qgsystem.repository;

import com.xjtu.qgsystem.entity.Context;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ContextRepository extends JpaRepository<Context, Long> {

    public Optional<Context> findById(Long id);

    public List<Context> findByTitle(String title);

    @Query(value = "select count(*) from context where language = ?1 and title= ?2 and subject= ?3 ;", nativeQuery = true)
    Long countContext(String s, String getcTitle, String getcSubject);
    //按照条件筛选出问题已经被标注的上下文
    @Query(value = "select distinct context.* from context inner join question  on context.id=question.contextId where if(?1 !='',language = ?1,1=1)  and if(?2 !='',title = ?2,1=1) and if(?3 !='',subject = ?3,1=1) and if(?4 !='',origin = ?4,1=1) and if(?5 !='',questionType = ?5,1=1) and if(?6 !='',whType = ?6,1=1) and if(?7 !='',cognitiveType = ?7,1=1) and if(IFNULL(?8,'')!='',fluency = ?8,1=1) and if(IFNULL(?9,'')!='',relevance = ?9,1=1) and if(IFNULL(?10,'')!='',difficulty = ?10,1=1) and if(IFNULL(?11,'')!='',reasonable = ?11,1=1) and if(IFNULL(?12,'')!='',score = ?12,1=1) and contextIsDeleted=0 and isChecked=1  order by context.id ;", nativeQuery = true)
    List<Context> findByCondition(String language, String title, String subject, String source,  String qType, String qQwType, String qCognitiveType, Integer qFluency, Integer qRelevance, Integer qDifficulty, Integer qReasonability, Integer qScore);

    //从数据库中拿取一个问题没有被标注的上下文
    @Query(value = "select  distinct context.* from context inner join question  on context.id=question.contextId where if(?2 !='',language = ?2,1=1)   and if(?1 !='',subject = ?1,1=1) and if(?3 !='',origin = ?3,1=1) and isChecked=0 and isDeleted=0  and contextIsDeleted=0  limit 0,1;", nativeQuery = true)
    Context noDefined(String cSubject, String cLanguage, String cSource);

    //从数据库中拿取一个问题没有被标注的上下文
    @Query(value = "select  distinct context.* from context inner join question on context.id=question.contextId where isChecked=0 and contextIsDeleted=0 limit 0,1;", nativeQuery = true)
    Context notCheckedAndDeleted();
}
