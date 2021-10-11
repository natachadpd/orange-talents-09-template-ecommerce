package br.com.zupacademy.natacha.mercadolivre.controller;

import br.com.zupacademy.natacha.mercadolivre.controller.form.CaracteristicasForm;
import br.com.zupacademy.natacha.mercadolivre.entity.Caracteristicas;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/caracteristicas")
public class CaracteristicasController {

    @PersistenceContext
    private EntityManager manager;



    @PostMapping
    @Transactional
    public void cadastraCaracteristicas(@RequestBody @Valid CaracteristicasForm caracteristicasForm) {
        Caracteristicas caracteristicas = caracteristicasForm.toCaracteristicas();
        manager.persist(caracteristicas);
    }
}
