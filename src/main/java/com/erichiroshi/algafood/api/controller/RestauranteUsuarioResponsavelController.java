package com.erichiroshi.algafood.api.controller;

import com.erichiroshi.algafood.api.dtos.UsuarioDto;
import com.erichiroshi.algafood.domain.model.Usuario;
import com.erichiroshi.algafood.domain.service.RestauranteService;
import com.erichiroshi.algafood.mappers.UsuarioMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/restaurantes/{restauranteId}/responsaveis")
public class RestauranteUsuarioResponsavelController {

    private final RestauranteService restauranteService;
    private final UsuarioMapper usuarioMapper;

    public RestauranteUsuarioResponsavelController(RestauranteService restauranteService, UsuarioMapper usuarioMapper) {
        this.restauranteService = restauranteService;
        this.usuarioMapper = usuarioMapper;
    }

    @GetMapping
    public ResponseEntity<Set<UsuarioDto>> listarResponsaveis(@PathVariable Long restauranteId) {
        Set<Usuario> list = restauranteService.listar(restauranteId);
        return ResponseEntity.ok(list.stream().map(usuarioMapper::toDto).collect(Collectors.toSet()));
    }

    @PutMapping("/{usuarioId}")
    public ResponseEntity<Void> associar(@PathVariable Long restauranteId, @PathVariable Long usuarioId) {
        restauranteService.associarResponsavel(restauranteId, usuarioId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{usuarioId}")
    public ResponseEntity<Void> desassociar(@PathVariable Long restauranteId, @PathVariable Long usuarioId) {
        restauranteService.desassociarResponsavel(restauranteId, usuarioId);
        return ResponseEntity.noContent().build();
    }


}
