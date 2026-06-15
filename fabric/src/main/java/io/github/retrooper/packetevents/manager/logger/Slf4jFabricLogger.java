package io.github.retrooper.packetevents.manager.logger;

import com.github.retrooper.packetevents.util.adventure.AdventureSerializer;
import net.kyori.adventure.text.ComponentLike;

import java.util.logging.Level;

public class Slf4jFabricLogger extends AbstractFabricLogger {
    private final org.slf4j.Logger logger;

    public Slf4jFabricLogger(org.slf4j.Logger logger) {
        this.logger = logger;
    }

    @Override
    public void log(Level level, ComponentLike component, Throwable error) {
        String msg = stripColorCodes(AdventureSerializer.stringify(component));
        switch (level.getName()) {
            case "SEVERE":
                logger.error(msg, error);
                break;
            case "WARNING":
                logger.warn(msg, error);
                break;
            case "INFO":
                logger.info(msg, error);
                break;
            case "CONFIG":
            case "FINE":
                logger.debug(msg, error);
                break;
            case "FINER":
            case "FINEST":
                logger.trace(msg, error);
                break;
            default:
                logger.info(msg, error);
                break;
        }
    }
}
