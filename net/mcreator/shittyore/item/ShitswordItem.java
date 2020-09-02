/*    */ package net.mcreator.shittyore.item;
/*    */ 
/*    */ import net.mcreator.shittyore.ShittyOreModElements;
/*    */ import net.mcreator.shittyore.ShittyOreModElements.ModElement.Tag;
/*    */ import net.minecraft.item.IItemTier;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemGroup;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.item.SwordItem;
/*    */ import net.minecraft.item.crafting.Ingredient;
/*    */ import net.minecraft.util.IItemProvider;
/*    */ import net.minecraftforge.registries.ObjectHolder;
/*    */ 
/*    */ @Tag
/*    */ public class ShitswordItem
/*    */   extends ShittyOreModElements.ModElement {
/*    */   @ObjectHolder("shitty_ore:shitsword")
/* 18 */   public static final Item block = null;
/*    */   public ShitswordItem(ShittyOreModElements instance) {
/* 20 */     super(instance, 11);
/*    */   }
/*    */ 
/*    */   
/*    */   public void initElements() {
/* 25 */     this.elements.items.add(() -> (Item)(new SwordItem(new IItemTier() {
/*    */             public int func_200926_a() {
/* 27 */               return 2500;
/*    */             }
/*    */             
/*    */             public float func_200928_b() {
/* 31 */               return 10.0F;
/*    */             }
/*    */             
/*    */             public float func_200929_c() {
/* 35 */               return 10.0F;
/*    */             }
/*    */             
/*    */             public int func_200925_d() {
/* 39 */               return 1;
/*    */             }
/*    */             
/*    */             public int func_200927_e() {
/* 43 */               return 10;
/*    */             }
/*    */             
/*    */             public Ingredient func_200924_f() {
/* 47 */               return Ingredient.func_193369_a(new ItemStack[] { new ItemStack((IItemProvider)ShitItem.block, 1) });
/*    */             }
/*    */           }3, -3.0F, (new Item.Properties()).func_200916_a(ItemGroup.field_78037_j)) {
/*    */         
/*    */         }).setRegistryName("shitsword"));
/*    */   }
/*    */ }


/* Location:              /home/somebody/Downloads/ShittyOre.1.15.2.jar!/net/mcreator/shittyore/item/ShitswordItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */