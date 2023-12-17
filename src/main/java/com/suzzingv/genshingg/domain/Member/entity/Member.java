package com.suzzingv.genshingg.domain.Member.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Member {
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
