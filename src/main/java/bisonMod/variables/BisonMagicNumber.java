package bisonMod.variables;

import basemod.abstracts.DynamicVariable;
import bisonMod.cards.AbstractBisonCard;
import com.megacrit.cardcrawl.cards.AbstractCard;

import static bisonMod.bisonMod.makeID;

public class BisonMagicNumber extends DynamicVariable {

    //For in-depth comments, check the other variable(BisonCustomVariable). It's nearly identical.

    @Override
    public String key() {
        return makeID("theDefault:SecondMagic"); // This is what you put between "!!" in your card strings to actually display the number.
        // You can name this anything (no spaces), but please pre-phase it with your mod name as otherwise mod conflicts can occur.
    }

    @Override
    public boolean isModified(AbstractCard card) {
        return ((AbstractBisonCard) card).isDefaultSecondMagicNumberModified;

    }

    @Override
    public int value(AbstractCard card) {
        return ((AbstractBisonCard) card).defaultSecondMagicNumber;
    }

    @Override
    public int baseValue(AbstractCard card) {
        return ((AbstractBisonCard) card).defaultBaseSecondMagicNumber;
    }

    @Override
    public boolean upgraded(AbstractCard card) {
        return ((AbstractBisonCard) card).upgradedDefaultSecondMagicNumber;
    }
}