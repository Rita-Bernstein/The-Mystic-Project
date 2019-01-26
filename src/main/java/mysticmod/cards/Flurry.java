package mysticmod.cards;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import mysticmod.actions.LoadCardImageAction;
import mysticmod.patches.AbstractCardEnum;
import mysticmod.patches.MysticTags;
import mysticmod.powers.SpellsPlayed;

public class Flurry extends AbstractAltArtMysticCard {
    public static final String ID = "mysticmod:Flurry";
    public static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    public static final String ALTERNATE_IMG_PATH = "mysticmod/images/cards/alternate/flurry.png";
    private static final int COST = 1;
    public static final int ATTACK_DMG = 2;
    private static final int ATTACK_COUNT = 2;
    private static final int ALTERNATIVE_ATTACK_COUNT = 4;
    private static final int UPGRADE_ATTACK_COUNT = 1;

    public Flurry() {
        super(ID, NAME, ALTERNATE_IMG_PATH, COST, DESCRIPTION,
                AbstractCard.CardType.ATTACK, AbstractCardEnum.MYSTIC_PURPLE,
                AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.ENEMY);
        IMG_PATH = "mysticmod/images/cards/flurry.png";
        this.loadCardImage(IMG_PATH);
        this.damage=this.baseDamage = ATTACK_DMG;
        this.secondMagicNumber = this.baseSecondMagicNumber = ALTERNATIVE_ATTACK_COUNT;
        this.magicNumber = this.baseMagicNumber = ATTACK_COUNT;
        this.tags.add(MysticTags.IS_ARTE);
        this.altGlowColor = Color.BLUE;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (p.hasPower(SpellsPlayed.POWER_ID) && p.getPower(SpellsPlayed.POWER_ID).amount >= 1) {
            for (int i = 0; i < this.secondMagicNumber; i++) {
                AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
            }
        } else {
            for (int i = 0; i < this.magicNumber; i++) {
                AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
            }
        }
        if (this.isArtAlternate) {
            AbstractDungeon.actionManager.addToBottom(new LoadCardImageAction(this, IMG_PATH, false));
            this.isArtAlternate = false;
        }
    }

    @Override
    public void applyPowers() {
        super.applyPowers();
        if (AbstractDungeon.player.hasPower(SpellsPlayed.POWER_ID)) {
            if (!this.isArtAlternate) {
                AbstractDungeon.actionManager.addToBottom(new LoadCardImageAction(this, ALTERNATE_IMG_PATH, true));
                this.isArtAlternate = true;
            }
        } else {
            if (this.isArtAlternate) {
                this.loadCardImage(IMG_PATH);
                this.isArtAlternate = false;
            }
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new Flurry();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeSecondMagicNumber(UPGRADE_ATTACK_COUNT);
            this.upgradeMagicNumber(UPGRADE_ATTACK_COUNT);
        }
    }
}
