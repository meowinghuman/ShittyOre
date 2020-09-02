/*     */ package net.mcreator.shittyore.entity;
/*     */ import net.mcreator.shittyore.ShittyOreModElements;
/*     */ import net.mcreator.shittyore.ShittyOreModElements.ModElement.Tag;
/*     */ import net.minecraft.block.BlockState;
/*     */ import net.minecraft.client.renderer.entity.EntityRenderer;
/*     */ import net.minecraft.client.renderer.entity.EntityRendererManager;
/*     */ import net.minecraft.client.renderer.entity.MobRenderer;
/*     */ import net.minecraft.client.renderer.entity.model.EntityModel;
/*     */ import net.minecraft.client.renderer.entity.model.SpiderModel;
/*     */ import net.minecraft.entity.CreatureAttribute;
/*     */ import net.minecraft.entity.CreatureEntity;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityClassification;
/*     */ import net.minecraft.entity.EntitySpawnPlacementRegistry;
/*     */ import net.minecraft.entity.EntityType;
/*     */ import net.minecraft.entity.MobEntity;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.goal.Goal;
/*     */ import net.minecraft.entity.ai.goal.HurtByTargetGoal;
/*     */ import net.minecraft.entity.ai.goal.LookRandomlyGoal;
/*     */ import net.minecraft.entity.ai.goal.MeleeAttackGoal;
/*     */ import net.minecraft.entity.ai.goal.RandomWalkingGoal;
/*     */ import net.minecraft.entity.ai.goal.SwimGoal;
/*     */ import net.minecraft.entity.monster.MonsterEntity;
/*     */ import net.minecraft.entity.monster.SpiderEntity;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemGroup;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.item.SpawnEggItem;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.IItemProvider;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.SoundEvent;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.biome.Biome;
/*     */ import net.minecraft.world.gen.Heightmap;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.api.distmarker.OnlyIn;
/*     */ import net.minecraftforge.common.DungeonHooks;
/*     */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*     */ import net.minecraftforge.fml.client.registry.RenderingRegistry;
/*     */ import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
/*     */ import net.minecraftforge.fml.network.FMLPlayMessages;
/*     */ import net.minecraftforge.fml.network.NetworkHooks;
/*     */ import net.minecraftforge.registries.ForgeRegistries;
/*     */ 
/*     */ @Tag
/*     */ public class ShitmonsterEntity extends ShittyOreModElements.ModElement {
/*  50 */   public static EntityType entity = null;
/*     */   public ShitmonsterEntity(ShittyOreModElements instance) {
/*  52 */     super(instance, 25);
/*  53 */     FMLJavaModLoadingContext.get().getModEventBus().register(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void initElements() {
/*  60 */     entity = (EntityType)EntityType.Builder.func_220322_a(CustomEntity::new, EntityClassification.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(CustomEntity::new).func_220321_a(1.4F, 0.9F).func_206830_a("shitmonster").setRegistryName("shitmonster");
/*  61 */     this.elements.entities.add(() -> entity);
/*  62 */     this.elements.items.add(() -> (Item)(new SpawnEggItem(entity, -1, -1, (new Item.Properties()).func_200916_a(ItemGroup.field_78026_f))).setRegistryName("shitmonster"));
/*     */   }
/*     */ 
/*     */   
/*     */   public void init(FMLCommonSetupEvent event) {
/*  67 */     for (Biome biome : ForgeRegistries.BIOMES.getValues()) {
/*  68 */       biome.func_76747_a(EntityClassification.MONSTER).add(new Biome.SpawnListEntry(entity, 20, 1, 4));
/*     */     }
/*  70 */     EntitySpawnPlacementRegistry.func_209343_a(entity, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, MonsterEntity::func_223324_d);
/*     */     
/*  72 */     DungeonHooks.addDungeonMob(entity, 180);
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public void registerModels(ModelRegistryEvent event) {
/*  78 */     RenderingRegistry.registerEntityRenderingHandler(entity, renderManager -> new MobRenderer(renderManager, (EntityModel)new SpiderModel(), 0.5F)
/*     */         {
/*     */           public ResourceLocation func_110775_a(Entity entity) {
/*  81 */             return new ResourceLocation("shitty_ore:textures/spider.png");
/*     */           }
/*     */         });
/*     */   }
/*     */   
/*     */   public static class CustomEntity extends SpiderEntity { public CustomEntity(FMLPlayMessages.SpawnEntity packet, World world) {
/*  87 */       this(ShitmonsterEntity.entity, world);
/*     */     }
/*     */     
/*     */     public CustomEntity(EntityType<CustomEntity> type, World world) {
/*  91 */       super(type, world);
/*  92 */       this.field_70728_aV = 5;
/*  93 */       func_94061_f(false);
/*     */     }
/*     */ 
/*     */     
/*     */     public IPacket<?> func_213297_N() {
/*  98 */       return NetworkHooks.getEntitySpawningPacket((Entity)this);
/*     */     }
/*     */ 
/*     */     
/*     */     protected void func_184651_r() {
/* 103 */       super.func_184651_r();
/* 104 */       this.field_70714_bg.func_75776_a(1, (Goal)new MeleeAttackGoal((CreatureEntity)this, 1.2D, false));
/* 105 */       this.field_70714_bg.func_75776_a(2, (Goal)new RandomWalkingGoal((CreatureEntity)this, 1.0D));
/* 106 */       this.field_70715_bh.func_75776_a(3, (Goal)new HurtByTargetGoal((CreatureEntity)this, new Class[0]));
/* 107 */       this.field_70714_bg.func_75776_a(4, (Goal)new LookRandomlyGoal((MobEntity)this));
/* 108 */       this.field_70714_bg.func_75776_a(5, (Goal)new SwimGoal((MobEntity)this));
/*     */     }
/*     */ 
/*     */     
/*     */     public CreatureAttribute func_70668_bt() {
/* 113 */       return CreatureAttribute.field_223223_b_;
/*     */     }
/*     */     
/*     */     protected void func_213333_a(DamageSource source, int looting, boolean recentlyHitIn) {
/* 117 */       super.func_213333_a(source, looting, recentlyHitIn);
/* 118 */       func_199701_a_(new ItemStack((IItemProvider)ShitnuggetItem.block, 1));
/*     */     }
/*     */ 
/*     */     
/*     */     public SoundEvent func_184639_G() {
/* 123 */       return (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.spider.ambient"));
/*     */     }
/*     */ 
/*     */     
/*     */     public void func_180429_a(BlockPos pos, BlockState blockIn) {
/* 128 */       func_184185_a((SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.spider.step")), 0.15F, 1.0F);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public SoundEvent func_184601_bQ(DamageSource ds) {
/* 134 */       return (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.spider.hurt"));
/*     */     }
/*     */ 
/*     */     
/*     */     public SoundEvent func_184615_bR() {
/* 139 */       return (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.spider.death"));
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean func_70097_a(DamageSource source, float amount) {
/* 144 */       if (source.func_76364_f() instanceof net.minecraft.entity.projectile.PotionEntity)
/* 145 */         return false; 
/* 146 */       if (source == DamageSource.field_76379_h)
/* 147 */         return false; 
/* 148 */       return super.func_70097_a(source, amount);
/*     */     }
/*     */ 
/*     */     
/*     */     protected void func_110147_ax() {
/* 153 */       super.func_110147_ax();
/* 154 */       if (func_110148_a(SharedMonsterAttributes.field_111263_d) != null)
/* 155 */         func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.3D); 
/* 156 */       if (func_110148_a(SharedMonsterAttributes.field_111267_a) != null)
/* 157 */         func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(30.0D); 
/* 158 */       if (func_110148_a(SharedMonsterAttributes.field_188791_g) != null)
/* 159 */         func_110148_a(SharedMonsterAttributes.field_188791_g).func_111128_a(0.4D); 
/* 160 */       if (func_110148_a(SharedMonsterAttributes.field_111264_e) == null)
/* 161 */         func_110140_aT().func_111150_b(SharedMonsterAttributes.field_111264_e); 
/* 162 */       func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(5.0D);
/*     */     } }
/*     */ 
/*     */ }


/* Location:              /home/somebody/Downloads/ShittyOre.1.15.2.jar!/net/mcreator/shittyore/entity/ShitmonsterEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */