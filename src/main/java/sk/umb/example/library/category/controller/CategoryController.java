package sk.umb.example.library.category.controller;

import org.springframework.web.bind.annotation.*;
import sk.umb.example.library.category.service.CategoryDetailDto;
import sk.umb.example.library.category.service.CategoryRequestDto;
import sk.umb.example.library.category.service.CategoryService;

import java.util.List;

@RestController
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/api/categories")
    public List<CategoryDetailDto> searchCategory(@RequestParam(required = false) String name) {
        System.out.println("Search category called. Name: " + name);

        if (name != null && !name.isEmpty() && !name.isBlank()) {
            return categoryService.searchCategoryByName(name);
        }
        return categoryService.getAllCategories();
    }

    @GetMapping("/api/categories/{categoryId}")
    public CategoryDetailDto getCategory(@PathVariable Long categoryId) {
        System.out.println("Get category called: ID = " + categoryId);
        return categoryService.getCategoryById(categoryId);
    }

    @PostMapping("/api/categories")
    public Long createCategory(@RequestBody CategoryRequestDto categoryRequestDto) {
        System.out.println("Create category called.");
        return categoryService.createCategory(categoryRequestDto);
    }

    @PutMapping("/api/categories/{categoryId}")
    public void updateCategory(@PathVariable Long categoryId, @RequestBody CategoryRequestDto categoryRequestDto) {
        System.out.println("Update category called: ID = " + categoryId);
        categoryService.updateCategory(categoryId, categoryRequestDto);
    }

    @DeleteMapping("/api/categories/{categoryId}")
    public void deleteCategory(@PathVariable Long categoryId) {
        System.out.println("Delete category called: ID = " + categoryId);
        categoryService.deleteCategory(categoryId);
    }
}
