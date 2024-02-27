package com.erichiroshi.algafood.core.jackson;

import com.erichiroshi.algafood.api.model.mixin.CidadeMixin;
import com.erichiroshi.algafood.api.model.mixin.CozinhaMixin;
import com.erichiroshi.algafood.domain.model.Cidade;
import com.erichiroshi.algafood.domain.model.Cozinha;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.stereotype.Component;

@Component
public class JacksonMixinModule extends SimpleModule {

    public JacksonMixinModule() {
        setMixInAnnotation(Cidade.class, CidadeMixin.class);
        setMixInAnnotation(Cozinha.class, CozinhaMixin.class);
    }
}
