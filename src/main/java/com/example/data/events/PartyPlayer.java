package com.example.data.events;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.runelite.client.party.PartyMember;

@Data
@EqualsAndHashCode
public class PartyPlayer {
    private transient PartyMember member;
    private String username;
    private int damageDealt;

    public PartyPlayer(final PartyMember member) {
        this.member = member;
        this.username = "";
        this.damageDealt = 0;
    }

}
