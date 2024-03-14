package com.github.anopensaucedev.libmcdev.test.mixin;

import com.github.anopensaucedev.libmcdev.test.DustPuffParticle;
import com.github.anopensaucedev.libmcdev.test.libMCdevTests;
import com.github.anopensaucedev.libmcdevfabric.Libmcdev;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.particle.Particle;
import net.minecraft.command.argument.ParticleEffectArgumentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends Entity {

    public PlayerEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Shadow protected abstract boolean isOnSoulSpeedBlock();

    @Shadow public abstract float getMovementSpeed();

    ThreadLocalRandom random = ThreadLocalRandom.current();

    @Inject(method = "travel",at = @At("HEAD"))
    public void kickUpDust(Vec3d movementInput, CallbackInfo ci){

        if(isOnGround() && isOnDustBlock() && !getWorld().isClient && isSprinting()){


            getServer().getWorld(((ServerWorld) getWorld()).getRegistryKey()).spawnParticles(libMCdevTests.DUST_PUFF,getX() + random.nextFloat(-1,1),getY(), getZ() + random.nextFloat(-1,1),1,0.0,0.0,0.0,0);
        }
    }

    @Unique
    public final Block[] DUST_BLOCKS = {Blocks.GRAVEL, Blocks.SAND,Blocks.RED_SAND};

    @Unique
    public boolean isOnDustBlock(){
        return Arrays.asList(DUST_BLOCKS).contains(getWorld().getBlockState(this.getVelocityAffectingPos()).getBlock());
    }

}
