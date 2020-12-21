package xyz.joebaria.Gamer;

import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.tags.CustomItemTagContainer;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.joebaria.Gamer.Events.Events;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Gamer extends JavaPlugin {
    @Override
    public void onEnable() {
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "\n\nStarted!\n\n");
        // register the custom events to this plugin
        getServer().getPluginManager().registerEvents(new Events(), this);

        // Spawner
        ItemStack spawner = new ItemStack(Material.SPAWNER);
        NamespacedKey spawnerKey = new NamespacedKey(this, "spawner_pog");
        ShapedRecipe spawnerRecipe = new ShapedRecipe(spawnerKey, spawner);

        // Recipe Shape
        spawnerRecipe.shape("III", "I*I", "BBB");
        spawnerRecipe.setIngredient('I', Material.IRON_INGOT);
        spawnerRecipe.setIngredient('*', Material.END_CRYSTAL);
        spawnerRecipe.setIngredient('B', Material.IRON_BARS);

        // Add Recipe
        Bukkit.addRecipe(spawnerRecipe);

        // Pokeball
        ItemStack pokeball = new ItemStack(Material.NETHER_STAR);
        NamespacedKey pokeballKey = new NamespacedKey(this, "pokeball");
        ShapedRecipe pokeballRecipe = new ShapedRecipe(pokeballKey, pokeball);

        // Pokeball Recipe
        pokeballRecipe.shape("*E*", "*W*", "*N*");
        pokeballRecipe.setIngredient('*', Material.AIR);
        pokeballRecipe.setIngredient('E', Material.END_CRYSTAL);
        pokeballRecipe.setIngredient('W', Material.WITHER_SKELETON_SKULL);
        pokeballRecipe.setIngredient('N', Material.NETHERITE_BLOCK);

        // Add Pokeball Recipe
        Bukkit.addRecipe(pokeballRecipe);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(cmd.getName().equalsIgnoreCase("destroy")) {
            Player target = Bukkit.getServer().getPlayer(args[0]);
            target.getWorld().generateTree(target.getLocation(), TreeType.DARK_OAK);
            target.setHealth(0.5);
            return true;
        }
        if(cmd.getName().equalsIgnoreCase("nick")) {
            Player target = (Player) sender;
            target.setDisplayName(args[0]);
            target.setPlayerListName(args[0]);
        }
        return false;
    }

    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage(ChatColor.RED + "\n\nStopped!\n\n");
    }
}
