package com.tecnology.bacthfirst.processor;

import com.tecnology.bacthfirst.model.persona;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import java.util.Locale;

public class PersonaItemProcessor implements ItemProcessor<persona, persona> {

    private static final Logger log = LoggerFactory.getLogger(PersonaItemProcessor.class);

    @Override
    public persona process(persona item) throws Exception {
        String primerNombre = item.getPrimerNombre().toUpperCase();
        String segundoNombre = item.getSegundoNombre().toUpperCase();
        String telefono = item.getTelefono();

        persona persona = new persona(primerNombre, segundoNombre, telefono);

        log.info("Convirtiendo: (" + item + ") a (" + persona + ")");

        return persona;
    }
}
