package com.remi.production_issue_tracker.repository;

import com.remi.production_issue_tracker.model.ProductionLine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductionLineRepository extends JpaRepository<ProductionLine, Long> {
}
