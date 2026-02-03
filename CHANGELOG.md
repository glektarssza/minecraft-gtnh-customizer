# Changelog #

## 0.15.0 ##

### Bug Fixes ###

* Fixed a bad Gradle update.

## Technical ##

* Updated GTNH build scripts to 2.0.19.

## 0.14.7 ##

### Bug Fixes ###

* Fixed bad mod dependency string.

## 0.14.6 ##

### Technical ###

* Updated dependencies.

## 0.14.5 ##

### Bug Fixes ###

* Fixed a bad world access on the client side that would cause a random crash
  when attacking hostile mobs.

## 0.14.4 ##

### Technical ###

* Updated dependencies.

## 0.14.3 ##

### Bug Fixes ###

* Fixed Tinker's tools not properly repairing via the `/repair` command when
  they had reached `0%` durability beforehand.

## 0.14.2 ##

### Features Removed ###

* Removed the change added in `v0.13.0` to `/nbtedit` that let you add things to
  empty NBT lists.
  * It has officially been added to GTNH in 2.8.0. See
    [GTNewHorizons/ServerUtilities#232](https://github.com/GTNewHorizons/ServerUtilities/pull/232).
* Removed the change added in `v0.9.0` to `/nbtedit` that better handled how the
  hotkey behaved when looking at nothing in the game world.
  * It has officially been added to GTNH in 2.8.0. See
    [GTNewHorizons/ServerUtilities#231](https://github.com/GTNewHorizons/ServerUtilities/pull/231).

### Technical ###

* Removed the version checker.
* Reworked CSpell configuration on the repository side.
* Fixed `prepareWorkspace` VSCode task.

## 0.14.1 ##

### Bug Fix ###

* Fixed bad world cast in `/extinguish` command that caused it to not work.

## 0.14.0 ##

### Features ###

* Added support for EnderZoo so that Enderminy's will no longer attack immune
  players.

### Bug Fix ###

* Fixed ServerUtilities `/reload` command no longer showing the
  `gtnh_customizer` namespace anymore.
* Fixed config changes not applying in a running game.

### Misc. ###

* Removed refreshing of top panel widgets in the ServerUtilities `/nbtedit`
  command now that it's been formally accepted into GTNH.

## 0.13.1 ##

### Technical ###

* Added a version checker "script" that runs during startup.
* Removed some unneeded logs.

## 0.13.0 ##

### Features ###

* Changed the ServerUtilities `/nbtedit` command so it can now add things to
  lists that are not empty.

## 0.12.3 ##

### Bug Fixes ###

* Fixed the `/extinguish` command causing an error when using the `/help`
  command the proper way...

## 0.12.2 ##

### Bug Fixes ###

* Fixed the `/extinguish` command causing an error when using the `/help`
  command.

## 0.12.1 ##

### Bug Fixes ###

* Fixed the `/extinguish` command causing an error on server consoles when using
  any command.

## 0.12.0 ##

### Features ###

* Added a setting to disable Enderman and Enderman-like mobs from teleporting.

### Bug Fixes ###

* Fixed the '/nbtedit' UI closing when you used the 'accept' hotkey.

## 0.11.1 ##

### Bug Fixes ###

* Fixed key bindings being loaded on the server side, causing a crash.

## 0.11.0 ##

### Features ###

* Fall back to in-hand item if the targeted block has no tile entity when
  calling ServerUtilities' `/nbtedit` command.
* Support a shortcut key to delete the selected NBT tag inside the
  ServerUtilities' `/nbtedit` command.
* Add customizable key binding support to the ServerUtilities' `/nbtedit`
  command hotkey support.

## 0.10.0 ##

### Features ###

* Patched ServerUtilities' `/nbtedit` command to let you use `Numpad enter` to
  submit your changes.

## 0.9.0 ##

### Features ###

* Patched ServerUtilities' `/nbtedit` command to edit the in-hand item, if
  present, if there is no targeted block/entity.

## 0.8.0 ##

### Features ###

* Added the ability to apply bone meal to Thaumcraft saplings.

## 0.7.0 ##

### Features ###

* The ability to apply bone meal to Tinker's Construct Slime Saplings can now be
  toggled on and off via a configuration option.


### Bug Fixes ###

* Fixed changing configuration values from the Forge mod options menu while in a
  single player world not taking effect immediately.

### Technical ###

* Big configuration system overhaul.
* Moved gameplay-related configuration entries into their own sections.
* Moved Tinker's Construct-related configuration entries into a sub-section of
  the gameplay-related configuration section.


## 0.6.3 ##

### Bug Fixes ###

* Fixed the `/repair` command not applying to Tinker's Construct and Gregtech
  tools.
* Fixed the `/repair` command breaking Tinker's Construct and Gregtech tools in
  weird ways.

## 0.6.2 ##

### Bug Fixes ###

* Fixed the `/reload` command not working properly on servers.

## 0.6.1 ##

### Bug Fixes ###

* Fixed the `/extinguish` command trying to access the currently configured
  render distance incorrectly on servers, causing a crash.

## 0.6.0 ##

### Features ###

* Added an `/extinguish` command to extinguish flames in an area.
  * Supports either a radius (in blocks) from the issuing player or a "block
    volume" in the form of two sets of coordinates that form the corners of a
    cube.
  * Radius defaults to the currently configured client render distance in single
    player or server render distance in multiplayer.

## 0.5.3 ##

### Misc. ###

* Adjusted the admin output of the `/repair` command.

## 0.5.2 ##

### Bug Fixes ###

* Fixed an issue where the `/repair` command was formatting the number of
  repaired items VERY wrong.

## 0.5.1 ##

### Bug Fixes ###

* Fixed an issue where the `/teleport-cross-dimension` command was parsing
  coordinates wrong.

## 0.5.0 ##

### Features ###

* Added `/repair` command to repair items.
  * Supports various arguments such as `hand` to repair the currently held item
    or `inventory` to repair only items in the main inventory.
  * Defaults to `everything` to repair all items in the player's inventory, hot
    bar, off-hand (if present), and armor slots.

## 0.4.0 ##

### Features ###

* Allow bone meal to be applied to Tinker's Construct Slime Saplings.

## 0.3.1 ##

### Bug Fixes ###

* Fixed Zombie Pigmen attacking players who are immune to mob aggressive when
  they mine ores in the Nether.

## 0.3.0 ##

### Features ###

* Integrated the mod into ServerUtilities.
  * The mod's configuration can now be reloaded from disk using the `/reload`
    command.
  * All the mod's resources can be reloaded using the
    `/reload gtnh_customizer:*` command.
  * Just the mod's configuration can now be reloaded from disk using the
    `/reload gtnh_customizer:config` command.

## 0.2.3 ##

### Bug Fixes ###

* Fixed the mod's adjustments to how Xaero's mods parse teleport commands not
  working.

## 0.2.2 ##

### Bug Fixes ###

* Fixed broken `/teleport-cross-dimension` command.

## 0.2.1 ##

### Bug Fixes ###

* Fixed `/teleport-cross-dimension` command argument processing.

## 0.2.0 ##

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

### Bug Fixes ###

* Fixed `/teleport-cross-dimension` command not respecting coordinates.

## 0.1.1 ##

### Bug Fixes ###

* Fixed configuration migrations not applying correctly.

## 0.1.0 ##

### Features ###

* Ported existing code from old repository.
* Added a cross-dimension teleport command.
* Changed logo.

### Technical ###

* Updated several dependencies.

## 0.0.0 ##

### Features ###

* Initial release.
