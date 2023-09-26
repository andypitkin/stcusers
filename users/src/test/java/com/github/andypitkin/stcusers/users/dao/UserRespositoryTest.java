package com.github.andypitkin.stcusers.users.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import jakarta.persistence.Query;

import static org.assertj.core.api.Assertions.*;

import java.util.Optional;

@DataJpaTest
public class UserRespositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private UserRespository userRespository;

    @Test
    void dataShouldBeEncryptedWhenSavedAndDecryptedWhenReturned() {

        User userDataToEncrypt = new User();
        userDataToEncrypt.setUsername("username");
        userDataToEncrypt.setFirstName("firstname");
        userDataToEncrypt.setLastName("lastname");
        User savedUser = userRespository.save(userDataToEncrypt);

        testEntityManager.flush();

        Query firstNameNativeQuery = testEntityManager.getEntityManager().createNativeQuery("SELECT USERNAME, FIRST_NAME, LAST_NAME FROM USER_DETAILS WHERE USER_ID=:userId");
        firstNameNativeQuery.setParameter("userId", savedUser.getUserId());
        Object[] result = (Object[]) firstNameNativeQuery.getSingleResult();

        //basic test to ensure that the text value in the database has been changed (i.e. encrypted in someway)
        assertThat(result[0]).isNotEqualTo("username");
        assertThat(result[1]).isNotEqualTo("firstname");
        assertThat(result[2]).isNotEqualTo("lastname");

        Optional<User> decryptedUserData = userRespository.findById(userDataToEncrypt.getUserId());
        //force refresh to ensure encrypted data retrieved from db
        User decryptedUserDataRefreshed = testEntityManager.refresh(decryptedUserData.get());

        assertThat(decryptedUserDataRefreshed).isEqualTo(userDataToEncrypt);
    }

}
