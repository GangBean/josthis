package com.gangbean.josthis.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Analyst extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(cascade = CascadeType.PERSIST, orphanRemoval = true)
    private Set<Report> reports;
}
