package com.hachicore.springwebservicebook.web;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.mock.env.MockEnvironment;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProfileControllerUnitTest {

    @Test
    @DisplayName("real profile이 조회된다")
    void realProfileCheck() {
        // given
        String expectedProfile = "real";
        MockEnvironment env = new MockEnvironment();
        env.addActiveProfile(expectedProfile);
        env.addActiveProfile("oauth");
        env.addActiveProfile("real-db");

        ProfileController controller = new ProfileController(env);

        // when
        String profile = controller.profile();

        // then
        assertEquals(profile, expectedProfile);
    }

    @Test
    @DisplayName("real profile이 없으면 첫 번째가 조회된다")
    void firstProfileCheck() {
        // given
        String expectedProfile = "oauth";
        MockEnvironment env = new MockEnvironment();
        env.addActiveProfile(expectedProfile);
        env.addActiveProfile("real-db");

        ProfileController controller = new ProfileController(env);

        // when
        String profile = controller.profile();

        // then
        assertEquals(profile, expectedProfile);
    }

    @Test
    @DisplayName("active profile이 없으면 default가 조회된다")
    void defaultProfileCheck() {
        // given
        String expectedProfile = "default";
        MockEnvironment env = new MockEnvironment();
        ProfileController controller = new ProfileController(env);

        // when
        String profile = controller.profile();

        // then
        assertEquals(profile, expectedProfile);
    }
}