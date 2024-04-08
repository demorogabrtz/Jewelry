package net.jewelry.api;

import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

import java.util.HashMap;

public class AttributeResolver {
    private static final HashMap<Identifier, EntityAttribute> attributes = new HashMap<>();

    @Deprecated
    /**
     * Called upon initialization of this mod.
     */
    public static void setup() { }

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
