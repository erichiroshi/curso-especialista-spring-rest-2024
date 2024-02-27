package com.erichiroshi.algafood.domain.service;

import com.erichiroshi.algafood.api.dtos.inputs.UsuarioSenhaUpdateDto;
import com.erichiroshi.algafood.api.dtos.inputs.UsuarioUpdateDto;
import com.erichiroshi.algafood.domain.exception.NegocioException;
import com.erichiroshi.algafood.domain.exception.UsuarioNaoEncontradoException;
import com.erichiroshi.algafood.domain.model.Usuario;
import com.erichiroshi.algafood.domain.repository.UsuarioRepository;
import com.erichiroshi.algafood.mappers.UsuarioMapper;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;

    private final UsuarioMapper mapper;

    public UsuarioService(UsuarioRepository repository, UsuarioMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Transactional(readOnly = true)
    public List<Usuario> findAll() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public Usuario findById(Long usuarioId) {
        return repository.findById(usuarioId)
                .orElseThrow(() -> new UsuarioNaoEncontradoException(usuarioId));
    }

    @Transactional
    public Usuario salvar(Usuario usuario) {
        return repository.save(usuario);
    }

    @Transactional
    public Usuario atualizar(Long usuarioId, @Valid UsuarioUpdateDto usuarioUpdateDto) {
        Usuario usuarioAtual = findById(usuarioId);
        usuarioAtual = mapper.partialUpdate(usuarioUpdateDto, usuarioAtual);

        return repository.save(usuarioAtual);
    }

    @Transactional
    public void atualizarSenha(Long usuarioId, UsuarioSenhaUpdateDto usuarioSenhaUpdateDto) {
        Usuario usuario = findById(usuarioId);

        if (!usuario.getSenha().equals(usuarioSenhaUpdateDto.senhaAtual())) {
            throw new NegocioException("Senha atual informada não coincide com a senha do usuário.");
        }

        usuario.setSenha(usuarioSenhaUpdateDto.novaSenha());

        repository.save(usuario);
    }
}
