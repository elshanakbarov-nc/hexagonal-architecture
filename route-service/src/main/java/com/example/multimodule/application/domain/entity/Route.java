package com.example.multimodule.application.domain.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private String locatiom;

}
