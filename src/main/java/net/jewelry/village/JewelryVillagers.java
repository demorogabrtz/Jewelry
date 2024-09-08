package net.jewelry.village;

import com.google.common.collect.ImmutableSet;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.fabricmc.fabric.api.object.builder.v1.world.poi.PointOfInterestHelper;
import net.jewelry.JewelryMod;
import net.jewelry.blocks.JewelryBlocks;
import net.jewelry.items.JewelryItems;
import net.jewelry.util.SoundHelper;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradeOffers;
import net.minecraft.village.VillagerProfession;
import net.minecraft.world.poi.PointOfInterestType;

import java.util.LinkedHashMap;
import java.util.List;

public class JewelryVillagers {
    public static final String JEWELER = "jeweler";

    public static PointOfInterestType registerPOI(String name, Block block) {
        return PointOfInterestHelper.register(Identifier.of(JewelryMod.ID, name),
                1, 10, ImmutableSet.copyOf(block.getStateManager().getStates()));
    }

    public static VillagerProfession registerProfession(String name, RegistryKey<PointOfInterestType> workStation) {
        var id = Identifier.of(JewelryMod.ID, name);
        return Registry.register(Registries.VILLAGER_PROFESSION, Identifier.of(JewelryMod.ID, name), new VillagerProfession(
                id.toString(),
                (entry) -> {
                    return entry.matchesKey(workStation);
                },
                (entry) -> {
                    return entry.matchesKey(workStation);
                },
                ImmutableSet.of(),
                ImmutableSet.of(),
                SoundHelper.JEWELRY_WORKBENCH)
        );
    }

//    private static class Offer {
//        int level;
//        ItemStack input;
//        ItemStack output;
//        int maxUses;
//        int experience;
//        float priceMultiplier;
//
//        public Offer(int level, ItemStack input, ItemStack output, int maxUses, int experience, float priceMultiplier) {
//            this.level = level;
//            this.input = input;
//            this.output = output;
//            this.maxUses = maxUses;
//            this.experience = experience;
//            this.priceMultiplier = priceMultiplier;
//        }
//
//        public static Offer buy(int level, ItemStack item, int price, int maxUses, int experience, float priceMultiplier) {
//            return new Offer(level, item, new ItemStack(Items.EMERALD, price), maxUses, experience, priceMultiplier);
//        }
//
//        public static Offer sell(int level, ItemStack item, int price, int maxUses, int experience, float priceMultiplier) {
//            return new Offer(level, new ItemStack(Items.EMERALD, price), item, maxUses, experience, priceMultiplier);
//        }
//    }

