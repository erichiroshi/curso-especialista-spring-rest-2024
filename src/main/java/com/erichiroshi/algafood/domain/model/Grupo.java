package com.erichiroshi.algafood.domain.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(of = "id")
@Entity
public class Grupo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @ManyToMany
    @JoinTable(name = "grupo_permissao",
            joinColumns = @JoinColumn(name = "grupo_id"),
            inverseJoinColumns = @JoinColumn(name = "permissao_id"))
    private final Set<Permissao> permissoes = new HashSet<>();

    public boolean adicionarPermissao(Permissao permissao) {
        return permissoes.add(permissao);
    }

    public boolean removerPermissao(Permissao permissao) {
        return permissoes.remove(permissao);
    }
}
