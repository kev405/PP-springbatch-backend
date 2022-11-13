//package com.tecnology.bacthfirst.processor;
//
//import com.tecnology.bacthfirst.model.ChargeStatus;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.batch.item.ItemProcessor;
//
//public class PersonaItemProcessor implements ItemProcessor<ChargeStatus, ChargeStatus> {
//
//    private static final Logger log = LoggerFactory.getLogger(PersonaItemProcessor.class);
//
//    @Override
//    public ChargeStatus process(ChargeStatus item) throws Exception {
//        String primerNombre = item.getReadSuccess().toUpperCase();
//        String segundoNombre = item.getReadError().toUpperCase();
//        String telefono = item.getWriteSuccess();
//
////        ChargeStatus chargeStatus = new ChargeStatus(primerNombre, segundoNombre, telefono);
//
//        log.info("Convirtiendo: (" + item + ") a (" + chargeStatus + ")");
//
//        return chargeStatus;
//    }
//}
