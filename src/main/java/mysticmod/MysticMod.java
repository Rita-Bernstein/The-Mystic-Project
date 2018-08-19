package mysticmod;

import mysticmod.cards.cantrips.AcidSplash;
import mysticmod.cards.cantrips.Prestidigitation;
import mysticmod.cards.cantrips.RayOfFrost;
import mysticmod.cards.cantrips.ReadMagic;
import mysticmod.cards.cantrips.Spark;
import mysticmod.cards.Alacrity;
import mysticmod.cards.AllIn;
import mysticmod.cards.ArcaneAccuracy;
import mysticmod.cards.ArcaneDodge;
import mysticmod.cards.BladeBurst;
import mysticmod.cards.BladedDash;
import mysticmod.cards.BladeMaster;
import mysticmod.cards.ChargedParry;
import mysticmod.cards.ClosingBarrage;
import mysticmod.cards.ComboCaster;
import mysticmod.cards.ComponentsPouch;
import mysticmod.cards.CorrosiveTouch;
import mysticmod.cards.CureLightWounds;
import mysticmod.cards.Daze;
import mysticmod.cards.Dedication;
import mysticmod.cards.DefendMystic;
import mysticmod.cards.Discipline;
import mysticmod.cards.Disengage;
import mysticmod.cards.Disintegrate;
import mysticmod.cards.DiviningBlow;
import mysticmod.cards.Doublecast;
import mysticmod.cards.EarthenWall;
import mysticmod.cards.EbbAndFlow;
import mysticmod.cards.EnergizedRift;
import mysticmod.cards.Feint;
import mysticmod.cards.Fireball;
import mysticmod.cards.FiveFootStep;
import mysticmod.cards.FloatingDisk;
import mysticmod.cards.Flourish;
import mysticmod.cards.Flurry;
import mysticmod.cards.Fly;
import mysticmod.cards.GeminiForm;
import mysticmod.cards.Grapple;
import mysticmod.cards.Grease;
import mysticmod.cards.GreaterInvisibility;
import mysticmod.cards.Haste;
import mysticmod.cards.HeavyStrike;
import mysticmod.cards.HunkerDown;
import mysticmod.cards.IllusionOfCalm;
import mysticmod.cards.KeenEdge;
import mysticmod.cards.KnowledgePool;
import mysticmod.cards.LightningBolt;
import mysticmod.cards.Lunge;
import mysticmod.cards.MagicMissile;
import mysticmod.cards.MagicWeapon;
import mysticmod.cards.MightyMagic;
import mysticmod.cards.MirrorEntity;
import mysticmod.cards.MirrorStrike;
import mysticmod.cards.Momentum;
import mysticmod.cards.Moulinet;
import mysticmod.cards.MysticalShield;
import mysticmod.cards.Natural20;
import mysticmod.cards.ObscuringMist;
import mysticmod.cards.PowerAttack;
import mysticmod.cards.PowerSlash;
import mysticmod.cards.PreparedCaster;
import mysticmod.cards.Probe;
import mysticmod.cards.PunishingArmor;
import mysticmod.cards.PureInstinct;
import mysticmod.cards.RapidCaster;
import mysticmod.cards.Riposte;
import mysticmod.cards.Shield;
import mysticmod.cards.ShockingGrasp;
import mysticmod.cards.Sideswipe;
import mysticmod.cards.Snowball;
import mysticmod.cards.SpellCombat;
import mysticmod.cards.SpellRecall;
import mysticmod.cards.Spellstrike;
import mysticmod.cards.SpontaneousCaster;
import mysticmod.cards.Stoneskin;
import mysticmod.cards.StrikeMystic;
import mysticmod.cards.StyleChange;
import mysticmod.cards.SuddenClarity;
import mysticmod.cards.TomeOfSpells;
import mysticmod.cards.VorpalThrust;
import mysticmod.character.MysticCharacter;
import mysticmod.patches.MysticEnum;
import mysticmod.patches.AbstractCardEnum;
import mysticmod.relics.BentSpoon;
import mysticmod.relics.BlessedBook;
import mysticmod.relics.CrystalBall;
import mysticmod.relics.DeckOfManyThings;
import mysticmod.relics.Kama;
import mysticmod.relics.RabbitsFoot;
import mysticmod.relics.RunicPrism;
import mysticmod.relics.SpellBook;
import mysticmod.relics.TrainingManual;
import mysticmod.powers.GeminiFormPower;
import mysticmod.powers.SpellsPlayed;
import mysticmod.potions.EssenceOfMagic;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.localization.RelicStrings;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import basemod.interfaces.EditCardsSubscriber;
import basemod.interfaces.EditCharactersSubscriber;
import basemod.interfaces.EditKeywordsSubscriber;
import basemod.interfaces.EditStringsSubscriber;
import basemod.interfaces.EditRelicsSubscriber;
import basemod.interfaces.PostBattleSubscriber;
import basemod.BaseMod;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.Gdx;
import java.util.*;
import java.nio.charset.StandardCharsets;

