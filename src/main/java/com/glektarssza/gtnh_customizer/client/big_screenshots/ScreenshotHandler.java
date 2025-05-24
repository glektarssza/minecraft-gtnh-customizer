package com.glektarssza.gtnh_customizer.client.big_screenshots;

import java.io.File;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.event.ClickEvent;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent.KeyInputEvent;
import cpw.mods.fml.common.gameevent.TickEvent.RenderTickEvent;

import com.glektarssza.gtnh_customizer.utils.functional.UnaryProcedureWithoutResult;

/**
 * A class which can take big screenshots.
 */
public class ScreenshotHandler {
    /**
     * The category for key bindings.
     */
    private static final String KEY_CATEGORY = "key.categories.gtnh_customizer";

    /**
     * The key binding for taking a screenshot.
     */
    private static final KeyBinding KEY_BINDING_CAPTURE_SCREENSHOT = new KeyBinding(
        "key.gtnh_customizer.capture_screenshot", Keyboard.KEY_F6,
        KEY_CATEGORY);

    /**
     * Whether a screenshot is being taken or not.
     */
    private boolean isTakingScreenshot = false;

    /**
     * Create a new instance.
     */
    public ScreenshotHandler() {
        ClientRegistry.registerKeyBinding(KEY_BINDING_CAPTURE_SCREENSHOT);
    }

    /**
     * Take a screenshot.
     *
     * @param width The width of the screenshot to take, in pixels.
     * @param height The height of the screenshot to take, in pixels.
     * @param directory The directory to save the screenshot file to.
     * @param name The name of the screenshot file to save the screenshot to.
     * @param successCallback The callback to call when the screenshot is taken
     *        successfully.
     * @param failureCallback The callback to call when the screenshot fails to
     *        take.
     */
    public void takeScreenshot(int width, int height, File directory,
        String name,
        UnaryProcedureWithoutResult<File> successCallback,
        UnaryProcedureWithoutResult<Throwable> failureCallback) {
        if (isTakingScreenshot) {
            return;
        }
        // -- Does nothing
    }

    /**
     * An event handler for key input events.
     */
    @SubscribeEvent
    public void onKeyInput(KeyInputEvent event) {
        if (KEY_BINDING_CAPTURE_SCREENSHOT.isPressed()) {
            takeScreenshot(1280, 720, Minecraft.getMinecraft().mcDataDir,
                "big_screenshot.png", (savedFile) -> {
                    String absPath = savedFile.getAbsolutePath();
                    Minecraft.getMinecraft().thePlayer
                        .addChatMessage(new ChatComponentTranslation(
                            "gtnh_customizer.screenshot.success",
                            new ChatComponentText(
                                absPath).setChatStyle(
                                    new ChatStyle().setItalic(true)
                                        .setUnderlined(true).setColor(
                                            EnumChatFormatting.GRAY)
                                        .setChatClickEvent(new ClickEvent(
                                            ClickEvent.Action.OPEN_FILE,
                                            absPath)))));
                }, (error) -> {
                    Minecraft.getMinecraft().thePlayer
                        .addChatMessage(new ChatComponentTranslation(
                            "gtnh_customizer.screenshot.failure",
                            error.getLocalizedMessage()).setChatStyle(
                                new ChatStyle().setColor(
                                    EnumChatFormatting.RED)));
                });
        }
    }

    /**
     * An event handler for render tick events.
     */
    @SubscribeEvent
    public void onRenderTick(RenderTickEvent event) {
        if (!isTakingScreenshot) {
            return;
        }
    }
}
