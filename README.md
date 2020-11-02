# Project S
*Project S* is a revival of the [Gulliver Mod](https://www.minecraftforum.net/forums/mapping-and-modding-java-edition/minecraft-mods/1282337-mc-forge-1-6-4-gulliver-the-resizing-mod-v0-14-3=AOvVaw3hS3MltVw-WPI0kdBrf6DF) (whose final version was for 1.6.4) into a more modern version of Minecraft, (1.12.2 initially.) This document plans to detail the features that will return from Gulliver, and new features we plan to either overwrite or add.

## The Basics: Size
This mod allows you to change both player height and mob sizes, in a range from 1/32x to 32x (configurable.) This chart describes those sizes and terminology used to describe it.

| Scale | Name   | Height (MC)          | Height (IRL) |
|-------|--------|----------------------|--------------|
| 1/32x | Micro  | 0.053125 bl (0.85px) | ~2.09in      |
| 1/16x | Tiny   | 0.10625 bl (1.7px)   | ~4.18in      |
| 1/8x  | Mini   | 0.2125 bl (3.4px)    | ~8.37in      |
| 1/4x  | Small  | 0.425 bl (6.8px)     | ~1ft4.73in   |
| 1/2x  | Half   | 0.85 bl (13.6px)     | ~2ft9.5in    |
| 1x    | Normal | 1.7 bl               | ~5ft6.9in    |
| 2x    | Double | 3.4 bl               | ~11ft2in     |
| 4x    | Big    | 6.8 bl               | ~22ft4in     |
| 8x    | Huge   | 13.6 bl              | ~44ft8in     |
| 16x   | Giant  | 27.2 bl (1.7 ch)     | ~89ft3in     |
| 32x   | Mega   | 54.4 bl (3.4 ch)     | ~178ft6in    |

## What effects does each size give?
### Linear Effects
The following stats increase or decrease linearly with scale, ergo, they are directly impacted by height

* Damage taken
* Damage delivered
* Walk/run speed (caps at half-speed, unless wearing gold armor)
* Food effectiveness (inverse)
* Mining speed
* Reach (caps at a minimum of 1 block.)
* XP effectiveness (inverse)
* Potion effectiveness (inverse)
* Mob notice range
* Jump height (crouch-jump gets you to a height one block, unless wearing gold armor)
* Eating/drinking speed
* Knockback effectiveness (inverse)
* Step height
* Hitbox size (small players can go under top-slabs, etc.)
* Water push effectiveness (inverse)

### Entity Interaction
* Riding
	* A player can ride a mob or player by holding a String and mounting them assuming their size ratio is at least 1:4.
* Squishing
	* A player or mob can hurt another player or mob by stepping on top of them, assuming their size ratio is at least 4:1. The damage done scales the higher that ratio is, and is worse if they large mob/player jumps or falls.
* Throwing
	* A player or mob can throw another player or mob by right-clicking them once, then aiming and right-clicking them again, assuming their size ratio is at least 4:1.
* Aggroing
	* If the size ratio between a player and a mob is 1:4, the player is ignored by that mob, with the exception of cats, spiders, silverfish, and golems (which require a 16:1 difference before they stop paying attention to you.)

### Movement Effects
* Small players (and smaller) movement options
	* Gliding with paper or maps
	* Floating on water with Lily Pads
	* Climbing up blocks with Slime Balls (no slime needed for "soft" blocks like Dirt and Sand, flowers, etc.)
	* Grapple with Fishing Rod
	* Mount mobs with String
* Tiny (and smaller) movement options
	* Walk on top of water (surface tension)
	* Climb any block without a Slime Ball
* Small (and smaller) movement effects
	* Snow slows you down
* Big (and bigger) movement effects
	* Brushing up against leaves destroy them
	* Destroy tall grass as they walk
	* Leave footprints (squares the size of 1x1x0.25bl, scaled) if they jump
#### Large players walking effects
 
| Scale | Blocks                                                         |
|-------|----------------------------------------------------------------|
| 3x    | Breaks glass and snow                                          |
| 4x    | Turns grass to dirt                                            |
| 8x    | Cracks stones                                                  |
| 16x   | Stone --> cobble, sandstone --> sand                           |
| 32x   | Stone and cobble --> gravel, destroy sand and dirt (not grass) |

### Misc. Size Effects
* Small (and smaller) players...
	* Get hurt by Roses, but not Cactus
	* Need a stick to do many things (open doors, chests, use buttons, levers...)
	* Nothing insta-breaks (torches, flowers, etc.)
	* Don't activate Tripwire
	* Can double-up on beds
* Mini (and smaller) players...
	* Will slowly drown in rain
	* Need to have multiple players on a Stone (or heavier) Pressure Plate to activate it
	* Can climb inside the hollow part of Chests.
* Big (and bigger) players...
	* Don't get hurt by Cactus
	*  Can punch multiple blocks at once (1x1x1bl, scaled) (unless you crouch)
	* Need more than one bed to sleep (a X by X structure of beds is required, X being the players scale rounded up.)
	* Can't reach into too small of holes
	* Have to right-click items to pick them up
	* Can "press" pressure plates like buttons
* Giant (and larger) players...
	* All mobs run from except zombies (brainless) and bosses
	* The Wither keeps his distance but maintains long-ranged attacks

## New Items
* Potions
	* Potion of Growing (red mushroom)
		* Level I doubles you, Level II quadruples you.
	* Potion of Shrinking (brown mushroom)
		* Level I halves you, Level II quarters you.
* Size Guns
	* Looks like a squirt gun.
	* Fill it with a Potion of Growing or a Potion of Shrinking.
* Size Goo
	* Growth Goo
		* Crafted with a purple dye and an iron nugget.
		* Holding it in an inventory slot doubles your size.
		* The more slots that have one in it, the more doubling occurs.
	* Shrink Goo
		* Crafted with a cyan dye and an iron nugget.
		* Same as Growth Goo but the opposite.
* Crumbs
	* When players eat, they drop crumb entities.
	* Any player who is at least 8x smaller than the player who dropped the crumb can right click on the crumb to eat it.
	* Despawns after a certain amount of time.
* Resin
	* A clear, carpet-like block.
	* Prevents the block below it cracking/changing from big players.
* A way to permanently change size. (this needs to be workshopped as to not be broken.)

## Misc.
* The Withering effect slowly (slowly) shrinks the player.
* In Creative, R and F change your basesize half or double.
	* A spawnable item allows an individual holding it to do this in Survival.

### Commands
* `/basesize <scale or height>`
* `/setsize <scale or height>` (changes your current mult by solving $bm =s$, where b is your basesize, m is your new mult, and s is the inputted size.
* `/showsize <player>` Shows info about a player's basesize, current size, and mult.
* `/adjustsize <height>` Adds the amount of height to your size, by adjusting your mult (use a negative number to shrink)
* `/adjustmult <mult>` Multiply your mult by this number.
* `/adjustbasesize <height>` Adds the amount of height to your basesize (use a negative number to shrink)
* `/adjustbasemult <mult>` Multiply your basesize by this number.
* `/gamerule sizeGriefing <bool>` Do bigs [fuck the earth?](https://www.youtube.com/watch?v=tLBL4M55tJU)

### Configurables
* Size caps (no hard caps, but give a warning for not going above or below what we set.)
* "Gold helmet effects" applied all the time to those it would effect.
* Dye-items either work or don't.
* Mobs/players default spawn size
