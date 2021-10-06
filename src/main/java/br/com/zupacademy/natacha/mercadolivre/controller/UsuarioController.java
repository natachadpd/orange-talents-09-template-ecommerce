package br.com.zupacademy.natacha.mercadolivre.controller;

import br.com.zupacademy.natacha.mercadolivre.entity.Usuario;
import br.com.zupacademy.natacha.mercadolivre.controller.form.UsuarioForm;
import br.com.zupacademy.natacha.mercadolivre.repository.UsuarioRepository;
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

