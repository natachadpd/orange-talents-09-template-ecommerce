package br.com.zupacademy.natacha.mercadolivre.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/usuarios")
    public class UsuarioController {

        @Autowired
        private UsuarioRepository usuarioRepository;


        @PostMapping
        @Transactional
        public void cadastrarUsuario(@RequestBody @Valid UsuarioForm usuarioForm){
            Usuario usuario = usuarioForm.toModel();
            usuarioRepository.save(usuario);
        }
    }

