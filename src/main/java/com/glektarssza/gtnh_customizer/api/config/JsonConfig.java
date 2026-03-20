package com.glektarssza.gtnh_customizer.api.config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Modifier;
import java.nio.charset.Charset;
import java.nio.file.AccessDeniedException;
import java.nio.file.AccessMode;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.google.gson.LongSerializationPolicy;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import com.glektarssza.gtnh_customizer.api.exceptions.args.ArgumentException;

/**
 * A JSON-based configuration provider.
 */
public class JsonConfig {
    /**
     * The JSON builder.
     */
    private final GsonBuilder gsonConfig;

    /**
     * The JSON serializer.
     */
    private final Gson gson;

    /**
     * The parser used to decode the raw JSON.
     */
    private final JsonParser jsonParser;

    /**
     * The root element of the configuration.
     */
    @Nullable
    private JsonElement rootElement;

    /**
     * The directory the configuration lives in.
     */
    public final Path configDir;

    /**
     * The name of the configuration.
     */
    public final String name;

    /**
     * Create a new JSON-based configuration instance.
     *
     * @param configDir The directory the configuration will live in.
     * @param name The name of the configuration to create.
     * @param version The version of the configuration.
     */
    public JsonConfig(@Nonnull Path configDir, @Nonnull String name,
        @Nonnull String version)
        throws NumberFormatException, NullPointerException {
        this(configDir, name, Double.parseDouble(version));
    }

    /**
     * Create a new JSON-based configuration instance.
     *
     * @param configDir The directory the configuration will live in.
     * @param name The name of the configuration to create.
     * @param version The version of the configuration.
     */
    public JsonConfig(@Nonnull Path configDir, @Nonnull String name,
        @Nonnull Double version) throws ArgumentException {
        if (name.endsWith(".json")) {
            throw new ArgumentException("name",
                "Configuration name must not end with `.json`");
        }
        try {
            if (Files.notExists(configDir)) {
                throw new ArgumentException("configDir",
                    "Configuration directory path must exist");
            }
        } catch (Throwable t) {
            throw new ArgumentException("configDir",
                "Exception while checking if configuration directory path exists, is",
                t);
        }
        try {
            if (!Files.isDirectory(configDir)) {
                throw new ArgumentException("configDir",
                    "Configuration directory path must be a directory");
            }
        } catch (Throwable t) {
            throw new ArgumentException("configDir",
                "Exception while checking if configuration directory path is not a directory",
                t);
        }
        try {
            List<AccessMode> accessMode = Arrays.asList(AccessMode.READ,
                AccessMode.WRITE);
            if (!java.lang.System.getProperty("os.name")
                .startsWith("Windows")) {
                accessMode.add(AccessMode.EXECUTE);
            }
            try {
                configDir.getFileSystem().provider().checkAccess(configDir,
                    (AccessMode[]) accessMode.toArray());
            } catch (Throwable t) {
                throw new ArgumentException("configDir",
                    "Configuration directory path must be accessible");
            }
        } catch (Throwable t) {
            throw new ArgumentException("configDir",
                "Exception while checking if configuration directory path is not accessible",
                t);
        }
        this.configDir = configDir;
        this.name = name;
        this.gsonConfig = new GsonBuilder();
        this.gsonConfig
            .disableInnerClassSerialization()
            .excludeFieldsWithModifiers(
                Modifier.PRIVATE | Modifier.PROTECTED | Modifier.FINAL
                    | Modifier.TRANSIENT | Modifier.STATIC)
            .excludeFieldsWithoutExposeAnnotation()
            .generateNonExecutableJson()
            .serializeNulls()
            // -- Date format is like 2026-03-20 15:30:22.450 -0600 (which is
            // -- when I wrote this comment! Roughly!)
            .setDateFormat("yyyy-MM-dd HH:mm:ss.SSS Z")
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .setLongSerializationPolicy(LongSerializationPolicy.STRING)
            .setPrettyPrinting()
            .setVersion(version);
        this.gson = this.gsonConfig.create();
        this.jsonParser = new JsonParser();
        this.rootElement = null;
    }

    /**
     * Get the path to the configuration file this instance uses.
     *
     * @return The path to the configuration file this instance uses.
     */
    public Path getConfigFile() {
        return this.configDir.resolve(this.name + ".json");
    }

    /**
     * Load the configuration.
     *
     * @throws FileNotFoundException Thrown if the configuration file does not
     *         exist on the file system.
     * @throws IOException Thrown if the configuration cannot be loaded due to
     *         an unexpected error.
     * @throws JsonIOException Thrown if the configuration was unable to be
     *         loaded due to a failure to read JSON information from the input
     *         stream.
     * @throws JsonSyntaxException Thrown if the configuration was unable to be
     *         loaded due to malformed JSON data.
     * @throws JsonParseException Thrown if the configuration was unable to be
     *         loaded due to an unexpected JSON parsing error.
     */
    public void load() throws FileNotFoundException, IOException,
        JsonIOException, JsonSyntaxException, JsonParseException {
        final Path configFile = this.getConfigFile();
        if (Files.notExists(configFile)) {
            throw new FileNotFoundException(
                "Configuration file `" + configFile + "` does not exist");
        }
        if (!Files.isReadable(configFile)) {
            throw new IOException(
                "Configuration file `" + configFile + "` is not readable");
        }
        @Nullable
        FileInputStream inputStream = null;
        @Nullable
        InputStreamReader inputStreamReader = null;
        @Nullable
        JsonReader jsonReader = null;
        try {
            inputStream = new FileInputStream(configFile.toFile());
            inputStreamReader = new InputStreamReader(inputStream,
                Charset.forName("UTF-8").newDecoder());
            jsonReader = new JsonReader(inputStreamReader);
            jsonReader.setLenient(true);
            this.rootElement = jsonParser.parse(jsonReader);
        } catch (JsonIOException ex) {
            throw new JsonIOException("Unable to read configuration file `"
                + configFile + "` due to failure to read JSON input stream",
                ex);
        } catch (JsonSyntaxException ex) {
            throw new JsonSyntaxException("Unable to read configuration file `"
                + configFile + "` due to bad JSON syntax found in file", ex);
        } catch (JsonParseException ex) {
            throw new JsonParseException("Unable to read configuration file `"
                + configFile + "` due to an unexpected JSON parsing error",
                ex);
        } catch (Throwable t) {
            throw new IOException("Unable to read configuration file `"
                + configFile + "` due to an unexpected error",
                t);
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (inputStreamReader != null) {
                inputStreamReader.close();
            }
            if (jsonReader != null) {
                jsonReader.close();
            }
        }
    }

    /**
     * Load the configuration.
     *
     * @throws FileNotFoundException Thrown if the configuration directory does
     *         not exist on the file system.
     * @throws AccessDeniedException Thrown if access to the configuration
     *         directory or to write to the configuration file is denied.
     * @throws IOException Thrown if the configuration cannot be saved due to an
     *         unexpected error.
     * @throws JsonIOException Thrown if the configuration was unable to be
     *         saved due to a failure to write JSON information to the output
     *         stream.
     * @throws JsonSyntaxException Thrown if the configuration was unable to be
     *         saved due to malformed JSON data.
     * @throws JsonParseException Thrown if the configuration was unable to be
     *         saved due to an unexpected JSON parsing error.
     */
    public void save() throws FileNotFoundException, AccessDeniedException,
        IOException, JsonIOException, JsonSyntaxException, JsonParseException {
        final Path configFile = this.getConfigFile();
        if (Files.notExists(this.configDir)) {
            throw new FileNotFoundException(
                "Configuration directory `" + configFile + "` does not exist");
        }
        if (!Files.isWritable(this.configDir)) {
            throw new AccessDeniedException(
                "Configuration directory `" + configFile + "` is not writable");
        }
        if (Files.exists(configFile)) {
            try {
                Files.move(configFile,
                    configFile
                        .resolveSibling(configFile.getFileName() + ".bak"),
                    StandardCopyOption.ATOMIC_MOVE,
                    StandardCopyOption.REPLACE_EXISTING,
                    StandardCopyOption.COPY_ATTRIBUTES);
            } catch (IOException ex) {
                throw new IOException(
                    "Failed to generate a backup copy of " + configFile, ex);
            }
        }
        @Nullable
        OutputStream outputStream = null;
        @Nullable
        OutputStreamWriter outputStreamWriter = null;
        @Nullable
        JsonWriter jsonWriter = null;
        try {
            outputStream = new FileOutputStream(
                configFile.toFile());
            outputStreamWriter = new OutputStreamWriter(
                outputStream, Charset.forName("UTF-8").newEncoder());
            jsonWriter = new JsonWriter(outputStreamWriter);
            jsonWriter.setLenient(false);
            jsonWriter.setHtmlSafe(true);
            jsonWriter.setSerializeNulls(true);
            jsonWriter.setIndent(" ");
            gson.toJson(this.rootElement, jsonWriter);
        } catch (JsonIOException ex) {
            throw new JsonIOException("Unable to write configuration file `"
                + configFile + "` due to failure to write JSON output stream",
                ex);
        } finally {
            if (jsonWriter != null) {
                jsonWriter.flush();
            }
            if (outputStreamWriter != null) {
                outputStreamWriter.flush();
            }
            if (outputStream != null) {
                outputStream.flush();
            }
            if (jsonWriter != null) {
                jsonWriter.close();
            }
            if (outputStreamWriter != null) {
                outputStreamWriter.close();
            }
            if (outputStream != null) {
                outputStream.close();
            }
        }
    }
}
