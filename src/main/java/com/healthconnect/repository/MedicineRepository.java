package com.healthconnect.repository;

import com.healthconnect.entity.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicineRepository extends JpaRepository<Medicine, Integer> {

    List<Medicine> findByCity(String city);

    List<Medicine> findByMedicineNameContainingIgnoreCaseAndCity(
            String medicineName,
            String city);

}
