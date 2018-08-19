package mysticmod.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.IntangiblePlayerPower;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import mysticmod.patches.AbstractCardEnum;
import mysticmod.powers.TechniquesPlayed;
import mysticmod.powers.SpellsPlayed;

import basemod.abstracts.CustomCard;

public class GreaterInvisibility
        extends CustomCard {
    public static final String ID = "mysticmod:GreaterInvisibility";
    public static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    public static final String IMG_PATH = "mysticmod/images/cards/greaterinvisibility.png";
    public static final String ALTERNATE_IMG_PATH = "mysticmod/images/cards/alternate/greaterinvisibility.png";
    private static final int COST = 2;
    private static final int REQUIRED_TECHNIQUES = 2;
    private static final int UPGRADE_TECHNIQUE_REDUCE = -1;

    public GreaterInvisibility() {
        super(ID, NAME, ALTERNATE_IMG_PATH, COST, DESCRIPTION,
                AbstractCard.CardType.SKILL, AbstractCardEnum.MYSTIC_PURPLE,
                AbstractCard.CardRarity.RARE, AbstractCard.CardTarget.SELF);
        loadCardImage(IMG_PATH);
        this.exhaust = true;
        this.isEthereal = true;
        this.magicNumber = this.baseMagicNumber = REQUIRED_TECHNIQUES;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        //set exhaust = false if techniques played >= magicNumber
        if ((p.hasPower(TechniquesPlayed.POWER_ID)) && (p.getPower(TechniquesPlayed.POWER_ID).amount >= this.magicNumber)) {
            this.exhaust = false;
        } else {
            this.exhaust = true;
        }
        //Intangible
        AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.ApplyPowerAction(p, p, new IntangiblePlayerPower(p, 1), 1));
        //spell functionality
        AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.ApplyPowerAction(p, p, new SpellsPlayed(p, 1), 1));
        loadCardImage(IMG_PATH);
    }

    @Override
    public void applyPowers() {
        super.applyPowers();
        if (AbstractDungeon.player.hasPower(TechniquesPlayed.POWER_ID) && AbstractDungeon.player.getPower(TechniquesPlayed.POWER_ID).amount >= this.magicNumber) {
            loadCardImage(ALTERNATE_IMG_PATH);
        } else {
            loadCardImage(IMG_PATH);
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new GreaterInvisibility();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(UPGRADE_TECHNIQUE_REDUCE);
            this.rawDescription = UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }
}