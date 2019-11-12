package com.garret.movies.dao.entity;

import com.garret.movies.dao.entity.enums.GenreType;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "genre")
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Enumerated(EnumType.STRING)
    private GenreType name;
}
