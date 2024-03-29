package com.github.anopensaucedev.libmcdevfabric.entity;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.Box;
import org.joml.Vector3d;

import java.util.List;
import java.util.function.Predicate;

public class EntityUtils {


    public static List<Entity> getEntitiesInRadius(Predicate<Entity> predicate, int radius, Entity source){
        return source.getWorld().getOtherEntities(source,new Box(source.getX() - radius, source.getY() - radius, source.getZ() - radius,source.getX() + radius, source.getY() + radius, source.getZ() + radius),predicate);
    }

    /**
     *
     * @param compensate whether to compensate for the player's initial height.
     * @param entities your entities
     * @return
     */
    public static Vector3d CreateMeanPosOfEntities(boolean compensate, Entity... entities){

        Vector3d entitymean = new Vector3d();

        for (Entity entity : entities){

            entitymean.x += entity.getX();
            entitymean.y += entity.getY();
            entitymean.z += entity.getZ();

        }

        entitymean.x /= entities.length;
        entitymean.y /= entities.length;
        entitymean.z /= entities.length;

        if(compensate){
            entitymean.y += 2;
        }

        return  entitymean;

    }

}
