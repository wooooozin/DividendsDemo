package com.example.dividends.persist;

import com.example.dividends.persist.entity.DividendEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DividendRepository extends JpaRepository<DividendEntity, Long> {
     List<DividendEntity> findAllByCompanyId(Long id);
}
