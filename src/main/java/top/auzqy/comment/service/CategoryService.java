package top.auzqy.comment.service;

import top.auzqy.comment.common.exception.BusinessException;
import top.auzqy.comment.model.CategoryModel;

import java.util.List;

public interface CategoryService {

    CategoryModel create(CategoryModel categoryModel) throws BusinessException;
    CategoryModel get(Integer id);
    List<CategoryModel> selectAll();

    Integer countAllCategory();
}
