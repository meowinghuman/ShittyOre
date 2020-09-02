/*    */ package net.mcreator.shittyore.world.structure;
/*    */ 
/*    */ import com.mojang.datafixers.Dynamic;
/*    */ import java.util.Random;
/*    */ import java.util.function.Function;
/*    */ import net.mcreator.shittyore.ShittyOreModElements;
/*    */ import net.mcreator.shittyore.ShittyOreModElements.ModElement.Tag;
/*    */ import net.minecraft.util.Mirror;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraft.util.Rotation;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.IWorld;
/*    */ import net.minecraft.world.biome.Biome;
/*    */ import net.minecraft.world.dimension.DimensionType;
/*    */ import net.minecraft.world.gen.ChunkGenerator;
/*    */ import net.minecraft.world.gen.GenerationStage;
/*    */ import net.minecraft.world.gen.Heightmap;
/*    */ import net.minecraft.world.gen.feature.Feature;
/*    */ import net.minecraft.world.gen.feature.IFeatureConfig;
/*    */ import net.minecraft.world.gen.feature.NoFeatureConfig;
/*    */ import net.minecraft.world.gen.feature.template.BlockIgnoreStructureProcessor;
/*    */ import net.minecraft.world.gen.feature.template.PlacementSettings;
/*    */ import net.minecraft.world.gen.feature.template.StructureProcessor;
/*    */ import net.minecraft.world.gen.feature.template.Template;
/*    */ import net.minecraft.world.gen.placement.IPlacementConfig;
/*    */ import net.minecraft.world.gen.placement.Placement;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
/*    */ import net.minecraftforge.registries.ForgeRegistries;
/*    */ 
/*    */ @Tag
/*    */ public class ShitruinStructure extends ShittyOreModElements.ModElement {
/*    */   public ShitruinStructure(ShittyOreModElements instance) {
/* 34 */     super(instance, 27);
/*    */   }
/*    */ 
/*    */   
/*    */   public void init(FMLCommonSetupEvent event) {
/* 39 */     Feature<NoFeatureConfig> feature = new Feature<NoFeatureConfig>(NoFeatureConfig::func_214639_a)
/*    */       {
/*    */         public boolean place(IWorld world, ChunkGenerator generator, Random random, BlockPos pos, NoFeatureConfig config) {
/* 42 */           int ci = pos.func_177958_n() >> 4 << 4;
/* 43 */           int ck = pos.func_177952_p() >> 4 << 4;
/* 44 */           DimensionType dimensionType = world.func_201675_m().func_186058_p();
/* 45 */           boolean dimensionCriteria = false;
/* 46 */           if (dimensionType == DimensionType.field_223227_a_)
/* 47 */             dimensionCriteria = true; 
/* 48 */           if (!dimensionCriteria)
/* 49 */             return false; 
/* 50 */           if (random.nextInt(1000000) + 1 <= 1500) {
/* 51 */             int count = random.nextInt(1) + 1;
/* 52 */             for (int a = 0; a < count; a++) {
/* 53 */               int i = ci + random.nextInt(16);
/* 54 */               int k = ck + random.nextInt(16);
/* 55 */               int j = world.func_201676_a(Heightmap.Type.OCEAN_FLOOR_WG, i, k);
/* 56 */               j--;
/* 57 */               Rotation rotation = Rotation.values()[random.nextInt(3)];
/* 58 */               Mirror mirror = Mirror.values()[random.nextInt(2)];
/* 59 */               BlockPos spawnTo = new BlockPos(i, j + 0, k);
/* 60 */               int x = spawnTo.func_177958_n();
/* 61 */               int y = spawnTo.func_177956_o();
/* 62 */               int z = spawnTo.func_177952_p();
/*    */               
/* 64 */               Template template = ((ServerWorld)world.func_201672_e()).func_217485_w().func_186340_h().func_200220_a(new ResourceLocation("shitty_ore", "shit_ruins"));
/* 65 */               if (template == null)
/* 66 */                 return false; 
/* 67 */               template.func_186253_b(world, spawnTo, (new PlacementSettings()).func_186220_a(rotation).func_189950_a(random).func_186214_a(mirror)
/* 68 */                   .func_215222_a((StructureProcessor)BlockIgnoreStructureProcessor.field_215204_a).func_186218_a(null).func_186222_a(false));
/*    */             } 
/*    */           } 
/* 71 */           return true;
/*    */         }
/*    */       };
/* 74 */     for (Biome biome : ForgeRegistries.BIOMES.getValues())
/* 75 */       biome.func_203611_a(GenerationStage.Decoration.SURFACE_STRUCTURES, feature.func_225566_b_((IFeatureConfig)IFeatureConfig.field_202429_e)
/* 76 */           .func_227228_a_(Placement.field_215022_h.func_227446_a_((IPlacementConfig)IPlacementConfig.field_202468_e))); 
/*    */   }
/*    */ }


/* Location:              /home/somebody/Downloads/ShittyOre.1.15.2.jar!/net/mcreator/shittyore/world/structure/ShitruinStructure.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */