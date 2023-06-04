package sk.umb.example.library.category.controller;

import org.springframework.web.bind.annotation.*;
import sk.umb.example.library.category.service.CategoryDetailDTO;
import sk.umb.example.library.category.service.CategoryRequestDTO;
import sk.umb.example.library.category.service.CategoryService;

import java.util.List;

@RestController
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/api/categories")
    public List<CategoryDetailDTO> searchCategory(@RequestParam(required = false) String name) {
        System.out.println("Search category called. Name: " + name);

        if (name != null && !name.isEmpty() && !name.isBlank()) {
            return categoryService.searchCategoryByName(name);
        }
        return categoryService.getAllCategories();
    }

    @GetMapping("/api/categories/{categoryId}")
    public CategoryDetailDTO getCategory(@PathVariable Long categoryId) {
        System.out.println("Get category called: ID = " + categoryId);
        return categoryService.getCategoryById(categoryId);
    }

    @PostMapping("/api/categories")
    public Long createCategory(@RequestBody CategoryRequestDTO categoryRequestDTO) {
        System.out.println("Create category called.");
        return categoryService.createCategory(categoryRequestDTO);
    }

    @PutMapping("/api/categories/{categoryId}")
    public void updateCategory(@PathVariable Long categoryId, @RequestBody CategoryRequestDTO categoryRequestDTO) {
        System.out.println("Update category called: ID = " + categoryId);
        categoryService.updateCategory(categoryId, categoryRequestDTO);
    }

    @DeleteMapping("/api/categories/{categoryId}")
    public void deleteCategory(@PathVariable Long categoryId) {
        System.out.println("Delete category called: ID = " + categoryId);
        categoryService.deleteCategory(categoryId);
    }
}
