package com.erichiroshi.algafood.core.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.ValidationException;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.util.Objects;

public class ValorZeroIncluiDescricaoValidator implements ConstraintValidator<ValorZeroIncluiDescricao, Object> {

    private String valorField;
    private String descricaoField;
    private String descricaoObrigatoria;

    @Override
    public void initialize(ValorZeroIncluiDescricao constraint) {
        this.valorField = constraint.valorField();
        this.descricaoField = constraint.descricaoField();
        this.descricaoObrigatoria = constraint.descricaoObrigatoria();
    }

    @Override
    public boolean isValid(Object objetoValidacao, ConstraintValidatorContext context) {
        boolean valido = true;

        try {
            BigDecimal valor = (BigDecimal) Objects.requireNonNull(BeanUtils.getPropertyDescriptor(objetoValidacao.getClass(), valorField))
                    .getReadMethod().invoke(objetoValidacao);

            String descricao = (String) Objects.requireNonNull(BeanUtils.getPropertyDescriptor(objetoValidacao.getClass(), descricaoField))
                    .getReadMethod().invoke(objetoValidacao);

            if (valor != null && BigDecimal.ZERO.compareTo(valor) == 0 && descricao != null) {
                valido = descricao.toLowerCase().contains(this.descricaoObrigatoria.toLowerCase());
            }

            return valido;
        } catch (Exception e) {
            throw new ValidationException(e);
        }
    }

}