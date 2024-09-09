package br.com.usatec.just_java_api.modules.category.exceptions;

public class CategoryInUseException extends RuntimeException {

    public CategoryInUseException() {
        super("Category in use");
    }
}
