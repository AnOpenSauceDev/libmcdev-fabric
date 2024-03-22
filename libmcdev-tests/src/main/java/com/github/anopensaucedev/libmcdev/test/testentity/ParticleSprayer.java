package com.github.anopensaucedev.libmcdev.test.testentity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.World;

public class ParticleSprayer extends Entity {


    public ParticleSprayer(EntityType<?> type, World world) {
        super(type, world);
    }

    

    @Override
    protected void initDataTracker() {

    }

    @Override
    protected void readCustomDataFromNbt(NbtCompound nbt) {

    }

    @Override
    protected void writeCustomDataToNbt(NbtCompound nbt) {

    }
}
