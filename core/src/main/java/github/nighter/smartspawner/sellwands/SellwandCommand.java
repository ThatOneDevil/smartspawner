package github.nighter.smartspawner.sellwands;

import github.nighter.smartspawner.SmartSpawner;
import io.papermc.paper.command.brigadier.BasicCommand;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class SellwandCommand implements BasicCommand {

    @Override
    public void execute(CommandSourceStack commandSourceStack, String[] args) {
        Player player = (Player) commandSourceStack.getExecutor();

        if (args.length == 0) {
            player.sendMessage("Usage: /sellwand <uses/infinite> <multiplier>");
            return;
        }

        if (args.length < 2) {
            player.sendMessage("You must provide both uses (or 'infinite') and a multiplier.");
            return;
        }


        if (!player.isOp()) {
            player.sendMessage("You do not have permission to use this command.");
            return;
        }

        try {
            int uses;
            if (args[0].equalsIgnoreCase("infinite")) {
                uses = -1;
            } else {
                uses = Integer.parseInt(args[0]);
            }

            float multiplier = Float.parseFloat(args[1]);

            SellwandManager sellwandManager = new SellwandManager(new ItemStack(Material.BLAZE_ROD));

            player.getInventory().addItem(sellwandManager.getSellwand(uses, multiplier, 0L, 0.0));

            String usesText = uses == -1 ? "infinite" : String.valueOf(uses);
            player.sendMessage("Sellwand updated with " + usesText + " uses and a multiplier of " + multiplier);
        } catch (NumberFormatException e) {
            player.sendMessage("Invalid number format. Please provide valid integers for uses (or 'infinite') and a float for multiplier.");
        }

    }
}
