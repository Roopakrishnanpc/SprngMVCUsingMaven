package com.springMVCMaven.Model;




import jakarta.persistence.Id;

import jakarta.persistence.Entity;

@Entity
public class SpringJpaDemo {

    @Id
    private Long id;
    private String name;

    // Getters and setters
}

