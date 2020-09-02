/*    */ package net.mcreator.shittyore.item;
/*    */ 
/*    */ import java.util.List;
/*    */ import net.mcreator.shittyore.ShittyOreModElements;
/*    */ import net.mcreator.shittyore.ShittyOreModElements.ModElement.Tag;
/*    */ import net.minecraft.block.BlockState;
/*    */ import net.minecraft.client.util.ITooltipFlag;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemGroup;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import net.minecraft.util.text.StringTextComponent;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import net.minecraftforge.registries.ObjectHolder;
/*    */ 
/*    */ 
/*    */ @Tag
/*    */ public class ShitItem
/*    */   extends ShittyOreModElements.ModElement
/*    */ {
/*    */   @ObjectHolder("shitty_ore:shit")
/* 24 */   public static final Item block = null;
/*    */   public ShitItem(ShittyOreModElements instance) {
/* 26 */     super(instance, 2);
/*    */   }
/*    */ 
/*    */   
/*    */   public void initElements() {
/* 31 */     this.elements.items.add(() -> new ItemCustom());
/*    */   }
/*    */   
/*    */   public static class ItemCustom extends Item { public ItemCustom() {
/* 35 */       super((new Item.Properties()).func_200916_a(ItemGroup.field_78035_l).func_200917_a(64));
/* 36 */       setRegistryName("shit");
/*    */     }
/*    */ 
/*    */     
/*    */     public int func_77619_b() {
/* 41 */       return 0;
/*    */     }
/*    */ 
/*    */     
/*    */     public int func_77626_a(ItemStack itemstack) {
/* 46 */       return 0;
/*    */     }
/*    */ 
/*    */     
/*    */     public float func_150893_a(ItemStack par1ItemStack, BlockState par2Block) {
/* 51 */       return 1.0F;
/*    */     }
/*    */ 
/*    */     
/*    */     @OnlyIn(Dist.CLIENT)
/*    */     public boolean func_77636_d(ItemStack itemstack) {
/* 57 */       return true;
/*    */     }
/*    */ 
/*    */     
/*    */     public void func_77624_a(ItemStack itemstack, World world, List<ITextComponent> list, ITooltipFlag flag) {
/* 62 */       super.func_77624_a(itemstack, world, list, flag);
/* 63 */       list.add(new StringTextComponent("\"shit\""));
/*    */     } }
/*    */ 
/*    */ }


/* Location:              /home/somebody/Downloads/ShittyOre.1.15.2.jar!/net/mcreator/shittyore/item/ShitItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */