package com.example.projeto.service;

import com.example.projeto.dto.UsuarioDTO;
import com.example.projeto.dto.UsuarioResponseDTO;
import com.example.projeto.model.Usuario;
import com.example.projeto.repository.UsuarioRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UsuarioService {
    private UsuarioRepository usuarioRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public Usuario salvarUsuario(UsuarioDTO dto){
        usuarioRepository.findByEmail(dto.getEmail())
                .ifPresent(u-> { throw new IllegalArgumentException("Email já cadastrado"); });

        Usuario usuario = new Usuario();
        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setSenha(bCryptPasswordEncoder.encode(dto.getSenha())); //Criptografando a senha.

        return usuarioRepository.save(usuario);
    }
    public List<UsuarioResponseDTO> listarTodosUsuarios(){
        return usuarioRepository.findAll()
            .stream()
            .map(this::toDTO)
            .toList();
    }
    private UsuarioResponseDTO toDTO(Usuario usuario){
        return new UsuarioResponseDTO(usuario.getNome(), usuario.getEmail());
    }
}
