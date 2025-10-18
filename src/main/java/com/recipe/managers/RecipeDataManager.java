package com.recipe.managers;

import java.util.List;

public interface RecipeDataManager <T>{
    void manageData(T data);

    void manageDatas(List<T> datas);
}
