/*    */ package net.mcreator.shittyore;
/*    */ 
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.enchantment.Enchantment;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraft.util.SoundEvent;
/*    */ import net.minecraft.world.biome.Biome;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import net.minecraftforge.common.MinecraftForge;
/*    */ import net.minecraftforge.event.RegistryEvent;
/*    */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*    */ import net.minecraftforge.fml.common.Mod;
/*    */ import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
/*    */ import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
/*    */ import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
/*    */ import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
/*    */ import net.minecraftforge.fml.network.NetworkRegistry;
/*    */ import net.minecraftforge.fml.network.simple.SimpleChannel;
/*    */ import net.minecraftforge.registries.IForgeRegistryEntry;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Mod("shitty_ore")
/*    */ public class ShittyOreMod
/*    */ {
/*    */   private static final String PROTOCOL_VERSION = "1";
/* 45 */   public static final SimpleChannel PACKET_HANDLER = NetworkRegistry.newSimpleChannel(new ResourceLocation("shitty_ore", "shitty_ore"), () -> "1", "1"::equals, "1"::equals);
/*    */   public ShittyOreModElements elements;
/*    */   
/*    */   public ShittyOreMod() {
/* 49 */     this.elements = new ShittyOreModElements();
/* 50 */     FMLJavaModLoadingContext.get().getModEventBus().addListener(this::init);
/* 51 */     FMLJavaModLoadingContext.get().getModEventBus().register(this);
/* 52 */     MinecraftForge.EVENT_BUS.register(this);
/*    */   }
/*    */   
/*    */   private void init(FMLCommonSetupEvent event) {
/* 56 */     this.elements.getElements().forEach(element -> element.init(event));
/*    */   }
/*    */   
/*    */   @SubscribeEvent
/*    */   public void serverLoad(FMLServerStartingEvent event) {
/* 61 */     this.elements.getElements().forEach(element -> element.serverLoad(event));
/*    */   }
/*    */   
/*    */   @SubscribeEvent
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   public void clientLoad(FMLClientSetupEvent event) {
/* 67 */     this.elements.getElements().forEach(element -> element.clientLoad(event));
/*    */   }
/*    */   
/*    */   @SubscribeEvent
/*    */   public void registerBlocks(RegistryEvent.Register<Block> event) {
/* 72 */     event.getRegistry().registerAll((IForgeRegistryEntry[])this.elements.getBlocks().stream().map(Supplier::get).toArray(x$0 -> new Block[x$0]));
/*    */   }
/*    */   
/*    */   @SubscribeEvent
/*    */   public void registerItems(RegistryEvent.Register<Item> event) {
/* 77 */     event.getRegistry().registerAll((IForgeRegistryEntry[])this.elements.getItems().stream().map(Supplier::get).toArray(x$0 -> new Item[x$0]));
/*    */   }
/*    */   
/*    */   @SubscribeEvent
/*    */   public void registerBiomes(RegistryEvent.Register<Biome> event) {
/* 82 */     event.getRegistry().registerAll((IForgeRegistryEntry[])this.elements.getBiomes().stream().map(Supplier::get).toArray(x$0 -> new Biome[x$0]));
/*    */   }
/*    */   
/*    */   @SubscribeEvent
/*    */   public void registerEntities(RegistryEvent.Register<EntityType<?>> event) {
/* 87 */     event.getRegistry().registerAll((IForgeRegistryEntry[])this.elements.getEntities().stream().map(Supplier::get).toArray(x$0 -> new EntityType[x$0]));
/*    */   }
/*    */   
/*    */   @SubscribeEvent
/*    */   public void registerEnchantments(RegistryEvent.Register<Enchantment> event) {
/* 92 */     event.getRegistry().registerAll((IForgeRegistryEntry[])this.elements.getEnchantments().stream().map(Supplier::get).toArray(x$0 -> new Enchantment[x$0]));
/*    */   }
/*    */   
/*    */   @SubscribeEvent
/*    */   public void registerSounds(RegistryEvent.Register<SoundEvent> event) {
/* 97 */     this.elements.registerSounds(event);
/*    */   }
/*    */ }


/* Location:              /home/somebody/Downloads/ShittyOre.1.15.2.jar!/net/mcreator/shittyore/ShittyOreMod.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */