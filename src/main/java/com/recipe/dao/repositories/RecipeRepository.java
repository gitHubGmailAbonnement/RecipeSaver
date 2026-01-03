package com.recipe.dao.repositories;

import com.recipe.dao.entities.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
//TODO il faut ajouter dee methodes pour que l'utilisateur puisse chercher par mot cle.
// Pour l'instant on va afficher ce qui est dans la BD et il va selectionner pour voir les details
public interface RecipeRepository extends JpaRepository<Recipe, UUID> {
    Optional<Recipe> findById(UUID id);
}