@SpireInitializer
public class MysticMod implements EditCardsSubscriber, EditCharactersSubscriber, EditKeywordsSubscriber, EditRelicsSubscriber, EditStringsSubscriber, PostBattleSubscriber {

    private static final Color mysticPurple = CardHelper.getColor(152.0f, 34.0f, 171.0f); //152, 34, 171
    private static final String attackCard = "mysticmod/images/512/bg_attack_mystic.png";
    private static final String skillCard = "mysticmod/images/512/bg_skill_mystic.png";
    private static final String powerCard = "mysticmod/images/512/bg_power_mystic.png";
    private static final String energyOrb = "mysticmod/images/512/card_mystic_orb.png";
    private static final String attackCardPortrait = "mysticmod/images/1024/bg_attack_mystic.png";
    private static final String skillCardPortrait = "mysticmod/images/1024/bg_skill_mystic.png";
    private static final String powerCardPortrait = "mysticmod/images/1024/bg_power_mystic.png";
    private static final String energyOrbPortrait = "mysticmod/images/1024/card_mystic_orb.png";
    private static final String charButton = "mysticmod/images/charSelect/button.png";
    private static final String charPortrait = "mysticmod/images/charSelect/portrait.png";
    public static final String badgeImg = "mysticmod/images/badge.png";
    public static boolean isDiscoveryLookingForSpells = false;
    public static int numberOfTimesDeckShuffledThisCombat = 0;

