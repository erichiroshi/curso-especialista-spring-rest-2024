package com.erichiroshi.algafood.api.controller;

import com.erichiroshi.algafood.api.dtos.RestauranteDto;
import com.erichiroshi.algafood.api.dtos.inputs.RestauranteInputDto;
import com.erichiroshi.algafood.domain.model.Restaurante;
import com.erichiroshi.algafood.domain.service.RestauranteService;
import com.erichiroshi.algafood.mappers.RestauranteMapper;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

    private final RestauranteService service;
    private final RestauranteMapper restauranteMapper;

    public RestauranteController(RestauranteService service, RestauranteMapper restauranteMapper) {
        this.service = service;
        this.restauranteMapper = restauranteMapper;
    }

    @GetMapping
    public ResponseEntity<List<RestauranteDto>> listar() {
        List<Restaurante> list = service.findAll();

        return ResponseEntity.ok(list.stream().map(restauranteMapper::toDto).toList());
    }

    @GetMapping("/{restauranteId}")
    public ResponseEntity<RestauranteDto> buscarId(@PathVariable Long restauranteId) {
        Restaurante restaurante = service.findById(restauranteId);

        return ResponseEntity.ok(restauranteMapper.toDto(restaurante));
    }

    @PostMapping
    public ResponseEntity<RestauranteDto> adicionar(@Valid @RequestBody RestauranteInputDto restauranteInputDto) {
        Restaurante restaurante = restauranteMapper.toEntity(restauranteInputDto);
        restaurante = service.salvar(restaurante);

        return ResponseEntity.status(HttpStatus.CREATED).body(restauranteMapper.toDto(restaurante));
    }

    @PutMapping("/{restauranteId}")
    public ResponseEntity<RestauranteDto> atualizar(@PathVariable Long restauranteId, @Valid @RequestBody RestauranteInputDto restauranteInputDto) {
        Restaurante restaurante = service.atualizar(restauranteId, restauranteInputDto);

        return ResponseEntity.ok(restauranteMapper.toDto(restaurante));
    }

    @PutMapping("/{restauranteId}/ativo")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void ativar(@PathVariable Long restauranteId) {
        service.ativar(restauranteId);
    }

    @DeleteMapping("/{restauranteId}/ativo")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void inativar(@PathVariable Long restauranteId) {
        service.inativar(restauranteId);
    }

    @PutMapping("/ativacoes")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void ativarMultiplos(@RequestBody List<Long> restauranteIds) {
        service.ativarVarios(restauranteIds);
    }

    @DeleteMapping("/ativacoes")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void inativarMultiplos(@RequestBody List<Long> restauranteIds) {
        service.inativarVarios(restauranteIds);
    }

    @PutMapping("/{restauranteId}/abertura")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void abrir(@PathVariable Long restauranteId) {
        service.abrir(restauranteId);
    }

    @PutMapping("/{restauranteId}/fechamento")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void fechar(@PathVariable Long restauranteId) {
        service.fechar(restauranteId);
    }
}
