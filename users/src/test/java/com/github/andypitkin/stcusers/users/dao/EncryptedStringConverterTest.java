package com.github.andypitkin.stcusers.users.dao;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class EncryptedStringConverterTest {

    @Test
    void convertToDatabaseColumnEncryptsValueAndCanBeReadByConvertToEntityAttribute() {
        EncryptedStringConverter encryptedStringConverter = new EncryptedStringConverter("mysecret");
        String encryptedValue = encryptedStringConverter.convertToDatabaseColumn("mydatabasevalue");

        //basic check that value has been modified (i.e. encrypted)
        assertThat(encryptedValue).isNotEqualTo("mydatabasevalue");

        //check value can be decrypted from the encrypted value
        assertThat(encryptedStringConverter.convertToEntityAttribute(encryptedValue)).isEqualTo("mydatabasevalue");
    }

}
