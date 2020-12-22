package xyz.joebaria.Gamer;

import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.*;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.joebaria.Gamer.Events.Events;
import xyz.joebaria.Gamer.Cache.Cache;

public class Gamer extends JavaPlugin {

    // so scuffed
    public Cache local_cache = new Cache();

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

        // Zombie Flesh Recipe
        ItemStack leather = new ItemStack(Material.LEATHER);
        NamespacedKey leatherKey = new NamespacedKey(this, "zombie_leather");
        FurnaceRecipe leatherRecipe = new FurnaceRecipe(leatherKey, leather, Material.ROTTEN_FLESH, 0.7f, 24);

        Bukkit.addRecipe(leatherRecipe);
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
            return true;
        }
        if(cmd.getName().equalsIgnoreCase("trade")) {
            Player target = Bukkit.getServer().getPlayer(args[0]);
            Player user = (Player) sender;

            // safety check
            if (local_cache.Items.containsKey(target.getName())){
                user.sendMessage(ChatColor.RED + "fucking idiot wait bitch pussy bitch");
                return false;
            }

            // get trade
            ItemStack target_item = target.getInventory().getItemInMainHand(); // TODO add catch
            ItemStack user_item = user.getInventory().getItemInMainHand();

            target.sendMessage("type /confirm to confirm the trade: " + user_item.getType().toString() + " for " + target_item.getType().toString());

            // cache items and store request
            local_cache.Items.put(target.getName(), new ItemStack[]{user_item, target_item});
            local_cache.Request.put(target.getName(), new Player[]{user, target});

            return true;
        }
        if(cmd.getName().equalsIgnoreCase("confirm")) {
            Player target = (Player) sender;
            if(local_cache.Request.containsKey(target.getName())) {
                // get items and players from the cache
                Player[] players = local_cache.Request.get(target.getName());
                ItemStack[] items = local_cache.Items.get(target.getName());

                // players
                Player from = players[0];
                Player to = players[1];

                // items being sent
                ItemStack from_item = items[0];
                ItemStack to_item = items[1];

                // swap items
                to.getInventory().setItemInMainHand(from_item);
                from.getInventory().setItemInMainHand(to_item);

                return true;
            }
            target.sendMessage(ChatColor.RED + "fucking idiot bitch");
            return false;
        }
        return false;
    }

    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage(ChatColor.RED + "\n\nStopped!\n\n");
    }
}
