package br.com.usatec.just_java_api.modules.category.repository;

import br.com.usatec.just_java_api.modules.category.entity.Category;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;



public interface CategoryRepository extends JpaRepository<Category, UUID> {
  
}