    public static void register() {
        var poi = registerPOI(JEWELER, JewelryBlocks.JEWELERS_KIT.block());
        var profession = registerProfession(
                JEWELER,
                RegistryKey.of(Registries.POINT_OF_INTEREST_TYPE.getKey(), Identifier.of(JewelryMod.ID, JEWELER)));

//        List<Offer> wizardMerchantOffers = List.of(
//                Offer.buy(1, new ItemStack(Items.COPPER_INGOT, 8), 2, 8, 2, 0.1f),
//                Offer.buy(1, new ItemStack(Items.STRING, 7), 1, 6, 2, 0.1f),
//                Offer.sell(1, JewelryItems.copper_ring.item().getDefaultStack(), 4, 12, 3, 0.2f),
//
//
//                Offer.buy(2, new ItemStack(Items.GOLD_INGOT, 7), 2, 8, 5, 0.1f),
//                Offer.sell(2, JewelryItems.iron_ring.item().getDefaultStack(), 4, 4, 4, 0.2f),
//                Offer.sell(2, JewelryItems.gold_ring.item().getDefaultStack(), 18, 4, 5, 0.2f),
//
//                // Mediocre necklaces and material buys
//
//                Offer.buy(3, new ItemStack(Items.DIAMOND, 1), 4, 12, 10, 0.05f),
//                Offer.sell(3, JewelryItems.emerald_necklace.item().getDefaultStack(), 20, 8, 10, 0.2f),
//                Offer.sell(3, JewelryItems.diamond_necklace.item().getDefaultStack(), 25, 8, 10, 0.2f),
//
//                // T1 Rings
//
//                Offer.sell(4, JewelryItems.ruby_ring.item().getDefaultStack(), 35, 5, 13, 0.1f),
//                Offer.sell(4, JewelryItems.topaz_ring.item().getDefaultStack(), 35, 5, 13, 0.1f),
//                Offer.sell(4, JewelryItems.citrine_ring.item().getDefaultStack(), 35, 5, 13, 0.1f),
//                Offer.sell(4, JewelryItems.jade_ring.item().getDefaultStack(), 35, 5, 13, 0.1f),
//                Offer.sell(4, JewelryItems.sapphire_ring.item().getDefaultStack(), 35, 5, 13, 0.1f),
//                Offer.sell(4, JewelryItems.tanzanite_ring.item().getDefaultStack(), 35, 5, 13, 0.1f),
//
//                // T1 Necklaces
//
//                Offer.sell(5, JewelryItems.ruby_necklace.item().getDefaultStack(), 45, 3, 13, 0.1f),
//                Offer.sell(5, JewelryItems.topaz_necklace.item().getDefaultStack(), 45, 3, 13, 0.1f),
//                Offer.sell(5, JewelryItems.citrine_necklace.item().getDefaultStack(), 45, 3, 13, 0.1f),
//                Offer.sell(5, JewelryItems.jade_necklace.item().getDefaultStack(), 45, 3, 13, 0.1f),
//                Offer.sell(5, JewelryItems.sapphire_necklace.item().getDefaultStack(), 45, 3, 13, 0.1f),
//                Offer.sell(5, JewelryItems.tanzanite_necklace.item().getDefaultStack(), 45, 3, 13, 0.1f)
//        );

        LinkedHashMap<Integer, List<TradeOffers.Factory>> trades = new LinkedHashMap<>();

        trades.put(1, List.of(
                new TradeOffers.BuyItemFactory(Items.COPPER_INGOT, 8, 8, 3, 2),
                new TradeOffers.BuyItemFactory(Items.STRING, 7, 6, 3, 2),
                new TradeOffers.SellItemFactory(JewelryItems.copper_ring.item(), 4, 1, 12, 4)
        ));
        trades.put(2, List.of(
                new TradeOffers.BuyItemFactory(Items.GOLD_INGOT, 7, 8, 2, 8),
                new TradeOffers.SellItemFactory(JewelryItems.iron_ring.item(), 4, 1, 6, 5),
                new TradeOffers.SellItemFactory(JewelryItems.gold_ring.item(), 18, 1, 6, 5)
        ));
        trades.put(3, List.of(
                new TradeOffers.BuyItemFactory(Items.DIAMOND, 1, 12, 10, 10),
                new TradeOffers.SellItemFactory(JewelryItems.emerald_necklace.item(), 20, 1, 12, 10),
                new TradeOffers.SellItemFactory(JewelryItems.diamond_necklace.item(), 25, 1, 12, 10)
        ));
        trades.put(4, List.of(
                new TradeOffers.SellItemFactory(JewelryItems.ruby_ring.item(), 35, 1, 5, 15),
                new TradeOffers.SellItemFactory(JewelryItems.topaz_ring.item(), 35, 1, 5, 15),
                new TradeOffers.SellItemFactory(JewelryItems.citrine_ring.item(), 35, 1, 5, 15),
                new TradeOffers.SellItemFactory(JewelryItems.jade_ring.item(), 35, 1, 5, 15),
                new TradeOffers.SellItemFactory(JewelryItems.sapphire_ring.item(), 35, 1, 5, 13),
                new TradeOffers.SellItemFactory(JewelryItems.tanzanite_ring.item(), 35, 1, 5, 13)
        ));
        trades.put(5, List.of(
                new TradeOffers.SellItemFactory(JewelryItems.ruby_necklace.item(), 45, 1, 3, 15),
                new TradeOffers.SellItemFactory(JewelryItems.topaz_necklace.item(), 45, 1, 3, 15),
                new TradeOffers.SellItemFactory(JewelryItems.citrine_necklace.item(), 45, 1, 3, 15),
                new TradeOffers.SellItemFactory(JewelryItems.jade_necklace.item(), 45, 1, 3, 15),
                new TradeOffers.SellItemFactory(JewelryItems.sapphire_necklace.item(), 45, 1, 3, 15),
                new TradeOffers.SellItemFactory(JewelryItems.tanzanite_necklace.item(), 45, 1, 3, 15)
        ));

        for (var entry: trades.entrySet()) {
            TradeOfferHelper.registerVillagerOffers(profession, entry.getKey(), factories -> {
                factories.addAll(entry.getValue());
            });
        }
    }
}
