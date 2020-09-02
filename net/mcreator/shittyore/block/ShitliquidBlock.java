/*     */ package net.mcreator.shittyore.block;
/*     */ import com.mojang.datafixers.Dynamic;
/*     */ import java.util.Random;
/*     */ import java.util.function.Function;
/*     */ import net.mcreator.shittyore.ShittyOreModElements;
/*     */ import net.mcreator.shittyore.ShittyOreModElements.ModElement.Tag;
/*     */ import net.mcreator.shittyore.world.dimension.ShitdimensionDimension;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.FlowingFluidBlock;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.client.renderer.RenderType;
/*     */ import net.minecraft.client.renderer.RenderTypeLookup;
/*     */ import net.minecraft.fluid.FlowingFluid;
/*     */ import net.minecraft.fluid.Fluid;
/*     */ import net.minecraft.item.BucketItem;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemGroup;
/*     */ import net.minecraft.item.Items;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.world.IWorld;
/*     */ import net.minecraft.world.biome.Biome;
/*     */ import net.minecraft.world.dimension.DimensionType;
/*     */ import net.minecraft.world.gen.ChunkGenerator;
/*     */ import net.minecraft.world.gen.GenerationStage;
/*     */ import net.minecraft.world.gen.feature.BlockStateFeatureConfig;
/*     */ import net.minecraft.world.gen.feature.IFeatureConfig;
/*     */ import net.minecraft.world.gen.feature.LakesFeature;
/*     */ import net.minecraft.world.gen.placement.ChanceConfig;
/*     */ import net.minecraft.world.gen.placement.IPlacementConfig;
/*     */ import net.minecraft.world.gen.placement.Placement;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.event.RegistryEvent;
/*     */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*     */ import net.minecraftforge.fluids.FluidAttributes;
/*     */ import net.minecraftforge.fluids.ForgeFlowingFluid;
/*     */ import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
/*     */ import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
/*     */ import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
/*     */ import net.minecraftforge.registries.ForgeRegistries;
/*     */ import net.minecraftforge.registries.IForgeRegistryEntry;
/*     */ import net.minecraftforge.registries.ObjectHolder;
/*     */ 
/*     */ @Tag
/*     */ public class ShitliquidBlock extends ShittyOreModElements.ModElement {
/*     */   @ObjectHolder("shitty_ore:shitliquid")
/*  47 */   public static final FlowingFluidBlock block = null;
/*     */   @ObjectHolder("shitty_ore:shitliquid_bucket")
/*  49 */   public static final Item bucket = null;
/*  50 */   public static FlowingFluid flowing = null;
/*  51 */   public static FlowingFluid still = null;
/*  52 */   private ForgeFlowingFluid.Properties fluidproperties = null;
/*     */   public ShitliquidBlock(ShittyOreModElements instance) {
/*  54 */     super(instance, 32);
/*  55 */     FMLJavaModLoadingContext.get().getModEventBus().register(this);
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public void registerFluids(RegistryEvent.Register<Fluid> event) {
/*  60 */     event.getRegistry().register((IForgeRegistryEntry)still);
/*  61 */     event.getRegistry().register((IForgeRegistryEntry)flowing);
/*     */   }
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public void clientLoad(FMLClientSetupEvent event) {
/*  67 */     RenderTypeLookup.setRenderLayer((Fluid)still, RenderType.func_228645_f_());
/*  68 */     RenderTypeLookup.setRenderLayer((Fluid)flowing, RenderType.func_228645_f_());
/*     */   }
/*     */ 
/*     */   
/*     */   public void initElements() {
/*  73 */     this
/*     */       
/*  75 */       .fluidproperties = (new ForgeFlowingFluid.Properties(() -> still, () -> flowing, FluidAttributes.builder(new ResourceLocation("shitty_ore:blocks/shit_liquid"), new ResourceLocation("shitty_ore:blocks/shit_liquid")).luminosity(0).density(1000).viscosity(1000))).bucket(() -> bucket).block(() -> block);
/*  76 */     still = (FlowingFluid)(new ForgeFlowingFluid.Source(this.fluidproperties)).setRegistryName("shitliquid");
/*  77 */     flowing = (FlowingFluid)(new ForgeFlowingFluid.Flowing(this.fluidproperties)).setRegistryName("shitliquid_flowing");
/*  78 */     this.elements.blocks.add(() -> (Block)(new FlowingFluidBlock(still, Block.Properties.func_200945_a(Material.field_151586_h)) {  }
/*     */         ).setRegistryName("shitliquid"));
/*  80 */     this.elements.items.add(() -> (Item)(new BucketItem((Fluid)still, (new Item.Properties()).func_200919_a(Items.field_151133_ar).func_200917_a(1).func_200916_a(ItemGroup.field_78026_f))).setRegistryName("shitliquid_bucket"));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void init(FMLCommonSetupEvent event) {
/*  86 */     for (Biome biome : ForgeRegistries.BIOMES.getValues()) {
/*  87 */       biome.func_203611_a(GenerationStage.Decoration.LOCAL_MODIFICATIONS, (new LakesFeature(BlockStateFeatureConfig::func_227271_a_)
/*     */           {
/*     */             public boolean func_212245_a(IWorld world, ChunkGenerator generator, Random rand, BlockPos pos, BlockStateFeatureConfig config) {
/*  90 */               DimensionType dimensionType = world.func_201675_m().func_186058_p();
/*  91 */               boolean dimensionCriteria = false;
/*  92 */               if (dimensionType == ShitdimensionDimension.type)
/*  93 */                 dimensionCriteria = true; 
/*  94 */               if (dimensionType == DimensionType.field_223227_a_)
/*  95 */                 dimensionCriteria = true; 
/*  96 */               if (!dimensionCriteria)
/*  97 */                 return false; 
/*  98 */               return super.func_212245_a(world, generator, rand, pos, config);
/*     */             }
/* 100 */           }).func_225566_b_((IFeatureConfig)new BlockStateFeatureConfig(block.func_176223_P()))
/* 101 */           .func_227228_a_(Placement.field_215006_E.func_227446_a_((IPlacementConfig)new ChanceConfig(5))));
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /home/somebody/Downloads/ShittyOre.1.15.2.jar!/net/mcreator/shittyore/block/ShitliquidBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */