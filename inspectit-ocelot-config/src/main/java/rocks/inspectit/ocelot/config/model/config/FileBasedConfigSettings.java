package rocks.inspectit.ocelot.config.model.config;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.validator.constraints.time.DurationMin;

import java.time.Duration;

/**
 * If path is not null and enabled is true a PropertySource
 * will be created for the given path. This configuration has the highest priority, meaning that it will be loaded first
 * and can configure other configuration sources.
 */
@Data
@NoArgsConstructor
public class FileBasedConfigSettings {
    /**
     * The path to the directory containing the .yml or .properties files.
     * Can be null or empty, in which case no file based configuration is used.
     */
    private String path;

    /**
     * Can be used to disable the file based config while the path is still specified.
     */
    private boolean enabled;

    /**
     * If true, a {@link rocks.inspectit.ocelot.core.config.propertysources.file.ConfigurationDirectoriesPoller} will be started to reload the configuration from the directory on changes.
     */
    private boolean watch;

    /**
     * The frequency at which the target folder should be polled for changes if {@link #watch} is true.
     */
    @NonNull
    @DurationMin(millis = 1)
    private Duration frequency;
}
