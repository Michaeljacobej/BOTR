package com.sysem.BOTR.repository;

import com.sysem.BOTR.models.entity.Reaction;
import com.sysem.BOTR.models.entity.Topic;
import com.sysem.BOTR.models.entity.Users;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface ReactionRepository extends JpaRepository<Reaction, Long> {
    Optional<Reaction> findByUserAndTopic(Users user, Topic topic);

    @Query("SELECT COUNT(r) FROM Reaction r WHERE r.user = :user AND r.reactionDate = :date")
    long countTodayVotes(@Param("user") Users user, @Param("date") LocalDate date);

    @Query("SELECT COUNT(r) FROM Reaction r WHERE r.topic = :topic AND r.reactionType = 'LIKE'")
    long countLikesByTopic(@Param("topic") Topic topic);

    @Query("SELECT COUNT(r) FROM Reaction r WHERE r.topic = :topic AND r.reactionType = 'DISLIKE'")
    long countDislikesByTopic(@Param("topic") Topic topic);

}
