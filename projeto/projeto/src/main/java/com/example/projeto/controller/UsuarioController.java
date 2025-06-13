package com.example.projeto.controller;

import com.example.projeto.dto.UsuarioDTO;
import com.example.projeto.dto.UsuarioResponseDTO;
import com.example.projeto.repository.UsuarioRepository;
import com.example.projeto.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    private UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> ListarTodos(){
        return ResponseEntity.ok(usuarioService.listarTodosUsuarios());
    }

    @PostMapping
    public ResponseEntity<Map<Object, String>> salvar(@Valid @RequestBody UsuarioDTO dto){
        usuarioService.salvarUsuario(dto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(Map.of("mensagem", "Usuário cadastrado com sucesso."));

    }
}
