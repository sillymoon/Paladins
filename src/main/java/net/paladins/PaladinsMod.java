package net.paladins;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.paladins.block.PaladinBlocks;
import net.paladins.config.Default;
import net.paladins.config.WorldGenConfig;
import net.paladins.effect.Effects;
import net.paladins.item.Group;
import net.paladins.item.PaladinBooks;
import net.paladins.item.armor.Armors;
import net.paladins.item.Weapons;
import net.paladins.villager.PaladinVillagers;
import net.spell_engine.api.item.ItemConfig;
import net.spell_engine.api.loot.LootConfig;
import net.tinyconfig.ConfigManager;

public class PaladinsMod {
    public static final String ID = "paladins";

    public static ConfigManager<ItemConfig> itemConfig = new ConfigManager<ItemConfig>
            ("items_v2", Default.itemConfig)
            .builder()
            .setDirectory(ID)
            .sanitize(true)
            .build();
    public static ConfigManager<LootConfig> lootConfig = new ConfigManager<LootConfig>
            ("loot_v2", Default.lootConfig)
            .builder()
            .setDirectory(ID)
            .sanitize(true)
            .constrain(LootConfig::constrainValues)
            .build();
    public static ConfigManager<WorldGenConfig> worldGenConfig = new ConfigManager<WorldGenConfig>
            ("world_gen", Default.worldGenConfig)
            .builder()
            .setDirectory(ID)
            .sanitize(true)
            .build();

    public static void init() {
        lootConfig.refresh();
        itemConfig.refresh();
        PaladinBooks.register();
        Registry.register(Registries.ITEM_GROUP, Group.KEY, Group.PALADINS);
        Weapons.register(itemConfig.value.weapons);
        Armors.register(itemConfig.value.armor_sets);
        itemConfig.save();
        worldGenConfig.refresh();
        Effects.register();
        PaladinBlocks.register();
        PaladinVillagers.register();
    }
}