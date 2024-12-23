package com.case_opener_game.case_opener.it;

import com.case_opener_game.case_opener.constants.ExceptionMessage;
import com.case_opener_game.case_opener.constants.Routes;
import com.case_opener_game.case_opener.constants.SessionAttributes;
import com.case_opener_game.case_opener.dto.GameDTO;
import com.case_opener_game.case_opener.dto.bet.BetRequestDTO;
import com.case_opener_game.case_opener.dto.bootstrap.BootstrapDTO;
import com.case_opener_game.case_opener.dto.user.BalanceDTO;
import com.case_opener_game.case_opener.enums.ErrorCode;
import com.case_opener_game.case_opener.enums.Game;
import com.case_opener_game.case_opener.enums.GameDifficulty;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class GameControllerIT {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void bootstrap_EasyDifficulty_ReturnsValidResponseEntity() throws Exception {
        // given
        GameDTO gameDTO = new GameDTO(Game.CASEOPENER.getName(), GameDifficulty.EASY.name());
        String json = objectMapper.writeValueAsString(gameDTO);
        var builder = MockMvcRequestBuilders.post(Routes.API_BOOTSTRAP_ROUTE)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);
        List<Double> easyMultipliers = List.of(
                0.0,
                1.0,
                2.0,
                5.0,
                10.0,
                20.0,
                40.0,
                60.0
        );
        BootstrapDTO bootstrapDTO = new BootstrapDTO(
                Game.CASEOPENER.getName(),
                easyMultipliers,
                GameDifficulty.EASY.name(),
                BigDecimal.valueOf(1000)
        );
        String expected = objectMapper.writeValueAsString(bootstrapDTO);
        // when
        this.mockMvc.perform(builder)
                // then
                .andExpectAll(
                        status().isOk(),
                        content()
                                .contentType(MediaType.APPLICATION_JSON),
                        content().json(expected),
                        jsonPath("$.game").exists(),
                        jsonPath("$.multipliers").exists(),
                        jsonPath("$.difficulty").exists(),
                        jsonPath("$.balance").exists()
                );
    }

    @Test
    void bootstrap_MediumDifficulty_ReturnsValidResponseEntity() throws Exception {
        // given
        GameDTO gameDTO = new GameDTO(Game.CASEOPENER.getName(), GameDifficulty.MEDIUM.name());
        String json = objectMapper.writeValueAsString(gameDTO);
        var builder = MockMvcRequestBuilders.post(Routes.API_BOOTSTRAP_ROUTE)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);
        List<Double> mediumMultipliers = List.of(
                0.0,
                0.5,
                1.0,
                2.0,
                5.0,
                10.0,
                20.0,
                40.0,
                60.0,
                80.0
        );
        BootstrapDTO bootstrapDTO = new BootstrapDTO(
                Game.CASEOPENER.getName(),
                mediumMultipliers,
                GameDifficulty.MEDIUM.name(),
                BigDecimal.valueOf(1000)
        );
        String expected = objectMapper.writeValueAsString(bootstrapDTO);
        // when
        this.mockMvc.perform(builder)
                // then
                .andExpectAll(
                        status().isOk(),
                        content()
                                .contentType(MediaType.APPLICATION_JSON),
                        content().json(expected),
                        jsonPath("$.game").exists(),
                        jsonPath("$.multipliers").exists(),
                        jsonPath("$.difficulty").exists(),
                        jsonPath("$.balance").exists()
                );
    }

    @Test
    void bootstrap_HardDifficulty_ReturnsValidResponseEntity() throws Exception {
        GameDTO gameDTO = new GameDTO(Game.CASEOPENER.getName(), GameDifficulty.HARD.name());
        String json = objectMapper.writeValueAsString(gameDTO);
        var builder = MockMvcRequestBuilders.post(Routes.API_BOOTSTRAP_ROUTE)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);
        List<Double> mediumMultipliers = List.of(
                0.0,
                0.2,
                0.5,
                1.0,
                2.0,
                5.0,
                10.0,
                20.0,
                40.0,
                60.0,
                80.0,
                120.0
        );
        BootstrapDTO bootstrapDTO = new BootstrapDTO(
                Game.CASEOPENER.getName(),
                mediumMultipliers,
                GameDifficulty.HARD.name(),
                BigDecimal.valueOf(1000)
        );
        String expected = objectMapper.writeValueAsString(bootstrapDTO);
        // when
        this.mockMvc.perform(builder)
                // then
                .andExpectAll(
                        status().isOk(),
                        content()
                                .contentType(MediaType.APPLICATION_JSON),
                        content().json(expected),
                        jsonPath("$.game").exists(),
                        jsonPath("$.multipliers").exists(),
                        jsonPath("$.difficulty").exists(),
                        jsonPath("$.balance").exists()
                );
    }

    @Test
    void bet_ValidRequestBody_ReturnsValidResponseEntity() throws Exception {
        // given
        String gameName = Game.CASEOPENER.getName();
        String difficulty = GameDifficulty.HARD.name();
        BigDecimal balance = BigDecimal.valueOf(1000);
        BetRequestDTO betRequestDTO = new BetRequestDTO(BigDecimal.ONE);

        String reqJson = objectMapper.writeValueAsString(betRequestDTO);

        var requestBuilder = MockMvcRequestBuilders
                .post(Routes.GAME_BET_ROUTE)
                .sessionAttr(SessionAttributes.GAME_NAME, gameName)
                .sessionAttr(SessionAttributes.DIFFICULTY, difficulty)
                .sessionAttr(SessionAttributes.BALANCE, balance)
                .contentType(MediaType.APPLICATION_JSON)
                .content(reqJson);
        // when
        this.mockMvc
                // then
                .perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.multiplier").exists())
                .andExpect(jsonPath("$.payout").exists())
                .andExpect(jsonPath("$.balance").exists());

    }

    @Test
    void bet_InvalidRequestBody_ReturnsLowBalanceException() throws Exception {
        // given
        String gameName = Game.CASEOPENER.getName();
        String difficulty = GameDifficulty.HARD.name();
        BigDecimal balance = BigDecimal.ZERO;
        BetRequestDTO betRequestDTO = new BetRequestDTO(BigDecimal.ONE);
        String reqJson = objectMapper.writeValueAsString(betRequestDTO);

        var requestBuilder = MockMvcRequestBuilders
                .post(Routes.GAME_BET_ROUTE)
                .sessionAttr(SessionAttributes.GAME_NAME, gameName)
                .sessionAttr(SessionAttributes.DIFFICULTY, difficulty)
                .sessionAttr(SessionAttributes.BALANCE, balance)
                .contentType(MediaType.APPLICATION_JSON)
                .content(reqJson);
        // when
        this.mockMvc
                // then
                .perform(requestBuilder)
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors[0].errorCode").value(ErrorCode.VALIDATION_FAILED.getCode()))
                .andExpect(jsonPath("$.errors[0].message").value(ExceptionMessage.INSUFFICIENT_BALANCE));
    }

    @Test
    void bet_BetIsHigherThanBalance_ReturnsLowBalanceException() throws Exception {
        // given
        String gameName = Game.CASEOPENER.getName();
        String difficulty = GameDifficulty.HARD.name();
        BigDecimal balance = BigDecimal.ONE;
        BetRequestDTO betRequestDTO = new BetRequestDTO(BigDecimal.TEN);
        String reqJson = objectMapper.writeValueAsString(betRequestDTO);

        var requestBuilder = MockMvcRequestBuilders
                .post(Routes.GAME_BET_ROUTE)
                .sessionAttr(SessionAttributes.GAME_NAME, gameName)
                .sessionAttr(SessionAttributes.DIFFICULTY, difficulty)
                .sessionAttr(SessionAttributes.BALANCE, balance)
                .contentType(MediaType.APPLICATION_JSON)
                .content(reqJson);
        // when
        this.mockMvc
                // then
                .perform(requestBuilder)
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors[0].errorCode").value(ErrorCode.VALIDATION_FAILED.getCode()))
                .andExpect(jsonPath("$.errors[0].message").value(ExceptionMessage.INSUFFICIENT_BALANCE));
    }

    @Test
    void getBalance_ReturnsValidResponseEntity() throws Exception {
        // given
        BalanceDTO betRequestDTO = new BalanceDTO(BigDecimal.ONE);
        String reqJson = objectMapper.writeValueAsString(betRequestDTO);

        var requestBuilder = MockMvcRequestBuilders
                .get(Routes.API_BALANCE_ROUTE)
                .sessionAttr(SessionAttributes.BALANCE, betRequestDTO.getBalance())
                .contentType(MediaType.APPLICATION_JSON)
                .content(reqJson);
        // when
        this.mockMvc
                // then
                .perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.balance").value(betRequestDTO.getBalance()));
    }
}
