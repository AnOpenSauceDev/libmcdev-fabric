package com.github.anopensaucedev.libmcdevfabric.entity;

import net.minecraft.entity.Entity;

import java.util.function.Predicate;


/**
 * A bunch of simple predicates.
 */
public class HelperPredicates {

    public static final Predicate<Entity> NOT_PLAYER = entity -> !entity.isPlayer();
    public static final Predicate<Entity> IS_DEAD = entity -> !entity.isAlive();
    public static final Predicate<Entity> IN_WATER = entity -> entity.isSubmergedInWater();



}
