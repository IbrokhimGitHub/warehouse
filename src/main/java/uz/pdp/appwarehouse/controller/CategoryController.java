package uz.pdp.appwarehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouse.entity.Category;
import uz.pdp.appwarehouse.payload.CategoryDto;
import uz.pdp.appwarehouse.payload.CategoryResult;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.service.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @PostMapping
    public Result addCategory(@RequestBody CategoryDto categoryDto) {

        return  categoryService.addCategory(categoryDto);
    }
    @GetMapping("getById/{id}")
    public CategoryResult getCategoryById(@PathVariable Integer id){
        CategoryResult categoryResult = categoryService.getCategory(id);
        return categoryResult;
    }
    @GetMapping
    public List<Category> getCategories(){
        List<Category> categories = categoryService.getCategories();
        return categories;
    }
    @DeleteMapping
    public Result delete(Integer id){
        Result result = categoryService.deleteCategory(id);
        return result;
    }
    @PutMapping("/editCategory/{id}")
    public Result edit(@PathVariable Integer id,@RequestBody CategoryDto categoryDto){
        Result result = categoryService.editCategory(id, categoryDto);
        return result;
    }

}
