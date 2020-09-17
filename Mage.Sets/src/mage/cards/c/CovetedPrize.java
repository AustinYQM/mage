package mage.cards.c;

import mage.abilities.common.SimpleStaticAbility;
import mage.abilities.condition.common.FullPartyCondition;
import mage.abilities.decorator.ConditionalOneShotEffect;
import mage.abilities.dynamicvalue.common.PartyCount;
import mage.abilities.effects.common.cost.CastWithoutPayingManaCostEffect;
import mage.abilities.effects.common.cost.SpellCostReductionForEachSourceEffect;
import mage.abilities.effects.common.search.SearchLibraryPutInHandEffect;
import mage.abilities.hint.common.PartyCountHint;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.Zone;
import mage.target.common.TargetCardInLibrary;

import java.util.UUID;

/**
 * @author TheElk801
 */
public final class CovetedPrize extends CardImpl {

    public CovetedPrize(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.SORCERY}, "{4}{B}");

        // This spell costs {1} less to cast for each creature in your party.
        this.addAbility(new SimpleStaticAbility(
                Zone.ALL, new SpellCostReductionForEachSourceEffect(1, PartyCount.instance)
        ).addHint(PartyCountHint.instance));

        // Search your library for a card, put it into your hand, then shuffle your library. If you have a full party, you may cast a spell with converted mana cost 4 or less from your hand without paying its mana cost.
        this.getSpellAbility().addEffect(new SearchLibraryPutInHandEffect(new TargetCardInLibrary()));
        this.getSpellAbility().addEffect(new ConditionalOneShotEffect(
                new CastWithoutPayingManaCostEffect(4),
                FullPartyCondition.instance, "If you have a full party, " +
                "you may cast a spell with converted mana cost 4 or less from your hand without paying its mana cost."
        ));
    }

    private CovetedPrize(final CovetedPrize card) {
        super(card);
    }

    @Override
    public CovetedPrize copy() {
        return new CovetedPrize(this);
    }
}