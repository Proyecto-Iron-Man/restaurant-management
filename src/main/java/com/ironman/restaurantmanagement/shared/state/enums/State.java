package com.ironman.restaurantmanagement.shared.state.enums;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ironman.restaurantmanagement.shared.state.serializer.StateSerializer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

// Swagger annotation
@Schema(type = "object", oneOf = {State.class}, implementation = State.class)

// Lombok annotations
@Getter

// Jackson annotation
@JsonSerialize(using = StateSerializer.class)
public enum State {
    ENABLED("A", true) {
        @Override
        public String toString() {
            return "Habilitado";
        }
    },
    DISABLED("E", false) {
        @Override
        public String toString() {
            return "Deshabilitado";
        }
    };


    private final String value;
    private final String name;
    private final boolean enabled;

    State(String value, boolean enabled) {
        this.value = value;
        this.name = this.toString();
        this.enabled = enabled;
    }

}
