package com.auth.repository;

import com.auth.model.Subscription;
import com.auth.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

    boolean existsByUserAndFollowing(User user, User following);

    Optional<Subscription> findByUserAndFollowing(User user, User following);
}
