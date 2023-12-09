package com.suzzingv.genshingg.domain.User.entity;

import com.suzzingv.genshingg.domain.Hero.entity.Hero;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class User {
    @Id @GeneratedValue
    @Column(name = "USER_ID")
    private Long id;

    private String name;

    private String password;
    private String nickName;
    private String image;

//    @OneToMany(mappedBy = "user")
//    private List<Hero> figures = new ArrayList<>();
}
