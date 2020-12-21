package xyz.joebaria.Gamer.Events;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Collection;
import java.util.Random;

public class Events implements Listener {

    @EventHandler
    public void NewSlimes(EntityDeathEvent event) {
        // check if silverfish
        if (event.getEntity().getType() == EntityType.SILVERFISH) {
            // clear old drops
            event.getDrops().clear();

            // generate a random amount of slime balls
            Random rand = new Random();
            int x = rand.nextInt(16);

            // create new drops
            ItemStack stack = new ItemStack(Material.SLIME_BALL, x);
            event.getEntity().getWorld().dropItemNaturally(event.getEntity().getLocation(), stack);
        }
    }

    @EventHandler
    public void NewSquid(EntityDeathEvent event) {
        if (event.getEntity().getType() == EntityType.SQUID) {
            // squids also drop fish now
            ItemStack stack = new ItemStack(Material.SALMON);
            event.getEntity().getWorld().dropItemNaturally(event.getEntity().getLocation(), stack);
        }
    }

    // Snowmen are immortal cause christmas
    @EventHandler
    public void Snowmen(EntitySpawnEvent event) {
        if(event.getEntityType() == EntityType.SNOWMAN) {
            Entity target = event.getEntity();
            target.setGlowing(true);
        }
        if(event.getEntityType() == EntityType.IRON_GOLEM) {
            Entity target = event.getEntity();
            target.setGlowing(true);
        }
    }

    // we gotta fix this but do tp bow first and try this after
    @EventHandler
    public void Pokeball(PlayerInteractEvent event) {
        Player target = event.getPlayer();
        if(target.getInventory().getItemInMainHand().getType() == Material.NETHER_STAR) {
            // get a list of entities from where the player is looking
            Collection<Entity> entities = target.getNearbyEntities(2.0, 2.0, 2.0);

            // get the first entity in the collection
            Entity first_entity = (Entity) entities.toArray()[0];
            EntityType selected = first_entity.getType();
            // despawn
            first_entity.remove();

            // set the item in the main hand of the player to be the selected entity egg
            ItemStack spawn_egg = new ItemStack(getSpawnEgg(selected));
            target.getInventory().setItemInMainHand(spawn_egg);
        }
    }

    // Gamer Stick update
    @EventHandler
    public void GamerStick(PlayerInteractEvent event) {
        Player target = event.getPlayer();
        if(target.getInventory().getItemInMainHand().getType() == Material.STICK && target.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals("gamer stick")) {
            Block block = target.getTargetBlock(null, 5);
            if(block.getType() == Material.BEDROCK) {
                block.setType(Material.AIR);
                ItemStack nothing = new ItemStack(Material.AIR);
                target.getInventory().setItemInMainHand(nothing);
            }
        }
    }

