package com.suzzingv.genshingg.domain.Weapon.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public class Weapon {
    @Id @GeneratedValue
    @Column(name = "WEAPON_ID")
    private Long id;

    private String name;
    private String image;
}
