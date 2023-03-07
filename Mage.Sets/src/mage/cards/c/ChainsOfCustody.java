package mage.cards.c;

import java.util.UUID;

import mage.abilities.Ability;
import mage.abilities.common.EntersBattlefieldAbility;
import mage.abilities.common.SimpleStaticAbility;
import mage.abilities.common.delayed.OnLeaveReturnExiledToBattlefieldAbility;
import mage.abilities.costs.mana.GenericManaCost;
import mage.abilities.effects.common.CreateDelayedTriggeredAbilityEffect;
import mage.abilities.effects.common.ExileUntilSourceLeavesEffect;
import mage.abilities.effects.common.continuous.GainAbilityAttachedEffect;
import mage.abilities.keyword.WardAbility;
import mage.constants.AttachmentType;
import mage.constants.SubType;
import mage.abilities.effects.common.AttachEffect;
import mage.constants.Outcome;
import mage.filter.StaticFilters;
import mage.target.TargetPermanent;
import mage.abilities.keyword.EnchantAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;

/**
 *
 * @author AustinYQM
 */
public final class ChainsOfCustody extends CardImpl {

    public ChainsOfCustody(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.ENCHANTMENT}, "{2}{W}");
        
        this.subtype.add(SubType.AURA);

        // Enchant creature you control
        TargetPermanent auraTarget = new TargetPermanent(StaticFilters.FILTER_CONTROLLED_CREATURE);
        this.getSpellAbility().addTarget(auraTarget);
        this.getSpellAbility().addEffect(new AttachEffect(Outcome.BoostCreature));
        this.addAbility(new EnchantAbility(auraTarget));

        // When Chains of Custody enters the battlefield, exile target nonland permanent an opponent controls until Chains of Custody leaves the battlefield.
        Ability ability = new EntersBattlefieldAbility(new ExileUntilSourceLeavesEffect());
        ability.addTarget(new TargetPermanent(StaticFilters.FILTER_OPPONENTS_PERMANENT_NON_LAND));
        ability.addEffect(new CreateDelayedTriggeredAbilityEffect(new OnLeaveReturnExiledToBattlefieldAbility()));
        this.addAbility(ability);

        // Enchanted creature has ward {2}.
        this.addAbility(new SimpleStaticAbility(new GainAbilityAttachedEffect(new WardAbility(new GenericManaCost(2)), AttachmentType.AURA)));
    }

    private ChainsOfCustody(final ChainsOfCustody card) {
        super(card);
    }

    @Override
    public ChainsOfCustody copy() {
        return new ChainsOfCustody(this);
    }
}
