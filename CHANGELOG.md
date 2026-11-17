<!-- omit in toc -->
# Changelog #

<details>

<summary><b>Table of Contents</b></summary>

* [0.18.4](#0184)
* [0.18.3](#0183)
* [0.18.2](#0182)
* [0.18.1](#0181)
* [0.18.0](#0180)
* [0.17.0](#0170)
* [0.16.0](#0160)
* [0.15.1](#0151)
* [0.15.0](#0150)
* [0.14.7](#0147)
* [0.14.6](#0146)
* [0.14.5](#0145)
* [0.14.4](#0144)
* [0.14.3](#0143)
* [0.14.2](#0142)
* [0.14.1](#0141)
* [0.14.0](#0140)
* [0.13.1](#0131)
* [0.13.0](#0130)
* [0.12.3](#0123)
* [0.12.2](#0122)
* [0.12.1](#0121)
* [0.12.0](#0120)
* [0.11.1](#0111)
* [0.11.0](#0110)
* [0.10.0](#0100)
* [0.9.0](#090)
* [0.8.0](#080)
* [0.7.0](#070)
* [0.6.3](#063)
* [0.6.2](#062)
* [0.6.1](#061)
* [0.6.0](#060)
* [0.5.3](#053)
* [0.5.2](#052)
* [0.5.1](#051)
* [0.5.0](#050)
* [0.4.0](#040)
* [0.3.1](#031)
* [0.3.0](#030)
* [0.2.3](#023)
* [0.2.2](#022)
* [0.2.1](#021)
* [0.2.0](#020)
* [0.1.2](#012)
* [0.1.1](#011)
* [0.1.0](#010)
* [0.0.0](#000)

</details>

## 0.18.4 ##

<!-- omit in toc -->
### Bug Fixes ###

* Ensure that tabbing between fields in the Journeymap waypoint editor respects
  the various possible field arrangements/orders.
* Tabbing between text fields in the Journeymap waypoint editor now correctly
  removes text selections on the newly unfocused text field and selects the
  entire contexts of the newly focused text field.

<!-- omit in toc -->
### Technical ###

* Updated VSCode workspaces.

## 0.18.3 ##

<!-- omit in toc -->
### Features ###

* Added the ability for Journeymap to reset waypoint coordinates by clearing the
  coordinate fields.
* Added the ability to tab between text fields in the waypoint editor.

<!-- omit in toc -->
### Bug Fixes ###

* Fixed Forge dependency specifier in `mcmod.info` being out of sync with the
  mod's actual dependencies.
* Fixed the mod's dependency specifier in the `@Mod` annotation being wrongly
  formatted.

## 0.18.2 ##

<!-- omit in toc -->
### Features ###

* Silverfish now respect the `mobGriefing` gamerule.
    * Little buggers...

## 0.18.1 ##

<!-- omit in toc -->
### Bug Fixes ###

* Fixed mod configuration getting wiped on startup.

<!-- omit in toc -->
### Technical ###

* Refactored changelog format.

## 0.18.0 ##

<!-- omit in toc -->
### Features ###

* Enabled support in Xaero's mods for cross-dimension teleportation when a valid
  sub-world connection is detected.
* Added a `/gtnh_customizer` command to manage the mod settings on the fly.
    * Specifically the "globally immune to mob targeting" list and reloading the
      mod configuration at runtime.

<!-- omit in toc -->
### Technical ###

* Updated GTNH build scripts to v2.0.34.
* Refactored project-level build scripts.
* Updated all project dependencies to their latest versions.
* Overhauled configuration system to better support dynamically saving
  configuration settings at runtime.
* Updated VSCode workspace configurations.
    * Pruned no longer relevant configuration entries.
    * Added some new entries that are either quality-of-life things or
      reasonable defaults for newly introduced settings since I last did this.

## 0.17.0 ##

<!-- omit in toc -->
### Major Changes ###

* GTNH 2.9.x (which is now in beta release) compatibility update.

<!-- omit in toc -->
### Minor Changes ###

* Pointed mod back to GitHub repository.

## 0.16.0 ##

<!-- omit in toc -->
### Features ###

* Enabled using the numpad across all ServerUtilities text boxes.
* Added a `/cure` command for curing various types of potion effects.

<!-- omit in toc -->
### Minor Changes ###

* Pointed mod to new Forgejo repository home.
* Updated mod logo.

<!-- omit in toc -->
### Bug Fixes ###

* Fixed some issues with mixins that were causing injections to not properly get
  loaded at runtime.

<!-- omit in toc -->
### Technical ###

* Updated GTNH build scripts to v2.0.24.
* Updated Checkstyle to v13.4.0.
* Refactored internal exceptions.
* Updated VSCode workspaces.

## 0.15.1 ##

<!-- omit in toc -->
### Bug Fixes ###

* Fixed back port of showing the biome when hovering over things in Xaero's
  World Map not working at all due to Xaero's World Map being obfuscated.
* Fixed logging not working at all.

<!-- omit in toc -->
### Technical ###

* Removed `slf4j` as it was causing logging to not work.

## 0.15.0 ##

<!-- omit in toc -->
### Features ###

* Added a mixin to display the hovered biome in Xaero's World Map.
  * Added relevant setting to control the new feature.

<!-- omit in toc -->
### Minor Changes ###

* Moved to hard-coded NBT editing key bindings to avoid polluting the key
  bindings pages.

<!-- omit in toc -->
### Bug Fixes ###

* Fixed a bad Gradle update.

<!-- omit in toc -->
### Technical ###

* Updated GTNH build scripts to v2.0.19.
* Updated dependencies.
* Switched to `slf4j` for logging.
* Moved to shadowed dependencies for `slf4j`.
* Overhauled logging code.
* Overhauled internal exceptions.

## 0.14.7 ##

<!-- omit in toc -->
### Bug Fixes ###

* Fixed bad mod dependency string.

## 0.14.6 ##

<!-- omit in toc -->
### Technical ###

* Updated dependencies.

## 0.14.5 ##

<!-- omit in toc -->
### Bug Fixes ###

* Fixed a bad world access on the client side that would cause a random crash
  when attacking hostile mobs.

## 0.14.4 ##

<!-- omit in toc -->
### Technical ###

* Updated dependencies.

## 0.14.3 ##

<!-- omit in toc -->
### Bug Fixes ###

* Fixed Tinker's tools not properly repairing via the `/repair` command when
  they had reached `0%` durability beforehand.

## 0.14.2 ##

<!-- omit in toc -->
### Features Removed ###

* Removed the change added in `v0.13.0` to `/nbtedit` that let you add things to
  empty NBT lists.
  * It has officially been added to GTNH in 2.8.0. See
    [GTNewHorizons/ServerUtilities#232](https://github.com/GTNewHorizons/ServerUtilities/pull/232).
* Removed the change added in `v0.9.0` to `/nbtedit` that better handled how the
  hotkey behaved when looking at nothing in the game world.
  * It has officially been added to GTNH in 2.8.0. See
    [GTNewHorizons/ServerUtilities#231](https://github.com/GTNewHorizons/ServerUtilities/pull/231).

<!-- omit in toc -->
### Technical ###

* Removed the version checker.
* Reworked CSpell configuration on the repository side.
* Fixed `prepareWorkspace` VSCode task.

## 0.14.1 ##

<!-- omit in toc -->
### Bug Fixes ###

* Fixed bad world cast in `/extinguish` command that caused it to not work.

## 0.14.0 ##

<!-- omit in toc -->
### Features ###

* Added support for EnderZoo so that Enderminy's will no longer attack immune
  players.

<!-- omit in toc -->
### Bug Fixes ###

* Fixed ServerUtilities `/reload` command no longer showing the
  `gtnh_customizer` namespace anymore.
* Fixed config changes not applying in a running game.

<!-- omit in toc -->
### Misc. ###

* Removed refreshing of top panel widgets in the ServerUtilities `/nbtedit`
  command now that it's been formally accepted into GTNH.

## 0.13.1 ##

<!-- omit in toc -->
### Technical ###

* Added a version checker "script" that runs during startup.
* Removed some unneeded logs.

## 0.13.0 ##

<!-- omit in toc -->
### Features ###

* Changed the ServerUtilities `/nbtedit` command so it can now add things to
  lists that are not empty.

## 0.12.3 ##

<!-- omit in toc -->
### Bug Fixes ###

* Fixed the `/extinguish` command causing an error when using the `/help`
  command the proper way...

## 0.12.2 ##

<!-- omit in toc -->
### Bug Fixes ###

* Fixed the `/extinguish` command causing an error when using the `/help`
  command.

## 0.12.1 ##

<!-- omit in toc -->
### Bug Fixes ###

* Fixed the `/extinguish` command causing an error on server consoles when using
  any command.

## 0.12.0 ##

<!-- omit in toc -->
### Features ###

* Added a setting to disable Enderman and Enderman-like mobs from teleporting.

<!-- omit in toc -->
### Bug Fixes ###

* Fixed the '/nbtedit' UI closing when you used the 'accept' hotkey.

## 0.11.1 ##

<!-- omit in toc -->
### Bug Fixes ###

* Fixed key bindings being loaded on the server side, causing a crash.

## 0.11.0 ##

<!-- omit in toc -->
### Features ###

* Fall back to in-hand item if the targeted block has no tile entity when
  calling ServerUtilities' `/nbtedit` command.
* Support a shortcut key to delete the selected NBT tag inside the
  ServerUtilities' `/nbtedit` command.
* Add customizable key binding support to the ServerUtilities' `/nbtedit`
  command hotkey support.

## 0.10.0 ##

<!-- omit in toc -->
### Features ###

* Patched ServerUtilities' `/nbtedit` command to let you use `Numpad enter` to
  submit your changes.

## 0.9.0 ##

<!-- omit in toc -->
### Features ###

* Patched ServerUtilities' `/nbtedit` command to edit the in-hand item, if
  present, if there is no targeted block/entity.

## 0.8.0 ##

<!-- omit in toc -->
### Features ###

* Added the ability to apply bone meal to Thaumcraft saplings.

## 0.7.0 ##

<!-- omit in toc -->
### Features ###

* The ability to apply bone meal to Tinker's Construct Slime Saplings can now be
  toggled on and off via a configuration option.


<!-- omit in toc -->
### Bug Fixes ###

* Fixed changing configuration values from the Forge mod options menu while in a
  single player world not taking effect immediately.

<!-- omit in toc -->
### Technical ###

* Big configuration system overhaul.
* Moved gameplay-related configuration entries into their own sections.
* Moved Tinker's Construct-related configuration entries into a sub-section of
  the gameplay-related configuration section.


## 0.6.3 ##

<!-- omit in toc -->
### Bug Fixes ###

* Fixed the `/repair` command not applying to Tinker's Construct and Gregtech
  tools.
* Fixed the `/repair` command breaking Tinker's Construct and Gregtech tools in
  weird ways.

## 0.6.2 ##

<!-- omit in toc -->
### Bug Fixes ###

* Fixed the `/reload` command not working properly on servers.

## 0.6.1 ##

<!-- omit in toc -->
### Bug Fixes ###

* Fixed the `/extinguish` command trying to access the currently configured
  render distance incorrectly on servers, causing a crash.

## 0.6.0 ##

<!-- omit in toc -->
### Features ###

* Added an `/extinguish` command to extinguish flames in an area.
  * Supports either a radius (in blocks) from the issuing player or a "block
    volume" in the form of two sets of coordinates that form the corners of a
    cube.
  * Radius defaults to the currently configured client render distance in single
    player or server render distance in multiplayer.

## 0.5.3 ##

<!-- omit in toc -->
### Misc. ###

* Adjusted the admin output of the `/repair` command.

## 0.5.2 ##

<!-- omit in toc -->
### Bug Fixes ###

* Fixed an issue where the `/repair` command was formatting the number of
  repaired items VERY wrong.

## 0.5.1 ##

<!-- omit in toc -->
### Bug Fixes ###

* Fixed an issue where the `/teleport-cross-dimension` command was parsing
  coordinates wrong.

## 0.5.0 ##

<!-- omit in toc -->
### Features ###

* Added `/repair` command to repair items.
  * Supports various arguments such as `hand` to repair the currently held item
    or `inventory` to repair only items in the main inventory.
  * Defaults to `everything` to repair all items in the player's inventory, hot
    bar, off-hand (if present), and armor slots.

## 0.4.0 ##

<!-- omit in toc -->
### Features ###

* Allow bone meal to be applied to Tinker's Construct Slime Saplings.

## 0.3.1 ##

<!-- omit in toc -->
### Bug Fixes ###

* Fixed Zombie Pigmen attacking players who are immune to mob aggressive when
  they mine ores in the Nether.

## 0.3.0 ##

<!-- omit in toc -->
### Features ###

* Integrated the mod into ServerUtilities.
  * The mod's configuration can now be reloaded from disk using the `/reload`
    command.
  * All the mod's resources can be reloaded using the
    `/reload gtnh_customizer:*` command.
  * Just the mod's configuration can now be reloaded from disk using the
    `/reload gtnh_customizer:config` command.

## 0.2.3 ##

<!-- omit in toc -->
### Bug Fixes ###

* Fixed the mod's adjustments to how Xaero's mods parse teleport commands not
  working.

## 0.2.2 ##

<!-- omit in toc -->
### Bug Fixes ###

* Fixed broken `/teleport-cross-dimension` command.

## 0.2.1 ##

<!-- omit in toc -->
### Bug Fixes ###

* Fixed `/teleport-cross-dimension` command argument processing.

## 0.2.0 ##

<!-- omit in toc -->
### Features ###

* Added ability to customize how Xaero's mods parse their configured teleport
  command.
  * The entire input is now parsed and then called rather than being used as a
    prefix to be appended with a set of coordinates.
  * Use `{x}`, `{y}`, and `{z}` to specify the target coordinates.
  * Use `{name}` or `{n}` to specify the issuing player's name.
  * Use `{dimension}`, `{dim}`, or `{d}` to specify the target dimension ID.
  * Use `{rotation}`, `{rot}`, `{r}`, or `{yaw}` to specify the target rotation.

## 0.1.2 ##

<!-- omit in toc -->
### Bug Fixes ###

* Fixed `/teleport-cross-dimension` command not respecting coordinates.

## 0.1.1 ##

<!-- omit in toc -->
### Bug Fixes ###

* Fixed configuration migrations not applying correctly.

## 0.1.0 ##

<!-- omit in toc -->
### Features ###

* Ported existing code from old repository.
* Added a cross-dimension teleport command.
* Changed logo.

<!-- omit in toc -->
### Technical ###

* Updated several dependencies.

## 0.0.0 ##

<!-- omit in toc -->
### Features ###

* Initial release.