    public Material getSpawnEgg(EntityType entity) {
        Material egg = Material.PIG_SPAWN_EGG;

        switch(entity) {
            case CHICKEN:
                egg = Material.CHICKEN_SPAWN_EGG;
                break;
            case BAT:
                egg = Material.BAT_SPAWN_EGG;
                break;
            case CAT:
                egg = Material.CAT_SPAWN_EGG;
                break;
            case DONKEY:
                egg = Material.DONKEY_SPAWN_EGG;
                break;
            case BEE:
                egg = Material.BEE_SPAWN_EGG;
                break;
            case BLAZE:
                egg = Material.BLAZE_SPAWN_EGG;
                break;
            case CAVE_SPIDER:
                egg = Material.CAVE_SPIDER_SPAWN_EGG;
                break;
            case COD:
                egg = Material.COD_SPAWN_EGG;
                break;
            case COW:
                egg = Material.COW_SPAWN_EGG;
                break;
            case CREEPER:
                egg = Material.CREEPER_SPAWN_EGG;
                break;
            case DOLPHIN:
                egg = Material.DOLPHIN_SPAWN_EGG;
                break;
            case DROWNED:
                egg = Material.DROWNED_SPAWN_EGG;
                break;
            case ELDER_GUARDIAN:
                egg = Material.ELDER_GUARDIAN_SPAWN_EGG;
                break;
            case ENDERMAN:
                egg = Material.ENDERMAN_SPAWN_EGG;
                break;
            case ENDERMITE:
                egg = Material.ENDERMITE_SPAWN_EGG;
                break;
            case FOX:
                egg = Material.FOX_SPAWN_EGG;
                break;
            case GHAST:
                egg = Material.GHAST_SPAWN_EGG;
                break;
            case GUARDIAN:
                egg = Material.GUARDIAN_SPAWN_EGG;
                break;
            case HOGLIN:
                egg = Material.HOGLIN_SPAWN_EGG;
                break;
            case HORSE:
                egg = Material.HORSE_SPAWN_EGG;
                break;
            case HUSK:
                egg = Material.HUSK_SPAWN_EGG;
                break;
            case LLAMA:
                egg = Material.LLAMA_SPAWN_EGG;
                break;
            case MAGMA_CUBE:
                egg = Material.MAGMA_CUBE_SPAWN_EGG;
                break;
            case MULE:
                egg = Material.MULE_SPAWN_EGG;
                break;
            case MUSHROOM_COW:
                egg = Material.MOOSHROOM_SPAWN_EGG;
                break;
            case OCELOT:
                egg = Material.OCELOT_SPAWN_EGG;
                break;
            case PANDA:
                egg = Material.PANDA_SPAWN_EGG;
                break;
            case PARROT:
                egg = Material.PARROT_SPAWN_EGG;
                break;
            case PHANTOM:
                egg = Material.PHANTOM_SPAWN_EGG;
                break;
            case PIG:
                egg = Material.PIG_SPAWN_EGG;
                break;
            case PIGLIN:
                egg = Material.PIGLIN_SPAWN_EGG;
                break;
            case PIGLIN_BRUTE:
                egg = Material.PIGLIN_BRUTE_SPAWN_EGG;
                break;
            case PILLAGER:
                egg = Material.PILLAGER_SPAWN_EGG;
                break;
            case POLAR_BEAR:
                egg = Material.POLAR_BEAR_SPAWN_EGG;
                break;
            case PUFFERFISH:
                egg = Material.PUFFERFISH_SPAWN_EGG;
                break;
            case RABBIT:
                egg = Material.RABBIT_SPAWN_EGG;
                break;
            case RAVAGER:
                egg = Material.RAVAGER_SPAWN_EGG;
                break;
            case SALMON:
                egg = Material.SALMON_SPAWN_EGG;
                break;
            case SHEEP:
                egg = Material.SHEEP_SPAWN_EGG;
                break;
            case SILVERFISH:
                egg = Material.SILVERFISH_SPAWN_EGG;
                break;
            case SKELETON:
                egg = Material.SKELETON_SPAWN_EGG;
                break;
            case SKELETON_HORSE:
                egg = Material.SKELETON_HORSE_SPAWN_EGG;
                break;
            case SLIME:
                egg = Material.SLIME_SPAWN_EGG;
                break;
            case SPIDER:
                egg = Material.SPIDER_SPAWN_EGG;
                break;
            case SQUID:
                egg = Material.SQUID_SPAWN_EGG;
                break;
            case  STRAY:
                egg = Material.STRAY_SPAWN_EGG;
                break;
            case STRIDER:
                egg = Material.STRIDER_SPAWN_EGG;
                break;
            case TRADER_LLAMA:
                egg = Material.TRADER_LLAMA_SPAWN_EGG;
                break;
            case TROPICAL_FISH:
                egg = Material.TROPICAL_FISH_SPAWN_EGG;
                break;
            case TURTLE:
                egg = Material.TURTLE_SPAWN_EGG;
                break;
            case VEX:
                egg = Material.VEX_SPAWN_EGG;
                break;
            case VILLAGER:
                egg = Material.VILLAGER_SPAWN_EGG;
                break;
            case VINDICATOR:
                egg = Material.VINDICATOR_SPAWN_EGG;
                break;
            case WANDERING_TRADER:
                egg = Material.WANDERING_TRADER_SPAWN_EGG;
                break;
            case WITCH:
                egg = Material.WITCH_SPAWN_EGG;
                break;
            case WITHER_SKELETON:
                egg = Material.WITHER_SKELETON_SPAWN_EGG;
                break;
            case WOLF:
                egg = Material.WOLF_SPAWN_EGG;
                break;
            case ZOGLIN:
                egg = Material.ZOGLIN_SPAWN_EGG;
                break;
            case ZOMBIE:
                egg = Material.ZOMBIE_SPAWN_EGG;
                break;
            case ZOMBIE_HORSE:
                egg = Material.ZOMBIE_HORSE_SPAWN_EGG;
                break;
            case ZOMBIFIED_PIGLIN:
                egg = Material.ZOMBIFIED_PIGLIN_SPAWN_EGG;
                break;
            case ZOMBIE_VILLAGER:
                egg = Material.ZOMBIE_VILLAGER_SPAWN_EGG;
                break;
        }
        return egg;
    }
}
