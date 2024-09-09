package br.com.usatec.just_java_api.modules.category.exceptions;

public class CategoryAlreadyExistException extends RuntimeException{

  public CategoryAlreadyExistException() {
    super("Category is already registered");
  }
}
