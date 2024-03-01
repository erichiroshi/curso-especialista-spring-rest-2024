package com.erichiroshi.algafood.api.controller;

import com.erichiroshi.algafood.api.model.dtos.RestauranteDto;
import com.erichiroshi.algafood.api.model.dtos.inputs.RestauranteInputDto;
import com.erichiroshi.algafood.api.model.view.RestauranteView;
import com.erichiroshi.algafood.domain.model.Restaurante;
import com.erichiroshi.algafood.domain.service.RestauranteService;
import com.erichiroshi.algafood.mappers.RestauranteMapper;
import com.fasterxml.jackson.annotation.JsonView;
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

    @JsonView(RestauranteView.Resumo.class)
    @GetMapping
    public ResponseEntity<List<RestauranteDto>> listar() {
        List<Restaurante> list = service.findAll();

        return ResponseEntity.ok(list.stream().map(restauranteMapper::toDto).toList());
    }

    @JsonView(RestauranteView.ApenasNome.class)
    @GetMapping(params = "projecao=apenas-nome")
    public ResponseEntity<List<RestauranteDto>> listarApenasNomes() {
        List<Restaurante> list = service.findAll();

        return ResponseEntity.ok(list.stream().map(restauranteMapper::toDto).toList());
    }

//	@GetMapping
//	public MappingJacksonValue listar(@RequestParam(required = false) String projecao) {
//		List<Restaurante> restaurantes = restauranteRepository.findAll();
//		List<RestauranteModel> restaurantesModel = restauranteModelAssembler.toCollectionModel(restaurantes);
//
//		MappingJacksonValue restaurantesWrapper = new MappingJacksonValue(restaurantesModel);
//
//		restaurantesWrapper.setSerializationView(RestauranteView.Resumo.class);
//
//		if ("apenas-nome".equals(projecao)) {
//			restaurantesWrapper.setSerializationView(RestauranteView.ApenasNome.class);
//		} else if ("completo".equals(projecao)) {
//			restaurantesWrapper.setSerializationView(null);
//		}
//
//		return restaurantesWrapper;
//	}

//	@GetMapping
//	public List<RestauranteModel> listar() {
//		return restauranteModelAssembler.toCollectionModel(restauranteRepository.findAll());
//	}
//
//	@JsonView(RestauranteView.Resumo.class)
//	@GetMapping(params = "projecao=resumo")
//	public List<RestauranteModel> listarResumido() {
//		return listar();
//	}
//
//	@JsonView(RestauranteView.ApenasNome.class)
//	@GetMapping(params = "projecao=apenas-nome")
//	public List<RestauranteModel> listarApenasNomes() {
//		return listar();
//	}

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
