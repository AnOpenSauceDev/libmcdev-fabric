package com.github.anopensaucedev.libmcdevfabric.render;

import net.fabricmc.loader.impl.lib.sat4j.core.Vec;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Vec3d;
import org.joml.Matrix3f;
import org.joml.Matrix4f;
import org.joml.Vector3f;

//warcrimes, that probably break Sodium, semi-based off of BeaconBlockEntityRenderer
public class ArbitraryRenderer {

    // render a cube at a location
    public static void renderArbitraryCube(MatrixStack matrices, VertexConsumerProvider vertexConsumers, float[] pos){

        matrices.push();

        // render cube geom

        Matrix4f positionMatrix = matrices.peek().getPositionMatrix();
        Matrix3f normalMatrix = matrices.peek().getNormalMatrix();

        VertexConsumer consumer = vertexConsumers.getBuffer(RenderLayer.getGlint());

        /*
        renderQuadFace(consumer,positionMatrix,normalMatrix,); // 4 faces, 4 vertices per face, 16 vertex points.
        renderQuadFace();
        renderQuadFace();
        renderQuadFace();

         */


        matrices.pop();

    }

    public static void renderArbitraryFace(MatrixStack matrices, VertexConsumerProvider vertexConsumers, Vector3f pos){

        matrices.push();

        Matrix4f positionMatrix = matrices.peek().getPositionMatrix();
        Matrix3f normalMatrix = matrices.peek().getNormalMatrix();

        VertexConsumer consumer = vertexConsumers.getBuffer(RenderLayer.getEndGateway());

        renderQuadFace(consumer,positionMatrix,normalMatrix,pos.x,pos.y,pos.z);

        matrices.pop();
    }


    // xyz = center
    public static void renderQuadFace(VertexConsumer consumer, Matrix4f pos, Matrix3f norm, float x, float y, float z){
        var offset = 0.5f;

        // top left, move up on your right hand 0.5, and left from your right thumb 0.5
        renderVertex(pos,norm,consumer,x - offset,y + offset,z);
        // top right
        renderVertex(pos,norm,consumer, x + offset, y + offset,z);
        // bottom left
        renderVertex(pos,norm,consumer,x - offset, y - offset,z);
        // bottom right
        renderVertex(pos,norm,consumer,x + offset, y - offset, z);
    }

    public static void renderVertex(Matrix4f position, Matrix3f normal,VertexConsumer vertices, float x, float y, float z){
        // render a vertex in pure white
        vertices.vertex(position,x,y,z).color(0xFFFFFFFF).next();
    }

}
