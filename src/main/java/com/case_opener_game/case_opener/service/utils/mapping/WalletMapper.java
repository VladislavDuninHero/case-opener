package com.case_opener_game.case_opener.service.utils.mapping;

import com.case_opener_game.case_opener.dto.wallet.WalletDTO;
import com.case_opener_game.case_opener.entity.Wallet;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface WalletMapper {
    @Mapping(source = "balance", target = "balance")
    Wallet toEntity(WalletDTO walletDTO);

    @Mapping(source = "balance", target = "balance")
    WalletDTO toDto(Wallet wallet);
}
