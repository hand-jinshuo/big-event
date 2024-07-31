package org.example.service;

import org.example.pojo.Category;

import java.util.List;

public interface CategoryService {
    void add(Category category);

    //列表查询
    List<Category> list();

    Category findById(Integer id);

    void update(Category category);
}
