/*    */ package net.mcreator.shittyore.item;
/*    */ 
/*    */ import net.mcreator.shittyore.ShittyOreModElements;
/*    */ import net.mcreator.shittyore.ShittyOreModElements.ModElement.Tag;
/*    */ import net.minecraft.block.BlockState;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemGroup;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import net.minecraftforge.registries.ObjectHolder;
/*    */ 
/*    */ @Tag
/*    */ public class ShitnuggetItem
/*    */   extends ShittyOreModElements.ModElement
/*    */ {
/*    */   @ObjectHolder("shitty_ore:shitnugget")
/* 18 */   public static final Item block = null;
/*    */   public ShitnuggetItem(ShittyOreModElements instance) {
/* 20 */     super(instance, 21);
/*    */   }
/*    */ 
/*    */   
/*    */   public void initElements() {
/* 25 */     this.elements.items.add(() -> new ItemCustom());
/*    */   }
/*    */   
/*    */   public static class ItemCustom extends Item { public ItemCustom() {
/* 29 */       super((new Item.Properties()).func_200916_a(ItemGroup.field_78035_l).func_200917_a(64));
/* 30 */       setRegistryName("shitnugget");
/*    */     }
/*    */ 
/*    */     
/*    */     public int func_77619_b() {
/* 35 */       return 0;
/*    */     }
/*    */ 
/*    */     
/*    */     public int func_77626_a(ItemStack itemstack) {
/* 40 */       return 0;
/*    */     }
/*    */ 
/*    */     
/*    */     public float func_150893_a(ItemStack par1ItemStack, BlockState par2Block) {
/* 45 */       return 1.0F;
/*    */     }
/*    */ 
/*    */     
/*    */     @OnlyIn(Dist.CLIENT)
/*    */     public boolean func_77636_d(ItemStack itemstack) {
/* 51 */       return true;
/*    */     } }
/*    */ 
/*    */ }


/* Location:              /home/somebody/Downloads/ShittyOre.1.15.2.jar!/net/mcreator/shittyore/item/ShitnuggetItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */