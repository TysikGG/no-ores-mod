package tysik.noores3;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;


public class Noores3 implements ModInitializer {
    @Override
    public void onInitialize() {
        MutableText errorMessage = Text.literal("Этот инструмент не подходит для добычи данной руды!")
            .formatted(Formatting.RED);

        PlayerBlockBreakEvents.BEFORE.register((world, player, pos, state, entity) -> {
            if (state.isOf(Blocks.DIAMOND_ORE) || state.isOf(Blocks.DEEPSLATE_DIAMOND_ORE)) {
                ItemStack mainHandStack = player.getMainHandStack();
                // Наносим урон инструменту
                mainHandStack.damage(248, player, (playerEntity) -> {
                    playerEntity.sendToolBreakStatus(playerEntity.getActiveHand());
                });
                player.sendMessage(errorMessage);
                // return false - запретить ломать блок
            }
            else if (state.isOf(Blocks.REDSTONE_ORE) ||
                    state.isOf(Blocks.LAPIS_ORE) ||
                    state.isOf(Blocks.GOLD_ORE) ||
                    state.isOf(Blocks.DEEPSLATE_GOLD_ORE) ||
                    state.isOf(Blocks.DEEPSLATE_LAPIS_ORE) ||
                    state.isOf(Blocks.DEEPSLATE_REDSTONE_ORE)
            ) {
                ItemStack mainHandStack = player.getMainHandStack();
                mainHandStack.damage(248, player, (playerEntity) -> {
                    playerEntity.sendToolBreakStatus(playerEntity.getActiveHand());
                });
                player.sendMessage(errorMessage);
            } else if (state.isOf(Blocks.IRON_ORE) || state.isOf(Blocks.DEEPSLATE_IRON_ORE)) {
                ItemStack mainHandStack = player.getMainHandStack();
                mainHandStack.damage(60, player, (playerEntity) -> {
                    playerEntity.sendToolBreakStatus(playerEntity.getActiveHand());
                });
                player.sendMessage(errorMessage);
            }
            return true;
        });
    }
}