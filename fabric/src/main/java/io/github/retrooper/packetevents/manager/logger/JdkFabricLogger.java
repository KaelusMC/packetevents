package io.github.retrooper.packetevents.manager.logger;

import com.github.retrooper.packetevents.util.adventure.AdventureSerializer;
import net.kyori.adventure.text.ComponentLike;

import java.util.logging.Level;
import java.util.logging.Logger;

public class JdkFabricLogger extends AbstractFabricLogger {
    private final Logger logger;

    public JdkFabricLogger(Logger logger) {
        this.logger = logger;
    }

    @Override
    public void log(Level level, ComponentLike component, Throwable error) {
        String msg = stripColorCodes(AdventureSerializer.stringify(component));
        if (error != null) {
            logger.log(level, msg, error);
        } else {
            logger.log(level, msg);
        }
    }
}
