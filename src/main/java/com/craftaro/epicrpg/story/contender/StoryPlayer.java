package com.craftaro.epicrpg.story.contender;

import java.util.UUID;

public class StoryPlayer extends StoryContender {
    private StoryParty party;
    private boolean inDialogCreation = false;
    private boolean silent;

    public StoryPlayer(UUID uniqueId) {
        super(uniqueId);
    }

    public boolean isInDialogCreation() {
        return this.inDialogCreation;
    }

    public void setInDialogCreation(boolean inDialogCreation) {
        this.inDialogCreation = inDialogCreation;
    }

    public StoryParty getParty() {
        return this.party;
    }

    public void setParty(StoryParty party) {
        this.party = party;
    }

    public boolean isSilent() {
        return this.silent;
    }

    public void setSilent(boolean silent) {
        this.silent = silent;
    }
}
