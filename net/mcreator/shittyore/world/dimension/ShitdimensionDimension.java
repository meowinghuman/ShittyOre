/*     */ package net.mcreator.shittyore.world.dimension;
/*     */ import com.google.common.cache.LoadingCache;
/*     */ import com.google.common.collect.ImmutableSet;
/*     */ import com.google.common.collect.Sets;
/*     */ import com.mojang.datafixers.Dynamic;
/*     */ import java.lang.reflect.Method;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collection;
/*     */ import java.util.Comparator;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Optional;
/*     */ import java.util.Random;
/*     */ import java.util.Set;
/*     */ import java.util.function.BiFunction;
/*     */ import java.util.function.Function;
/*     */ import java.util.function.LongFunction;
/*     */ import java.util.stream.Collectors;
/*     */ import javax.annotation.Nullable;
/*     */ import net.mcreator.shittyore.ShittyOreModElements;
/*     */ import net.mcreator.shittyore.ShittyOreModElements.ModElement.Tag;
/*     */ import net.mcreator.shittyore.block.ShitblockBlock;
/*     */ import net.mcreator.shittyore.block.ShitliquidBlock;
/*     */ import net.mcreator.shittyore.block.ShitresidueBlock;
/*     */ import net.mcreator.shittyore.item.ShitdimensionItem;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockState;
/*     */ import net.minecraft.block.Blocks;
/*     */ import net.minecraft.block.NetherPortalBlock;
/*     */ import net.minecraft.block.SoundType;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.pattern.BlockPattern;
/*     */ import net.minecraft.client.renderer.RenderType;
/*     */ import net.minecraft.client.renderer.RenderTypeLookup;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.entity.player.ServerPlayerEntity;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.particles.IParticleData;
/*     */ import net.minecraft.state.IProperty;
/*     */ import net.minecraft.util.CachedBlockInfo;
/*     */ import net.minecraft.util.Direction;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.SoundCategory;
/*     */ import net.minecraft.util.SoundEvent;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.ChunkPos;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.util.math.Vec3d;
/*     */ import net.minecraft.util.math.Vec3i;
/*     */ import net.minecraft.util.registry.Registry;
/*     */ import net.minecraft.village.PointOfInterest;
/*     */ import net.minecraft.village.PointOfInterestManager;
/*     */ import net.minecraft.village.PointOfInterestType;
/*     */ import net.minecraft.world.IWorld;
/*     */ import net.minecraft.world.IWorldReader;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.biome.Biome;
/*     */ import net.minecraft.world.biome.provider.BiomeProvider;
/*     */ import net.minecraft.world.dimension.Dimension;
/*     */ import net.minecraft.world.dimension.DimensionType;
/*     */ import net.minecraft.world.gen.ChunkGenerator;
/*     */ import net.minecraft.world.gen.GenerationStage;
/*     */ import net.minecraft.world.gen.IExtendedNoiseRandom;
/*     */ import net.minecraft.world.gen.INoiseRandom;
/*     */ import net.minecraft.world.gen.LazyAreaLayerContext;
/*     */ import net.minecraft.world.gen.OverworldChunkGenerator;
/*     */ import net.minecraft.world.gen.OverworldGenSettings;
/*     */ import net.minecraft.world.gen.area.IAreaFactory;
/*     */ import net.minecraft.world.gen.area.LazyArea;
/*     */ import net.minecraft.world.gen.carver.CaveWorldCarver;
/*     */ import net.minecraft.world.gen.carver.ICarverConfig;
/*     */ import net.minecraft.world.gen.carver.WorldCarver;
/*     */ import net.minecraft.world.gen.feature.ProbabilityConfig;
/*     */ import net.minecraft.world.gen.layer.IslandLayer;
/*     */ import net.minecraft.world.gen.layer.Layer;
/*     */ import net.minecraft.world.gen.layer.ZoomLayer;
/*     */ import net.minecraft.world.gen.layer.traits.IC0Transformer;
/*     */ import net.minecraft.world.server.ServerWorld;
/*     */ import net.minecraft.world.server.TicketType;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.api.distmarker.OnlyIn;
/*     */ import net.minecraftforge.common.DimensionManager;
/*     */ import net.minecraftforge.common.MinecraftForge;
/*     */ import net.minecraftforge.common.ModDimension;
/*     */ import net.minecraftforge.common.extensions.IForgeDimension;
/*     */ import net.minecraftforge.common.util.ITeleporter;
/*     */ import net.minecraftforge.event.RegistryEvent;
/*     */ import net.minecraftforge.event.world.RegisterDimensionsEvent;
/*     */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*     */ import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
/*     */ import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
/*     */ import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
/*     */ import net.minecraftforge.registries.ForgeRegistries;
/*     */ import net.minecraftforge.registries.IForgeRegistryEntry;
/*     */ import net.minecraftforge.registries.ObjectHolder;
/*     */ 
/*     */ @Tag
/*     */ public class ShitdimensionDimension extends ShittyOreModElements.ModElement {
/*     */   @ObjectHolder("shitty_ore:shitdimension")
/* 101 */   public static final ModDimension dimension = null;
/*     */   @ObjectHolder("shitty_ore:shitdimension_portal")
/* 103 */   public static final CustomPortalBlock portal = null;
/* 104 */   public static DimensionType type = null; private static Biome[] dimensionBiomes;
/*     */   
/*     */   public ShitdimensionDimension(ShittyOreModElements instance) {
/* 107 */     super(instance, 32);
/* 108 */     MinecraftForge.EVENT_BUS.register(this);
/* 109 */     FMLJavaModLoadingContext.get().getModEventBus().register(this);
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public void registerDimension(RegistryEvent.Register<ModDimension> event) {
/* 114 */     event.getRegistry().register((new CustomModDimension()).setRegistryName("shitdimension"));
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onRegisterDimensionsEvent(RegisterDimensionsEvent event) {
/* 119 */     if (DimensionType.func_193417_a(new ResourceLocation("shitty_ore:shitdimension")) == null) {
/* 120 */       DimensionManager.registerDimension(new ResourceLocation("shitty_ore:shitdimension"), dimension, null, true);
/*     */     }
/* 122 */     type = DimensionType.func_193417_a(new ResourceLocation("shitty_ore:shitdimension"));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void init(FMLCommonSetupEvent event) {
/* 132 */     dimensionBiomes = new Biome[] { (Biome)ForgeRegistries.BIOMES.getValue(new ResourceLocation("ocean")), (Biome)ForgeRegistries.BIOMES.getValue(new ResourceLocation("plains")), (Biome)ForgeRegistries.BIOMES.getValue(new ResourceLocation("desert")), (Biome)ForgeRegistries.BIOMES.getValue(new ResourceLocation("mountains")), (Biome)ForgeRegistries.BIOMES.getValue(new ResourceLocation("mountains")), (Biome)ForgeRegistries.BIOMES.getValue(new ResourceLocation("forest")), (Biome)ForgeRegistries.BIOMES.getValue(new ResourceLocation("taiga")), (Biome)ForgeRegistries.BIOMES.getValue(new ResourceLocation("swamp")), (Biome)ForgeRegistries.BIOMES.getValue(new ResourceLocation("river")) };
/*     */   }
/*     */ 
/*     */   
/*     */   public void initElements() {
/* 137 */     this.elements.blocks.add(() -> new CustomPortalBlock());
/* 138 */     this.elements.items.add(() -> (Item)(new ShitdimensionItem()).setRegistryName("shitdimension"));
/*     */   }
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public void clientLoad(FMLClientSetupEvent event) {
/* 144 */     RenderTypeLookup.setRenderLayer((Block)portal, RenderType.func_228645_f_());
/*     */   }
/*     */   
/*     */   public static class CustomPortalBlock extends NetherPortalBlock { public CustomPortalBlock() {
/* 148 */       super(Block.Properties.func_200945_a(Material.field_151567_E).func_200942_a().func_200944_c().func_200943_b(-1.0F).func_200947_a(SoundType.field_185853_f)
/* 149 */           .func_200951_a(0).func_222380_e());
/* 150 */       setRegistryName("shitdimension_portal");
/*     */     }
/*     */ 
/*     */     
/*     */     public void func_225534_a_(BlockState state, ServerWorld world, BlockPos pos, Random random) {}
/*     */ 
/*     */     
/*     */     public void portalSpawn(World world, BlockPos pos) {
/* 158 */       Size portalsize = isValid((IWorld)world, pos);
/* 159 */       if (portalsize != null)
/* 160 */         portalsize.placePortalBlocks(); 
/*     */     }
/*     */     
/*     */     @Nullable
/*     */     public Size isValid(IWorld worldIn, BlockPos pos) {
/* 165 */       Size netherportalblock$size = new Size(worldIn, pos, Direction.Axis.X);
/* 166 */       if (netherportalblock$size.isValid() && netherportalblock$size.portalBlockCount == 0) {
/* 167 */         return netherportalblock$size;
/*     */       }
/* 169 */       Size netherportalblock$size1 = new Size(worldIn, pos, Direction.Axis.Z);
/* 170 */       return (netherportalblock$size1.isValid() && netherportalblock$size1.portalBlockCount == 0) ? netherportalblock$size1 : null;
/*     */     }
/*     */ 
/*     */     
/*     */     public static BlockPattern.PatternHelper createPatternHelper(IWorld p_181089_0_, BlockPos worldIn) {
/* 175 */       Direction.Axis direction$axis = Direction.Axis.Z;
/* 176 */       Size netherportalblock$size = new Size(p_181089_0_, worldIn, Direction.Axis.X);
/* 177 */       LoadingCache<BlockPos, CachedBlockInfo> loadingcache = BlockPattern.func_181627_a((IWorldReader)p_181089_0_, true);
/* 178 */       if (!netherportalblock$size.isValid()) {
/* 179 */         direction$axis = Direction.Axis.X;
/* 180 */         netherportalblock$size = new Size(p_181089_0_, worldIn, Direction.Axis.Z);
/*     */       } 
/* 182 */       if (!netherportalblock$size.isValid()) {
/* 183 */         return new BlockPattern.PatternHelper(worldIn, Direction.NORTH, Direction.UP, loadingcache, 1, 1, 1);
/*     */       }
/* 185 */       int[] aint = new int[(Direction.AxisDirection.values()).length];
/* 186 */       Direction direction = netherportalblock$size.rightDir.func_176735_f();
/* 187 */       BlockPos blockpos = netherportalblock$size.bottomLeft.func_177981_b(netherportalblock$size.getHeight() - 1);
/* 188 */       for (Direction.AxisDirection direction$axisdirection : Direction.AxisDirection.values()) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 194 */         BlockPattern.PatternHelper blockpattern$patternhelper = new BlockPattern.PatternHelper((direction.func_176743_c() == direction$axisdirection) ? blockpos : blockpos.func_177967_a(netherportalblock$size.rightDir, netherportalblock$size.getWidth() - 1), Direction.func_181076_a(direction$axisdirection, direction$axis), Direction.UP, loadingcache, netherportalblock$size.getWidth(), netherportalblock$size.getHeight(), 1);
/* 195 */         for (int i = 0; i < netherportalblock$size.getWidth(); i++) {
/* 196 */           for (int j = 0; j < netherportalblock$size.getHeight(); j++) {
/* 197 */             CachedBlockInfo cachedblockinfo = blockpattern$patternhelper.func_177670_a(i, j, 1);
/* 198 */             if (!cachedblockinfo.func_177509_a().func_196958_f()) {
/* 199 */               aint[direction$axisdirection.ordinal()] = aint[direction$axisdirection.ordinal()] + 1;
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/* 204 */       Direction.AxisDirection direction$axisdirection1 = Direction.AxisDirection.POSITIVE;
/* 205 */       for (Direction.AxisDirection direction$axisdirection2 : Direction.AxisDirection.values()) {
/* 206 */         if (aint[direction$axisdirection2.ordinal()] < aint[direction$axisdirection1.ordinal()]) {
/* 207 */           direction$axisdirection1 = direction$axisdirection2;
/*     */         }
/*     */       } 
/* 210 */       return new BlockPattern.PatternHelper(
/* 211 */           (direction.func_176743_c() == direction$axisdirection1) ? blockpos : blockpos
/*     */           
/* 213 */           .func_177967_a(netherportalblock$size.rightDir, netherportalblock$size.getWidth() - 1), 
/* 214 */           Direction.func_181076_a(direction$axisdirection1, direction$axis), Direction.UP, loadingcache, netherportalblock$size
/* 215 */           .getWidth(), netherportalblock$size.getHeight(), 1);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public BlockState func_196271_a(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
/* 228 */       Direction.Axis direction$axis = facing.func_176740_k();
/* 229 */       Direction.Axis direction$axis1 = (Direction.Axis)stateIn.func_177229_b((IProperty)field_176550_a);
/* 230 */       boolean flag = (direction$axis1 != direction$axis && direction$axis.func_176722_c());
/* 231 */       return (!flag && facingState.func_177230_c() != this && !(new Size(worldIn, currentPos, direction$axis1)).func_208508_f()) ? Blocks.field_150350_a
/* 232 */         .func_176223_P() : super
/* 233 */         .func_196271_a(stateIn, facing, facingState, worldIn, currentPos, facingPos);
/*     */     }
/*     */ 
/*     */     
/*     */     @OnlyIn(Dist.CLIENT)
/*     */     public void func_180655_c(BlockState state, World world, BlockPos pos, Random random) {
/* 239 */       for (int i = 0; i < 4; i++) {
/* 240 */         double px = (pos.func_177958_n() + random.nextFloat());
/* 241 */         double py = (pos.func_177956_o() + random.nextFloat());
/* 242 */         double pz = (pos.func_177952_p() + random.nextFloat());
/* 243 */         double vx = (random.nextFloat() - 0.5D) / 2.0D;
/* 244 */         double vy = (random.nextFloat() - 0.5D) / 2.0D;
/* 245 */         double vz = (random.nextFloat() - 0.5D) / 2.0D;
/* 246 */         int j = random.nextInt(4) - 1;
/* 247 */         if (world.func_180495_p(pos.func_177976_e()).func_177230_c() != this && world.func_180495_p(pos.func_177974_f()).func_177230_c() != this) {
/* 248 */           px = pos.func_177958_n() + 0.5D + 0.25D * j;
/* 249 */           vx = (random.nextFloat() * 2.0F * j);
/*     */         } else {
/* 251 */           pz = pos.func_177952_p() + 0.5D + 0.25D * j;
/* 252 */           vz = (random.nextFloat() * 2.0F * j);
/*     */         } 
/* 254 */         world.func_195594_a((IParticleData)ParticleTypes.field_197599_J, px, py, pz, vx, vy, vz);
/*     */       } 
/* 256 */       if (random.nextInt(110) == 0) {
/* 257 */         world.func_184134_a(pos.func_177958_n() + 0.5D, pos.func_177956_o() + 0.5D, pos.func_177952_p() + 0.5D, (SoundEvent)ForgeRegistries.SOUND_EVENTS
/* 258 */             .getValue(new ResourceLocation("block.portal.ambient")), SoundCategory.BLOCKS, 0.5F, random
/* 259 */             .nextFloat() * 0.4F + 0.8F, false);
/*     */       }
/*     */     }
/*     */     
/*     */     public void func_196262_a(BlockState state, World world, BlockPos pos, Entity entity) {
/* 264 */       if (!entity.func_184218_aH() && !entity.func_184207_aI() && entity.func_184222_aU() && !entity.field_70170_p.field_72995_K) {
/* 265 */         if (entity.field_71088_bW > 0) {
/* 266 */           entity.field_71088_bW = entity.func_82147_ab();
/* 267 */         } else if (entity.field_71093_bK != ShitdimensionDimension.type) {
/* 268 */           entity.field_71088_bW = entity.func_82147_ab();
/* 269 */           teleportToDimension(entity, pos, ShitdimensionDimension.type);
/*     */         } else {
/* 271 */           entity.field_71088_bW = entity.func_82147_ab();
/* 272 */           teleportToDimension(entity, pos, DimensionType.field_223227_a_);
/*     */         } 
/*     */       }
/*     */     }
/*     */     
/*     */     private void teleportToDimension(Entity entity, BlockPos pos, DimensionType destinationType) {
/* 278 */       entity.changeDimension(destinationType, getTeleporterForDimension(entity, pos, entity.func_184102_h().func_71218_a(destinationType)));
/*     */     }
/*     */     
/*     */     private ShitdimensionDimension.TeleporterDimensionMod getTeleporterForDimension(Entity entity, BlockPos pos, ServerWorld nextWorld) {
/* 282 */       BlockPattern.PatternHelper bph = createPatternHelper((IWorld)entity.field_70170_p, pos);
/*     */ 
/*     */       
/* 285 */       double d0 = (bph.func_177669_b().func_176740_k() == Direction.Axis.X) ? bph.func_181117_a().func_177952_p() : bph.func_181117_a().func_177958_n();
/* 286 */       double d1 = (bph.func_177669_b().func_176740_k() == Direction.Axis.X) ? entity.func_226281_cx_() : entity.func_226277_ct_();
/* 287 */       d1 = Math.abs(MathHelper.func_181160_c(d1 - ((bph.func_177669_b().func_176746_e().func_176743_c() == Direction.AxisDirection.NEGATIVE) ? true : false), d0, d0 - bph
/* 288 */             .func_181118_d()));
/* 289 */       double d2 = MathHelper.func_181160_c(entity.func_226278_cu_() - 1.0D, bph.func_181117_a().func_177956_o(), (bph
/* 290 */           .func_181117_a().func_177956_o() - bph.func_181119_e()));
/* 291 */       return new ShitdimensionDimension.TeleporterDimensionMod(nextWorld, new Vec3d(d1, d2, 0.0D), bph.func_177669_b());
/*     */     }
/*     */     public static class Size { private final IWorld world;
/*     */       private final Direction.Axis axis;
/*     */       private final Direction rightDir;
/*     */       private final Direction leftDir;
/*     */       private int portalBlockCount;
/*     */       @Nullable
/*     */       private BlockPos bottomLeft;
/*     */       private int height;
/*     */       private int width;
/*     */       
/*     */       public Size(IWorld worldIn, BlockPos pos, Direction.Axis axisIn) {
/* 304 */         this.world = worldIn;
/* 305 */         this.axis = axisIn;
/* 306 */         if (axisIn == Direction.Axis.X) {
/* 307 */           this.leftDir = Direction.EAST;
/* 308 */           this.rightDir = Direction.WEST;
/*     */         } else {
/* 310 */           this.leftDir = Direction.NORTH;
/* 311 */           this.rightDir = Direction.SOUTH;
/*     */         } 
/* 313 */         BlockPos blockpos = pos;
/* 314 */         for (; pos.func_177956_o() > blockpos.func_177956_o() - 21 && pos.func_177956_o() > 0 && func_196900_a(worldIn.func_180495_p(pos.func_177977_b())); pos = pos.func_177977_b());
/*     */ 
/*     */         
/* 317 */         int i = getDistanceUntilEdge(pos, this.leftDir) - 1;
/* 318 */         if (i >= 0) {
/* 319 */           this.bottomLeft = pos.func_177967_a(this.leftDir, i);
/* 320 */           this.width = getDistanceUntilEdge(this.bottomLeft, this.rightDir);
/* 321 */           if (this.width < 2 || this.width > 21) {
/* 322 */             this.bottomLeft = null;
/* 323 */             this.width = 0;
/*     */           } 
/*     */         } 
/* 326 */         if (this.bottomLeft != null) {
/* 327 */           this.height = calculatePortalHeight();
/*     */         }
/*     */       }
/*     */       
/*     */       protected int getDistanceUntilEdge(BlockPos pos, Direction directionIn) {
/*     */         int i;
/* 333 */         for (i = 0; i < 22; i++) {
/* 334 */           BlockPos blockpos = pos.func_177967_a(directionIn, i);
/* 335 */           if (!func_196900_a(this.world.func_180495_p(blockpos)) || this.world
/* 336 */             .func_180495_p(blockpos.func_177977_b()).func_177230_c() != ShitblockBlock.block.func_176223_P().func_177230_c()) {
/*     */             break;
/*     */           }
/*     */         } 
/* 340 */         BlockPos framePos = pos.func_177967_a(directionIn, i);
/* 341 */         return (this.world.func_180495_p(framePos).func_177230_c() == ShitblockBlock.block.func_176223_P().func_177230_c()) ? i : 0;
/*     */       }
/*     */       
/*     */       public int getHeight() {
/* 345 */         return this.height;
/*     */       }
/*     */       
/*     */       public int getWidth() {
/* 349 */         return this.width;
/*     */       }
/*     */       
/*     */       protected int calculatePortalHeight() {
/* 353 */         label41: for (this.height = 0; this.height < 21; this.height++) {
/* 354 */           for (int i = 0; i < this.width; i++) {
/* 355 */             BlockPos blockpos = this.bottomLeft.func_177967_a(this.rightDir, i).func_177981_b(this.height);
/* 356 */             BlockState blockstate = this.world.func_180495_p(blockpos);
/* 357 */             if (!func_196900_a(blockstate)) {
/*     */               break label41;
/*     */             }
/* 360 */             Block block = blockstate.func_177230_c();
/* 361 */             if (block == ShitdimensionDimension.portal) {
/* 362 */               this.portalBlockCount++;
/*     */             }
/* 364 */             if (i == 0) {
/* 365 */               BlockPos framePos = blockpos.func_177972_a(this.leftDir);
/* 366 */               if (this.world.func_180495_p(framePos).func_177230_c() != ShitblockBlock.block.func_176223_P().func_177230_c()) {
/*     */                 break label41;
/*     */               }
/* 369 */             } else if (i == this.width - 1) {
/* 370 */               BlockPos framePos = blockpos.func_177972_a(this.rightDir);
/* 371 */               if (this.world.func_180495_p(framePos).func_177230_c() != ShitblockBlock.block.func_176223_P().func_177230_c()) {
/*     */                 break label41;
/*     */               }
/*     */             } 
/*     */           } 
/*     */         } 
/* 377 */         for (int j = 0; j < this.width; j++) {
/* 378 */           BlockPos framePos = this.bottomLeft.func_177967_a(this.rightDir, j).func_177981_b(this.height);
/* 379 */           if (this.world.func_180495_p(framePos).func_177230_c() != ShitblockBlock.block.func_176223_P().func_177230_c()) {
/* 380 */             this.height = 0;
/*     */             break;
/*     */           } 
/*     */         } 
/* 384 */         if (this.height <= 21 && this.height >= 3) {
/* 385 */           return this.height;
/*     */         }
/* 387 */         this.bottomLeft = null;
/* 388 */         this.width = 0;
/* 389 */         this.height = 0;
/* 390 */         return 0;
/*     */       }
/*     */ 
/*     */       
/*     */       protected boolean func_196900_a(BlockState pos) {
/* 395 */         Block block = pos.func_177230_c();
/* 396 */         return (pos.func_196958_f() || block == Blocks.field_150480_ab || block == ShitdimensionDimension.portal);
/*     */       }
/*     */       
/*     */       public boolean isValid() {
/* 400 */         return (this.bottomLeft != null && this.width >= 2 && this.width <= 21 && this.height >= 3 && this.height <= 21);
/*     */       }
/*     */       
/*     */       public void placePortalBlocks() {
/* 404 */         for (int i = 0; i < this.width; i++) {
/* 405 */           BlockPos blockpos = this.bottomLeft.func_177967_a(this.rightDir, i);
/* 406 */           for (int j = 0; j < this.height; j++) {
/* 407 */             this.world.func_180501_a(blockpos.func_177981_b(j), (BlockState)ShitdimensionDimension.portal.func_176223_P().func_206870_a((IProperty)NetherPortalBlock.field_176550_a, (Comparable)this.axis), 18);
/*     */           }
/*     */         } 
/*     */       }
/*     */       
/*     */       private boolean func_196899_f() {
/* 413 */         return (this.portalBlockCount >= this.width * this.height);
/*     */       }
/*     */       
/*     */       public boolean func_208508_f() {
/* 417 */         return (isValid() && func_196899_f());
/*     */       } } }
/*     */ 
/*     */   
/* 421 */   private static PointOfInterestType poi = null;
/* 422 */   public static final TicketType<BlockPos> CUSTOM_PORTAL = TicketType.func_223183_a("shitdimension_portal", Vec3i::compareTo, 300);
/*     */   @SubscribeEvent
/*     */   public void registerPointOfInterest(RegistryEvent.Register<PointOfInterestType> event) {
/*     */     try {
/* 426 */       Method method = ObfuscationReflectionHelper.findMethod(PointOfInterestType.class, "func_226359_a_", new Class[] { String.class, Set.class, int.class, int.class });
/*     */       
/* 428 */       method.setAccessible(true);
/* 429 */       poi = (PointOfInterestType)method.invoke((Object)null, new Object[] { "shitdimension_portal", 
/* 430 */             Sets.newHashSet((Iterable)ImmutableSet.copyOf((Collection)portal.func_176194_O().func_177619_a())), Integer.valueOf(0), Integer.valueOf(1) });
/* 431 */       event.getRegistry().register((IForgeRegistryEntry)poi);
/* 432 */     } catch (Exception e) {
/* 433 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */   public static class TeleporterDimensionMod implements ITeleporter { private Vec3d lastPortalVec;
/*     */     private Direction teleportDirection;
/*     */     protected final ServerWorld world;
/*     */     protected final Random random;
/*     */     
/*     */     public TeleporterDimensionMod(ServerWorld worldServer, Vec3d lastPortalVec, Direction teleportDirection) {
/* 442 */       this.world = worldServer;
/* 443 */       this.random = new Random(worldServer.func_72905_C());
/* 444 */       this.lastPortalVec = lastPortalVec;
/* 445 */       this.teleportDirection = teleportDirection;
/*     */     }
/*     */ 
/*     */     
/*     */     @Nullable
/*     */     public BlockPattern.PortalInfo placeInExistingPortal(BlockPos p_222272_1_, Vec3d p_222272_2_, Direction directionIn, double p_222272_4_, double p_222272_6_, boolean p_222272_8_) {
/* 451 */       PointOfInterestManager pointofinterestmanager = this.world.func_217443_B();
/* 452 */       pointofinterestmanager.func_226347_a_((IWorldReader)this.world, p_222272_1_, 128);
/*     */ 
/*     */       
/* 455 */       List<PointOfInterest> list = (List<PointOfInterest>)pointofinterestmanager.func_226353_b_(p_226705_0_ -> (p_226705_0_ == ShitdimensionDimension.poi), p_222272_1_, 128, PointOfInterestManager.Status.ANY).collect(Collectors.toList());
/* 456 */       Optional<PointOfInterest> optional = list.stream().min(Comparator.<PointOfInterest>comparingDouble(p_226706_1_ -> p_226706_1_.func_218261_f().func_177951_i((Vec3i)p_222272_1_))
/*     */           
/* 458 */           .thenComparingInt(p_226704_0_ -> p_226704_0_.func_218261_f().func_177956_o()));
/*     */ 
/*     */       
/* 461 */       return optional.<BlockPattern.PortalInfo>map(p_226707_7_ -> {
/*     */             BlockPos blockpos = p_226707_7_.func_218261_f();
/*     */             
/*     */             this.world.func_72863_F().func_217228_a(ShitdimensionDimension.CUSTOM_PORTAL, new ChunkPos(blockpos), 3, blockpos);
/*     */             BlockPattern.PatternHelper blockpattern$patternhelper = ShitdimensionDimension.CustomPortalBlock.createPatternHelper((IWorld)this.world, blockpos);
/*     */             return blockpattern$patternhelper.func_222504_a(directionIn, blockpos, p_222272_6_, p_222272_2_, p_222272_4_);
/* 467 */           }).orElse((BlockPattern.PortalInfo)null);
/*     */     }
/*     */     
/*     */     public boolean placeInPortal(Entity p_222268_1_, float p_222268_2_) {
/* 471 */       Vec3d vec3d = this.lastPortalVec;
/* 472 */       Direction direction = this.teleportDirection;
/* 473 */       BlockPattern.PortalInfo blockpattern$portalinfo = placeInExistingPortal(new BlockPos(p_222268_1_), p_222268_1_.func_213322_ci(), direction, vec3d.field_72450_a, vec3d.field_72448_b, p_222268_1_ instanceof PlayerEntity);
/*     */       
/* 475 */       if (blockpattern$portalinfo == null) {
/* 476 */         return false;
/*     */       }
/* 478 */       Vec3d vec3d1 = blockpattern$portalinfo.field_222505_a;
/* 479 */       Vec3d vec3d2 = blockpattern$portalinfo.field_222506_b;
/* 480 */       p_222268_1_.func_213317_d(vec3d2);
/* 481 */       p_222268_1_.field_70177_z = p_222268_2_ + blockpattern$portalinfo.field_222507_c;
/* 482 */       p_222268_1_.func_225653_b_(vec3d1.field_72450_a, vec3d1.field_72448_b, vec3d1.field_72449_c);
/* 483 */       return true;
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean makePortal(Entity entityIn) {
/* 488 */       int i = 16;
/* 489 */       double d0 = -1.0D;
/* 490 */       int j = MathHelper.func_76128_c(entityIn.func_226277_ct_());
/* 491 */       int k = MathHelper.func_76128_c(entityIn.func_226278_cu_());
/* 492 */       int l = MathHelper.func_76128_c(entityIn.func_226281_cx_());
/* 493 */       int i1 = j;
/* 494 */       int j1 = k;
/* 495 */       int k1 = l;
/* 496 */       int l1 = 0;
/* 497 */       int i2 = this.random.nextInt(4);
/* 498 */       BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable();
/* 499 */       for (int j2 = j - 16; j2 <= j + 16; j2++) {
/* 500 */         double d1 = j2 + 0.5D - entityIn.func_226277_ct_();
/* 501 */         for (int l2 = l - 16; l2 <= l + 16; l2++) {
/* 502 */           double d2 = l2 + 0.5D - entityIn.func_226281_cx_(); int j3;
/* 503 */           label160: for (j3 = this.world.func_72940_L() - 1; j3 >= 0; j3--) {
/* 504 */             if (this.world.func_175623_d((BlockPos)blockpos$mutable.func_181079_c(j2, j3, l2))) {
/* 505 */               while (j3 > 0 && this.world.func_175623_d((BlockPos)blockpos$mutable.func_181079_c(j2, j3 - 1, l2))) {
/* 506 */                 j3--;
/*     */               }
/* 508 */               for (int k3 = i2; k3 < i2 + 4; k3++) {
/* 509 */                 int l3 = k3 % 2;
/* 510 */                 int i4 = 1 - l3;
/* 511 */                 if (k3 % 4 >= 2) {
/* 512 */                   l3 = -l3;
/* 513 */                   i4 = -i4;
/*     */                 } 
/* 515 */                 for (int j4 = 0; j4 < 3; j4++) {
/* 516 */                   for (int k4 = 0; k4 < 4; k4++) {
/* 517 */                     for (int l4 = -1; l4 < 4; ) {
/* 518 */                       int i5 = j2 + (k4 - 1) * l3 + j4 * i4;
/* 519 */                       int j5 = j3 + l4;
/* 520 */                       int k5 = l2 + (k4 - 1) * i4 - j4 * l3;
/* 521 */                       blockpos$mutable.func_181079_c(i5, j5, k5);
/* 522 */                       if (l4 >= 0 || this.world.func_180495_p((BlockPos)blockpos$mutable).func_185904_a().func_76220_a()) { if (l4 >= 0 && 
/* 523 */                           !this.world.func_175623_d((BlockPos)blockpos$mutable))
/*     */                           continue label160;  l4++; }
/*     */                        continue label160;
/*     */                     } 
/*     */                   } 
/*     */                 } 
/* 529 */                 double d5 = j3 + 0.5D - entityIn.func_226278_cu_();
/* 530 */                 double d7 = d1 * d1 + d5 * d5 + d2 * d2;
/* 531 */                 if (d0 < 0.0D || d7 < d0) {
/* 532 */                   d0 = d7;
/* 533 */                   i1 = j2;
/* 534 */                   j1 = j3;
/* 535 */                   k1 = l2;
/* 536 */                   l1 = k3 % 4;
/*     */                 } 
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/* 543 */       if (d0 < 0.0D) {
/* 544 */         for (int l5 = j - 16; l5 <= j + 16; l5++) {
/* 545 */           double d3 = l5 + 0.5D - entityIn.func_226277_ct_();
/* 546 */           for (int j6 = l - 16; j6 <= l + 16; j6++) {
/* 547 */             double d4 = j6 + 0.5D - entityIn.func_226281_cx_(); int i7;
/* 548 */             label158: for (i7 = this.world.func_72940_L() - 1; i7 >= 0; i7--) {
/* 549 */               if (this.world.func_175623_d((BlockPos)blockpos$mutable.func_181079_c(l5, i7, j6))) {
/* 550 */                 while (i7 > 0 && this.world.func_175623_d((BlockPos)blockpos$mutable.func_181079_c(l5, i7 - 1, j6))) {
/* 551 */                   i7--;
/*     */                 }
/* 553 */                 for (int l7 = i2; l7 < i2 + 2; l7++) {
/* 554 */                   int l8 = l7 % 2;
/* 555 */                   int k9 = 1 - l8;
/* 556 */                   for (int i10 = 0; i10 < 4; i10++) {
/* 557 */                     for (int k10 = -1; k10 < 4; ) {
/* 558 */                       int i11 = l5 + (i10 - 1) * l8;
/* 559 */                       int j11 = i7 + k10;
/* 560 */                       int k11 = j6 + (i10 - 1) * k9;
/* 561 */                       blockpos$mutable.func_181079_c(i11, j11, k11);
/* 562 */                       if (k10 >= 0 || this.world.func_180495_p((BlockPos)blockpos$mutable).func_185904_a().func_76220_a()) { if (k10 >= 0 && 
/* 563 */                           !this.world.func_175623_d((BlockPos)blockpos$mutable))
/*     */                           continue label158;  k10++; }
/*     */                        continue label158;
/*     */                     } 
/*     */                   } 
/* 568 */                   double d6 = i7 + 0.5D - entityIn.func_226278_cu_();
/* 569 */                   double d8 = d3 * d3 + d6 * d6 + d4 * d4;
/* 570 */                   if (d0 < 0.0D || d8 < d0) {
/* 571 */                     d0 = d8;
/* 572 */                     i1 = l5;
/* 573 */                     j1 = i7;
/* 574 */                     k1 = j6;
/* 575 */                     l1 = l7 % 2;
/*     */                   } 
/*     */                 } 
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       }
/* 583 */       int i6 = i1;
/* 584 */       int k2 = j1;
/* 585 */       int k6 = k1;
/* 586 */       int l6 = l1 % 2;
/* 587 */       int i3 = 1 - l6;
/* 588 */       if (l1 % 4 >= 2) {
/* 589 */         l6 = -l6;
/* 590 */         i3 = -i3;
/*     */       } 
/* 592 */       if (d0 < 0.0D) {
/* 593 */         j1 = MathHelper.func_76125_a(j1, 70, this.world.func_72940_L() - 10);
/* 594 */         k2 = j1;
/* 595 */         for (int j7 = -1; j7 <= 1; j7++) {
/* 596 */           for (int i8 = 1; i8 < 3; i8++) {
/* 597 */             for (int i9 = -1; i9 < 3; i9++) {
/* 598 */               int l9 = i6 + (i8 - 1) * l6 + j7 * i3;
/* 599 */               int j10 = k2 + i9;
/* 600 */               int l10 = k6 + (i8 - 1) * i3 - j7 * l6;
/* 601 */               boolean flag = (i9 < 0);
/* 602 */               blockpos$mutable.func_181079_c(l9, j10, l10);
/* 603 */               this.world.func_175656_a((BlockPos)blockpos$mutable, flag ? ShitblockBlock.block
/* 604 */                   .func_176223_P().func_177230_c().func_176223_P() : Blocks.field_150350_a.func_176223_P());
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/* 609 */       for (int k7 = -1; k7 < 3; k7++) {
/* 610 */         for (int j8 = -1; j8 < 4; j8++) {
/* 611 */           if (k7 == -1 || k7 == 2 || j8 == -1 || j8 == 3) {
/* 612 */             blockpos$mutable.func_181079_c(i6 + k7 * l6, k2 + j8, k6 + k7 * i3);
/* 613 */             this.world.func_180501_a((BlockPos)blockpos$mutable, ShitblockBlock.block.func_176223_P().func_177230_c().func_176223_P(), 3);
/*     */           } 
/*     */         } 
/*     */       } 
/* 617 */       BlockState blockstate = (BlockState)ShitdimensionDimension.portal.func_176223_P().func_206870_a((IProperty)NetherPortalBlock.field_176550_a, (l6 == 0) ? (Comparable)Direction.Axis.Z : (Comparable)Direction.Axis.X);
/* 618 */       for (int k8 = 0; k8 < 2; k8++) {
/* 619 */         for (int j9 = 0; j9 < 3; j9++) {
/* 620 */           blockpos$mutable.func_181079_c(i6 + k8 * l6, k2 + j9, k6 + k8 * i3);
/* 621 */           this.world.func_180501_a((BlockPos)blockpos$mutable, blockstate, 18);
/* 622 */           this.world.func_217443_B().func_219135_a((BlockPos)blockpos$mutable, ShitdimensionDimension.poi);
/*     */         } 
/*     */       } 
/* 625 */       return true;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public Entity placeEntity(Entity entity, ServerWorld serverworld, ServerWorld serverworld1, float yaw, Function<Boolean, Entity> repositionEntity) {
/* 631 */       double d0 = entity.func_226277_ct_();
/* 632 */       double d1 = entity.func_226278_cu_();
/* 633 */       double d2 = entity.func_226281_cx_();
/* 634 */       if (entity instanceof ServerPlayerEntity) {
/* 635 */         entity.func_70012_b(d0, d1, d2, yaw, entity.field_70125_A);
/* 636 */         if (!placeInPortal(entity, yaw)) {
/* 637 */           makePortal(entity);
/* 638 */           placeInPortal(entity, yaw);
/*     */         } 
/* 640 */         entity.func_70029_a((World)serverworld1);
/* 641 */         serverworld1.func_217447_b((ServerPlayerEntity)entity);
/* 642 */         ((ServerPlayerEntity)entity).field_71135_a.func_147364_a(entity.func_226277_ct_(), entity.func_226278_cu_(), entity.func_226281_cx_(), yaw, entity.field_70125_A);
/*     */         
/* 644 */         return entity;
/*     */       } 
/* 646 */       Vec3d vec3d = entity.func_213322_ci();
/* 647 */       BlockPos blockpos = new BlockPos(d0, d1, d2);
/* 648 */       BlockPattern.PortalInfo blockpattern$portalinfo = placeInExistingPortal(blockpos, vec3d, this.teleportDirection, this.lastPortalVec.field_72450_a, this.lastPortalVec.field_72448_b, entity instanceof PlayerEntity);
/*     */       
/* 650 */       if (blockpattern$portalinfo == null)
/* 651 */         return null; 
/* 652 */       blockpos = new BlockPos(blockpattern$portalinfo.field_222505_a);
/* 653 */       vec3d = blockpattern$portalinfo.field_222506_b;
/* 654 */       float f = blockpattern$portalinfo.field_222507_c;
/* 655 */       Entity entityNew = entity.func_200600_R().func_200721_a((World)serverworld1);
/* 656 */       if (entityNew != null) {
/* 657 */         entityNew.func_180432_n(entity);
/* 658 */         entityNew.func_174828_a(blockpos, entityNew.field_70177_z + f, entityNew.field_70125_A);
/* 659 */         entityNew.func_213317_d(vec3d);
/* 660 */         serverworld1.func_217460_e(entityNew);
/*     */       } 
/* 662 */       return entityNew;
/*     */     } }
/*     */ 
/*     */   
/*     */   public static class CustomModDimension
/*     */     extends ModDimension
/*     */   {
/*     */     public BiFunction<World, DimensionType, ? extends Dimension> getFactory() {
/* 670 */       return CustomDimension::new;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class CustomDimension extends Dimension {
/* 675 */     private ShitdimensionDimension.BiomeProviderCustom biomeProviderCustom = null;
/*     */     public CustomDimension(World world, DimensionType type) {
/* 677 */       super(world, type, 0.5F);
/* 678 */       this.field_76576_e = false;
/*     */     }
/*     */ 
/*     */     
/*     */     @OnlyIn(Dist.CLIENT)
/*     */     public Vec3d func_76562_b(float cangle, float ticks) {
/* 684 */       return new Vec3d(0.545098039216D, 0.270588235294D, 0.098039215686D);
/*     */     }
/*     */ 
/*     */     
/*     */     public ChunkGenerator<?> func_186060_c() {
/* 689 */       if (this.biomeProviderCustom == null) {
/* 690 */         this.biomeProviderCustom = new ShitdimensionDimension.BiomeProviderCustom(this.field_76579_a);
/*     */       }
/* 692 */       return (ChunkGenerator<?>)new ShitdimensionDimension.ChunkProviderModded((IWorld)this.field_76579_a, this.biomeProviderCustom);
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean func_76569_d() {
/* 697 */       return true;
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean func_76567_e() {
/* 702 */       return false;
/*     */     }
/*     */ 
/*     */     
/*     */     @OnlyIn(Dist.CLIENT)
/*     */     public boolean func_76568_b(int x, int z) {
/* 708 */       return true;
/*     */     }
/*     */ 
/*     */     
/*     */     public IForgeDimension.SleepResult canSleepAt(PlayerEntity player, BlockPos pos) {
/* 713 */       return IForgeDimension.SleepResult.ALLOW;
/*     */     }
/*     */     
/*     */     @Nullable
/*     */     public BlockPos func_206920_a(ChunkPos chunkPos, boolean checkValid) {
/* 718 */       return null;
/*     */     }
/*     */     
/*     */     @Nullable
/*     */     public BlockPos func_206921_a(int x, int z, boolean checkValid) {
/* 723 */       return null;
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean func_177500_n() {
/* 728 */       return false;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public float func_76563_a(long worldTime, float partialTicks) {
/* 736 */       double d0 = MathHelper.func_181162_h(worldTime / 24000.0D - 0.25D);
/* 737 */       double d1 = 0.5D - Math.cos(d0 * Math.PI) / 2.0D;
/* 738 */       return (float)(d0 * 2.0D + d1) / 3.0F;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class ChunkProviderModded extends OverworldChunkGenerator {
/*     */     public ChunkProviderModded(IWorld world, BiomeProvider provider) {
/* 744 */       super(world, provider, new OverworldGenSettings() {
/*     */             public BlockState func_205532_l() {
/* 746 */               return ShitresidueBlock.block.func_176223_P();
/*     */             }
/*     */             
/*     */             public BlockState func_205533_m() {
/* 750 */               return ShitliquidBlock.block.func_176223_P();
/*     */             }
/*     */           });
/* 753 */       this.field_222558_e.func_202423_a(5349);
/*     */     }
/*     */ 
/*     */     
/*     */     public void func_203222_a(ServerWorld worldIn, boolean spawnHostileMobs, boolean spawnPeacefulMobs) {}
/*     */   }
/*     */   
/*     */   public static class BiomeLayerCustom
/*     */     implements IC0Transformer
/*     */   {
/*     */     public int func_202726_a(INoiseRandom context, int value) {
/* 764 */       return Registry.field_212624_m.func_148757_b(ShitdimensionDimension.dimensionBiomes[context.func_202696_a(ShitdimensionDimension.dimensionBiomes.length)]);
/*     */     } }
/*     */   
/*     */   public static class BiomeProviderCustom extends BiomeProvider {
/*     */     private Layer genBiomes;
/*     */     private static boolean biomesPatched = false;
/*     */     
/*     */     public BiomeProviderCustom(World world) {
/* 772 */       super(new HashSet(Arrays.asList((Object[])ShitdimensionDimension.dimensionBiomes)));
/* 773 */       this.genBiomes = getBiomeLayer(world.func_72905_C());
/* 774 */       if (!biomesPatched) {
/* 775 */         for (Biome biome : this.field_226837_c_) {
/* 776 */           biome.func_203609_a(GenerationStage.Carving.AIR, Biome.func_203606_a((WorldCarver)new CaveWorldCarver(ProbabilityConfig::func_214645_a, 256) {  }(ICarverConfig)new ProbabilityConfig(0.14285715F)));
/*     */         } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 784 */         biomesPatched = true;
/*     */       } 
/*     */     }
/*     */     
/*     */     public Biome func_225526_b_(int x, int y, int z) {
/* 789 */       return this.genBiomes.func_215738_a(x, z);
/*     */     }
/*     */     
/*     */     private Layer getBiomeLayer(long seed) {
/* 793 */       LongFunction<IExtendedNoiseRandom<LazyArea>> contextFactory = l -> new LazyAreaLayerContext(25, seed, l);
/* 794 */       IAreaFactory<LazyArea> parentLayer = IslandLayer.INSTANCE.func_202823_a(contextFactory.apply(1L));
/* 795 */       IAreaFactory<LazyArea> biomeLayer = (new ShitdimensionDimension.BiomeLayerCustom()).func_202713_a(contextFactory.apply(200L), parentLayer);
/* 796 */       biomeLayer = ZoomLayer.NORMAL.func_202713_a(contextFactory.apply(1000L), biomeLayer);
/* 797 */       biomeLayer = ZoomLayer.NORMAL.func_202713_a(contextFactory.apply(1001L), biomeLayer);
/* 798 */       biomeLayer = ZoomLayer.NORMAL.func_202713_a(contextFactory.apply(1002L), biomeLayer);
/* 799 */       biomeLayer = ZoomLayer.NORMAL.func_202713_a(contextFactory.apply(1003L), biomeLayer);
/* 800 */       biomeLayer = ZoomLayer.NORMAL.func_202713_a(contextFactory.apply(1004L), biomeLayer);
/* 801 */       biomeLayer = ZoomLayer.NORMAL.func_202713_a(contextFactory.apply(1005L), biomeLayer);
/* 802 */       return new Layer(biomeLayer);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/somebody/Downloads/ShittyOre.1.15.2.jar!/net/mcreator/shittyore/world/dimension/ShitdimensionDimension.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */