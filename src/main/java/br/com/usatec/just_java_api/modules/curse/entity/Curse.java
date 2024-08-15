package br.com.usatec.just_java_api.modules.curse.entity;

import br.com.usatec.just_java_api.modules.category.entity.Category;
import br.com.usatec.just_java_api.modules.curse.enums.CurseStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;





@Data
@Entity(name = "curse")
public class Curse {
  
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  private String name;

  @Column(name = "active")
  private CurseStatus curseStatus;

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
