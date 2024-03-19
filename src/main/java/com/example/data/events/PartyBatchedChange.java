package com.example.data.events;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import net.runelite.client.party.messages.PartyMemberMessage;

import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PartyBatchedChange extends PartyMemberMessage {
    private String playerName;
    private int damageDealt;

    public PartyBatchedChange(String playerName, int damageDealt) {
        this.playerName = playerName;
        this.damageDealt = damageDealt;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getDamageDealt() {
        return damageDealt;
    }


}
