/*    */ package net.mcreator.shittyore.item;
/*    */ 
/*    */ import net.mcreator.shittyore.world.dimension.ShitdimensionDimension;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemGroup;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.item.ItemUseContext;
/*    */ import net.minecraft.util.ActionResultType;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.registries.ObjectHolder;
/*    */ 
/*    */ public class ShitdimensionItem
/*    */   extends Item
/*    */ {
/*    */   @ObjectHolder("shitty_ore:shitdimension")
/* 19 */   public static final Item block = null;
/*    */   public ShitdimensionItem() {
/* 21 */     super((new Item.Properties()).func_200916_a(ItemGroup.field_78040_i).func_200918_c(64));
/*    */   }
/*    */ 
/*    */   
/*    */   public ActionResultType func_195939_a(ItemUseContext context) {
/* 26 */     PlayerEntity entity = context.func_195999_j();
/* 27 */     BlockPos pos = context.func_195995_a().func_177972_a(context.func_196000_l());
/* 28 */     ItemStack itemstack = context.func_195996_i();
/* 29 */     World world = context.func_195991_k();
/* 30 */     if (!entity.func_175151_a(pos, context.func_196000_l(), itemstack)) {
/* 31 */       return ActionResultType.FAIL;
/*    */     }
/* 33 */     int x = pos.func_177958_n();
/* 34 */     int y = pos.func_177956_o();
/* 35 */     int z = pos.func_177952_p();
/* 36 */     if (world.func_175623_d(pos))
/* 37 */       ShitdimensionDimension.portal.portalSpawn(world, pos); 
/* 38 */     itemstack.func_222118_a(1, (LivingEntity)entity, c -> c.func_213334_d(context.func_221531_n()));
/* 39 */     return ActionResultType.SUCCESS;
/*    */   }
/*    */ }


/* Location:              /home/somebody/Downloads/ShittyOre.1.15.2.jar!/net/mcreator/shittyore/item/ShitdimensionItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */