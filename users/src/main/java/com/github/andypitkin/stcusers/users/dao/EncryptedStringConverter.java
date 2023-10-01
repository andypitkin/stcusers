package com.github.andypitkin.stcusers.users.dao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.security.crypto.keygen.KeyGenerators;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

/**
 * A class that handles converting String attributes to and from their encrypted
 * form.
 * 
 */
@Converter
public class EncryptedStringConverter implements AttributeConverter<String, String> {

    private final String secretKey;

    public EncryptedStringConverter(@Value("${users.secretKey}") String secretKey) {
        this.secretKey = secretKey;
    }

    @Override
    public String convertToDatabaseColumn(String attribute) {
        String salt = KeyGenerators.string().generateKey();
        TextEncryptor encryptor = Encryptors.text(secretKey, salt);
        String encryptedValue = encryptor.encrypt(attribute);
        // encrypt the string value for storage and store along side the generated salt
        return encryptedValue + ";" + salt;
    }

    @Override
    public String convertToEntityAttribute(String dbData) {
        // pull out the salt from the stored data
        int separatorIndex = dbData.lastIndexOf(";");
        String encryptedValue = dbData.substring(0, separatorIndex);
        String salt = dbData.substring(separatorIndex + 1);
        TextEncryptor encryptor = Encryptors.text(secretKey, salt);
        // decrypt the encrypted value only for use in the application
        return encryptor.decrypt(encryptedValue);
    }

}
