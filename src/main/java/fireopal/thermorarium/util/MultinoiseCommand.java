package fireopal.thermorarium.util;

import java.util.ArrayList;
import java.util.List;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.command.argument.BlockPosArgumentType;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.LiteralTextContent;
import net.minecraft.text.MutableText;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.noise.NoiseConfig;

public class MultinoiseCommand {
    public static void register() {

        CommandRegistrationCallback.EVENT.register((dispatcher, access, dedicated) -> {
            dispatcher.register(
                (LiteralArgumentBuilder<ServerCommandSource>) CommandManager.literal("multinoise").requires(source -> source.hasPermissionLevel(2))
                    .then(CommandManager.argument("pos", BlockPosArgumentType.blockPos())
                        .executes(context -> MultinoiseCommand.execute((ServerCommandSource)context.getSource(), BlockPosArgumentType.getLoadedBlockPos(context, "pos"))
                    )
                )
            );
        });
    }

    public static int execute(ServerCommandSource source, BlockPos pos) {
        ServerWorld serverWorld = source.getWorld();
        ChunkGenerator chunkGenerator = serverWorld.getChunkManager().getChunkGenerator();
        NoiseConfig noiseConfig = serverWorld.getChunkManager().getNoiseConfig();
        
        List<String> textList = new ArrayList<>();
        chunkGenerator.getDebugHudText(textList, noiseConfig, pos);

        MutableText text = MutableText.of(
            new LiteralTextContent("Multinoise values for the position " + pos.toShortString() + "\n")
        );

        for (String s : textList) {
            text.append("\n" + s);
        }

        source.sendFeedback(text, false);
        return 0;
    }
}
