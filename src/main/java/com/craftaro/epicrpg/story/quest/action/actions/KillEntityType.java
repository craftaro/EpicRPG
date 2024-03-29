package com.craftaro.epicrpg.story.quest.action.actions;

import com.craftaro.core.utils.TextUtils;
import com.craftaro.epicrpg.EpicRPG;
import com.craftaro.epicrpg.data.ActionDataStore;
import com.craftaro.epicrpg.story.quest.Objective;
import com.craftaro.epicrpg.story.quest.action.AbstractAction;
import com.craftaro.epicrpg.story.quest.action.ActiveAction;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;

import java.util.Collections;
import java.util.List;

public class KillEntityType extends AbstractAction {
    public KillEntityType(EpicRPG plugin) {
        super(plugin);
    }

    @Override
    public String getType() {
        return "KILL_ENTITY_TYPE";
    }

    @Override
    public List<String> getDescription(ActionDataStore actionDataStore) {
        KillEntityTypeDataStore dataStore = (KillEntityTypeDataStore) actionDataStore;
        return Collections.singletonList(TextUtils.formatText("&fEntity Type: &6"
                + (dataStore.getEntityType() == null ? "Undefined" : dataStore.getEntityType().name())));
    }

    @Override
    public void onInteractWithEntity(PlayerInteractAtEntityEvent event, ActiveAction activeAction) {
        Player player = event.getPlayer();
        Entity entity = event.getRightClicked();

        KillEntityTypeDataStore dataStore = (KillEntityTypeDataStore) activeAction.getActionDataStore();

        if (dataStore.isBeingSetup(event.getPlayer())) {
            dataStore.setEntityType(entity.getType());
            dataStore.finishSetup(this.plugin, player, activeAction);
        }
    }

    @Override
    public void onEntityKill(EntityDeathEvent event, ActiveAction activeAction) {
        KillEntityTypeDataStore dataStore = (KillEntityTypeDataStore) activeAction.getActionDataStore();
        Player player = event.getEntity().getKiller();
        if (player == null) {
            return;
        }

        if (dataStore.getEntityType() == event.getEntityType()) {
            performAction(activeAction, 1, event.getEntity().getKiller());
        }
    }

    @Override
    public ActiveAction setup(Player player, Objective objective) {
        player.sendMessage("Right click the entity type you want to assign to this action.");
        KillEntityTypeDataStore dataStore = new KillEntityTypeDataStore(objective);
        dataStore.startSetup(player);
        return new ActiveAction(this, dataStore);
    }

    public static class KillEntityTypeDataStore extends ActionDataStore {
        private EntityType entityType;

        public KillEntityTypeDataStore(Objective objective) {
            super(objective);
        }

        public EntityType getEntityType() {
            return this.entityType;
        }

        public void setEntityType(EntityType entityType) {
            this.entityType = entityType;
        }
    }
}