    public MysticMod(){
        BaseMod.subscribe(this);

        BaseMod.addColor(AbstractCardEnum.MYSTIC_PURPLE.toString(),
                mysticPurple, mysticPurple, mysticPurple, mysticPurple, mysticPurple, mysticPurple, mysticPurple,   //Background color, back color, frame color, frame outline color, description box color, glow color
                attackCard, skillCard, powerCard, energyOrb,                                                        //attack background image, skill background image, power background image, energy orb image
                attackCardPortrait, skillCardPortrait, powerCardPortrait, energyOrbPortrait);                       //as above, but for card inspect view
        Color essenceOfMagicColor = CardHelper.getColor(255.0f, 255.0f, 255.0f);
        BaseMod.addPotion(EssenceOfMagic.class, essenceOfMagicColor, essenceOfMagicColor, essenceOfMagicColor, EssenceOfMagic.POTION_ID, MysticEnum.MYSTIC_CLASS);
    }
    //Used by @SpireInitializer
    public static void initialize(){

        //This creates an instance of our classes and gets our code going after BaseMod and ModTheSpire initialize.
        MysticMod mysticMod = new MysticMod();
    }
    @Override
    public void receiveEditCards() {
        //Basic. 2 attacks, 2 skills
        BaseMod.addCard(new ArcaneDodge());
        BaseMod.addCard(new DefendMystic());
        BaseMod.addCard(new ShockingGrasp());
        BaseMod.addCard(new StrikeMystic());

        //"cantrips". 2 attacks, 3 skills
        BaseMod.addCard(new AcidSplash());
        BaseMod.addCard(new Prestidigitation());
        BaseMod.addCard(new RayOfFrost());
        BaseMod.addCard(new ReadMagic());
        BaseMod.addCard(new Spark());

        //Commons
        //11 attacks
        BaseMod.addCard(new ComponentsPouch());
        BaseMod.addCard(new CorrosiveTouch());
        BaseMod.addCard(new DiviningBlow());
        BaseMod.addCard(new HeavyStrike());
        BaseMod.addCard(new LightningBolt());
        BaseMod.addCard(new Moulinet());
        BaseMod.addCard(new PowerSlash());
        BaseMod.addCard(new Probe());
        BaseMod.addCard(new Riposte());
        BaseMod.addCard(new Sideswipe());
        BaseMod.addCard(new Snowball());
        //7 skills
        BaseMod.addCard(new Alacrity());
        BaseMod.addCard(new Daze());
        BaseMod.addCard(new Disengage());
        BaseMod.addCard(new PureInstinct());
        BaseMod.addCard(new Shield());
        BaseMod.addCard(new SuddenClarity());
        BaseMod.addCard(new TomeOfSpells());

        //Uncommons
        //12 attacks
        BaseMod.addCard(new AllIn());
        BaseMod.addCard(new BladeBurst());
        BaseMod.addCard(new BladedDash());
        BaseMod.addCard(new Feint());
        BaseMod.addCard(new Fireball());
        BaseMod.addCard(new FiveFootStep());
        BaseMod.addCard(new Flourish());
        BaseMod.addCard(new Flurry());
        BaseMod.addCard(new MagicMissile());
        BaseMod.addCard(new MirrorStrike());
        BaseMod.addCard(new SpellCombat());
        BaseMod.addCard(new VorpalThrust());
        //17 skills
        BaseMod.addCard(new BladeMaster());
        BaseMod.addCard(new ChargedParry());
        BaseMod.addCard(new CureLightWounds());
        BaseMod.addCard(new EarthenWall());
        BaseMod.addCard(new EnergizedRift());
        BaseMod.addCard(new FloatingDisk());
        BaseMod.addCard(new Fly());
        BaseMod.addCard(new Grapple());
        BaseMod.addCard(new Grease());
        BaseMod.addCard(new HunkerDown());
        BaseMod.addCard(new IllusionOfCalm());
        BaseMod.addCard(new KeenEdge());
        BaseMod.addCard(new MagicWeapon());
        BaseMod.addCard(new PowerAttack());
        BaseMod.addCard(new PunishingArmor());
        BaseMod.addCard(new Stoneskin());
        BaseMod.addCard(new StyleChange());
        //6 powers
        BaseMod.addCard(new ArcaneAccuracy());
        BaseMod.addCard(new ComboCaster());
        BaseMod.addCard(new Dedication());
        BaseMod.addCard(new EbbAndFlow());
        BaseMod.addCard(new MightyMagic());
        BaseMod.addCard(new RapidCaster());

        //Rares.
        //3 attacks
        BaseMod.addCard(new ClosingBarrage());
        BaseMod.addCard(new Disintegrate());
        BaseMod.addCard(new Spellstrike());
        //9 skills
        BaseMod.addCard(new Doublecast());
        BaseMod.addCard(new GreaterInvisibility());
        BaseMod.addCard(new Haste());
        BaseMod.addCard(new KnowledgePool());
        BaseMod.addCard(new Lunge());
        BaseMod.addCard(new Natural20());
        BaseMod.addCard(new ObscuringMist());
        BaseMod.addCard(new PreparedCaster());
        BaseMod.addCard(new SpellRecall());
        //6 powers
        BaseMod.addCard(new Discipline());
        BaseMod.addCard(new GeminiForm());
        BaseMod.addCard(new MirrorEntity());
        BaseMod.addCard(new Momentum());
        BaseMod.addCard(new MysticalShield());
        BaseMod.addCard(new SpontaneousCaster());
    }

