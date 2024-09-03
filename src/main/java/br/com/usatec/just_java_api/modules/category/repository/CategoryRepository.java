package br.com.usatec.just_java_api.modules.category.repository;

import br.com.usatec.just_java_api.modules.category.entity.Category;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, UUID> {
  Optional<Category> findByNameIgnoreCase(String name);
  List<Category> findByNameContainingIgnoreCase(String name);
}
