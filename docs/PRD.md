# Product Requirements Document (PRD): Villagers Come More Alive

**Version:** 1.0.0+1.20.1

**Date:** 2025/10/09

**Author:** Liu Dongyu, Potted_plant

**Status:**

- [x] Draft
- [ ] In Review
- [ ] Approved

**Issue Tracker:** [https://github.com/Viola-Siemens/villagers-come-more-alive/issues](https://github.com/Viola-Siemens/Spectrum-Rush/issues)

---

### **1. Overview & Vision**

This document outlines the requirements for the development of the Villagers Come More Alive mod for Minecraft. As an addon for MCAR (Minecraft Comes Alive: Reborn), this mod aims to deepen the simulation and role-playing aspects of villagers by introducing life-cycle mechanics, genetic inheritance, and enhanced player-villager interaction.

**1.1. Problem Statement**

The current villager system in Minecraft (and by extension, in MCAR) is static and lacks depth. Villagers do not age, their offspring are generic clones, and their interactions with the player are limited and automated. This limits long-term engagement and emotional attachment for players who wish to build and manage a living, evolving community.

**1.2. Vision Statement**

To transform villagers from simple trading machines into dynamic members of a player's world with distinct life stories, familial ties, and natural life cycles, fostering a deeper sense of connection and management.

**1.3. Goals & Success Metrics**

Player Adoption: 1,000 downloads on CurseForge & Modrinth within the first month.

Community Sentiment: Positive feedback highlighting the added depth and realism to village management.

Integration: Seamless integration with MCAR without conflicts reported.

### **2. User Personas & Stories**

2.1. Persona 1: The World-Builder William

Playstyle: Enjoys creating elaborate towns and stories. Focuses on aesthetics and the "life" of his world.

Goals: To create a village that feels alive, with unique citizens who have histories and families.

Frustrations: All villagers look and act the same. Children are just small adults with no connection to their parents.

2.2. Persona 2: The Technical Tester Tina

Playstyle: Enjoys complex systems, mechanics, and optimization. Likes to have fine-grained control over her world.

Goals: To manage her village efficiently and understand the underlying rules of genetics and aging.

Frustrations: Lack of control over villager professions and annoying, repetitive sounds.

2.3. User Stories

As William, I want child villagers to inherit physical traits like height and face shape from their parents so that I can see a visual lineage and feel that families are unique.

As William, I want villagers to eventually grow old and pass away so that I can experience a natural life cycle in my village, adding to its story.

As Tina, I want to use a special item to summon specific villagers to my location so that I can efficiently manage and relocate my villagers without tedious pushing.

As Tina, I want to manually assign a villager to a job site block so that I can precisely control the profession distribution in my village.

As Tina, I want to be able to mute laughter of adult villagers so that I can build in peace without disruptive noise.

### **3. Competitive Analysis (Mod Landscape)**

This mod is an addon to MCAR and focuses on a niche of villager life simulation. Direct competitors are few, but it complements existing villager enhancement mods by focusing on genetics and aging rather than new professions or trades.

<table>
    <tr>
        <th>Mod Name</th>
        <th>Strengths</th>
        <th>Weaknesses</th>
        <th>Open source</th>
        <th>Compatible with 1.20+</th>
        <th>Gallery</th>
        <th>How We Differentiate</th>
    </tr>
    <tr>
        <td><b>Minecraft Comes Alive: Reborn (MCAR)</b></td>
        <td><strong>1. Deep Social & Family Simulation:</strong> Transforms villagers into named humans, enabling complex interactions like marriage, having children, and establishing multi-generational families. Children can grow up and perform chores.<br><br> <strong>2. Rich Role-Playing Elements:</strong> Offers extensive role-playing depth with over 2000 dialogue lines, 20 languages, and more than 200 skins. Villagers possess unique personalities, moods, and memories, significantly enhancing immersion.<br><br> <strong>3. Unique Boss Mechanics & Progression:</strong> Features a unique end-game challenge where players can summon and battle the Grim Reaper, a powerful boss with complex abilities, to obtain the Staff of Life for resurrecting deceased family members.<br><br> <strong>4. Multiplayer Support:</strong> Integrates well into server environments, allowing players to marry and have children with each other, enhancing the social experience in multiplayer.</td>
        <td><strong>1. Potential for Inconsistency Between Versions:</strong> Features may vary between versions. For example, the magic crystal ball used for initial character setup in earlier versions was reportedly removed in newer ones, which could lead to user confusion.<br><br> <strong>2. High Difficulty Spike for Key Features:</strong> Acquiring the Staff of Life requires defeating the Grim Reaper, a very tough boss immune to projectiles and poison, which may pose a significant challenge for many players.<br><br> <strong>3. Potential for World Generation Conflicts:</strong> The initial setup using the magic crystal ball was noted to generate buildings that could overwrite existing structures in the world, potentially causing issues in modded environments.<br><br> <strong>4. Complexity in Configuration:</strong> The mod offers a vast array of configurable options, which, while powerful, could be overwhelming for less technical users to fine-tune.</td>
        <td>✔</td>
        <td>✔</td>
        <td><img src="https://cdn.modrinth.com/data/1W98a849/images/3cfd812436d5dbf095ad31c6265564fbca24233a.png" style="max-height: 200px"/></td>
        <td><strong>Our mod, "Villagers Come More Alive," acts as an addon to MCAR and focuses on deepening the simulation by introducing:</strong><br/>1. A defined lifespan and aging system, leading to natural death.<br/>2. Genetic inheritance for physical traits in offspring.<br/>3. Quality-of-life enhancements like villager teleportation and manual job assignment, addressing some of MCAR's management complexities.</td>
    </tr>
</table>

### **4. Core Features & Modules**

![PRD](Product%20Architecture%20Diagram.png)

#### **4.1 Teleportation System**

- **Description**: Allows players to summon villagers over long distances for better management.
- **Features**:
  - **Blueprint Item**: The "Blueprint", a modded item from MCAR, can be used as the key for villager teleportation.
  - **Summoning Mechanic**: Player can right-click while holding the Blueprint to open a GUI to select a villager from the current village, ~~or use it on a specific villager to mark them~~. A subsequent right-click in the target location will summon the selected/~~marked~~ villager to the player's side.
  - **Cooldown/Cost**: <u>*The process may have a cooldown or require experience levels to balance its utility*</u>.

#### **4.2. Genetics & Inheritance System**

- **Description**: Governs the physical appearance of child villagers based on their parents.
- **Features**:

  - **Trait Pool**: Defines inheritable traits: Height, Weight, Face Type, Skin Tone.
- **Inheritance Logic**: When a child villager is born, for each trait (height, weight, face, skin), the game randomly selects one gene per allele from the both two parents and copies that specific trait from them. This creates a child that is a unique mix of both parents, rather than a random one or a generic clone.

#### **4.3. Lifespan & Aging System**

- **Description**: Introduces a full life cycle for villagers, culminating in old age and death.
- **Features**:

  - **Aging Timer**: A villager enters adulthood (as per vanilla/MCAR). From that point, a timer starts. After a default of 360,000 game ticks (15 in-game days, 5 hours in real-time), the villager becomes an Elder.
  - **Elderly Characteristics**:
    - **Unemployable**: The villager enters an un-employed state and can no longer be bound to any job site block.
    - **Reduced Mobility**: The villager's walking speed is significantly reduced.
  - **Death from Old Age**: After becoming an Elder, a second timer of 2,000 game ticks (100 seconds in real-time) begins. Once this timer elapses, the villager dies.

#### **4.4. Quality of Life & Interaction Tweaks**

- **Description**: Small but impactful changes to villager behavior and player control.
- **Features**:
  - **Manual Job Assignment**: Villagers will no longer automatically claim a job site block upon proximity. Instead, the player must manually initiate the binding (by crouch-right-clicking the villager with the job site block in hand).
  - **Villager Sound Muting**: Players are given the ability to toggle off the laughter emitted by adult villagers. This could be done via a config option or an in-game interaction (e.g., using a specific item on the villager).

### **5. Task Priority**

<table>
  <tr>
    <th>Module</th>
    <th>Sub-Module</th>
    <th>Priority</th>
    <th>Status</th>
  </tr>
  <tr>
    <td rowspan="2">Teleportation System</td>
    <td>Blueprint Item & Summoning Mechanic</td>
    <td><span style="color:orchid;font-weight:600">P1</span></td>
    <td><input type="checkbox"/></td>
  </tr>
  <tr>
    <td>GUI & Cost/Balance</td>
    <td><span style="color:deepskyblue">P2</span></td>
    <td><input type="checkbox"/></td>
  </tr>
  <tr>
    <td rowspan="2">Genetics & Inheritance System</td>
    <td>Trait Pool</td>
    <td><span style="color:red;font-weight:900">P0</span></td>
    <td><input type="checkbox"/></td>
  </tr>
  <tr>
    <td>Inheritance Logic</td>
    <td><span style="color:red;font-weight:900">P0</span></td>
    <td><input type="checkbox"/></td>
  </tr>
  <tr>
    <td rowspan="3">Lifespan & Aging System</td>
    <td>Aging Timer & Elder State Transition</td>
    <td><span style="color:red;font-weight:900">P0</span></td>
    <td><input type="checkbox"/></td>
  </tr>
  <tr>
    <td>Elderly Characteristics</td>
    <td><span style="color:orchid;font-weight:600">P1</span></td>
    <td><input type="checkbox"/></td>
  </tr>
  <tr>
    <td>Death from Old Age</td>
    <td><span style="color:red;font-weight:900">P0</span></td>
    <td><input type="checkbox"/></td>
  </tr>
  <tr>
    <td rowspan="2">Quality of Life & Interaction Tweaks</td>
    <td>Manual Job Assignment</td>
    <td><span style="color:orchid;font-weight:600">P1</span></td>
    <td><input type="checkbox"/></td>
  </tr>
  <tr>
    <td>Villager Sound Muting</td>
    <td><span>P3</span></td>
    <td><input type="checkbox"/></td>
  </tr>
</table>

### **6. Technical Architecture**

- **Target Minecraft Version:** 1.20.1
- **Mod Loaders:** Forge.
- **Java Version:** JDK 17
- **Key Dependencies:** MCAR, Fabric API (Fabric/Quilt only), Jade (optional).
