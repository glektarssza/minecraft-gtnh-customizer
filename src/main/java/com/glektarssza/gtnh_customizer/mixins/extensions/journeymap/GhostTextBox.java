package com.glektarssza.gtnh_customizer.mixins.extensions.journeymap;

import net.minecraft.client.gui.FontRenderer;

import com.glektarssza.gtnh_customizer.api.v2.annotation.NotNull;
import com.glektarssza.gtnh_customizer.mixins.extensions.vanilla.ITextBoxAccessor;

import journeymap.client.ui.component.TextBox;

/**
 * A text box which displays "ghost text" when it has no contents.
 */
public class GhostTextBox extends TextBox implements ITextBoxAccessor {
    /**
     * The text to display when the instance has no contents.
     */
    @NotNull
    private String ghostText = "";

    /**
     * The color to draw the "ghost text" in.
     *
     * <p>
     * Defaults to the "disabled" color as of Minecraft 1.7.10.
     * </p>
     */
    private int ghostTextColor = 7368816;

    public GhostTextBox(final Object text, final FontRenderer fontRenderer,
        final int width, final int height) {
        this(text, fontRenderer, width, height, false, false, () -> {});
    }

    public GhostTextBox(final Object text, final FontRenderer fontRenderer,
        final int width, final int height, final OnPress onPress) {
        this(text, fontRenderer, width, height, false, false, onPress);
    }

    public GhostTextBox(final Object text, final FontRenderer fontRenderer,
        final int width, final int height, final boolean isNumeric,
        final boolean negative) {
        this(text, fontRenderer, width, height, isNumeric, negative, () -> {});
    }

    public GhostTextBox(final Object text, final FontRenderer fontRenderer,
        final int width, final int height, final boolean isNumeric,
        final boolean negative,
        final OnPress onPress) {
        super(text, fontRenderer, width, height, isNumeric, negative, onPress);
    }

    /**
     * {@inheritdoc net.minecraft.client.gui.GuiTextField#setMinLength(int)}
     */
    @Override
    public void setMinLength(final int _minLength) {
        super.setMinLength(0);
    }

    /**
     * Get the "ghost text" color.
     *
     * @return The value of the "ghost text" color.
     */
    public int getGhostTextColor() {
        return this.ghostTextColor;
    }

    /**
     * Set the "ghost text" color.
     *
     * @param color The value to set the "ghost text" color to.
     */
    public void setGhostTextColor(final int color) {
        this.ghostTextColor = color;
    }

    /**
     * Reset the "ghost text" color to its default ({@code 7368816} in decimal
     * or {@code #707070} in hexadecimal.).
     */
    public void resetGhostTextColor() {
        this.setGhostTextColor(7368816);
    }

    /**
     * Get the "ghost text".
     *
     * @return The value of the "ghost text".
     */
    @NotNull
    public String getGhostText() {
        return this.ghostText;
    }

    /**
     * Set the "ghost text".
     *
     * @param text The value to set the "ghost text" to.
     */
    public void setGhostText(@NotNull final String text) {
        this.ghostText = text;
    }

    /**
     * Reset the "ghost text" to no value.
     */
    public void clearGhostText() {
        this.setGhostText("");
    }

    @Override
    public void drawTextBox() {
        super.drawTextBox();
        if (!this.getVisible() || (this.hasMinLength()
            && this.hasErrorState())) {
            return;
        }
        if (this.getText().isEmpty() && !this.getGhostText().isEmpty()) {
            this.getFontRenderer().drawStringWithShadow(this.getGhostText(),
                this.isBackgroundDrawingEnabled() ? this.xPosition + 4
                    : this.xPosition,
                this.isBackgroundDrawingEnabled()
                    ? this.yPosition + (this.height - 8) / 2
                    : this.yPosition,
                this.getGhostTextColor());
        }
    }
}
