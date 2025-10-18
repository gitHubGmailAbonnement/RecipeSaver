package com.recipe.controllers;

import com.recipe.managers.RecipeManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ControlerImpl implements Controler {
    @Autowired
    RecipeManager manager;
    ControlerImpl(RecipeManager manager)
    {
        this.manager = manager;
    }
    final private String BASE_ENDPOINT="recipes";
    @Override
    @PostMapping("/add")
    public void addewRecipe(@RequestParam(name="url", defaultValue="") String url) {
        manager.manage(url);
    }
}
