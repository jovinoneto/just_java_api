package br.com.usatec.just_java_api.modules.category.dto;

import java.util.UUID;

public record CategoryResponseDTO(
  UUID id,
  String name,
  String createdAt,
  String updatedAd) {}