package hr.ferit.kreso.labela;

import io.realm.RealmObject;

public class MaxGamePoint extends RealmObject {
    int maxGame ;

    public MaxGamePoint() {
    }

    public MaxGamePoint(int maxGame) {
        this.maxGame = maxGame;
    }

    public int getMaxGame() {
        return maxGame;
    }

    public void setMaxGame(int maxGame) {
        this.maxGame = maxGame;
    }
}
