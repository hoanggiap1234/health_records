package com.viettel.etc.dto;

import lombok.Data;

@Data
public class AccountHSSKDTO {
    String password;

    String client_id;

    String client_secret;

    String grant_type;

    String username;

    String code;

    public AccountHSSKDTO() {
        this.password = System.getenv("HSSK_PASS");
        this.client_id = "hsskv2";
        this.client_secret = "801ebd51-d6e4-4a3a-a3a1-13ac18fe8f94";
        this.grant_type = "password";
        this.username = "hssk_telecare";
    }
    @Override
    public String toString() {
        return "grant_type=" + grant_type + "&" +
                "client_id=" + client_id + "&" +
                "client_secret=" + client_secret + "&" +
                "username=" + username + "&" +
                "code=" + code + "&" +
                "password=" + password;
    }
}
