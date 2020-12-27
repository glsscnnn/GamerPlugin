package xyz.joebaria.Gamer.Events;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.BoundingBox;

import java.util.Collection;
import java.util.Objects;
import java.util.Random;

public class DreamLuck implements Listener {
    @EventHandler
    public void WitherSkull(EntityDeathEvent event) {
        if(event.getEntity().getType() == EntityType.WITHER_SKELETON) {
            Random rand = new Random();
            int x = rand.nextInt(100);
            // inc. the drop rate for wither skulls
            if (x > 90) {
                ItemStack wither_skull = new ItemStack(Material.WITHER_SKELETON_SKULL);
                event.getEntity().getWorld().dropItemNaturally(event.getEntity().getLocation(), wither_skull);
            }
        }
    }

    @EventHandler
    public void EndPearls(EntityDeathEvent event) {
        if(event.getEntity().getType() == EntityType.ENDERMAN) {
            Random rand = new Random();
            int x = rand.nextInt(100);
            // inc. the drop rate for e-pearls
            if (x > 80) {
                ItemStack e_pearl = new ItemStack(Material.ENDER_PEARL);
                event.getEntity().getWorld().dropItemNaturally(event.getEntity().getLocation(), e_pearl);
            }
        }
    }

    @EventHandler
    public void MaxBookTrade(PlayerDropItemEvent event) {

        Collection<Entity> entities = event.getItemDrop().getNearbyEntities(2.0, 2.0, 2.0);
        Entity first_entity = (Entity) entities.toArray()[0];
        EntityType selected = first_entity.getType();

        if (event.getItemDrop().getItemStack().getType() == Material.NETHERITE_INGOT && selected == EntityType.PIGLIN) {
            // delete the item
            event.getItemDrop().remove();
            ItemStack book = getRandomMaxBook();
            // drop the item
            Objects.requireNonNull(event.getItemDrop().getLocation().getWorld()).dropItem(event.getItemDrop().getLocation(), book);
        }
    }

    public ItemStack getRandomMaxBook() {
        Random rand = new Random();
        int x = rand.nextInt(16);

        ItemStack drop = new ItemStack(Material.BOOK);

        switch (x) {
            case 0:
                drop.getItemMeta().addEnchant(Enchantment.SWEEPING_EDGE, 3, false);
                break;
            case 1:
                drop.getItemMeta().addEnchant(Enchantment.DAMAGE_ALL, 5, false);
                break;
            case 3:
                drop.getItemMeta().addEnchant(Enchantment.MENDING, 1, true);
                break;
            case 4:
                drop.getItemMeta().addEnchant(Enchantment.DAMAGE_ARTHROPODS, 12, true);
                break;
            case 5:
                drop.getItemMeta().addEnchant(Enchantment.DAMAGE_UNDEAD, 5, true);
                break;
            case 6:
                drop.getItemMeta().addEnchant(Enchantment.ARROW_DAMAGE, 5, true);
                break;
            case 7:
                drop.getItemMeta().addEnchant(Enchantment.RIPTIDE, 1, true);
                break;
            case 8:
                drop.getItemMeta().addEnchant(Enchantment.THORNS, 5, true);
                break;
            case 9:
                drop.getItemMeta().addEnchant(Enchantment.SILK_TOUCH, 1, true);
                break;
            case 10:
                drop.getItemMeta().addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4, true);
                break;
            case 11:
                drop.getItemMeta().addEnchant(Enchantment.PROTECTION_FIRE, 4, true);
                break;
            case 12:
                drop.getItemMeta().addEnchant(Enchantment.PROTECTION_EXPLOSIONS, 4, true);
            case 13:
                drop.getItemMeta().addEnchant(Enchantment.PROTECTION_PROJECTILE, 4, true);
                break;
            case 14:
                drop.getItemMeta().addEnchant(Enchantment.DIG_SPEED, 6, true);
                break;
            case 15:
                drop.getItemMeta().addEnchant(Enchantment.ARROW_DAMAGE, 6, true);
                break;
        }

        return drop;
    }
}
