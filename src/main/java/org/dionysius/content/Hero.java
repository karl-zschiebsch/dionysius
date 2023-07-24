package org.dionysius.content;

import io.scvis.geometry.Vector2D;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import org.dionysius.game.*;
import org.dionysius.game.Indicator.BarIndicator;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class Hero extends Creature {

    private static final AnimationLoader animation = new AnimationLoader(new File("src/main/resources/text/animation/Hero.json"));
    static {
        Consumer<Creature> near = new Attack(1, 80, 90).asConsumer();
        Consumer<Creature> range = new Attack(2, 130, 90).asConsumer();
        List<AnimationFrame> attack1frames = animation.getLoaded().get(ANIMATION_ATTACK_1);
        attack1frames.get(2).setOnPlayed(near);
        attack1frames.get(3).setOnPlayed(near);
        attack1frames.get(4).setOnPlayed(near);
        attack1frames.get(5).setOnPlayed(near);
        attack1frames.get(10).setOnPlayed(range);
        attack1frames.get(11).setOnPlayed(range);

        Consumer<Creature> charge = new Attack(6, 130, 90).asConsumer();
        List<AnimationFrame> attack2frames = animation.getLoaded().get(ANIMATION_ATTACK_2);
        attack2frames.get(5).setOnPlayed(charge);
        attack2frames.get(10).setOnPlayed(charge);
    }

    public final Map<KeyCode, Runnable> keymap = new HashMap<>();
    {
		keymap.putAll(Map.of(
			KeyCode.A, () -> {
				flip(DIRECTION_LEFT);
				walk();
			}, KeyCode.D, () -> {
				flip(DIRECTION_RIGHT);
				walk();
			}, KeyCode.SPACE, this::jump, KeyCode.SHIFT, this::dash,
			KeyCode.E, this::attack1, KeyCode.F, this::attack2,
			KeyCode.X, this::interact, KeyCode.DIGIT1, this::next1,
			KeyCode.DIGIT2, this::next2, KeyCode.DIGIT3, this::next3
		));
    }

    public Hero(Game game) {
        super(game, new Vector2D(100, 0));
        getHealth().setMax(60.0);
        getHealth().setValue(60.0);
        getHealth().getIndicator().setClip(new Vector2D(10, 10));
        getHealth().getIndicator().setSize(BarIndicator.MEDIUM_SIZE);
        getHitbox().setWidth(56);
        getHitbox().setHeight(27);

        applyTo(game);

        getAnimations().putAll(animation.getLoaded());

        idle();
    }

    public void next1() {
        if (getGame().getDialog() != null) getGame().getDialog().next(this, 0);
    }

    public void next2() {
        if (getGame().getDialog() != null) getGame().getDialog().next(this, 1);
    }

    public void next3() {
        if (getGame().getDialog() != null) getGame().getDialog().next(this, 2);
    }

    public void applyTo(Scene node) {
        node.addEventHandler(KeyEvent.KEY_PRESSED, e -> {
			Runnable action = keymap.get(e.getCode());
			if (action != null) action.run();
        });
        node.addEventHandler(KeyEvent.KEY_RELEASED, e -> {
			if (hasHigherPriority(ANIMATION_RUN)) idle();
        });
        node.addEventHandler(MouseEvent.MOUSE_PRESSED, e -> {
        });
    }
}
