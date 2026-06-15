package io.github.retrooper.packetevents.manager.logger;

import com.github.retrooper.packetevents.util.adventure.AdventureSerializer;
import net.kyori.adventure.text.ComponentLike;

import java.util.logging.Level;

public class Log4jFabricLogger extends AbstractFabricLogger {
    private final org.apache.logging.log4j.Logger logger;

    public Log4jFabricLogger(org.apache.logging.log4j.Logger logger) {
        this.logger = logger;
    }

    @Override
    public void log(Level level, ComponentLike component, Throwable error) {
        String msg = stripColorCodes(AdventureSerializer.stringify(component));
        org.apache.logging.log4j.Level log4jLevel;

        switch (level.getName()) {
            case "SEVERE":
                log4jLevel = org.apache.logging.log4j.Level.ERROR;
                break;
            case "WARNING":
                log4jLevel = org.apache.logging.log4j.Level.WARN;
                break;
            case "INFO":
                log4jLevel = org.apache.logging.log4j.Level.INFO;
                break;
            case "CONFIG":
            case "FINE":
                log4jLevel = org.apache.logging.log4j.Level.DEBUG;
                break;
            case "FINER":
            case "FINEST":
                log4jLevel = org.apache.logging.log4j.Level.TRACE;
                break;
            default:
                log4jLevel = org.apache.logging.log4j.Level.INFO;
                break;
        }

        logger.log(log4jLevel, msg, error);
    }
}