    @Override
    public void receiveEditCharacters() {
        BaseMod.addCharacter(MysticCharacter.class, "The Mystic", "MysticCharacter",
                AbstractCardEnum.MYSTIC_PURPLE.toString(), "The Mystic", charButton, charPortrait,
                MysticEnum.MYSTIC_CLASS.toString());
    }

    @Override
    public void receiveEditKeywords() {
        String[] keywordCantrips = {"cantrip", "cantrips"};
        String[] keywordSpells = {"spell", "spells"};
        String[] keywordTechniques = {"technique", "techniques"};
        String[] keywordArcane = {"arcane"};
        String[] keywordTechnical = {"technical"};
        String[] keywordFeat = {"feat"};
        BaseMod.addKeyword(keywordCantrips, "Considered a spell so long as you've played fewer than 2 spells this turn.");
        BaseMod.addKeyword(keywordSpells, "Advance the \"spells played\" counter for the turn.");
        BaseMod.addKeyword(keywordTechniques, "Advance the \"Techniques played\" counter for the turn.");
        BaseMod.addKeyword(keywordArcane, "Has a special effect if you played a spell this turn.");
        BaseMod.addKeyword(keywordTechnical, "Has a special effect if you played a Technique this turn.");
        BaseMod.addKeyword(keywordFeat, "Can only be played as the first card of the turn.");
    }

