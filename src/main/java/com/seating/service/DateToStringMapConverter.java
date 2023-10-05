package com.seating.service;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

@Converter
public class DateToStringMapConverter implements AttributeConverter<Map<Date, String>, String> {

    @Override
    public String convertToDatabaseColumn(Map<Date, String> attribute) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(attribute);
        } catch (JsonProcessingException e) {
            // Handle the exception
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Map<Date, String> convertToEntityAttribute(String dbData) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(dbData, new TypeReference<HashMap<Date, String>>() {});
        } catch (IOException e) {
            // Handle the exception
            e.printStackTrace();
        }
        return null;
    }
}