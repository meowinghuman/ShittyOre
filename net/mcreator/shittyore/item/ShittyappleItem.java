/*    */ package net.mcreator.shittyore.item;
/*    */ 
/*    */ import java.util.List;
/*    */ import net.mcreator.shittyore.ShittyOreModElements;
/*    */ import net.mcreator.shittyore.ShittyOreModElements.ModElement.Tag;
/*    */ import net.minecraft.client.util.ITooltipFlag;
/*    */ import net.minecraft.item.Food;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemGroup;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.item.UseAction;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import net.minecraft.util.text.StringTextComponent;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import net.minecraftforge.registries.ObjectHolder;
/*    */ 
/*    */ 
/*    */ @Tag
/*    */ public class ShittyappleItem
/*    */   extends ShittyOreModElements.ModElement
/*    */ {
/*    */   @ObjectHolder("shitty_ore:shittyapple")
/* 25 */   public static final Item block = null;
/*    */   public ShittyappleItem(ShittyOreModElements instance) {
/* 27 */     super(instance, 24);
/*    */   }
/*    */ 
/*    */   
/*    */   public void initElements() {
/* 32 */     this.elements.items.add(() -> new FoodItemCustom());
/*    */   }
/*    */   
/*    */   public static class FoodItemCustom extends Item { public FoodItemCustom() {
/* 36 */       super((new Item.Properties()).func_200916_a(ItemGroup.field_78039_h).func_200917_a(64)
/* 37 */           .func_221540_a((new Food.Builder()).func_221456_a(4).func_221454_a(0.3F).func_221455_b().func_221453_d()));
/* 38 */       setRegistryName("shittyapple");
/*    */     }
/*    */ 
/*    */     
/*    */     @OnlyIn(Dist.CLIENT)
/*    */     public boolean func_77636_d(ItemStack itemstack) {
/* 44 */       return true;
/*    */     }
/*    */ 
/*    */     
/*    */     public UseAction func_77661_b(ItemStack par1ItemStack) {
/* 49 */       return UseAction.EAT;
/*    */     }
/*    */ 
/*    */     
/*    */     public void func_77624_a(ItemStack itemstack, World world, List<ITextComponent> list, ITooltipFlag flag) {
/* 54 */       super.func_77624_a(itemstack, world, list, flag);
/* 55 */       list.add(new StringTextComponent("A pretty shitty apple"));
/*    */     } }
/*    */ 
/*    */ }


/* Location:              /home/somebody/Downloads/ShittyOre.1.15.2.jar!/net/mcreator/shittyore/item/ShittyappleItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */