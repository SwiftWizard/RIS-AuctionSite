package com.ris.ris.project.repository;

import com.ris.ris.project.model.Auction;
import com.ris.ris.project.model.Feedback;
import com.ris.ris.project.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    List<Feedback> findAllByUsersContaining(User user);
}
