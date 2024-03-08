package com.github.anopensaucedev.libmcdev.data;

import com.github.anopensaucedev.libmcdevfabric.Libmcdev;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;

public class SyncedVariable { // a synced "key" that is persistent

    public SyncedVariable(byte[] data, Identifier ID){
        this.data = data;
        this.ID = ID;
    }

    Identifier ID;
    byte[] data;

    public void update(byte[] data){
        if(Libmcdev.isClient){
            // client variables will not be synced.
        }else {
            PacketByteBuf buf = PacketByteBufs.create();
            buf.writeBytes(data);
            ClientPlayNetworking.send(ID,buf);
        }
    }


}
