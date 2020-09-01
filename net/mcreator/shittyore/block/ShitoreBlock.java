/*     */ package net.mcreator.shittyore.block;
/*     */ import com.mojang.datafixers.Dynamic;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import java.util.function.Function;
/*     */ import net.mcreator.shittyore.ShittyOreModElements;
/*     */ import net.mcreator.shittyore.ShittyOreModElements.ModElement.Tag;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockState;
/*     */ import net.minecraft.block.Blocks;
/*     */ import net.minecraft.block.SoundType;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.client.util.ITooltipFlag;
/*     */ import net.minecraft.item.BlockItem;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemGroup;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.Direction;
/*     */ import net.minecraft.util.IItemProvider;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.StringTextComponent;
/*     */ import net.minecraft.world.IBlockReader;
/*     */ import net.minecraft.world.IWorld;
/*     */ import net.minecraft.world.biome.Biome;
/*     */ import net.minecraft.world.dimension.DimensionType;
/*     */ import net.minecraft.world.gen.ChunkGenerator;
/*     */ import net.minecraft.world.gen.GenerationStage;
/*     */ import net.minecraft.world.gen.feature.IFeatureConfig;
/*     */ import net.minecraft.world.gen.feature.OreFeature;
/*     */ import net.minecraft.world.gen.feature.OreFeatureConfig;
/*     */ import net.minecraft.world.gen.placement.CountRangeConfig;
/*     */ import net.minecraft.world.gen.placement.IPlacementConfig;
/*     */ import net.minecraft.world.gen.placement.Placement;
/*     */ import net.minecraft.world.storage.loot.LootContext;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.api.distmarker.OnlyIn;
/*     */ import net.minecraftforge.common.ToolType;
/*     */ import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
/*     */ import net.minecraftforge.registries.ForgeRegistries;
/*     */ import net.minecraftforge.registries.ObjectHolder;
/*     */ 
/*     */ @Tag
/*     */ public class ShitoreBlock extends ShittyOreModElements.ModElement {
/*     */   @ObjectHolder("shitty_ore:shitore")
/*  47 */   public static final Block block = null;
/*     */   public ShitoreBlock(ShittyOreModElements instance) {
/*  49 */     super(instance, 1);
/*     */   }
/*     */ 
/*     */   
/*     */   public void initElements() {
/*  54 */     this.elements.blocks.add(() -> new CustomBlock());
/*  55 */     this.elements.items
/*  56 */       .add(() -> (Item)(new BlockItem(block, (new Item.Properties()).func_200916_a(ItemGroup.field_78030_b))).setRegistryName(block.getRegistryName()));
/*     */   }
/*     */   
/*     */   public static class CustomBlock extends Block { public CustomBlock() {
/*  60 */       super(Block.Properties.func_200945_a(Material.field_151576_e).func_200947_a(SoundType.field_185851_d).func_200948_a(1.35F, 10.0F).func_200951_a(0).harvestLevel(2)
/*  61 */           .harvestTool(ToolType.PICKAXE));
/*  62 */       setRegistryName("shitore");
/*     */     }
/*     */ 
/*     */     
/*     */     @OnlyIn(Dist.CLIENT)
/*     */     public void func_190948_a(ItemStack itemstack, IBlockReader world, List<ITextComponent> list, ITooltipFlag flag) {
/*  68 */       super.func_190948_a(itemstack, world, list, flag);
/*  69 */       list.add(new StringTextComponent("\"shit\""));
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean canSustainPlant(BlockState state, IBlockReader world, BlockPos pos, Direction direction, IPlantable plantable) {
/*  74 */       return true;
/*     */     }
/*     */ 
/*     */     
/*     */     public List<ItemStack> func_220076_a(BlockState state, LootContext.Builder builder) {
/*  79 */       List<ItemStack> dropsOriginal = super.func_220076_a(state, builder);
/*  80 */       if (!dropsOriginal.isEmpty())
/*  81 */         return dropsOriginal; 
/*  82 */       return Collections.singletonList(new ItemStack((IItemProvider)this, 1));
/*     */     } }
/*     */ 
/*     */   
/*     */   public void init(FMLCommonSetupEvent event) {
/*  87 */     for (Biome biome : ForgeRegistries.BIOMES.getValues()) {
/*  88 */       biome.func_203611_a(GenerationStage.Decoration.UNDERGROUND_ORES, (new OreFeature(OreFeatureConfig::func_214641_a)
/*     */           {
/*     */             public boolean func_212245_a(IWorld world, ChunkGenerator generator, Random rand, BlockPos pos, OreFeatureConfig config) {
/*  91 */               DimensionType dimensionType = world.func_201675_m().func_186058_p();
/*  92 */               boolean dimensionCriteria = false;
/*  93 */               if (dimensionType == DimensionType.field_223227_a_)
/*  94 */                 dimensionCriteria = true; 
/*  95 */               if (!dimensionCriteria)
/*  96 */                 return false; 
/*  97 */               return super.func_212245_a(world, generator, rand, pos, config);
/*     */             }
/*  99 */           }).func_225566_b_((IFeatureConfig)new OreFeatureConfig(OreFeatureConfig.FillerBlockType.create("shitore", "shitore", blockAt -> { boolean blockCriteria = false; if (blockAt.func_177230_c() == Blocks.field_150348_b.func_176223_P().func_177230_c()) blockCriteria = true;  return blockCriteria; }), block
/*     */ 
/*     */ 
/*     */ 
/*     */               
/* 104 */               .func_176223_P(), 5)).func_227228_a_(Placement.field_215028_n.func_227446_a_((IPlacementConfig)new CountRangeConfig(12, 4, 4, 50))));
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /home/somebody/Downloads/ShittyOre.1.15.2.jar!/net/mcreator/shittyore/block/ShitoreBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */