package com.github.anopensaucedev.libmcdevfabric.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.predicate.entity.EntityPredicates;

import java.util.function.Predicate;

public class HelperPredicates {

    public static final Predicate<Entity> NOT_PLAYER = entity -> !entity.isPlayer();
    public static final Predicate<Entity> IS_DEAD = entity -> !entity.isAlive();
    public static final Predicate<Entity> IN_WATER = entity -> entity.isSubmergedInWater();


}
