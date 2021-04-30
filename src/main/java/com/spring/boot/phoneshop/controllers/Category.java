package com.spring.boot.phoneshop.controllers;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public enum Category {
    COMPUTER("Computer", "comp-plp"),
    NOTEBOOK("Notebook", "note-plp"),
    PHONE("Phone", "phone-plp");

    String name;
    String page;
}
