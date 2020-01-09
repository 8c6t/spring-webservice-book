package com.hachicore.springwebservicebook.web.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HelloResponseDtoTest {

    @Test
    @DisplayName("롬복 기능 테스트")
    void lombokTest() {
        // given
        String name = "test";
        Integer amount = 1000;

        // when
        HelloResponseDto dto = new HelloResponseDto(name, amount);

        // then
        assertEquals(dto.getName(), name);
        assertEquals(dto.getAmount(), amount);
    }

}