    @Override
    public void receiveEditStrings() {
        String relicStrings = Gdx.files.internal("mysticmod/strings/relics.json").readString(String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings(RelicStrings.class, relicStrings);
        String cardStrings = Gdx.files.internal("mysticmod/strings/cards.json").readString(String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings(CardStrings.class, cardStrings);
        String powerStrings = Gdx.files.internal("mysticmod/strings/powers.json").readString(String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings(PowerStrings.class, powerStrings);
        String potionStrings = Gdx.files.internal("mysticmod/strings/potions.json").readString(String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings(PotionStrings.class, potionStrings);
    }

    @Override
    public void receiveEditRelics() {
        //starter
        BaseMod.addRelicToCustomPool(new SpellBook(), AbstractCardEnum.MYSTIC_PURPLE.toString());

        //Common
        BaseMod.addRelicToCustomPool(new TrainingManual(), AbstractCardEnum.MYSTIC_PURPLE.toString());

        //Uncommon
        BaseMod.addRelicToCustomPool(new RabbitsFoot(), AbstractCardEnum.MYSTIC_PURPLE.toString());
        BaseMod.addRelicToCustomPool(new RunicPrism(), AbstractCardEnum.MYSTIC_PURPLE.toString());

        //Rare
        BaseMod.addRelicToCustomPool(new Kama(), AbstractCardEnum.MYSTIC_PURPLE.toString());

        //Shop
        BaseMod.addRelicToCustomPool(new BentSpoon(), AbstractCardEnum.MYSTIC_PURPLE.toString());

        //Boss
        BaseMod.addRelicToCustomPool(new BlessedBook(), AbstractCardEnum.MYSTIC_PURPLE.toString()); //replaces starting relic
        BaseMod.addRelicToCustomPool(new CrystalBall(), AbstractCardEnum.MYSTIC_PURPLE.toString());
        BaseMod.addRelicToCustomPool(new DeckOfManyThings(), AbstractCardEnum.MYSTIC_PURPLE.toString());
    }

    @Override
    public void receivePostBattle(final AbstractRoom p0) { //for Magic Missile
        numberOfTimesDeckShuffledThisCombat = 0;
        for (final AbstractCard card : AbstractDungeon.player.masterDeck.group) {
            if (card instanceof MagicMissile) {
                card.rawDescription = MagicMissile.DESCRIPTION;
                card.initializeDescription();
            }
            if (card instanceof ClosingBarrage) {
                card.rawDescription = ClosingBarrage.DESCRIPTION;
                card.initializeDescription();
            }
        }
    }

    public static boolean isThisASpell(final AbstractCard card, final boolean onUseCard) { //Is this a pigeon?
        if (card.type == AbstractCard.CardType.SKILL || card.type == AbstractCard.CardType.ATTACK) {
            if (AbstractDungeon.player.hasPower(GeminiFormPower.POWER_ID)) {
                int attackOrSkillNumber = GeminiFormPower.attacksAndSkillsPlayedThisTurn;
                if (onUseCard) {
                    attackOrSkillNumber--;
                }
                if (attackOrSkillNumber < AbstractDungeon.player.getPower(GeminiFormPower.POWER_ID).amount) {
                    return true;
                }
            } else if (card.rawDescription.startsWith("Spell.")) {
                return true;
            } else if (card.rawDescription.startsWith("Cantrip.")) {
                if (!AbstractDungeon.player.hasPower(SpellsPlayed.POWER_ID) || (AbstractDungeon.player.getPower(SpellsPlayed.POWER_ID).amount < 2)) {
                    return true;
                }
            } else if (AbstractDungeon.player.hasRelic(CrystalBall.ID) && card.type == AbstractCard.CardType.SKILL && !card.rawDescription.startsWith("Technique.")) {
                return true;
            }
        }
        return false;
    }

    public static boolean isThisATechnique(final AbstractCard card, final boolean onUseCard) {
        if (card.type == AbstractCard.CardType.SKILL || card.type == AbstractCard.CardType.ATTACK) {
            if (AbstractDungeon.player.hasPower(GeminiFormPower.POWER_ID)) {
                int attackOrSkillNumber = GeminiFormPower.attacksAndSkillsPlayedThisTurn;
                if (onUseCard) {
                    attackOrSkillNumber--;
                }
                if (attackOrSkillNumber < AbstractDungeon.player.getPower(GeminiFormPower.POWER_ID).amount) {
                    return true;
                }
            } else if (card.rawDescription.startsWith("Technique.")) {
                return true;
            } else if (AbstractDungeon.player.hasRelic(CrystalBall.ID) && card.type == AbstractCard.CardType.ATTACK && !card.rawDescription.startsWith("Spell.")) {
                if (!(card.rawDescription.startsWith("Cantrip") && (!AbstractDungeon.player.hasPower(SpellsPlayed.POWER_ID) || AbstractDungeon.player.getPower(SpellsPlayed.POWER_ID).amount == 1))) {
                    return true;
                }
            }
        }
        return false;
    }

    public static AbstractCard returnTrulyRandomSpell() {
        final ArrayList<AbstractCard> list = new ArrayList<>();
        for (final Map.Entry<String, AbstractCard> potentialSpell : CardLibrary.cards.entrySet()) {
            final AbstractCard card = potentialSpell.getValue();
            if (card.rarity != AbstractCard.CardRarity.BASIC && card.rawDescription.startsWith("Spell.")) {
                list.add(card);
            }
        }
        return list.get(AbstractDungeon.cardRandomRng.random(list.size() - 1));
    }

    public static AbstractCard returnTrulyRandomTechnique() {
        final ArrayList<AbstractCard> list = new ArrayList<>();
        for (final Map.Entry<String, AbstractCard> potentialTechnique : CardLibrary.cards.entrySet()) {
            final AbstractCard card = potentialTechnique.getValue();
            if (card.rarity != AbstractCard.CardRarity.BASIC && card.rawDescription.startsWith("Technique.")) {
                list.add(card);
            }
        }
        return list.get(AbstractDungeon.cardRandomRng.random(list.size() - 1));
    }
}