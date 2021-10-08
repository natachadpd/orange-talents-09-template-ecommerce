package br.com.zupacademy.natacha.mercadolivre.commons.validator;

import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class ExistsIdValidator implements ConstraintValidator <ExistsId, Object>{

    private String campo;
    private Class<?> classes;
    @PersistenceContext
    private EntityManager manager;

    @Override
    public void initialize(ExistsId params) {
        campo = params.fieldName();
        classes = params.domainClass();
    }

    @Override
    public boolean isValid(Object valorCampo, ConstraintValidatorContext context) {
        if(valorCampo == null) {
            return true;
        }

        Query query = manager.createQuery("select 1 from "+classes.getName()+" where "+campo+"=:value");
        query.setParameter("value", valorCampo);


        List<?> list = query.getResultList();
        Assert.isTrue(list.size() <=1, "você tem mais de um "+classes+" com o atributo "+campo+" com o valor = "+valorCampo);

        return !list.isEmpty();
    }
}