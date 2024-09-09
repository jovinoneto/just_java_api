package br.com.usatec.just_java_api.modules.course.enums;

import java.util.stream.Stream;

public enum CourseStatus {
  
  ATIVO(1),
  INATIVO(2);

  private final int status;

  private CourseStatus(int status) {
    this.status = status;
  }

  public int getCourseStatus() {
    return status;
  }

  public static CourseStatus of(int status) {
    return Stream.of(CourseStatus.values())
    .filter(p -> p.getCourseStatus() == status)
    .findFirst()
    .orElseThrow(IllegalArgumentException::new);
  }
}
