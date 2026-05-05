# Test Case Design Document: Villagers Come More Alive

**Version:** 1.0.0

**Date:** 2026-04-27

**Author:** Liu Dongyu

**Related Documents:**

- [PRD.md](./PRD.md)
- [blueprint.puml](./blueprint.puml)

---

## Table of Contents

1. [Test Environment & General Preconditions](#1-test-environment--general-preconditions)
2. [Module A: Teleportation System](#2-module-a-teleportation-system)
3. [Module B: Genetics & Inheritance System](#3-module-b-genetics--inheritance-system)
4. [Module C: Lifespan & Aging System](#4-module-c-lifespan--aging-system)
5. [Module D: Quality of Life & Interaction Tweaks](#5-module-d-quality-of-life--interaction-tweaks)
6. [Integration & Regression Tests](#6-integration--regression-tests)
7. [Traceability Matrix](#7-traceability-matrix)

---

## 1. Test Environment & General Preconditions

| Item | Value |
|---|---|
| Minecraft Version | 1.20.1 |
| Mod Loader | Forge |
| Java Version | JDK 17 |
| Required Dependencies | MCAR (Minecraft Comes Alive: Reborn) |
| Optional Dependencies | Jade |
| Test World Type | Creative (for rapid setup) & Survival (for gameplay validation) |

**General Preconditions (applicable to all tests unless overridden):**

- GP-1: MCAR is installed and loaded successfully.
- GP-2: VCMA (Villagers Come More Alive) is installed and loaded successfully.
- GP-3: A world with at least one MCAR village containing adult villagers exists.
- GP-4: The player has operator/cheat privileges for setup convenience.

---

## 2. Module A: Teleportation System

### 2.1 Blueprint Item & GUI Flow

#### TC-A-001: Open Blueprint GUI via Right-Click

| Field | Detail |
|---|---|
| **Priority** | P1 |
| **Test Type** | Manual |
| **Preconditions** | Player holds a Blueprint item (provided by MCAR). |
| **Steps** | 1. Hold the Blueprint in the main hand.<br>2. Right-click in the air or on a block. |
| **Expected Result** | A GUI screen opens (MCAR logic creates and binds the menu). The screen displays interactive elements including a "Villager" button. |
| **Notes** | This step is driven by MCAR logic; VCMA extends it in subsequent steps. |

#### TC-A-002: Query and Display All Villagers

| Field | Detail |
|---|---|
| **Priority** | P2 |
| **Test Type** | Manual |
| **Preconditions** | Blueprint GUI is open (from TC-A-001). The current village has at least 3 adult villagers. |
| **Steps** | 1. Click the "Villager" button in the Blueprint GUI. |
| **Expected Result** | A list of all villagers belonging to the current village is displayed. Each entry shows the villager's name. |
| **Notes** | Verify that villagers outside the current village are NOT shown. |

#### TC-A-003: Query Villagers — Empty Village

| Field | Detail |
|---|---|
| **Priority** | P2 |
| **Test Type** | Manual |
| **Preconditions** | Blueprint GUI is open. The current village has no villagers (all dead or moved away). |
| **Steps** | 1. Click the "Villager" button. |
| **Expected Result** | The list is empty, or a message indicating "no villagers available" is displayed. No crash or error occurs. |

#### TC-A-004: Right-Click Villager Name — Show Operation List

| Field | Detail |
|---|---|
| **Priority** | P2 |
| **Test Type** | Manual |
| **Preconditions** | Villager list is displayed (from TC-A-002). |
| **Steps** | 1. Right-click on a villager's name in the list. |
| **Expected Result** | The operation is validated (VCMA modded logic). An operation list is shown, containing at least a "Teleport" option. |
| **Notes** | This is the entry point for VCMA-specific logic per the blueprint sequence diagram. |

#### TC-A-005: Click "Teleport" — Summon Villager

| Field | Detail |
|---|---|
| **Priority** | P2 |
| **Test Type** | Manual |
| **Preconditions** | Operation list is displayed for a specific villager (from TC-A-004). The target villager is alive and loaded. |
| **Steps** | 1. Click the "Teleport" button in the operation list. |
| **Expected Result** | The GUI screen closes. The selected villager is teleported to the player's current location (same coordinates). |
| **Notes** | Verify the villager appears immediately at the player's side, not at the original location. |

#### TC-A-006: Teleport — Villager in Unloaded Chunk

| Field | Detail |
|---|---|
| **Priority** | P2 |
| **Test Type** | Manual |
| **Preconditions** | The selected villager is in a chunk that is not currently loaded (e.g., player is far away in another dimension). |
| **Steps** | 1. Open Blueprint GUI, select a villager from a distant village.<br>2. Click "Teleport". |
| **Expected Result** | The villager is force-loaded (or the chunk is loaded) and teleported to the player. If force-loading is not implemented, the operation should fail gracefully with an appropriate message. |
| **Notes** | Determine expected behavior based on implementation decision. Document the chosen approach. |

#### TC-A-007: Teleport — Villager Already Dead

| Field | Detail |
|---|---|
| **Priority** | P2 |
| **Test Type** | Manual |
| **Preconditions** | A villager was listed in the GUI, but died (e.g., killed by zombie) before the player clicked "Teleport". |
| **Steps** | 1. Open the villager list. Kill the target villager via commands or mob attack.<br>2. Click "Teleport" for the now-dead villager. |
| **Expected Result** | The operation fails gracefully. An error message is shown (e.g., "Villager no longer exists") or the teleport is silently ignored. No crash occurs. |

#### TC-A-008: Teleport — Across Dimensions

| Field | Detail |
|---|---|
| **Priority** | P2 |
| **Test Type** | Manual |
| **Preconditions** | Player is in the Nether or the End. The target villager is in the Overworld. |
| **Steps** | 1. Use the Blueprint in the Nether/End.<br>2. Select an Overworld villager and click "Teleport". |
| **Expected Result** | The villager is teleported across dimensions to the player's location. If cross-dimension teleport is not supported, the operation should fail with a clear message. |
| **Notes** | Confirm design intent — PRD does not explicitly restrict cross-dimension teleport. |

#### TC-A-009: Close GUI Without Teleporting

| Field | Detail |
|---|---|
| **Priority** | P2 |
| **Test Type** | Manual |
| **Preconditions** | Blueprint GUI is open at any stage (villager list or operation list). |
| **Steps** | 1. Press Escape or click the close button. |
| **Expected Result** | The GUI closes. No villager is teleported. The Blueprint item is not consumed. |

#### TC-A-010:alen: Blueprint Item Not Consumed on Use

| Field | Detail |
|---|---|
| **Priority** | P1 |
| **Test Type** | Manual |
| **Preconditions** | Player has exactly 1 Blueprint. |
| **Steps** | 1. Use the Blueprint to open the GUI.<br>2. Complete a full teleport cycle (select villager → teleport).<br>3. Check inventory. |
| **Expected Result** | The Blueprint item remains in the player's inventory after both opening the GUI and completing a teleport. |

---

## 3. Module B: Genetics & Inheritance System

### 3.1 Trait Pool Definition

#### TC-B-001: Trait Pool Contains Required Traits

| Field | Detail |
|---|---|
| **Priority** | P0 |
| **Test Type** | Automated (Unit) |
| **Preconditions** | Mod is loaded. |
| **Steps** | 1. Inspect the trait pool registry or data structure.<br>2. Verify the presence of Face Type, Skin Tone, and Skin Pattern traits. |
| **Expected Result** | All three traits (Face Type, Skin Tone, Skin Pattern) are defined in the trait pool. No additional undocumented traits exist. |

#### TC-B-002: Each Trait Has Valid Value Range

| Field | Detail |
|---|---|
| **Priority** | P0 |
| **Test Type** | Automated (Unit) |
| **Preconditions** | Mod is loaded. |
| **Steps** | 1. For each trait (Face Type, Skin Tone, Skin Pattern), enumerate all possible values.<br>2. Verify each value maps to a valid visual representation (texture/skin reference). |
| **Expected Result** | All trait values are valid and non-null. No orphaned or missing texture references. |

### 3.2 Inheritance Logic

#### TC-B-003: Child Inherits Traits from Parents — Basic Case

| Field | Detail |
|---|---|
| **Priority** | P0 |
| **Test Type** | Automated (Unit) |
| **Preconditions** | Two parent villagers with known, distinct trait values for all three traits. |
| **Steps** | 1. Trigger a child birth event for the two parents.<br>2. Inspect the child's Face Type, Skin Tone, and Skin Pattern.<br>3. Repeat 20 times to gather a statistical sample. |
| **Expected Result** | For each trait, the child's value is always one of the two parental values (never a third, random value). Over 20 trials, both parental values appear for each trait at least once. |
| **Notes** | This validates the "randomly selects one gene per allele from both parents" rule. |

#### TC-B-004: Child Inheritance — Parents with Same Trait Value

| Field | Detail |
|---|---|
| **Priority** | P0 |
| **Test Type** | Automated (Unit) |
| **Preconditions** | Both parents have the identical value for Face Type (e.g., both "Type A"). |
| **Steps** | 1. Trigger child birth.<br>2. Inspect child's Face Type. |
| **Expected Result** | The child's Face Type is always the same as both parents (deterministic). |

#### TC-B-005: Child Inheritance — Each Trait Independent

| Field | Detail |
|---|---|
| **Priority** | P0 |
| **Test Type** | Automated (Unit) |
| **Preconditions** | Father: Face=A, SkinTone=Light, SkinPattern=None. Mother: Face=B, SkinTone=Dark, SkinPattern=Freckles. |
| **Steps** | 1. Trigger child birth.<br>2. Inspect all three traits on the child. |
| **Expected Result** | The child's Face is either A or B (independent of other traits). Skin Tone is either Light or Dark. Skin Pattern is either None or Freckles. The three traits are inherited independently — e.g., a child can have Face=A, SkinTone=Dark, SkinPattern=Freckles. |

#### TC-B-006: Multiple Children from Same Parents — Variation

| Field | Detail |
|---|---|
| **Priority** | P0 |
| **Test Type** | Manual (in-game observation) |
| **Preconditions** | Two parent villagers with distinct traits. |
| **Steps** | 1. Have the same couple produce at least 5 children.<br>2. Observe the trait combinations of all children. |
| **Expected Result** | Not all children are identical. At least two different trait combinations are observed across the 5 children. |
| **Notes** | This is a manual validation of the user story: "I want child villagers to inherit physical traits... so that families are unique." |

#### TC-B-007: Inheritance Does Not Affect Non-Visual Properties

| Field | Detail |
|---|---|
| **Priority** | P0 |
| **Test Type** | Automated (Unit) |
| **Preconditions** | Two parent villagers. |
| **Steps** | 1. Trigger child birth.<br>2. Verify the child's non-visual properties (profession, inventory, health, etc.) are set to default child values, not inherited from parents. |
| **Expected Result** | Only the three defined traits (Face Type, Skin Tone, Skin Pattern) are inherited. No other properties leak from parents. |

---

## 4. Module C: Lifespan & Aging System

### 4.1 Aging Timer & Elder State Transition

#### TC-C-001: Adult-to-Elder Transition After 360,000 Ticks

| Field | Detail |
|---|---|
| **Priority** | P0 |
| **Test Type** | Automated (Unit / Integration) |
| **Preconditions** | A villager that has just reached adulthood (age = 0 as adult). Game time is controllable. |
| **Steps** | 1. Record the game tick when the villager becomes an adult.<br>2. Advance game time by 359,999 ticks.<br>3. Verify the villager is still an adult (not Elder).<br>4. Advance 1 more tick (total 360,000).<br>5. Verify the villager is now in the Elder state. |
| **Expected Result** | The transition occurs exactly at tick 360,000 after adulthood. Not earlier, not later. |

#### TC-C-002: Child Villager Does Not Age into Elder Directly

| Field | Detail |
|---|---|
| **Priority** | P0 |
| **Test Type** | Automated (Unit) |
| **Preconditions** | A child villager (pre-adulthood). |
| **Steps** | 1. Advance game time by 720,000 ticks (well beyond the adult→elder threshold).<br>2. Observe the villager's state. |
| **Expected Result** | The villager first transitions to Adult (per vanilla/MCAR rules), and only then starts the 360,000-tick aging timer toward Elder. The child does NOT skip directly to Elder. |

#### TC-C-003: Aging Timer Persists Across Chunk Unload/Reload

| Field | Detail |
|---|---|
| **Priority** | P0 |
| **Test Type** | Manual |
| **Preconditions** | An adult villager with 100,000 ticks elapsed on the aging timer. |
| **Steps** | 1. Note the current timer value.<br>2. Move far away so the villager's chunk unloads.<br>3. Wait 30 seconds in real-time.<br>4. Return to the villager's chunk. |
| **Expected Result** | The aging timer has advanced by the amount of game time that passed while the chunk was unloaded. The villager does NOT reset to 0. |

#### TC-C-004: Aging Timer Persists Across World Reload

| Field | Detail |
|---|---|
| **Priority** | P0 |
| **Test Type** | Manual |
| **Preconditions** | An adult villager with 200,000 ticks elapsed on the aging timer. |
| **Steps** | 1. Note the current timer value.<br>2. Save and quit to title.<br>3. Reload the world.<br>4. Check the villager's aging timer. |
| **Expected Result** | The aging timer value is preserved (approximately 200,000 ticks). The villager does NOT reset to 0. |

#### TC-C-005: Aging Timer Configurable

| Field | Detail |
|---|---|
| **Priority** | P1 |
| **Test Type** | Manual |
| **Preconditions** | Access to mod configuration file. |
| **Steps** | 1. Change the aging timer value in the config to 180,000 ticks.<br>2. Reload the world or restart the game.<br>3. Observe an adult villager's transition timing. |
| **Expected Result** | The villager transitions to Elder after 180,000 ticks (the configured value), not the default 360,000. |

### 4.2 Elderly Characteristics

#### TC-C-006: Elder Villager is Unemployable

| Field | Detail |
|---|---|
| **Priority** | P1 |
| **Test Type** | Manual |
| **Preconditions** | A villager has just transitioned to Elder. The villager previously had a profession (e.g., Farmer) bound to a job site block (e.g., Composter). |
| **Steps** | 1. Observe the Elder villager's profession state.<br>2. Attempt to assign a new job by crouch-right-clicking with a job site block. |
| **Expected Result** | The Elder villager's profession is cleared (unemployed state). The previously bound job site block is unclaimed. The Elder villager cannot be assigned a new profession — the manual job assignment attempt fails or is ignored. |

#### TC-C-007: Elder Villager Has Reduced Walking Speed

| Field | Detail |
|---|---|
| **Priority** | P1 |
| **Test Type** | Manual |
| **Preconditions** | An Elder villager and a normal Adult villager side by side. |
| **Steps** | 1. Measure or visually compare the walking speed of both villagers over a20 a fixed distance (e.g., 10 blocks). |
| **Expected Result** | The Elder villager walks noticeably slower than the Adult villager. The speed reduction is "significant" as stated in the PRD. |

#### TC-C-008: Elder Villager Cannot Breed

| Field | Detail |
|---|---|
| **Priority** | P1 |
| **Test Type** | Manual |
| **Preconditions** | An Elder villager and another adult villager. |
| **Steps** | 1. Attempt to initiate breeding between the Elder and an adult (via MCAR mechanics). |
| **Expected Result** | Breeding is not possible with the Elder villager. Hearts/breeding particles do not appear. |
| **Notes** | This may be implicit from the "unemployable"/elderly state but should be explicitly verified. |

### 4.3 Death from Old Age

#### TC-C-009: Elder Dies After 2,000 Ticks

| Field | Detail |
|---|---|
| **Priority** | P0 |
| **Test Type** | Automated (Unit / Integration) |
| **Preconditions** | A villager has just transitioned to Elder. |
| **Steps** | 1. Record the game tick of the Elder transition.<br>2. Advance game time by 1,999 ticks — verify villager is still alive.<br>3. Advance 1 more tick (total 2,000). |
| **Expected Result** | The Elder villager dies exactly at tick 2,000 after becoming Elder. |

#### TC-C-010: Death from Old Age — Visual & Audio Feedback

| Field | Detail |
|---|---|
| **Priority** | P1 |
| **Test Type** | Manual |
| **Preconditions** | An Elder villager about to die (timer at ~1,990 ticks). |
| **Steps** | 1. Stand near the Elder villager and watch the death occur. |
| **Expected Result** | The death has appropriate visual particles/sound (e.g., the standard villager death sound, or a custom effect). The villager's entity is removed from the world. |
| **Notes** | Confirm whether a death message appears in chat. |

#### TC-C-011: Death from Old Age — Inventory Handling

| Field | Detail |
|---|---|
| **Priority** | P1 |
| **Test Type** | Manual |
| **Preconditions** | An Elder villager carrying items in their inventory (if MCAR villagers have inventories). |
| **Steps** | 1. Give the villager some items.<br>2. Wait for the villager to die of old age. |
| **Expected Result** | Items are dropped on the ground at the death location, consistent with vanilla/MCAR death behavior. Items are not lost/destroyed. |

#### TC-C-012: Death from Old Age —MCAR Family Relationships

| Field | Detail |
|---|---|
| **Priority** | P1 |
| **Test Type** | Manual |
| **Preconditions** | An Elder villager who is a spouse or parent in an MCAR family. |
| **Steps** | 1. Wait for the Elder to die of old age.<br>2. Check the family tree / relationship status of surviving family members. |
| **Expected Result** | The deceased villager is marked as "deceased" in MCAR's family system. Surviving family members' relationships are updated (e.g., spouse becomes widowed). No crash or data corruption occurs. |

#### TC-C-013: Elder Death Timer Persists Across World Reload

| Field | Detail |
|---|---|
| **Priority** | P0 |
| **Test Type** | Manual |
| **Preconditions** | An Elder villager with 1,000 ticks elapsed on the death timer. |
| **Steps** | 1. Save and quit to title.<br>2. Reload the world.<br>3. Advance time by 1,000 ticks. |
| **Expected Result** | The Elder dies after the remaining 1,000 ticks (total 2,000). The death timer was correctly persisted. |

---

## 5. Module D: Quality of Life & Interaction Tweaks

### 5.1 Manual Job Assignment

#### TC-D-001: Crouch-Right-Click Assigns Job

| Field | Detail |
|---|---|
| **Priority** | P1 |
| **Test Type** | Manual |
| **Preconditions** | An unemployed adult villager. Player holds a job site block (e.g., Composter) in the main hand. |
| **Steps** | 1. Crouch (sneak).<br>2. Right-click on the villager while holding the job site block. |
| **Expected Result** | The villager is assigned the corresponding profession (e.g., Farmer for Composter). Visual indicators (particles, villager clothing change) confirm the assignment. |

#### TC-D-002: Normal Right-Click Does NOT Assign Job

| Field | Detail |
|---|---|
| **Priority** | P1 |
| **Test Type** | Manual |
| **Preconditions** | An unemployed adult villager. Player holds a job site block. |
| **Steps** | 1. Right-click on the villager WITHOUT crouching. |
| **Expected Result** | The normal MCAR interaction occurs (dialogue/interaction menu). No job assignment takes place. |

#### TC-D-003: Villagers Do NOT Auto-Claim Job Site Blocks

| Field | Detail |
|---|---|
| **Priority** | P1 |
| **Test Type** | Manual |
| **Preconditions** | An unemployed adult villager standing next to an unclaimed job site block (e.g., Composter). |
| **Steps** | 1. Place the job site block near the villager.<br>2. Wait for 30 seconds (or several day/night cycles).<br>3. Observe whether the villager claims the block. |
| **Expected Result** | The villager remains unemployed. The job site block remains unclaimed. No automatic claiming occurs. |

#### TC-D-004:2416 Manual Assignment — Job Site Block Must Be in Hand

| Field | Detail |
|---|---|
| **Priority** | P1 |
| **Test Type** | Manual |
| **Preconditions** | An unemployed adult villager. Player has an empty hand. |
| **Steps** | 1. Crouch-right-click the villager with empty hand. |
| **Expected Result** | No job assignment occurs. The normal MCAR interaction (or nothing) happens. |

#### TC-D-005: Manual Assignment — Already Employed Villager

| Field | Detail |
|---|---|
| **Priority** | P1 |
| **Test Type** | Manual |
| **Preconditions** | A villager already employed as a Farmer (bound to Composter A). Player holds a different job site block (e.g., Smithing Table). |
| **Steps** | 1. Crouch-right-click the employed villager with the Smithing Table. |
| **Expected Result** | The villager's profession changes from Farmer to Tools Smith. The old job site block (Composter A) is unclaimed. The new block is bound. |

#### TC-D-006: Manual Assignment — Same Profession Re-Assignment

| Field | Detail |
|---|---|
| **Priority** | P2 |
| **Test Type** | Manual |
| **Preconditions** | A villager employed as a Farmer bound to Composter A. Player holds a different Composter (Composter B). |
| **Steps** | 1. Crouch-right-click the Farmer with Composter B. |
| **Expected Result** | The villager remains a Farmer but is now bound to Composter B. Composter A is unclaimed. |

#### TC-D-007: Manual Assignment — Job Site Block Out of Range

| Field | Detail |
|---|---|
| **Priority** | P2 |
| **Test Type** | Manual |
| **Preconditions** | An unemployed adult villager and a job site block400 placed 50 blocks away. |
| **Steps** | 1.42 Crouch-right-click the villager with the job site block in hand.<br>2.58 Observe whether the block is bound. |
| **Expected Result** | If there is a range limit, the assignment fails with feedback. If no range limit, the block is bound regardless of distance. |
| **Notes** | Determine expected behavior based on design. PRD does not specify a range limit. |

#### TC-D-008: Manual Assignment — Elder Villager Rejection

| Field | Detail |
|---|---|
| **Priority** | P1 |
| **Test Type** | Manual |
| **Preconditions** | An Elder villager (unemployable per TC-C-006). Player holds a job site block. |
| **Steps** | 1. Crouch-right-click the Elder villager with the job site block. |
| **Expected Result** | No job assignment occurs. The Elder remains unemployed. |
| **Notes** | This is a cross-module validation with Module C. |

---

## 6. Integration & Regression Tests

### 6.1 MCAR Compatibility

#### TC-I-001: MCAR Core Features Unaffected

| Field | Detail |
|---|---|
| **Priority** | P0 |
| **Test Type** | Manual |
| **Preconditions** | VCMA installed alongside MCAR. |
| **Steps** | 1. Test MCAR's core features: villager interaction dialogue, marriage, having children, family tree, gift-giving, chores.<br>2. Verify each feature works as expected. |
| **Expected Result** | All MCAR core features function normally. No conflicts, crashes, or unexpected behavior introduced by VCMA. |

#### TC-I-002:2416 MCAR Villager Data Integrity

| Field | Detail |
|---|---|
| **Priority** | P0 |
| **Test Type** | Manual |
| **Preconditions** | A world with MCAR villagers that have established relationships, memories, personalities. |
| **Steps** | 1. Install VCMA into the existing world.<br>2. Load the world.<br>3. Inspect several villagers' MCAR data (relationships, personality, memories). |
| **Expected Result** | Existing MCAR villager data is intact. No data loss or corruption. VCMA's new data fields (aging timer, traits) are initialized with sensible defaults. |

### 6.2 Multiplayer

#### TC-I-003: Teleport in Multiplayer — Client-Initiated

| Field | Detail |
|---|---|
| **Priority** | P2 |
| **Test Type** | Manual |
| **Preconditions** | Two players on a server. Player A uses the Blueprint. |
| **Steps** | 1. Player A opens Blueprint GUI and teleports a villager.<br>2. Player B observes the result. |
| **Expected Result** | The villager is teleported to Player A's location. Player B sees the villager disappear from the original location and appear at Player A's location. No desync or ghost entity. |

#### TC-I-004: Manual Job Assignment in Multiplayer

| Field | Detail |
|---|---|
| **Priority** | P2 |
| **Test Type** | Manual |
| **Preconditions** | Two players near the same villager. |
| **Steps** | 1. Player A assigns a job to the villager.<br>2. Player B observes the villager's profession change. |
| **Expected Result** | Both players see the profession update. The job site block is marked as claimed for both players. |

### 6.3 Edge Cases & Robustness

#### TC-I-005: World Load with No MCAR Installed

| Field | Detail |
|---|---|
| **Priority** | P1 |
| **Test Type** | Manual |
| **Preconditions** | VCMA installed, MCAR NOT installed. |
| **Steps** | 1. Attempt to load the game. |
| **Expected Result** | The game either fails to load with a clear dependency error, or VCMA disables itself gracefully with a warning in the log. The game does not crash silently or corrupt the world. |

#### TC-I-006: Tick-Performance — Aging Timer Overhead

| Field | Detail |
|---|---|
| **Priority** | P1 |
| **Test Type** | Manual (profiling) |
| **Preconditions** | A village with 50+ adult villagers. |
| **Steps** | 1. Use a profiler (e.g., Spark) to measure server tick time with VCMA enabled vs. disabled.<br>2. Compare MSPT (milliseconds per tick). |
| **Expected Result** | The additional overhead from VCMA's aging timer is negligible (< 0.5 ms per tick increase). No observable TPS drop. |

#### TC-I-007: Command-Based Manipulation (if applicable)

| Field | Detail |
|---|---|
| **Priority** | P2 |
| **Test Type** | Manual |
| **Preconditions** | Operator privileges. |
| **Steps** | 1. If VCMA provides commands (e.g., `/vcma setAge <villager> <ticks>`), test each command with valid and invalid arguments. |
| **Expected Result** | Commands function as documented. Invalid arguments produce clear error messages. |
| **Notes** | Skip if no commands are implemented in this version. |

---

## 7. Traceability Matrix

| Requirement (PRD Section) | Test Case IDs |
|---|---|
| 4.1 Blueprint Item (P1) | TC-A-001, TC-A-010 |
| 4.1 GUI & Summoning Mechanic (P2) | TC-A-002, TC-A-003, TC-A-004, TC-A-005, TC-A-006, TC-A-007, TC-A-008, TC-A-009 |
| 4.2 Trait Pool (P0) | TC-B-001, TC-B-002 |
| 4.2 Inheritance Logic (P0) | TC-B-003, TC-B-004, TC-B-005, TC-B-006, TC-B-007 |
| 4.3 Aging Timer & Elder Transition (P0) | TC-C-001, TC-C-002, TC-C-003, TC-C-004, TC-C-005 |
| 4.3 Elderly Characteristics (P1) | TC-C-006, TC-C-007, TC-C-008 |
| 4.3 Death from Old Age (P0) | TC-C-009, TC-C-010, TC-C-011, TC-C-012, TC-C-013 |
| 4.4 Manual Job Assignment (P1) | TC-D-001, TC-D-002, TC-D-003, TC-D-004, TC-D-005, TC-D-006, TC-D-007, TC-D-008 |
| 1.3 Seamless MCAR Integration | TC-I-001, TC-I-002 |
| 1.3 Multiplayer Support (implied) | TC-I-003, TC-I-004 |
| Robustness / Edge Cases | TC-I-005, TC-I-006, TC-I-007 |

---

*End of Test Case Design Document*
