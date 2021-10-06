package br.com.zupacademy.natacha.mercadolivre.commons.validator;

import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class ValorUnicoValidator implements ConstraintValidator<ValorUnico, Object> {

    private String campo;
    private Class<?> classes;
    @PersistenceContext
    private EntityManager man;

    @Override
    public void initialize(ValorUnico params) {
        campo = params.fieldName();
        classes = params.domainClass();
    }

    @Override
    public boolean isValid(Object valorCampo, ConstraintValidatorContext constraintValidatorContext) {
        Query q = man.createQuery("select 1 from " + classes.getName() + " where " + campo + "=:value");
        q.setParameter("value", valorCampo);
        List<?> list = q.getResultList();
        Assert.state(list.size() <=1, "Foi encontrado mais de um "+classes+" com o atributo "+campo+" = "+campo);

        return list.isEmpty();
    }

}


