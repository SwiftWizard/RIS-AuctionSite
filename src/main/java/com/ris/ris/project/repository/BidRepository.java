package com.ris.ris.project.repository;

import com.ris.ris.project.model.Bid;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BidRepository extends JpaRepository<Bid, Long> {
}
