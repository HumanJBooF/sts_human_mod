package bisonMod;

import basemod.BaseMod;
import basemod.ModLabel;
import basemod.ModPanel;
import basemod.helpers.RelicType;
import basemod.interfaces.*;
import bisonMod.patches.BisonEnum;
import bisonMod.relics.BisonClickableRelic;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.Keyword;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.google.gson.Gson;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import bisonMod.cards.*;
import bisonMod.characters.Bison;
import bisonMod.patches.AbstractCardEnum;
import bisonMod.potions.PlaceholderPotion;
import bisonMod.relics.PlaceholderRelic;
import bisonMod.relics.PlaceholderRelic2;
import bisonMod.variables.BisonCustomVariable;
import bisonMod.variables.BisonMagicNumber;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.charset.StandardCharsets;

@SpireInitializer
public class bisonMod implements
        EditCardsSubscriber,
        EditRelicsSubscriber,
        EditStringsSubscriber,
        EditKeywordsSubscriber,
        EditCharactersSubscriber,
        PostInitializeSubscriber {
    // Make sure to implement the subscribers *you* are using (read basemod wiki). Editing cards? EditCardsSubscriber.
    // Making relics? EditRelicsSubscriber. etc., etc., for a full list and how to make your own, visit the basemod wiki.
    public static final Logger logger = LogManager.getLogger(bisonMod.class.getName());

    //This is for the in-game mod settings panel.
    private static final String MODNAME = "Bison Mod";
    private static final String AUTHOR = "HumanJBooF";
    private static final String DESCRIPTION = "First attempt at making a mod";

    // =============== INPUT TEXTURE LOCATION =================

    // Colors (RGB)
    // Character Color
    public static final Color BISON_GRAY = CardHelper.getColor(64.0f, 70.0f, 70.0f);

    // Potion Colors in RGB
    public static final Color PLACEHOLDER_POTION_LIQUID = CardHelper.getColor(209.0f, 53.0f, 18.0f); // Orange-ish Red
    public static final Color PLACEHOLDER_POTION_HYBRID = CardHelper.getColor(255.0f, 230.0f, 230.0f); // Near White
    public static final Color PLACEHOLDER_POTION_SPOTS = CardHelper.getColor(100.0f, 25.0f, 10.0f); // Super Dark Red/Brown

    // Card backgrounds - The actual rectangular card.
    private static final String ATTACK_BISON_GRAY = "bisonModResources/images/512/bg_attack_default_gray.png";
    private static final String SKILL_BISON_GRAY = "bisonModResources/images/512/bg_skill_default_gray.png";
    private static final String POWER_BISON_GRAY = "bisonModResources/images/512/bg_power_default_gray.png";
    private static final String ENERGY_ORB_BISON_GRAY = "bisonModResources/images/512/card_default_gray_orb.png";
    private static final String CARD_ENERGY_ORB = "bisonModResources/images/512/card_small_orb.png";

    private static final String ATTACK_BISON_GRAY_PORTRAIT = "bisonModResources/images/1024/bg_attack_default_gray.png";
    private static final String SKILL_BISON_GRAY_PORTRAIT = "bisonModResources/images/1024/bg_skill_default_gray.png";
    private static final String POWER_BISON_GRAY_PORTRAIT = "bisonModResources/images/1024/bg_power_default_gray.png";
    private static final String ENERGY_ORB_BISON_GRAY_PORTRAIT = "bisonModResources/images/1024/card_default_gray_orb.png";

    // Character assets
    private static final String BISON_BUTTON = "bisonModResources/images/charSelect/DefaultCharacterButton.png";
    private static final String BISON_PORTRAIT = "bisonModResources/images/charSelect/DefaultCharacterPortraitBG.png";

    public static final String BISON_SHOULDER_1 = "bisonModResources/images/char/bisonCharacter/shoulder.png";
    public static final String BISON_SHOULDER_2 = "bisonModResources/images/char/bisonCharacter/shoulder2.png";
    public static final String BISON_CORPSE = "bisonModResources/images/char/bisonCharacter/corpse.png";

    //Mod Badge - A small icon that appears in the mod settings menu next to your mod.
    public static final String BADGE_IMAGE = "bisonModResources/images/Badge.png";

    // Atlas and JSON files for the Animations
    public static final String BISON_SKELETON_ATLAS = "bisonModResources/images/char/bisonCharacter/skeleton.atlas";
    public static final String BISON_SKELETON_JSON = "bisonModResources/images/char/bisonCharacter/skeleton.json";

    // =============== /INPUT TEXTURE LOCATION/ =================


    // =============== SUBSCRIBE, CREATE THE COLOR, INITIALIZE =================

    public bisonMod() {
        logger.info("Subscribe to BaseMod hooks");

        BaseMod.subscribe(this);

        logger.info("Done subscribing");

        logger.info("Creating the color " + AbstractCardEnum.DEFAULT_GRAY.toString());

        BaseMod.addColor(AbstractCardEnum.DEFAULT_GRAY, BISON_GRAY, BISON_GRAY, BISON_GRAY,
                BISON_GRAY, BISON_GRAY, BISON_GRAY, BISON_GRAY,
                ATTACK_BISON_GRAY, SKILL_BISON_GRAY, POWER_BISON_GRAY, ENERGY_ORB_BISON_GRAY,
                ATTACK_BISON_GRAY_PORTRAIT, SKILL_BISON_GRAY_PORTRAIT, POWER_BISON_GRAY_PORTRAIT,
                ENERGY_ORB_BISON_GRAY_PORTRAIT, CARD_ENERGY_ORB);

        logger.info("Done creating the color");
    }

    @SuppressWarnings("unused")
    public static void initialize() {
        logger.info("========================= Initializing Default Mod. Hi. =========================");
        bisonMod bisonMod = new bisonMod();
        logger.info("========================= /Default Mod Initialized. Hello World./ =========================");
    }

    // ============== /SUBSCRIBE, CREATE THE COLOR, INITIALIZE/ =================


    // =============== LOAD THE CHARACTER =================

    @Override
    public void receiveEditCharacters() {
        logger.info("Beginning to edit characters. " + "Add " + BisonEnum.BISON.toString());

        BaseMod.addCharacter(new Bison("the Default", BisonEnum.BISON),
                BISON_BUTTON, BISON_PORTRAIT, BisonEnum.BISON);

        receiveEditPotions();
        logger.info("Added " + BisonEnum.BISON.toString());
    }

    // =============== /LOAD THE CHARACTER/ =================


    // =============== POST-INITIALIZE =================


    @Override
    public void receivePostInitialize() {

        logger.info("Loading badge image and mod options");
        // Load the Mod Badge
        Texture badgeTexture = new Texture(BADGE_IMAGE);

        // Create the Mod Menu
        ModPanel settingsPanel = new ModPanel();
        settingsPanel.addUIElement(new ModLabel("bisonMod doesn't have any settings! An example of those may come later.", 400.0f, 700.0f,
                settingsPanel, (me) -> {
        }));
        BaseMod.registerModBadge(badgeTexture, MODNAME, AUTHOR, DESCRIPTION, settingsPanel);

        logger.info("Done loading badge Image and mod options");

    }

    // =============== / POST-INITIALIZE/ =================


    // ================ ADD POTIONS ===================


    public void receiveEditPotions() {
        logger.info("Beginning to edit potions");

        // Class Specific Potion. If you want your potion to not be class-specific,
        // just remove the player class at the end (in this case the "BisonEnum.THE_DEFAULT".
        // Remember, you can press ctrl+P inside parentheses like addPotions)
        BaseMod.addPotion(PlaceholderPotion.class, PLACEHOLDER_POTION_LIQUID, PLACEHOLDER_POTION_HYBRID, PLACEHOLDER_POTION_SPOTS, PlaceholderPotion.POTION_ID, BisonEnum.BISON);

        logger.info("Done editing potions");
    }

    // ================ /ADD POTIONS/ ===================


    // ================ ADD RELICS ===================

    @Override
    public void receiveEditRelics() {
        logger.info("Adding relics");

        // This adds a character specific relic. Only when you play with the mentioned color, will you get this relic.
        BaseMod.addRelicToCustomPool(new PlaceholderRelic(), AbstractCardEnum.DEFAULT_GRAY);
        BaseMod.addRelicToCustomPool(new BisonClickableRelic(), AbstractCardEnum.DEFAULT_GRAY);

        // This adds a relic to the Shared pool. Every character can find this relic.
        BaseMod.addRelic(new PlaceholderRelic2(), RelicType.SHARED);

        logger.info("Done adding relics!");
    }

    // ================ /ADD RELICS/ ===================


    // ================ ADD CARDS ===================

    @Override
    public void receiveEditCards() {
        logger.info("Adding variables");
        // Add the Custom Dynamic Variables
        logger.info("Add variabls");
        // Add the Custom Dynamic variabls
        BaseMod.addDynamicVariable(new BisonCustomVariable());
        BaseMod.addDynamicVariable(new BisonMagicNumber());

        logger.info("Adding cards");
        // Add the cards
        BaseMod.addCard(new OrbSkill());
        BaseMod.addCard(new BisonSecondMagicNumberSkill());
        BaseMod.addCard(new BisonCommonAttack());
        BaseMod.addCard(new BisonAttackWithVariable());
        BaseMod.addCard(new BisonCommonSkill());
        BaseMod.addCard(new BisonCommonPower());
        BaseMod.addCard(new BisonUncommonSkill());
        BaseMod.addCard(new BisonUncommonAttack());
        BaseMod.addCard(new BisonUncommonPower());
        BaseMod.addCard(new BisonRareAttack());
        BaseMod.addCard(new BisonRareSkill());
        BaseMod.addCard(new BisonRarePower());

        logger.info("Making sure the cards are unlocked.");
        // Unlock the cards
        UnlockTracker.unlockCard(OrbSkill.ID);
        UnlockTracker.unlockCard(BisonSecondMagicNumberSkill.ID);
        UnlockTracker.unlockCard(BisonCommonAttack.ID);
        UnlockTracker.unlockCard(BisonAttackWithVariable.ID);
        UnlockTracker.unlockCard(BisonCommonSkill.ID);
        UnlockTracker.unlockCard(BisonCommonPower.ID);
        UnlockTracker.unlockCard(BisonUncommonSkill.ID);
        UnlockTracker.unlockCard(BisonUncommonAttack.ID);
        UnlockTracker.unlockCard(BisonUncommonPower.ID);
        UnlockTracker.unlockCard(BisonRareAttack.ID);
        UnlockTracker.unlockCard(BisonRareSkill.ID);
        UnlockTracker.unlockCard(BisonRarePower.ID);

        logger.info("Done adding cards!");
    }

    // There are better ways to do this than listing every single individual card, but I do not want to complicate things
    // in a "tutorial" mod. This will do and it's completely ok to use. If you ever want to clean up and
    // shorten all the imports, go look take a look at other mods, such conspire or Hubris.

    // ================ /ADD CARDS/ ===================


    // ================ LOAD THE TEXT ===================

    @Override
    public void receiveEditStrings() {
        logger.info("Beginning to edit strings");

        // CardStrings
        BaseMod.loadCustomStringsFile(CardStrings.class,
                "bisonModResources/localization/eng/bisonMod-Card-Strings.json");

        // PowerStrings
        BaseMod.loadCustomStringsFile(PowerStrings.class,
                "bisonModResources/localization/eng/bisonMod-Power-Strings.json");

        // RelicStrings
        BaseMod.loadCustomStringsFile(RelicStrings.class,
                "bisonModResources/localization/eng/bisonMod-Relic-Strings.json");

        // PotionStrings
        BaseMod.loadCustomStringsFile(PotionStrings.class,
                "bisonModResources/localization/eng/bisonMod-Potion-Strings.json");

        // CharacterStrings
        BaseMod.loadCustomStringsFile(CharacterStrings.class,
                "bisonModResources/localization/eng/bisonMod-Character-Strings.json");

        // OrbStrings
        BaseMod.loadCustomStringsFile(OrbStrings.class,
                "bisonModResources/localization/eng/bisonMod-Orb-Strings.json");

        logger.info("Done edittting strings");
    }

    // ================ /LOAD THE TEXT/ ===================

    // ================ LOAD THE KEYWORDS ===================

    @Override
    public void receiveEditKeywords() {
    // multiword keywords are done with_underscores
    // so if you do lesser_potion in keyword.NAMES and Lesser_Potion on a card basemod will auto-resolve it, showing it as Lesser Potion
        Gson gson = new Gson();
        String json = Gdx.files.internal("bisonModResources/localization/eng/bisonMod-Keyword-Strings.json").readString(String.valueOf(StandardCharsets.UTF_8));
        com.evacipated.cardcrawl.mod.stslib.Keyword[] keywords = gson.fromJson(json, com.evacipated.cardcrawl.mod.stslib.Keyword[].class);

        if (keywords != null) {
            for (Keyword keyword : keywords) {
                BaseMod.addKeyword(keyword.NAMES, keyword.DESCRIPTION);
            }
        }
    }
        
    // ================ /LOAD THE KEYWORDS/ ===================    

    // this adds "ModName:" before the ID of any card/relic/power etc.
    // in order to avoid conflicts if any other mod uses the same ID.
    public static String makeID(String idText) {
        return "Bison:" + idText;
    }

}
