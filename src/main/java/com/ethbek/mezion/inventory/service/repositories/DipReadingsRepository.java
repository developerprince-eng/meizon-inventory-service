package com.ethbek.mezion.inventory.service.repositories;

import com.ethbek.mezion.inventory.service.models.entites.DipSheetReadings;
import com.ethbek.mezion.inventory.service.models.entites.DipSheetReadingsKey;
import com.ethbek.mezion.inventory.service.models.entites.Tank;
import com.ethbek.mezion.inventory.service.models.entites.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface DipReadingsRepository extends JpaRepository<DipSheetReadings, DipSheetReadingsKey> {
    List<DipSheetReadings> findByTank(Tank tank);
    Page<DipSheetReadings> findByUser(User user, Pageable pageable);
    Optional<DipSheetReadings> findByUserAndCreatedDate(User user, Date date);
    Page<DipSheetReadings> findAll(Pageable pageable);
    Optional<DipSheetReadings> findByDailyReadingId(String dailyReadings);
}
