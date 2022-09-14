package com.domo.boardtest.domain;

import lombok.*;

import javax.persistence.*;

import static com.domo.boardtest.domain.User.TABLE_PREFIX;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
// prefix
//suffix
@Table(name = TABLE_PREFIX + "user")
public class User {
    static final String TABLE_PREFIX = "TBL_";

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "name", unique = true, nullable = false)
    private String nickname;

    @Column(name = "password", nullable = false)
    private String password;

}
