package br.nagualcode.kitchenorders.domain;

import org.springframework.data.annotation.Id;



public class Customer {

	@Id
    private Long id;
    private String email;
    private String name;

    // Construtor padrão
    public Customer() {}

    // Construtor com todos os campos
    public Customer(Long id, String email, String name) {
        this.id = id;
        this.email = email;
        this.name = name;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
