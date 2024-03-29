package com.craftaro.epicrpg.story.contender;

import com.craftaro.epicrpg.EpicRPG;
import com.craftaro.epicrpg.story.quest.ActiveQuest;
import com.craftaro.epicrpg.story.quest.Quest;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class StoryParty extends StoryContender {

    private final Map<UUID, MemberType> players = new HashMap<>();

    public StoryParty(StoryPlayer player) {
        this(UUID.randomUUID(), player);
    }

    public StoryParty(UUID uniqueId, StoryPlayer player) {
        super(uniqueId);
        this.players.put(player.getUniqueId(), MemberType.LEADER);
    }

    public void addPlayer(StoryPlayer player) {
        this.players.put(player.getUniqueId(), MemberType.MEMBER);
        player.setParty(this);
    }

    public void removePlayer(StoryPlayer player) {
        this.players.remove(player.getUniqueId());
    }

    public boolean isLeader(StoryPlayer storyPlayer) {
        return this.players.get(storyPlayer.getUniqueId()) == MemberType.LEADER;
    }

    public boolean isMember(StoryPlayer storyPlayer) {
        return this.players.containsKey(storyPlayer.getUniqueId());
    }

    public void disband() {
        EpicRPG plugin = JavaPlugin.getPlugin(EpicRPG.class);
        for (UUID uniqueId : this.players.keySet()) {
            StoryPlayer player = plugin.getContendentManager().getPlayer(uniqueId);
            player.setParty(null);
        }
        this.players.clear();
    }

    @Override
    public void completeQuest(Quest quest) {
        EpicRPG plugin = JavaPlugin.getPlugin(EpicRPG.class);
        ActiveQuest active = getActiveQuest(quest);
        for (UUID uniqueId : this.players.keySet()) {
            StoryPlayer player = plugin.getContendentManager().getPlayer(uniqueId);
            player.addCompletedQuests(getCompletedQuests());
        }
        this.activeQuests.remove(active);
    }

    public void swapQuest(Quest quest) {
        this.activeQuests.clear();
        addActiveQuest(quest);
    }

    public void swapQuest(ActiveQuest quest) {
        this.activeQuests.clear();
        addActiveQuest(quest);
    }
}
