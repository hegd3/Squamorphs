package com.hedge.squamorphs.client.particle.sonic_boom;

import com.hedge.squamorphs.client.particle.ModParticles;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.util.FastColor;
import org.joml.Vector3f;

import java.util.Locale;

public class ElementalSonicBoomParticleOptions implements ParticleOptions {


    private final Vector3f color;


    public ElementalSonicBoomParticleOptions(int color) {
        float r = Math.min(FastColor.ARGB32.red(color) / 255F, 1);
        float g = Math.min(FastColor.ARGB32.green(color) / 255F, 1);
        float b = Math.min(FastColor.ARGB32.blue(color) / 255F, 1);

        this.color = new Vector3f(r, g, b);
    }

    public ElementalSonicBoomParticleOptions(float r, float g, float b) {

        this.color = new Vector3f(r, g, b);

    }




    public Vector3f getColor() {
        return this.color;
    }

    public static final Codec<ElementalSonicBoomParticleOptions> CODEC =
            RecordCodecBuilder.create(instance ->
                    instance.group(
                            Codec.FLOAT.fieldOf("r").forGetter(o -> o.getColor().x),
                            Codec.FLOAT.fieldOf("g").forGetter(o -> o.getColor().y),
                            Codec.FLOAT.fieldOf("b").forGetter(o -> o.getColor().z)
                    ).apply(instance, ElementalSonicBoomParticleOptions::new)
            );

    public static final Deserializer<ElementalSonicBoomParticleOptions> DESERIALIZER =
            new Deserializer<>() {
                @Override
                public ElementalSonicBoomParticleOptions fromCommand(
                        ParticleType<ElementalSonicBoomParticleOptions> type,
                        StringReader reader
                ) throws CommandSyntaxException {
                    reader.expect(' ');
                    float r = reader.readFloat();
                    reader.expect(' ');
                    float g = reader.readFloat();
                    reader.expect(' ');
                    float b = reader.readFloat();
                    return new ElementalSonicBoomParticleOptions(r, g, b);
                }

                @Override
                public ElementalSonicBoomParticleOptions fromNetwork(
                        ParticleType<ElementalSonicBoomParticleOptions> type,
                        FriendlyByteBuf buf
                ) {
                    return new ElementalSonicBoomParticleOptions(
                            buf.readFloat(),
                            buf.readFloat(),
                            buf.readFloat()
                    );
                }
            };



    @Override
    public ParticleType<?> getType() {
        return ModParticles.GENERIC_SONIC_BOOM.get();
    }


    @Override
    public void writeToNetwork(FriendlyByteBuf pBuffer) {
        pBuffer.writeFloat(this.color.x());
        pBuffer.writeFloat(this.color.y());
        pBuffer.writeFloat(this.color.z());

    }

    public String writeToString() {
        return String.format(Locale.ROOT, "%s %.2f %.2f %.2f", BuiltInRegistries.PARTICLE_TYPE.getKey(this.getType()), this.color.x(), this.color.y(), this.color.z());
    }
}
