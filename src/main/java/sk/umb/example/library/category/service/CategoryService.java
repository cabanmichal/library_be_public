package sk.umb.example.library.category.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import sk.umb.example.library.category.persistence.entity.CategoryEntity;
import sk.umb.example.library.category.persistence.repository.CategoryRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<CategoryDetailDto> getAllCategories() {

        return mapToDtoList(categoryRepository.findAll());
    }

    public List<CategoryDetailDto> searchCategoryByName(String name) {
        return mapToDtoList(categoryRepository.findByName(name));
    }

    public CategoryDetailDto getCategoryById(Long categoryId) {
        return mapToDto(getCategoryEntityById(categoryId));
    }

    @Transactional
    public Long createCategory(CategoryRequestDto categoryRequestDto) {
        CategoryEntity category = mapToEntity(categoryRequestDto);

        return categoryRepository.save(category).getId();
    }

    @Transactional
    public void updateCategory(Long categoryId, CategoryRequestDto categoryRequestDto) {
        CategoryEntity categoryEntity = getCategoryEntityById(categoryId);

        String name = categoryRequestDto.getName();
        if (name != null && !name.isEmpty() && !name.isBlank()) {
            categoryEntity.setName(name);
        }

        categoryRepository.save(categoryEntity);
    }

    @Transactional
    public void deleteCategory(Long categoryId) {
        categoryRepository.deleteById(categoryId);
    }

    private List<CategoryDetailDto> mapToDtoList(Iterable<CategoryEntity> categoryEntities) {
        List<CategoryDetailDto> categories = new ArrayList<>();

        categoryEntities.forEach(categoryEntity -> {
            CategoryDetailDto dto = mapToDto(categoryEntity);
            categories.add(dto);
        });

        return categories;
    }

    private CategoryDetailDto mapToDto(CategoryEntity categoryEntity) {
        CategoryDetailDto dto = new CategoryDetailDto();
        dto.setId(categoryEntity.getId());
        dto.setName(categoryEntity.getName());
        return dto;
    }

    private CategoryEntity mapToEntity(CategoryRequestDto dto) {
        CategoryEntity category = new CategoryEntity();
        category.setName(dto.getName());
        return category;
    }

    private CategoryEntity getCategoryEntityById(Long categoryId) {
        Optional<CategoryEntity> category = categoryRepository.findById(categoryId);

        if (category.isEmpty()) {
            throw new IllegalArgumentException("Category not found. ID: " + categoryId);
        }

        return category.get();
    }
}
