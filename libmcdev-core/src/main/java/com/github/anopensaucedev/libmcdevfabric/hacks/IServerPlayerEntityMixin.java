package com.github.anopensaucedev.libmcdevfabric.hacks;

import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Unique;

public interface IServerPlayerEntityMixin {

    @Unique
    void SetCameraEntityProperly(Entity entity);
}
