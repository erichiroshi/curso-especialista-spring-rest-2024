package com.erichiroshi.algafood.core.jackson;

import com.erichiroshi.algafood.api.model.mixin.RestauranteMixin;
import com.erichiroshi.algafood.domain.model.Restaurante;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.stereotype.Component;

@Component
public class JacksonMixinModule extends SimpleModule {

    public JacksonMixinModule() {
        setMixInAnnotation(Restaurante.class, RestauranteMixin.class);
    }
}
