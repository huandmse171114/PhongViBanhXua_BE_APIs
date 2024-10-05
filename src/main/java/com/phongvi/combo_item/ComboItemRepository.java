package com.phongvi.combo_item;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComboItemRepository extends JpaRepository<ComboItem, Long> {

}
