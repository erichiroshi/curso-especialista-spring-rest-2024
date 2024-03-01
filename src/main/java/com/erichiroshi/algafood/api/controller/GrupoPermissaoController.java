package com.erichiroshi.algafood.api.controller;

import com.erichiroshi.algafood.api.model.dtos.PermissaoDto;
import com.erichiroshi.algafood.domain.model.Permissao;
import com.erichiroshi.algafood.domain.service.GrupoService;
import com.erichiroshi.algafood.mappers.PermissaoMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/grupos/{grupoId}/permissoes")
public class GrupoPermissaoController {

    private final GrupoService grupoService;
    private final PermissaoMapper mapper;

    public GrupoPermissaoController(GrupoService grupoService, PermissaoMapper mapper) {
        this.grupoService = grupoService;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<Set<PermissaoDto>> listar(@PathVariable Long grupoId) {
        Set<Permissao> list = grupoService.listar(grupoId);

        return ResponseEntity.ok(list.stream().map(mapper::toDto).collect(Collectors.toSet()));
    }

    @PutMapping("/{permissaoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void associar(@PathVariable Long grupoId, @PathVariable Long permissaoId) {
        grupoService.associarPermissao(grupoId, permissaoId);
    }

    @DeleteMapping("/{permissaoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void desassociar(@PathVariable Long grupoId, @PathVariable Long permissaoId) {
        grupoService.desassociarPermissao(grupoId, permissaoId);
    }

}
