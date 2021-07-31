package uz.pdp.appwarehouse.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appwarehouse.entity.Category;
import uz.pdp.appwarehouse.payload.CategoryDto;
import uz.pdp.appwarehouse.payload.CategoryResult;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.respository.CategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;
    public Result addCategory(CategoryDto categoryDto){

        Category category=new Category();
        category.setName(categoryDto.getName());
        if (categoryDto.getParentCategoryId()!=null){
            Optional<Category> optionalParentCategory = categoryRepository.findById(categoryDto.getParentCategoryId());
            if (!optionalParentCategory.isPresent()) {
                category.setParentCategory(optionalParentCategory.get());
                return new Result("such parent category is not find",false);

            }
        }
        categoryRepository.save(category);
        return new Result("Category saved successfully",true);
    }
    public List<Category> getCategories(){
        List<Category> categories = categoryRepository.findAll();
        return categories;
    }
    public CategoryResult getCategory(Integer id){
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isPresent()) {
            return new CategoryResult(optionalCategory.get(),true);
        }else {
            return new CategoryResult(new Category(),false);
        }
    }

    public Result deleteCategory(Integer id){
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isPresent()) {
            Category category = optionalCategory.get();
            category.setActive(false);
            return new Result("category deactivated",true);
        }
        else
            return new Result("cant find such category",false);
    }
    public Result editCategory(Integer id, CategoryDto categoryDto){
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (!optionalCategory.isPresent()) {
            return new Result("cant find such category",false);
        }
        Category category = optionalCategory.get();
        category.setName(categoryDto.getName());
        if (categoryDto.getParentCategoryId()!=null){
            Optional<Category> optionalParentCategory = categoryRepository.findById(categoryDto.getParentCategoryId());
            if (!optionalParentCategory.isPresent()) {
                category.setParentCategory(optionalParentCategory.get());
                return new Result("such parent category is not find",false);

            }
        }
        categoryRepository.save(category);
        return new Result("Category edited successfully",true);

    }
}
