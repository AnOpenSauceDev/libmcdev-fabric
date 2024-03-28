package com.github.anopensaucedev.libmcdevfabric.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;

import java.util.function.Predicate;


/**
 * A bunch of simple predicates.
 */
public class HelperPredicates {

    public static final Predicate<Entity> NOT_PLAYER = entity -> !entity.isPlayer();
    public static final Predicate<Entity> IS_DEAD = entity -> !entity.isAlive();
    public static final Predicate<Entity> IN_WATER = entity -> entity.isSubmergedInWater();
    public static final  Predicate<LivingEntity> LOW_HEALTH = livingEntity -> livingEntity.getHealth() <= 3;
    public static final  Predicate<LivingEntity> FULL_HEALTH = livingEntity -> livingEntity.getHealth() == livingEntity.getMaxHealth();



}
