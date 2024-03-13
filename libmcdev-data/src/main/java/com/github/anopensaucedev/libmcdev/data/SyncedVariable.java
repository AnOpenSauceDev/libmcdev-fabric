package com.github.anopensaucedev.libmcdev.data;

import com.github.anopensaucedev.libmcdevfabric.Libmcdev;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import java.util.List;


 public class SyncedVariable { // a synced "key" that is persistent (Why do I keep making variants of HashMap?). Once the value updates, the "recivers" will have the value updated.
    // so let's say that person A (sender) changes "foo" to "bar", Person B, C and the server will change that value to represent it. All senders can receive, but not all receivers can send.

     public static Logger variableLogger = LoggerFactory.getLogger("libMCdev Data API");

    public List<ServerPlayerEntity> receivers;
    public List<ServerPlayerEntity> senders;

    public boolean receiversAreEveryone, receiversAreAlsoSenders;

     /**
      * @param data
      * @param ID
      * @param receiversList List of people who can receive, leave null to have everyone as a receiver.
      * @param sendersList List of people who can send, leave null for everyone who is a receiver.
      */
    public SyncedVariable(byte[] data, Identifier ID, List<ServerPlayerEntity> receiversList, List<ServerPlayerEntity> sendersList){
        this.data = data;
        this.ID = ID;
        if(receiversList != null){
            receivers = receiversList;
        }else {
            receiversAreEveryone = true;
        }
        if(sendersList != null){
            senders = sendersList;
        }else {
            receiversAreAlsoSenders = true;
        }
        init();
    }

    public void init(){
        if(!Libmcdev.isClient){
            ServerPlayNetworking.registerGlobalReceiver(ID, ((server, player, handler, buf, responseSender) -> {

                if(receiversAreEveryone){ receivers = server.getPlayerManager().getPlayerList();}

                if(receiversAreAlsoSenders){ senders = receivers;}

                if(senders.contains(player)){
                receivers.parallelStream().forEach((entity -> {
                    data = buf.readByteArray(); // grab the data
                    ServerPlayNetworking.send(entity,ID,buf); // send it out to everyone else
                }));
                }else {
                    variableLogger.warn("unprivileged user " + player.getName().getLiteralString() + "tried to send data as a sender.");
                }
            }));
        }else {
            ClientPlayNetworking.registerGlobalReceiver(ID,(client, handler, buf, responseSender) -> {
               data = buf.readByteArray();
            });
        }
    }

    Identifier ID;
    public byte[] data;

    public void update(byte[] sendData){
        if(Libmcdev.isClient){
            data = sendData;
        }
        PacketByteBuf buf = PacketByteBufs.create();
        buf.writeBytes(data);
        ClientPlayNetworking.send(ID,buf);
    }


}
