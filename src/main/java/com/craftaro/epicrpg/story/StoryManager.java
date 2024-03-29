package com.craftaro.epicrpg.story;

import com.craftaro.epicrpg.story.quest.Objective;
import com.craftaro.epicrpg.story.quest.Quest;
import com.craftaro.epicrpg.story.quest.requirement.AbstractRequirement;
import com.craftaro.epicrpg.story.quest.requirement.Requirement;
import com.craftaro.epicrpg.story.quest.reward.AbstractReward;
import com.craftaro.epicrpg.story.quest.reward.Reward;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class StoryManager {
    private final List<Story> stories = new LinkedList<>();

    public Story addStory(Story story) {
        for (Quest quest : story.getQuests()) {
            quest.setStory(story);
            for (Reward reward : quest.getRewards()) {
                ((AbstractReward) reward).setQuest(quest);
            }
            for (Objective objective : quest.getObjectives()) {
                for (Requirement requirement : objective.getRequirements()) {
                    ((AbstractRequirement) requirement).setObjective(objective);
                }
                objective.setQuest(quest);
            }
        }
        this.stories.add(story);
        return story;
    }

    public void removeStory(Story story) {
        this.stories.remove(story);
    }

    public List<Story> getStories() {
        return Collections.unmodifiableList(this.stories);
    }

    public List<Quest> getQuests() {
        List<Quest> quests = new ArrayList<>();
        for (Story story : this.stories) {
            if (story.isActive()) {
                quests.addAll(story.getQuests());
            }
        }
        return quests;
    }

    public Quest getQuest(UUID activeQuest) {
        for (Quest quest : getQuests()) {
            if (activeQuest.equals(quest.getUniqueId())) {
                return quest;
            }
        }
        return null;
    }

    public Quest getEnabledQuest(UUID activeQuest) {
        for (Quest quest : getQuests()) {
            if (activeQuest.equals(quest.getUniqueId()) && quest.isActive()) {
                return quest;
            }
        }
        return null;
    }
}
