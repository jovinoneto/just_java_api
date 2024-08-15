package br.com.usatec.just_java_api.modules.curse.enums;

import java.util.stream.Stream;

public enum CurseStatus {
  
  ATIVO(1),
  INATIVO(2);

  private int status;

  private CurseStatus(int status) {
    this.status = status;
  }

  public int getCurseStatus() {
    return status;
  }

  public static CurseStatus of(int status) {
    return Stream.of(CurseStatus.values())
    .filter(p -> p.getCurseStatus() == status)
    .findFirst()
    .orElseThrow(IllegalArgumentException::new);
  }
}
