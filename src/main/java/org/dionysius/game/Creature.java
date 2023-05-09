package org.dionysius.game;

import javax.annotation.Nonnull;

import io.scvis.geometry.Vector2D;

public class Creature extends AnimatedGraphic {

	public static final byte ANIMATION_WALK = 10;
	public static final byte ANIMATION_RUN = 11;
	public static final byte ANIMATION_DASH = 12;
	public static final byte ANIMATION_JUMP = 13;
	public static final byte ANIMATION_ATTACK_1 = 20;
	public static final byte ANIMATION_ATTACK_2 = 21;
	public static final byte ANIMATION_ATTACK_3 = 22;
	public static final byte ANIMATION_DEATH = Byte.MAX_VALUE;

	private final Health health = new Health(this, 20.0);

	public Creature(Game game, @Nonnull Vector2D position) {
		super(game, position);
		game.getCreatures().add(this);
	}

	public void walk(byte direction) {
		if (isInAnimationIdle()) {
			setDestination(new Vector2D(direction, 0));
			if (getAnimations().containsKey(ANIMATION_WALK))
				setAnimationPlayed(ANIMATION_WALK);
			flip(direction);
		}
	}

	public void run(byte direction) {
		if (isInAnimationIdle()) {
			setDestination(new Vector2D(2 * direction, 0));
			if (getAnimations().containsKey(ANIMATION_RUN))
				setAnimationPlayed(ANIMATION_RUN);
			flip(direction);
		}
	}

	public void dash() {
		if (isInAnimationIdle()) {
			System.out.println(getPosition());
			System.out.println(125 * getDirection());
			setPosition(getPosition().add(125 * getDirection(), 0));
			setDestination(Vector2D.ZERO);
			setVelocity(Vector2D.ZERO);
			System.out.println(getPosition());
			if (getAnimations().containsKey(ANIMATION_DASH))
				setAnimationPlayed(ANIMATION_DASH);
		}
	}

	public void jump() {
		if (isInAnimationIdle()) {
			setVelocity(new Vector2D(0, 20));
			if (getAnimations().containsKey(ANIMATION_JUMP))
				setAnimationPlayed(ANIMATION_JUMP);
		}
	}

	public void attack1() {
		if (isInAnimationIdle()) {
			if (getAnimations().containsKey(ANIMATION_ATTACK_1))
				setAnimationPlayed(ANIMATION_ATTACK_1);
		}
	}

	public void attack2() {
		if (isInAnimationIdle()) {
			if (getAnimations().containsKey(ANIMATION_ATTACK_2))
				setAnimationPlayed(ANIMATION_ATTACK_2);
		}
	}

	public void attack3() {
		if (isInAnimationIdle()) {
			if (getAnimations().containsKey(ANIMATION_ATTACK_3))
				setAnimationPlayed(ANIMATION_ATTACK_3);
		}
	}

	public Health getHealth() {
		return health;
	}
}
