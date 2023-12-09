package com.suzzingv.genshingg.domain.Hero.entity;

import com.suzzingv.genshingg.domain.Weapon.entity.Weapon;
import com.suzzingv.genshingg.domain.Weapon.entity.WeaponType;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;

@Entity
@Getter
public class Hero {
    @Id @GeneratedValue
    @Column(name = "HERO_ID")
    private Long id;

    private String name;
    private Element element;
    private String image;
    private WeaponType weaponType;

//    @OneToMany(mappedBy = "hero")
//    private List<Weapon> recommendWeapons;
    @Embedded
    private BaseProperty baseProperty;
}
