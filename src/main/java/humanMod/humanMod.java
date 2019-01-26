package humanMod;

import basemod.BaseMod;
import basemod.interfaces.*;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.helpers.CardHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SpireInitializer
public class humanMod implements
        EditCardsSubscriber,
        EditRelicsSubscriber,
        EditStringsSubscriber,
        EditKeywordsSubscriber,
        EditCharactersSubscriber,
        PostInitializeSubscriber {

    public static final Logger logger = LogManager.getLogger(humanMod.class.getName());

    //This is for the in-game mod settings panel.
    private static final String MODNAME = "HumanMod";
    private static final String AUTHOR = "HumanJBooF";
    private static final String DESCRIPTION = "Attempting to make a slay the spire mod";

    // =============== INPUT TEXTURE LOCATION =================

    public static final Color HUMAN_GRAY = CardHelper.getColor(64.0f, 70.0f, 70.0f);

    // Potion Colors in RGB
    public static final Color PLACEHOLDER_POTION_LIQUID = CardHelper.getColor(209.0f, 53.0f, 18.0f); // Orange-ish Red
    public static final Color PLACEHOLDER_POTION_HYBRID = CardHelper.getColor(255.0f, 230.0f, 230.0f); // Near White
    public static final Color PLACEHOLDER_POTION_SPOTS = CardHelper.getColor(100.0f, 25.0f, 10.0f); // Super Dark Red/Brown

    // Card backgrounds - The actual rectangular card.
    private static final String ATTACK_HUMAN_GRAY = "stsHumanModResources/images/512/bg_attack_default_gray.png";
    private static final String SKILL_HUMAN_GRAY = "stsHumanModResources/images/512/bg_skill_default_gray.png";
    private static final String POWER_HUMAN_GRAY = "stsHumanModResources/images/512/bg_power_default_gray.png";
    private static final String ENERGY_ORB_HUMAN_GRAY = "stsHumanModResources/images/512/card_default_gray_orb.png";
    private static final String CARD_ENERGY_ORB = "stsHumanModResources/images/512/card_small_orb.png";

    private static final String ATTACK_DEFAULT_GRAY_PORTRAIT = "stsHumanModResources/images/1024/bg_attack_default_gray.png";
    private static final String SKILL_DEFAULT_GRAY_PORTRAIT = "stsHumanModResources/images/1024/bg_skill_default_gray.png";
    private static final String POWER_DEFAULT_GRAY_PORTRAIT = "stsHumanModResources/images/1024/bg_power_default_gray.png";
    private static final String ENERGY_ORB_DEFAULT_GRAY_PORTRAIT = "stsHumanModResources/images/1024/card_default_gray_orb.png";

    // Character assets
    private static final String THE_DEFAULT_BUTTON = "stsHumanModResources/images/charSelect/DefaultCharacterButton.png";
    private static final String THE_DEFAULT_PORTRAIT = "stsHumanModResources/images/charSelect/DefaultCharacterPortraitBG.png";

    public static final String THE_DEFAULT_SHOULDER_1 = "stsHumanModResources/images/defaultCharacter/shoulder.png";
    public static final String THE_DEFAULT_SHOULDER_2 = "stsHumanModResources/images/defaultCharacter/shoulder2.png";
    public static final String THE_DEFAULT_CORPSE = "stsHumanModResources/images/defaultCharacter/corpse.png";

    //Mod Badge - A small icon that appears in the mod settings menu next to your mod.
    public static final String BADGE_IMAGE = "stsHumanModResources/images/Badge.png";

    // Atlas and JSON files for the Animations
    public static final String THE_DEFAULT_SKELETON_ATLAS = "stsHumanModResources/images/defaultCharacter/skeleton.atlas";
    public static final String THE_DEFAULT_SKELETON_JSON = "stsHumanModResources/images/defaultCharacter/skeleton.json";


    public humanMod() {
        logger.info("Subcribe to all Basemod hooks");
        BaseMod.subscribe(this);
    }


    public static void initialize() {
        @SuppressWarnings("unused")
        humanMod mod = new humanMod();
    }


    @Override
    public void receiveEditCharacters() {

    }


    @Override
    public void receivePostInitialize() {

    }


    @Override
    public void receiveEditPotions() {

    }


    @Override
    public void receiveEditRelics() {

    }


    @Override
    public void receiveEditCards() {

    }


    @Override
    public void receiveEditStrings() {

    }


    @Override
    public void receiveEditKeywords() {

    }

}
