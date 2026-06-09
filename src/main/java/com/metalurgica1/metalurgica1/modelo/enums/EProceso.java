package com.metalurgica1.metalurgica1.modelo.enums;

import lombok.Getter;

@Getter
public enum EProceso {
    ESPERA("En espera"),ENPROCESO("En proceso"),TERMINADO("Terminado");

    private String proceso;

    EProceso(String proceso) {
        this.proceso = proceso;
    }

}
