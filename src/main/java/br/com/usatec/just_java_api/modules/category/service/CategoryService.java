package br.com.usatec.just_java_api.modules.category.service;

import br.com.usatec.just_java_api.modules.category.dto.CategoryRequestDTO;
import br.com.usatec.just_java_api.modules.category.dto.CategoryResponseDTO;
import br.com.usatec.just_java_api.modules.category.entity.Category;
import br.com.usatec.just_java_api.modules.category.exceptions.CategoryAlreadyExistException;
import br.com.usatec.just_java_api.modules.category.exceptions.CategoryInUseException;
import br.com.usatec.just_java_api.modules.category.exceptions.CategoryNotFoundException;
import br.com.usatec.just_java_api.modules.category.repository.CategoryRepository;
import br.com.usatec.just_java_api.modules.course.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CategoryService {

  @Autowired
  private CategoryRepository categoryRepository;

  @Autowired
  private CourseRepository courseRepository;

  public Category createCategory(CategoryRequestDTO body) {
    this.verifyCategoryRegistered(body.name());

    Category newCategory = new Category();
    newCategory.setName(body.name());
    newCategory.setCreatedAt(LocalDateTime.now());

    return this.categoryRepository.save(newCategory);
  }

  public Category updateCategory(UUID id, CategoryRequestDTO categoryDTO) {
    Category category = categoryRepository.findById(id).orElseThrow(CategoryNotFoundException::new);
    this.verifyCategoryRegistered(categoryDTO.name());

    category.setName(categoryDTO.name());
    category.setUpdatedAt(LocalDateTime.now());

    return this.categoryRepository.save(category);
  }

  public List<CategoryResponseDTO> getCategories(String name) {
    name = name == null ? "" : name;

    List<Category> categories = this.categoryRepository.findByNameContainingIgnoreCase(name);

    if(categories.isEmpty())
      throw new CategoryNotFoundException();

    return categories.stream().map(category -> new CategoryResponseDTO(
            category.getId(),
            category.getName(),
            category.getCreatedAt().toString(),
            category.getUpdatedAt() != null ? category.getUpdatedAt().toString() : null
    )).toList();
  }

  public void deleteCategory(UUID id) {
    Category category = this.categoryRepository.findById(id).orElseThrow(CategoryNotFoundException::new);

    if (courseRepository.existsByCategoryId(id)) {
      throw new CategoryInUseException();
    }

    categoryRepository.delete(category);
  }

  public void verifyCategoryRegistered(String name) {
    Optional<Category> isCategoryRegister = this.categoryRepository.findByNameIgnoreCase(name);

    if(isCategoryRegister.isPresent())
      throw new CategoryAlreadyExistException();
  }
}
