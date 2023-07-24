package com.gangbean.josthis.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Institution extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(cascade = CascadeType.PERSIST)
    private Set<Analyst> analysts;
}
