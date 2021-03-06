package mysticmod.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import mysticmod.MysticMod;

public class MirrorEntityPower extends AbstractPower implements CloneablePowerInterface {
    public static final String POWER_ID = "mysticmod:MirrorEntityPower";
    public static final PowerStrings cardStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = cardStrings.NAME;
    public static final String[] DESCRIPTIONS = cardStrings.DESCRIPTIONS;

    public MirrorEntityPower(AbstractCreature owner) {
        name = NAME;
        ID = POWER_ID;
        this.owner = owner;
        region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage("mysticmod/images/powers/mirror entity power 84.png"), 0, 0, 84, 84);
        region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage("mysticmod/images/powers/mirror entity power 32.png"), 0, 0, 32, 32);
        type = PowerType.BUFF;
        amount = -1;
        updateDescription();
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0];
    }

    @Override
    public void onUseCard(final AbstractCard card, final UseCardAction action) {
        if (MysticMod.isThisASpell(card)) {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new SpellsPlayedNextTurn(AbstractDungeon.player, 1), 1));
        }
        if (MysticMod.isThisAnArte(card)) {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new ArtesPlayedNextTurn(AbstractDungeon.player, 1), 1));
        }
    }

    @Override
    public AbstractPower makeCopy() {
        return new MirrorEntityPower(owner);
    }
}
