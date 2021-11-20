package com.xjtu.qgsystem.repository;

import com.xjtu.qgsystem.entity.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    @Query(value = "SELECT * FROM question WHERE checkedTimes = 0 AND isDeleted = 0 LIMIT 1", nativeQuery = true)
    Question findFirstUnchecked();

    @Query(value = "SELECT count(*) FROM question WHERE checkedTimes = 0", nativeQuery = true)
    int findCountOfUnchecked();

    @Query(value = "SELECT count(*) FROM question WHERE checkedTimes = 1", nativeQuery = true)
    int findCountOfChecked();

    List<Question> getAllByCheckedTimes(int checkedTimes);

    @Query(value = "SELECT COUNT(*) FROM question WHERE fluency BETWEEN 0 AND 10;", nativeQuery = true)
    int findCountOfFluencyOneStar();
    @Query(value = "SELECT COUNT(*) FROM question WHERE fluency BETWEEN 11 AND 20;", nativeQuery = true)
    int findCountOfFluencyTwoStar();
    @Query(value = "SELECT COUNT(*) FROM question WHERE fluency BETWEEN 21 AND 30;", nativeQuery = true)
    int findCountOfFluencyThreeStar();
    @Query(value = "SELECT COUNT(*) FROM question WHERE fluency BETWEEN 31 AND 40;", nativeQuery = true)
    int findCountOfFluencyThirdStar();
    @Query(value = "SELECT COUNT(*) FROM question WHERE fluency BETWEEN 41 AND 50;", nativeQuery = true)
    int findCountOfFluencyFifthStar();

    @Query(value = "SELECT COUNT(*) FROM question WHERE reasonable BETWEEN 0 AND 10;", nativeQuery = true)
    int findCountOfReasonableOneStar();
    @Query(value = "SELECT COUNT(*) FROM question WHERE reasonable BETWEEN 11 AND 20;", nativeQuery = true)
    int findCountOfReasonableTwoStar();
    @Query(value = "SELECT COUNT(*) FROM question WHERE reasonable BETWEEN 21 AND 30;", nativeQuery = true)
    int findCountOfReasonableThreeStar();
    @Query(value = "SELECT COUNT(*) FROM question WHERE reasonable BETWEEN 31 AND 40;", nativeQuery = true)
    int findCountOfReasonableThirdStar();
    @Query(value = "SELECT COUNT(*) FROM question WHERE reasonable BETWEEN 41 AND 50;", nativeQuery = true)
    int findCountOfReasonableFifthStar();

    @Query(value = "SELECT COUNT(*) FROM question WHERE relevance BETWEEN 0 AND 10;", nativeQuery = true)
    int findCountOfRelevanceOneStar();
    @Query(value = "SELECT COUNT(*) FROM question WHERE relevance BETWEEN 11 AND 20;", nativeQuery = true)
    int findCountOfRelevanceTwoStar();
    @Query(value = "SELECT COUNT(*) FROM question WHERE relevance BETWEEN 21 AND 30;", nativeQuery = true)
    int findCountOfRelevanceThreeStar();
    @Query(value = "SELECT COUNT(*) FROM question WHERE relevance BETWEEN 31 AND 40;", nativeQuery = true)
    int findCountOfRelevanceThirdStar();
    @Query(value = "SELECT COUNT(*) FROM question WHERE relevance BETWEEN 41 AND 50;", nativeQuery = true)
    int findCountOfRelevanceFifthStar();

    @Query(value = "SELECT * \n" +
            "FROM question AS q1 JOIN (SELECT ROUND(RAND() * (\n" +
            "\t\t(SELECT MAX(id) FROM question WHERE checkedTimes = 0) - (SELECT MIN(id) FROM question WHERE checkedTimes = 0)) + (SELECT MIN(id) FROM question WHERE checkedTimes = 0)\n" +
            "\t) as id\n" +
            ") as q2\n" +
            "WHERE q1.checkedTimes = 0 and q1.id >= q2.id\n" +
            "ORDER BY q1.id limit 1;", nativeQuery = true)
    Optional<Question> findQuestionRandomly();

    /**
     * 获取根据 title 划分的问题分布
     * @return Map<title, count>
     */
    @Query(value = "SELECT c.title, COUNT(*)\n" +
            "FROM question AS q\n" +
            "LEFT JOIN context AS c\n" +
            "ON q.contextId = c.id\n" +
            "GROUP BY c.title;", nativeQuery = true)
    List<Map<String, Object>> getDistributionByTitle();

    Page<Question> findAllByCheckedTimesAndUserId(int checkedTimes, long userId, Pageable pageable);

    @Query(value = "select text from question;", nativeQuery = true)
    List<String> findAllName();
}
