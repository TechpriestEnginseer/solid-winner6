package data.missions.forthegreaterlud;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.characters.MutableCharacterStatsAPI;
import com.fs.starfarer.api.combat.BaseEveryFrameCombatPlugin;
import com.fs.starfarer.api.combat.CombatEngineAPI;
import com.fs.starfarer.api.combat.ShipAPI;
import com.fs.starfarer.api.fleet.FleetGoal;
import com.fs.starfarer.api.fleet.FleetMemberAPI;
import com.fs.starfarer.api.fleet.FleetMemberType;
import com.fs.starfarer.api.impl.campaign.events.OfficerManagerEvent;
import com.fs.starfarer.api.impl.campaign.fleets.FleetFactoryV3;
import com.fs.starfarer.api.impl.campaign.ids.Factions;
import com.fs.starfarer.api.impl.campaign.ids.Personalities;
import com.fs.starfarer.api.impl.campaign.ids.Skills;
import com.fs.starfarer.api.mission.FleetSide;
import com.fs.starfarer.api.mission.MissionDefinitionAPI;
import com.fs.starfarer.api.mission.MissionDefinitionPlugin;
import com.fs.starfarer.api.util.Misc;
import java.util.List;
import java.util.Random;

public class MissionDefinition implements MissionDefinitionPlugin {

