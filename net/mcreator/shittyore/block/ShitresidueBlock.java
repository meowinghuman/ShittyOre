/*    */ package net.mcreator.shittyore.block;
/*    */ 
/*    */ import java.util.Collections;
/*    */ import java.util.List;
/*    */ import net.mcreator.shittyore.ShittyOreModElements;
/*    */ import net.mcreator.shittyore.ShittyOreModElements.ModElement.Tag;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.BlockState;
/*    */ import net.minecraft.block.SoundType;
/*    */ import net.minecraft.block.material.Material;
/*    */ import net.minecraft.client.util.ITooltipFlag;
/*    */ import net.minecraft.item.BlockItem;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemGroup;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.IItemProvider;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import net.minecraft.util.text.StringTextComponent;
/*    */ import net.minecraft.world.IBlockReader;
/*    */ import net.minecraft.world.storage.loot.LootContext;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import net.minecraftforge.common.ToolType;
/*    */ import net.minecraftforge.registries.ObjectHolder;
/*    */ 
/*    */ @Tag
/*    */ public class ShitresidueBlock
/*    */   extends ShittyOreModElements.ModElement
/*    */ {
/*    */   @ObjectHolder("shitty_ore:shitresidue")
/* 31 */   public static final Block block = null;
/*    */   public ShitresidueBlock(ShittyOreModElements instance) {
/* 33 */     super(instance, 27);
/*    */   }
/*    */ 
/*    */   
/*    */   public void initElements() {
/* 38 */     this.elements.blocks.add(() -> new CustomBlock());
/* 39 */     this.elements.items
/* 40 */       .add(() -> (Item)(new BlockItem(block, (new Item.Properties()).func_200916_a(ItemGroup.field_78030_b))).setRegistryName(block.getRegistryName()));
/*    */   }
/*    */   
/*    */   public static class CustomBlock extends Block { public CustomBlock() {
/* 44 */       super(Block.Properties.func_200945_a(Material.field_151578_c).func_200947_a(SoundType.field_185849_b).func_200948_a(1.0F, 1.0F).func_200951_a(0).harvestLevel(1)
/* 45 */           .harvestTool(ToolType.SHOVEL));
/* 46 */       setRegistryName("shitresidue");
/*    */     }
/*    */ 
/*    */     
/*    */     @OnlyIn(Dist.CLIENT)
/*    */     public void func_190948_a(ItemStack itemstack, IBlockReader world, List<ITextComponent> list, ITooltipFlag flag) {
/* 52 */       super.func_190948_a(itemstack, world, list, flag);
/* 53 */       list.add(new StringTextComponent("ancient shit"));
/*    */     }
/*    */ 
/*    */     
/*    */     public List<ItemStack> func_220076_a(BlockState state, LootContext.Builder builder) {
/* 58 */       List<ItemStack> dropsOriginal = super.func_220076_a(state, builder);
/* 59 */       if (!dropsOriginal.isEmpty())
/* 60 */         return dropsOriginal; 
/* 61 */       return Collections.singletonList(new ItemStack((IItemProvider)this, 1));
/*    */     } }
/*    */ 
/*    */ }


/* Location:              /home/somebody/Downloads/ShittyOre.1.15.2.jar!/net/mcreator/shittyore/block/ShitresidueBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */