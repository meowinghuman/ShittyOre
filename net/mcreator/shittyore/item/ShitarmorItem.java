/*    */ package net.mcreator.shittyore.item;
/*    */ 
/*    */ import net.mcreator.shittyore.ShittyOreModElements;
/*    */ import net.mcreator.shittyore.ShittyOreModElements.ModElement.Tag;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.inventory.EquipmentSlotType;
/*    */ import net.minecraft.item.ArmorItem;
/*    */ import net.minecraft.item.IArmorMaterial;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemGroup;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.item.crafting.Ingredient;
/*    */ import net.minecraft.util.IItemProvider;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraft.util.SoundEvent;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import net.minecraftforge.registries.ForgeRegistries;
/*    */ import net.minecraftforge.registries.ObjectHolder;
/*    */ 
/*    */ @Tag
/*    */ public class ShitarmorItem extends ShittyOreModElements.ModElement {
/*    */   @ObjectHolder("shitty_ore:shitarmor_helmet")
/* 24 */   public static final Item helmet = null;
/*    */   @ObjectHolder("shitty_ore:shitarmor_chestplate")
/* 26 */   public static final Item body = null;
/*    */   @ObjectHolder("shitty_ore:shitarmor_leggings")
/* 28 */   public static final Item legs = null;
/*    */   @ObjectHolder("shitty_ore:shitarmor_boots")
/* 30 */   public static final Item boots = null;
/*    */   public ShitarmorItem(ShittyOreModElements instance) {
/* 32 */     super(instance, 4);
/*    */   }
/*    */ 
/*    */   
/*    */   public void initElements() {
/* 37 */     IArmorMaterial armormaterial = new IArmorMaterial() {
/*    */         public int func_200896_a(EquipmentSlotType slot) {
/* 39 */           (new int[4])[0] = 13; (new int[4])[1] = 15; (new int[4])[2] = 16; (new int[4])[3] = 11; return (new int[4])[slot.func_188454_b()] * 25;
/*    */         }
/*    */         
/*    */         public int func_200902_b(EquipmentSlotType slot) {
/* 43 */           (new int[4])[0] = 4; (new int[4])[1] = 6; (new int[4])[2] = 6; (new int[4])[3] = 4; return (new int[4])[slot.func_188454_b()];
/*    */         }
/*    */         
/*    */         public int func_200900_a() {
/* 47 */           return 10;
/*    */         }
/*    */         
/*    */         public SoundEvent func_200899_b() {
/* 51 */           return (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("shitty_ore:dryfart"));
/*    */         }
/*    */         
/*    */         public Ingredient func_200898_c() {
/* 55 */           return Ingredient.func_193369_a(new ItemStack[] { new ItemStack((IItemProvider)ShitItem.block, 1) });
/*    */         }
/*    */         
/*    */         @OnlyIn(Dist.CLIENT)
/*    */         public String func_200897_d() {
/* 60 */           return "shitarmor";
/*    */         }
/*    */         
/*    */         public float func_200901_e() {
/* 64 */           return 0.1F;
/*    */         }
/*    */       };
/* 67 */     this.elements.items.add(() -> (Item)(new ArmorItem(armormaterial, EquipmentSlotType.HEAD, (new Item.Properties()).func_200916_a(ItemGroup.field_78037_j))
/*    */         {
/*    */           public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlotType slot, String type) {
/* 70 */             return "shitty_ore:textures/models/armor/shit__layer_" + ((slot == EquipmentSlotType.LEGS) ? "2" : "1") + ".png";
/*    */           }
/*    */         }).setRegistryName("shitarmor_helmet"));
/* 73 */     this.elements.items.add(() -> (Item)(new ArmorItem(armormaterial, EquipmentSlotType.CHEST, (new Item.Properties()).func_200916_a(ItemGroup.field_78037_j))
/*    */         {
/*    */           public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlotType slot, String type) {
/* 76 */             return "shitty_ore:textures/models/armor/shit__layer_" + ((slot == EquipmentSlotType.LEGS) ? "2" : "1") + ".png";
/*    */           }
/*    */         }).setRegistryName("shitarmor_chestplate"));
/* 79 */     this.elements.items.add(() -> (Item)(new ArmorItem(armormaterial, EquipmentSlotType.LEGS, (new Item.Properties()).func_200916_a(ItemGroup.field_78037_j))
/*    */         {
/*    */           public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlotType slot, String type) {
/* 82 */             return "shitty_ore:textures/models/armor/shit__layer_" + ((slot == EquipmentSlotType.LEGS) ? "2" : "1") + ".png";
/*    */           }
/*    */         }).setRegistryName("shitarmor_leggings"));
/* 85 */     this.elements.items.add(() -> (Item)(new ArmorItem(armormaterial, EquipmentSlotType.FEET, (new Item.Properties()).func_200916_a(ItemGroup.field_78037_j))
/*    */         {
/*    */           public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlotType slot, String type) {
/* 88 */             return "shitty_ore:textures/models/armor/shit__layer_" + ((slot == EquipmentSlotType.LEGS) ? "2" : "1") + ".png";
/*    */           }
/*    */         }).setRegistryName("shitarmor_boots"));
/*    */   }
/*    */ }


/* Location:              /home/somebody/Downloads/ShittyOre.1.15.2.jar!/net/mcreator/shittyore/item/ShitarmorItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */