package com.training.codingstandards;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class SecurityUtilTest {

    @Test
    void hashIdentifierIsStableAndUsesSha256Length() {
        String hash = SecurityUtil.hashIdentifier("1001asha@example.com");

        assertEquals(64, hash.length());
        assertEquals(hash, SecurityUtil.hashIdentifier("1001asha@example.com"));
    }

    @Test
    void sessionTokensAreNotConstant() {
        assertNotEquals(SecurityUtil.sessionToken(), SecurityUtil.sessionToken());
    }

    @Test
    void missingAdminPasswordIsRejected() {
        assertFalse(SecurityUtil.isAdmin("Admin@12345"));
    }
}