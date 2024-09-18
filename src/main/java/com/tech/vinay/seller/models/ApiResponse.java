package com.tech.vinay.seller.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Data
@Getter
@Setter
public class ApiResponse {
    private ResponseStatusTypeEnum status;
    private String message;
    private Object data;

    public enum ResponseStatusTypeEnum {
        SUCCESS("S"),
        FAIL("F"),
        UNAUTHORIZED("404");

        private final static Map<String, ResponseStatusTypeEnum> CONSTANTS = new HashMap<String, ResponseStatusTypeEnum>();

        static {
            for (ResponseStatusTypeEnum c: values()) {
                CONSTANTS.put(c.code, c);
            }
        }

        private final String code;

        ResponseStatusTypeEnum(String code) {
            this.code = code;
        }

        @JsonValue
        public String getCode() {
            return code;
        }

        public static ResponseStatusTypeEnum fromCode(String code) {
            for (ResponseStatusTypeEnum type : ResponseStatusTypeEnum.values()) {
                if (type.code.equals(code)) {
                    return type;
                }
            }
            throw new IllegalArgumentException("Invalid response type code: " + code);
        }

        @JsonCreator
        public static String fromValue(String value) {
            ResponseStatusTypeEnum constant = CONSTANTS.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant.code;
            }
        }

        @Override
        public String toString() {
            return this.code;
        }
    }


}
