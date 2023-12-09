package com.suzzingv.genshingg.domain.Weapon.repository;

import com.suzzingv.genshingg.domain.Weapon.entity.Weapon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface WeaponRepository extends JpaRepository<Weapon, Long> {
}
