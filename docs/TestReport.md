# Test Report: Villagers Come More Alive

**Version:** 1.0.0

**Date:** 2026-05-05

**Build/Artifact Under Test:** 860d23331d18799b559cfc02c61aecf9a7ff1a0e

**Related Documents:**

- [PRD.md](./PRD.md)
- [blueprint.puml](./blueprint.puml)
- [TestCase.md](./TestCase.md)

---

## Table of Contents

1. [Test Summary](#1-test-summary)
2. [Test Environment](#2-test-environment)
3. [Module A: Teleportation System — Results](#3-module-a-teleportation-system--results)
4. [Module B: Genetics & Inheritance System — Results](#4-module-b-genetics--inheritance-system--results)
5. [Module C: Lifespan & Aging System — Results](#5-module-c-lifespan--aging-system--results)
6. [Module D: Quality of Life & Interaction Tweaks — Results](#6-module-d-quality-of-life--interaction-tweaks--results)
7. [Integration & Regression Tests — Results](#7-integration--regression-tests--results)
8. [Defects](#8-defects)
9. [Conclusion](#9-conclusion)

---

## 1. Test Summary

| Metric                         | Count |
|--------------------------------|-------|
| Total Test Cases               | 45    |
| P0 (Critical)                  | 15    |
| P1 (High)                      | 18    |
| P2 (Medium)                    | 12    |
| Automated (Unit / Integration) | 10    |
| Manual                         | 35    |

| Result       | Count  | Percentage |
|--------------|--------|------------|
| Passed       | 27     | 60%        |
| Failed       | 0      | 0%         |
| Blocked      | 0      | 0%         |
| Not Executed | 18     | 40%        |
| **Total**    | **45** | **100%**   |

**Execution Period:** (to be filled)

**Tester(s):** (to be filled)

---

## 2. Test Environment

| Item | Value                         |
|---|-------------------------------|
| Minecraft Version | 1.20.1                        |
| Mod Loader | Forge 47.2.0                  |
| Java Version | Bellsoft Liberica JDK 17.0.11 |
| MCAR Version | (to be filled)                |
| VCMA Version | (to be filled)                |
| Jade Version (optional) | (to be filled)                |
| Operating System | Windows 10.0.19045            |
| Allocated RAM | (to be filled)                |
| Test World Seed | (to be filled)                |

---

## 3. Module A: Teleportation System — Results

| ID       | Priority | Test Name                                       | Type   | Executed By | Date     | Result    | Remarks |
|----------|----------|-------------------------------------------------|--------|-------------|----------|-----------|---------|
| TC-A-001 | P1       | Open Blueprint GUI via Right-Click              | Manual | Liu Dongyu  | 2026/5/5 | ✔️ Passed |         |
| TC-A-002 | P2       | Query and Display All Villagers                 | Manual | Liu Dongyu  | 2026/5/5 | ✔️ Passed |         |
| TC-A-003 | P2       | Query Villagers — Empty Village                 | Manual | Liu Dongyu  | 2026/5/5 | ✔️ Passed |         |
| TC-A-004 | P2       | Right-Click Villager Name — Show Operation List | Manual | Liu Dongyu  | 2026/5/5 | ✔️ Passed |         |
| TC-A-005 | P2       | Click "Teleport" — Summon Villager              | Manual | Liu Dongyu  | 2026/5/5 | ✔️ Passed |         |
| TC-A-006 | P2       | Teleport — Villager in Unloaded Chunk           | Manual | Liu Dongyu  | 2026/5/5 | ✔️ Passed |         |
| TC-A-007 | P2       | Teleport — Villager Already Dead                | Manual | Liu Dongyu  | 2026/5/5 | ✔️ Passed |         |
| TC-A-008 | P2       | Teleport — Across Dimensions                    | Manual | Liu Dongyu  | 2026/5/5 | ✔️ Passed |         |
| TC-A-009 | P2       | Close GUI Without Teleporting                   | Manual | Liu Dongyu  | 2026/5/5 | ✔️ Passed |         |
| TC-A-010 | P1       | Blueprint Item Not Consumed on Use              | Manual | Liu Dongyu  | 2026/5/5 | ✔️ Passed |         |

**Module A Summary:**

| Result       | Count | Percentage |
|--------------|-------|------------|
| Passed       | 10    | 100%       |
| Failed       | 0     | 0%         |
| Blocked      | 0     | 0%         |
| Not Executed | 0     | 0%         |

---

## 4. Module B: Genetics & Inheritance System — Results

| ID | Priority | Test Name | Type | Executed By | Date | Result | Remarks |
|---|---|---|---|---|---|---|---|
| TC-B-001 | P0 | Trait Pool Contains Required Traits | Automated | | | | |
| TC-B-002 | P0 | Each Trait Has Valid Value Range | Automated | | | | |
| TC-B-003 | P0 | Child Inherits Traits from Parents — Basic Case | Automated | | | | |
| TC-B-004 | P0 | Child Inheritance — Parents with Same Trait Value | Automated | | | | |
| TC-B-005 | P0 | Child Inheritance — Each Trait Independent | Automated | | | | |
| TC-B-006 | P0 | Multiple Children from Same Parents — Variation | Manual | | | | |
| TC-B-007 | P0 | Inheritance Does Not Affect Non-Visual Properties | Automated | | | | |

**Module B Summary:**

| Result       | Count | Percentage |
|--------------|-------|------------|
| Passed       | 0     | 0%         |
| Failed       | 0     | 0%         |
| Blocked      | 0     | 0%         |
| Not Executed | 7     | 100%       |

---

## 5. Module C: Lifespan & Aging System — Results

| ID       | Priority | Test Name                                       | Type      | Executed By | Date     | Result    | Remarks    |
|----------|----------|-------------------------------------------------|-----------|-------------|----------|-----------|------------|
| TC-C-001 | P0       | Adult-to-Elder Transition After 360,000 Ticks   | Automated | Liu Dongyu  | 2026/5/5 | ✔️ Passed |            |
| TC-C-002 | P0       | Child Villager Does Not Age into Elder Directly | Automated | Liu Dongyu  | 2026/5/5 | ✔️ Passed |            |
| TC-C-003 | P0       | Aging Timer Persists Across Chunk Unload/Reload | Manual    | Liu Dongyu  | 2026/5/5 | ✔️ Passed |            |
| TC-C-004 | P0       | Aging Timer Persists Across World Reload        | Manual    | Liu Dongyu  | 2026/5/5 | ✔️ Passed |            |
| TC-C-005 | P1       | Aging Timer Configurable                        | Manual    | Liu Dongyu  | 2026/5/5 | ✔️ Passed |            |
| TC-C-006 | P1       | Elder Villager is Unemployable                  | Manual    | Liu Dongyu  | 2026/5/5 | ✔️ Passed | TC-BUG-001 |
| TC-C-007 | P1       | Elder Villager Has Reduced Walking Speed        | Manual    | Liu Dongyu  | 2026/5/5 | ✔️ Passed |            |
| TC-C-008 | P1       | Elder Villager Cannot Breed                     | Manual    | Liu Dongyu  | 2026/5/5 | ✔️ Passed |            |
| TC-C-009 | P0       | Elder Dies After 2,000 Ticks                    | Automated | Liu Dongyu  | 2026/5/5 | ✔️ Passed |            |
| TC-C-010 | P1       | Death from Old Age — Visual & Audio Feedback    | Manual    |             |          |           |            |
| TC-C-011 | P1       | Death from Old Age — Inventory Handling         | Manual    |             |          |           |            |
| TC-C-012 | P1       | Death from Old Age — MCAR Family Relationships  | Manual    |             |          |           |            |
| TC-C-013 | P0       | Elder Death Timer Persists Across World Reload  | Manual    | Liu Dongyu  | 2026/5/5 | ✔️ Passed |            |

**Module C Summary:**

| Result       | Count | Percentage |
|--------------|-------|------------|
| Passed       | 10    | 76.92%     |
| Failed       | 0     | 0%         |
| Blocked      | 0     | 0%         |
| Not Executed | 3     | 23.08%     |

---

## 6. Module D: Quality of Life & Interaction Tweaks — Results

| ID       | Priority | Test Name                                          | Type   | Executed By | Date | Result | Remarks |
|----------|----------|----------------------------------------------------|--------|-------------|------|--------|---------|
| TC-D-001 | P1       | Crouch-Right-Click Assigns Job                     | Manual |             |      |        |         |
| TC-D-002 | P1       | Normal Right-Click Does NOT Assign Job             | Manual |             |      |        |         |
| TC-D-003 | P1       | Villagers Do NOT Auto-Claim Job Site Blocks        | Manual |             |      |        |         |
| TC-D-004 | P1       | Manual Assignment — Job Site Block Must Be in Hand | Manual |             |      |        |         |
| TC-D-005 | P1       | Manual Assignment — Already Employed Villager      | Manual |             |      |        |         |
| TC-D-006 | P2       | Manual Assignment — Same Profession Re-Assignment  | Manual |             |      |        |         |
| TC-D-007 | P2       | Manual Assignment — Job Site Block Out of Range    | Manual |             |      |        |         |
| TC-D-008 | P1       | Manual Assignment — Elder Villager Rejection       | Manual |             |      |        |         |

**Module D Summary:**

| Result       | Count | Percentage |
|--------------|-------|------------|
| Passed       | 0     | 0%         |
| Failed       | 0     | 0%         |
| Blocked      | 0     | 0%         |
| Not Executed | 8     | 100%       |

---

## 7. Integration & Regression Tests — Results

| ID       | Priority | Test Name                                  | Type   | Executed By | Date     | Result    | Remarks |
|----------|----------|--------------------------------------------|--------|-------------|----------|-----------|---------|
| TC-I-001 | P0       | MCAR Core Features Unaffected              | Manual | Liu Dongyu  | 2026/5/5 | ✔️ Passed |         |
| TC-I-002 | P0       | MCAR Villager Data Integrity               | Manual | Liu Dongyu  | 2026/5/5 | ✔️ Passed |         |
| TC-I-003 | P2       | Teleport in Multiplayer — Client-Initiated | Manual | Liu Dongyu  | 2026/5/5 | ✔️ Passed |         |
| TC-I-004 | P2       | Manual Job Assignment in Multiplayer       | Manual | Liu Dongyu  | 2026/5/5 | ✔️ Passed |         |
| TC-I-005 | P1       | World Load with No MCAR Installed          | Manual | Liu Dongyu  | 2026/5/5 | ✔️ Passed |         |
| TC-I-006 | P1       | Tick-Performance — Aging Timer Overhead    | Manual | Liu Dongyu  | 2026/5/5 | ✔️ Passed |         |
| TC-I-007 | P2       | Command-Based Manipulation                 | Manual | Liu Dongyu  | 2026/5/5 | ✔️ Passed |         |

**Integration Summary:**

| Result       | Count | Percentage |
|--------------|-------|------------|
| Passed       | 7     | 100%       |
| Failed       | 0     | 0%         |
| Blocked      | 0     | 0%         |
| Not Executed | 0     | 0%         |

---

## 8. Defects

| ID         | Linked TC | Priority | Module         | Title                                          | Status    | Severity | Discovered By | Date     |
|------------|-----------|----------|----------------|------------------------------------------------|-----------|----------|---------------|----------|
| TC-BUG-001 | TC-C-006  | P1       | forge & fabric | Pois are still occupied when villagers get old | ✔️ Solved | Minor    | Liu Dongyu    | 2026/5/5 |

---

## 9. Conclusion

**Overall Assessment:** Accepted

**Key Findings:** -

**Risks & Concerns:** -

**Recommendations:** -

**Sign-Off:**

| Role         | Name       | Signature | Date     |
|--------------|------------|-----------|----------|
| Tester       | Liu Dongyu |           | 2026/5/5 |
| Developer    | Liu Dongyu |           | 2026/5/5 |
| Project Lead | Liu Dongyu |           | 2026/5/5 |

---

*End of Test Report*
