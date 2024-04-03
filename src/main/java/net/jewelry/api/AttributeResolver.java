package net.jewelry.api;

import net.combatroll.api.EntityAttributes_CombatRoll;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.projectile_damage.api.EntityAttributes_ProjectileDamage;

import java.util.HashMap;

public class AttributeResolver {
    private static final HashMap<Identifier, EntityAttribute> attributes = new HashMap<>();

    /**
     * Called upon initialization of this mod.
     */
    public static void setup() {
        if (FabricLoader.getInstance().isModLoaded("projectile_damage")) {
            register(
                EntityAttributes_ProjectileDamage.attributeId,
                EntityAttributes_ProjectileDamage.GENERIC_PROJECTILE_DAMAGE
            );
        }
        if (FabricLoader.getInstance().isModLoaded("combatroll")) {
            register(EntityAttributes_CombatRoll.countId, EntityAttributes_CombatRoll.COUNT);
            register(EntityAttributes_CombatRoll.distanceId, EntityAttributes_CombatRoll.DISTANCE);
            register(EntityAttributes_CombatRoll.rechargeId, EntityAttributes_CombatRoll.RECHARGE);
        }
    }

    public static void register(Identifier id, EntityAttribute attribute) {
        attributes.put(id, attribute);
    }

    public static EntityAttribute get(Identifier id) {
        // Check for custom attribute
        var attribute = attributes.get(id);
        if (attribute == null) {
            attribute = Registries.ATTRIBUTE.get(id);
        }
        return attribute;
    }
}
