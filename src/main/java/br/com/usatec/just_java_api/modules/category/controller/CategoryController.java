package br.com.usatec.just_java_api.modules.category.controller;

import br.com.usatec.just_java_api.modules.category.dto.CategoryRequestDTO;
import br.com.usatec.just_java_api.modules.category.dto.CategoryResponseDTO;
import br.com.usatec.just_java_api.modules.category.entity.Category;
import br.com.usatec.just_java_api.modules.category.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/category")
public class CategoryController {

  @Autowired
  private CategoryService categoryService;
 
  @PostMapping("")
  public ResponseEntity<Category> createCategory(@Valid @RequestBody CategoryRequestDTO body) {
    Category createdCategory = categoryService.createCategory(body);

    return ResponseEntity.status(201).body(createdCategory);
  }

  @GetMapping("")
  public ResponseEntity<List<CategoryResponseDTO>> getCategories(@Valid @RequestParam String name) {
    List<CategoryResponseDTO> categories = categoryService.getCategories(name);

    return ResponseEntity.ok(categories);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Category> updateCategory(@PathVariable("id") UUID id, @Valid @RequestBody CategoryRequestDTO body) {
    Category updatedCategory = categoryService.updateCategory(id, body);

    return ResponseEntity.ok(updatedCategory);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteCategory(@Valid @PathVariable("id") UUID id) {
      categoryService.deleteCategory(id);

      return ResponseEntity.noContent().build();
  }
}
