package com.case_opener_game.case_opener.service.utils.mapping;

import com.case_opener_game.case_opener.dto.bet.BetRequestDTO;
import com.case_opener_game.case_opener.dto.bet.BetResponseDTO;
import com.case_opener_game.case_opener.dto.bet.CalculatedRoundDTO;
import com.case_opener_game.case_opener.entity.Round;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoundMapper {

    @Mapping(source = "amount", target = "amount")
    @Mapping(source = "multiplier", target = "multiplier")
    @Mapping(source = "payout", target = "payout")
    Round toEntity(CalculatedRoundDTO calculatedRoundDTO);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "amount", target = "amount")
    @Mapping(source = "multiplier", target = "multiplier")
    @Mapping(source = "payout", target = "payout")
    BetResponseDTO toDto(Round round);
}
