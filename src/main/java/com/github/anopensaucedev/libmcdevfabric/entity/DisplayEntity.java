package com.github.anopensaucedev.libmcdevfabric.entity;

import com.github.anopensaucedev.libmcdevfabric.media.ImageSequenceMovie;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.World;

public class DisplayEntity extends MobEntity {

    ImageSequenceMovie storedMovie = new ImageSequenceMovie(6572); // 6572 == the length of Bad Apple!!! in frames (video is @30FPS)

    public DisplayEntity(EntityType<? extends MobEntity> entityType, World world) {
        super(entityType, world);
    }
}
