package br.com.products.kanban.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.springframework.beans.factory.annotation.Value;

import org.jasypt.util.text.AES256TextEncryptor;

@Converter
public class DocumentNumberConverter implements AttributeConverter<Long, String> {
    private final AES256TextEncryptor textEncryptor;
    public DocumentNumberConverter(@Value("${encryption.document.password}") String encryptionPassword) {
        this.textEncryptor = new AES256TextEncryptor();
        this.textEncryptor.setPassword(encryptionPassword);
    }

    @Override
    public String convertToDatabaseColumn(Long documentNumber) {
        if (documentNumber == null) {
            return null;
        }
        return textEncryptor.encrypt(String.valueOf(documentNumber));
    }

    @Override
    public Long convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }
        return Long.valueOf(textEncryptor.decrypt(dbData));
    }
} 
