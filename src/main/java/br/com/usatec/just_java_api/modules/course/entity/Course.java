package br.com.usatec.just_java_api.modules.curse.entity;

import br.com.usatec.just_java_api.modules.category.entity.Category;
import br.com.usatec.just_java_api.modules.curse.enums.CourseStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Getter
@Setter
@Entity(name = "curse")
public class Course {
  
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @NotBlank(message = "Name is mandatory!")
  private String name;

  @Column(name = "active")
  private CourseStatus curseStatus;

  @ManyToOne()
  @JoinColumn(name = "category_id", insertable = false, updatable = false)
  private Category category;

  @CreationTimestamp
  @Column(name = "created_at")
  private LocalDateTime createdAt;

  @UpdateTimestamp
  @Column(name = "updated_at")
  private LocalDateTime updatedAt;
}
