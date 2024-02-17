package com.github.anopensaucedev.libmcdev.test.displayEntity;

import com.github.anopensaucedev.libmcdev.media.ImageSequenceMovie;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.world.World;

public class DeveloperDisplayEntity extends MobEntity {

    ImageSequenceMovie storedMovie = new ImageSequenceMovie(6572); // 6572 == the length of "Bad Apple!!!" in frames (video is @30FPS)

    public DeveloperDisplayEntity(EntityType<? extends MobEntity> entityType, World world) {
        super(entityType, world);
    }
}
