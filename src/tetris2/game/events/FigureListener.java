package tetris2.game.events;

import tetris2.game.events.FigureEvent;

public interface FigureListener {
    public void figureMoved();

    public void figureLanded(FigureEvent var1);

    public boolean askForPlace(FigureEvent var1);
}

