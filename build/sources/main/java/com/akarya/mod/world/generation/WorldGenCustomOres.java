package com.akarya.mod.world.generation;

import com.akarya.mod.init.BlockInit;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

public class WorldGenCustomOres implements IWorldGenerator {

    private WorldGenerator akaryia_ore;

    public WorldGenCustomOres()
    {
        akaryia_ore = new WorldGenMinable(BlockInit.AKARYIA_ORE.getDefaultState(), 6, BlockMatcher.forBlock(Blocks.STONE));
    }

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {

        switch (world.provider.getDimension()) {

            case -1:
                runGenerator(akaryia_ore, world, random, chunkX, chunkZ, 30, 2, 16);
                break;

        }}

    public void runGenerator(WorldGenerator gen, World world, Random rand, int chunkX, int chunkZ, int chance, int minHeigth, int maxHeigth)
    {
        if(minHeigth > maxHeigth || minHeigth < 0 || maxHeigth > 256) throw new IllegalArgumentException("Ores generated out of bounds");

        int heightDiff = maxHeigth - minHeigth + 1;
        for(int i = 0; i < chance; i++)
        {
            int x = chunkX * 16 + rand.nextInt(16);
            int y = minHeigth + rand.nextInt(heightDiff);
            int z = chunkZ * 16 + rand.nextInt(16);

            gen.generate(world, rand, new BlockPos(x, y ,z));
        }
    }
}
