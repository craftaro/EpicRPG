package com.craftaro.epicrpg.story.quest.action;

import com.craftaro.epicrpg.data.ActionDataStore;
import com.craftaro.epicrpg.story.quest.Objective;
import io.lumine.mythic.bukkit.utils.events.extra.ArmorEquipEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

import java.util.List;

public interface Action {
    String getType();

    List<String> getDescription(ActionDataStore actionDataStore);

    void onInteract(PlayerInteractEvent event, ActiveAction activeAction);

    void onInteractWithEntity(PlayerInteractAtEntityEvent event, ActiveAction activeAction);

    void onPickup(PlayerPickupItemEvent event, ActiveAction activeAction);

    void onDrop(PlayerDropItemEvent event, ActiveAction activeAction);

    void onEquip(ArmorEquipEvent event, ActiveAction activeAction);

    void onEntityKill(EntityDeathEvent event, ActiveAction activeAction);

    void onBlockBreak(BlockBreakEvent event, ActiveAction action);

    void moveTick(Player player, ActiveAction action);

    ActiveAction setup(Player player, Objective objective);
}
