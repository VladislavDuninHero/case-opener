package com.case_opener_game.case_opener.enums;

import lombok.Getter;

@Getter
public enum Game {
    CASEOPENER("CaseOpener");

    private String name;

    private Game(String name) {
        this.name = name;
    }
}
