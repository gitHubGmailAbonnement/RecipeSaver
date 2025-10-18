package com.recipe.dao.entities;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name="recipes")
public class Recipe {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(nullable = false)
    private UUID recipe_id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String ingredients;
    @Column(nullable = false)
    private String steps;
    private String notes;
    @Column(nullable = false)
    private String source;

}
