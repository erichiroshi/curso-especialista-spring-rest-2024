package com.erichiroshi.algafood.domain.service;

import com.erichiroshi.algafood.api.dtos.inputs.UsuarioSenhaUpdateDto;
import com.erichiroshi.algafood.api.dtos.inputs.UsuarioUpdateDto;
import com.erichiroshi.algafood.domain.exception.NegocioException;
import com.erichiroshi.algafood.domain.exception.UsuarioNaoEncontradoException;
import com.erichiroshi.algafood.domain.model.Grupo;
import com.erichiroshi.algafood.domain.model.Usuario;
import com.erichiroshi.algafood.domain.repository.UsuarioRepository;
import com.erichiroshi.algafood.mappers.UsuarioMapper;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;

    private final UsuarioMapper mapper;

    private final GrupoService grupoService;

    public UsuarioService(UsuarioRepository repository, UsuarioMapper mapper, GrupoService grupoService) {
        this.repository = repository;
        this.mapper = mapper;
        this.grupoService = grupoService;
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
        repository.detach(usuario);
        Optional<Usuario> usuarioExistente = repository.findByEmail(usuario.getEmail());

        if (usuarioExistente.isPresent() && !usuarioExistente.get().equals(usuario)) {
            throw new NegocioException(
                    String.format("Já existe um usuário cadastrado com o e-mail %s", usuario.getEmail()));
        }

        return repository.save(usuario);
    }

    @Transactional
    public Usuario atualizar(Long usuarioId, @Valid UsuarioUpdateDto usuarioUpdateDto) {
        Usuario usuarioAtual = findById(usuarioId);
        usuarioAtual = mapper.partialUpdate(usuarioUpdateDto, usuarioAtual);

        return salvar(usuarioAtual);
    }

    @Transactional
    public void atualizarSenha(Long usuarioId, UsuarioSenhaUpdateDto usuarioSenhaUpdateDto) {
        Usuario usuario = findById(usuarioId);

        if (!usuario.getSenha().equals(usuarioSenhaUpdateDto.senhaAtual())) {
            throw new NegocioException("Senha atual informada não coincide com a senha do usuário.");
        }

        usuario.setSenha(usuarioSenhaUpdateDto.novaSenha());

        salvar(usuario);
    }

    @Transactional(readOnly = true)
    public Set<Grupo> listarGrupos(Long usuarioId) {
        Usuario usuario = findById(usuarioId);
        return usuario.getGrupos();
    }

    @Transactional
    public void associarGrupo(Long usuarioId, Long grupoId) {
        Usuario usuario = findById(usuarioId);
        Grupo grupo = grupoService.findById(grupoId);

        usuario.adicionarGrupo(grupo);
    }

    @Transactional
    public void desassociarGrupo(Long usuarioId, Long grupoId) {
        Usuario usuario = findById(usuarioId);
        Grupo grupo = grupoService.findById(grupoId);

        usuario.removerGrupo(grupo);
    }
}
