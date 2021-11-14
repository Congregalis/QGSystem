package com.xjtu.qgsystem.repository;

import com.xjtu.qgsystem.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
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


}
