package com.craftaro.epicrpg.storage.json.adapters;

import com.craftaro.epicrpg.story.quest.reward.Reward;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

public class RewardAdapter implements JsonSerializer<Reward> {
    @Override
    public JsonElement serialize(Reward reward, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject result = new JsonObject();
        result.add("class", new JsonPrimitive(reward.getClass().getCanonicalName()));
        result.add("properties", context.serialize(reward, reward.getClass()));

        return result;
    }
}