	public void defineMission(MissionDefinitionAPI api) {

		// Set up the fleets so we can add ships and fighter wings to them.
		// In this scenario, the fleets are attacking each other, but
		// in other scenarios, a fleet may be defending or trying to escape
		api.initFleet(FleetSide.PLAYER, "", FleetGoal.ATTACK, false, 5);
		api.initFleet(FleetSide.ENEMY, "TTS", FleetGoal.ATTACK, true, 7);

		// Set a small blurb for each fleet that shows up on the mission detail and
		// mission results screens to identify each side.
		api.setFleetTagline(FleetSide.PLAYER, "ISS Black Star and Luddic Path strike force");
		api.setFleetTagline(FleetSide.ENEMY, "PLS Praxis with escort and Tri-Tachyon allies");
		
		// These show up as items in the bulleted list under 
		// "Tactical Objectives" on the mission detail screen
		api.addBriefingItem("Distract the enemy flagship to allow your bombers to flank it");
		api.addBriefingItem("Defeat all enemy forces");
		api.addBriefingItem("ISS Black Star must survive");
		
		// Set up the player's fleet.  Variant names come from the
		// files in data/variants and data/variants/fighters
		api.getDefaultCommander(FleetSide.PLAYER).getStats().setSkillLevel(Skills.COORDINATED_MANEUVERS, 1);
		FleetMemberAPI member = api.addToFleet(FleetSide.PLAYER, "hammerhead_Balanced", FleetMemberType.SHIP, "ISS Black Star", true);
        member.setCaptain(OfficerManagerEvent.createOfficer(Global.getSector().getFaction(Factions.INDEPENDENT), 2, FleetFactoryV3.getSkillPrefForShip(member), true, null, true, true, 1, new Random()));
		FleetMemberAPI member2 = api.addToFleet(FleetSide.PLAYER, "condor_Strike", FleetMemberType.SHIP, false);
        member2.setCaptain(OfficerManagerEvent.createOfficer(Global.getSector().getFaction(Factions.LUDDIC_PATH), 2, FleetFactoryV3.getSkillPrefForShip(member2), true, null, true, true, 1, new Random()));
        member2.getCaptain().setPersonality(Personalities.AGGRESSIVE);
		FleetMemberAPI member3 = api.addToFleet(FleetSide.PLAYER, "condor_Strike", FleetMemberType.SHIP, false);
        member3.setCaptain(OfficerManagerEvent.createOfficer(Global.getSector().getFaction(Factions.LUDDIC_PATH), 2, FleetFactoryV3.getSkillPrefForShip(member3), true, null, true, true, 1, new Random()));
        member3.getCaptain().setPersonality(Personalities.AGGRESSIVE);
		FleetMemberAPI member4 = api.addToFleet(FleetSide.PLAYER, "condor_Support", FleetMemberType.SHIP, false);
        member4.setCaptain(OfficerManagerEvent.createOfficer(Global.getSector().getFaction(Factions.LUDDIC_PATH), 2, FleetFactoryV3.getSkillPrefForShip(member4), true, null, true, true, 1, new Random()));
         member4.getCaptain().setPersonality(Personalities.AGGRESSIVE);
		FleetMemberAPI member5 = api.addToFleet(FleetSide.PLAYER, "hound_Standard", FleetMemberType.SHIP, false);
        member5.setCaptain(OfficerManagerEvent.createOfficer(Global.getSector().getFaction(Factions.LUDDIC_PATH), 3, FleetFactoryV3.getSkillPrefForShip(member5), true, null, true, true, 1, new Random()));
        member5.getCaptain().setPersonality(Personalities.AGGRESSIVE);
		FleetMemberAPI member6 = api.addToFleet(FleetSide.PLAYER, "hound_Standard", FleetMemberType.SHIP, false);
        member6.setCaptain(OfficerManagerEvent.createOfficer(Global.getSector().getFaction(Factions.LUDDIC_PATH), 3, FleetFactoryV3.getSkillPrefForShip(member6), true, null, true, true, 1, new Random()));
        member6.getCaptain().setPersonality(Personalities.AGGRESSIVE);
		
		// Mark both ships as essential - losing either one results
		// in mission failure. Could also be set on an enemy ship,
		// in which case destroying it would result in a win.
		api.defeatOnShipLoss("ISS Black Star");
		
		// Set up the enemy fleet.
		// It's got more ships than the player's, but they're not as strong.
        api.getDefaultCommander(FleetSide.ENEMY).getStats().setSkillLevel(Skills.FLUX_REGULATION, 1);
		FleetMemberAPI member7 = api.addToFleet(FleetSide.ENEMY, "dominator_Outdated", FleetMemberType.SHIP, "PLS Praxis", true);
        member7.setCaptain(OfficerManagerEvent.createOfficer(Global.getSector().getFaction(Factions.PERSEAN), 3, FleetFactoryV3.getSkillPrefForShip(member7), true, null, true, false, 0, new Random()));
		//api.addToFleet(FleetSide.ENEMY, "sunder_CS", FleetMemberType.SHIP, "ESS Arach", false);
		//api.addToFleet(FleetSide.ENEMY, "vigilance_FS", FleetMemberType.SHIP, false);
		//api.addToFleet(FleetSide.ENEMY, "vigilance_FS", FleetMemberType.SHIP, false);
		FleetMemberAPI member8 = api.addToFleet(FleetSide.ENEMY, "brawler_Assault", FleetMemberType.SHIP, "PLS Kleobis", false);
        member8.setCaptain(OfficerManagerEvent.createOfficer(Global.getSector().getFaction(Factions.PERSEAN), 1, FleetFactoryV3.getSkillPrefForShip(member8), true, null, true, false, 0, new Random()));
		FleetMemberAPI member9 = api.addToFleet(FleetSide.ENEMY, "brawler_Assault", FleetMemberType.SHIP, "PLS Biton", false);
        member9.setCaptain(OfficerManagerEvent.createOfficer(Global.getSector().getFaction(Factions.PERSEAN), 1, FleetFactoryV3.getSkillPrefForShip(member9), true, null, true, false, 0, new Random()));
		//api.addToFleet(FleetSide.ENEMY, "brawler_Assault", FleetMemberType.SHIP, false);
		FleetMemberAPI member10 = api.addToFleet(FleetSide.ENEMY, "tempest_Attack", FleetMemberType.SHIP, "TTS Regulus", false);
		member10.setCaptain(OfficerManagerEvent.createOfficer(Global.getSector().getFaction(Factions.TRITACHYON), 3, FleetFactoryV3.getSkillPrefForShip(member10), true, null, true, true, 1, new Random()));
		//api.addToFleet(FleetSide.ENEMY, "wasp_wing", FleetMemberType.FIGHTER_WING, false);
		
		
		// Set up the map.
		float width = 24000f;
		float height = 18000f;
		api.initMap((float)-width/2f, (float)width/2f, (float)-height/2f, (float)height/2f);
		
		float minX = -width/2;
		float minY = -height/2;
		
		// All the addXXX methods take a pair of coordinates followed by data for
		// whatever object is being added.
		
		api.addNebula(minX + width * 0.4f, minY + height * 0.4f, 2000);
		api.addNebula(minX + width * 0.5f, minY + height * 0.5f, 2500);
		api.addNebula(minX + width * 0.6f, minY + height * 0.6f, 2000);
		
		// And a few random ones to spice up the playing field.
		// A similar approach can be used to randomize everything
		// else, including fleet composition.
		for (int i = 0; i < 7; i++) {
			float x = (float) Math.random() * width - width/2;
			float y = (float) Math.random() * height - height/2;
			float radius = 100f + (float) Math.random() * 800f; 
			api.addNebula(x, y, radius);
		}
		
		// Add objectives. These can be captured by each side
		// and provide stat bonuses and extra command points to
		// bring in reinforcements.
		// Reinforcements only matter for large fleets - in this
		// case, assuming a 100 command point battle size,
		// both fleets will be able to deploy fully right away.
		api.addObjective(minX + width * 0.25f + 2000, minY + height * 0.25f + 2000, "sensor_array");
		api.addObjective(minX + width * 0.75f - 2000, minY + height * 0.75f - 2000, "sensor_array");
		api.addObjective(minX + width * 0.4f + 1000, minY + height * 0.5f, "nav_buoy");
		api.addObjective(minX + width * 0.6f - 1000, minY + height * 0.5f, "nav_buoy");
		
		// Add an asteroid field going diagonally across the
		// battlefield, 2000 pixels wide, with a maximum of 
		// 100 asteroids in it.
		// 20-70 is the range of asteroid speeds.
		api.addAsteroidField(minX, minY + height * 0.5f, 0, 18000f,
								20f, 70f, 250);
		
		// Add some planets.  These are defined in data/config/planets.json.
		api.addPlanet(-320, -140, 200f, "barren", 250f, true);
		api.addPlanet(512, 256, 64f, "star_orange", 1f, true);
                api.addPlugin(new BaseEveryFrameCombatPlugin() {
			public void init(CombatEngineAPI engine) {
			}
			public void advance(float amount, List events) {
                            if (Global.getCombatEngine().isPaused()) {
                                return;
                            }
                            for (ShipAPI ship : Global.getCombatEngine().getShips()) {
                                if (ship.getCustomData().get("poopystinky") == null) {
									if (ship.getCaptain() != null && ship.getOwner() == 0 && ship.getCaptain().getStats().getSkillsCopy().size() > 4) {
                                        String text = "";
                                        for (int u = 4; u < ship.getCaptain().getStats().getSkillsCopy().size(); u++) {
											if (u < ship.getCaptain().getStats().getSkillsCopy().size()-1) {text = text+(((MutableCharacterStatsAPI.SkillLevelAPI) ship.getCaptain().getStats().getSkillsCopy().get(u)).getLevel() > 1 ?  ((MutableCharacterStatsAPI.SkillLevelAPI) ship.getCaptain().getStats().getSkillsCopy().get(u)).getSkill().getName()+"+, " :  ((MutableCharacterStatsAPI.SkillLevelAPI) ship.getCaptain().getStats().getSkillsCopy().get(u)).getSkill().getName()+", ");} else {text = text+(((MutableCharacterStatsAPI.SkillLevelAPI) ship.getCaptain().getStats().getSkillsCopy().get(u)).getLevel() > 1 ? ((MutableCharacterStatsAPI.SkillLevelAPI) ship.getCaptain().getStats().getSkillsCopy().get(u)).getSkill().getName()+"+." :  ((MutableCharacterStatsAPI.SkillLevelAPI) ship.getCaptain().getStats().getSkillsCopy().get(u)).getSkill().getName()+".");}
                                        }
									if (ship.getFleetMember() != null) {
									Global.getCombatEngine().getCombatUI().addMessage(1, ship.getFleetMember(), Misc.getPositiveHighlightColor(), ship.getName(), Misc.getTextColor(), "", Global.getSettings().getColor("standardTextColor"), "is skilled in "+text);}
                                    }
                                    ship.setCurrentCR(ship.getCurrentCR()+ship.getMutableStats().getMaxCombatReadiness().getModifiedValue()); //Properly adds the max CR, for some reason it cannot be caught as FleetMemberAPI or this would have been easier...
                                    ship.setCRAtDeployment(ship.getCRAtDeployment()+ship.getMutableStats().getMaxCombatReadiness().getModifiedValue()); //This only affects the "score" result of said mission, but the algorithm is mostly 100% since you have to basically LOSE ships to lose score. I don't think this needs setting, but eh couldn't help but tried.
                                    ship.setCustomData("poopystinky", true); //Fires once per ship.
                                }
                            }
                        }
		});
	}

}
