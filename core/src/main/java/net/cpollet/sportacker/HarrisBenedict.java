package net.cpollet.sportacker;

import net.cpollet.sportracker.data.Person;
import net.cpollet.sportracker.quantities.EnergyQuantity;
import net.cpollet.sportracker.quantities.Quantity;
import net.cpollet.sportracker.units.Energy;
import net.cpollet.sportracker.units.EnergyUnit;
import net.cpollet.sportracker.units.LengthUnit;
import net.cpollet.sportracker.units.MassUnit;
import org.joda.time.LocalDate;
import org.joda.time.Years;

import java.math.BigDecimal;

/**
 * @author Christophe Pollet
 * @see http://www.mon-imc.com/calories.html
 */
public class HarrisBenedict implements DailyEnergyNeedCalculator {
	private enum PersonConstants {
		MALE(655, 9.6, 1.8, 4.7),
		FEMALE(66, 13.7, 5.0, 6.76);

		private int base;
		private double weightFactor;
		private double heightFactor;
		private double ageFactor;

		private PersonConstants(int base, double weightFactor, double heightFactor, double ageFactor) {
			this.base = base;
			this.weightFactor = weightFactor;
			this.heightFactor = heightFactor;
			this.ageFactor = ageFactor;
		}

		public static PersonConstants fromGender(Person.Gender gender) {
			for (PersonConstants personConstants : values()) {
				if (personConstants.name().equals(gender.name())) {
					return personConstants;
				}
			}

			throw new IllegalArgumentException("No constant found for gender [" + gender + "]");
		}

		private int getBase() {
			return base;
		}

		private double getWeightFactor() {
			return weightFactor;
		}

		private double getHeightFactor() {
			return heightFactor;
		}

		private double getAgeFactor() {
			return ageFactor;
		}
	}

	private enum ActivityConstants {
		NONE(1.2), LOW(1.375), MEDIUM(1.55), HIGH(1.725), MAX(1.9);

		private double factor;

		private ActivityConstants(double factor) {
			this.factor = factor;
		}

		public static ActivityConstants fromActivity(ActivityLevel activityLevel) {
			for (ActivityConstants activityConstants : values()) {
				if (activityConstants.name().equals(activityLevel.name())) {
					return activityConstants;
				}
			}

			throw new IllegalArgumentException("No constant found for activityLevel [" + activityLevel + "]");
		}

		private double getFactor() {
			return factor;
		}
	}

	@Override
	public Quantity<Energy> compute(Person person, ActivityLevel activityLevel) {
		PersonConstants personConstants = PersonConstants.fromGender(person.getGender());

		double base = personConstants.getBase() //
				+ personConstants.getWeightFactor() * person.getWeight().in(MassUnit.kg).getValue().doubleValue() //
				+ personConstants.getHeightFactor() * person.getHeight().in(LengthUnit.cm).getValue().doubleValue() //
				- personConstants.getAgeFactor() * getPersonsAge(person);

		ActivityConstants activityConstants = ActivityConstants.fromActivity(activityLevel);

		return new EnergyQuantity(BigDecimal.valueOf(Math.ceil(base * activityConstants.getFactor())), EnergyUnit.kcal);
	}

	private int getPersonsAge(Person person) {
		LocalDate birthDate = new LocalDate(person.getBirthdate());
		LocalDate now = new LocalDate();

		return Years.yearsBetween(birthDate, now).getYears();
	}
}
