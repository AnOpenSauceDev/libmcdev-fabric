package com.github.anopensaucedev.libmcdevfabric.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.World;

public class DisplayEntity extends MobEntity {


    public DisplayEntity(EntityType<? extends MobEntity> entityType, World world) {
        super(entityType, world);
    }
}
