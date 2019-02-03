package hr.ferit.kreso.labela;


import io.realm.RealmObject;

public class GameItem extends RealmObject {

    int pointWe;
    int pointThem;

    public GameItem() {
    }

    public GameItem(int pointWe, int pointThem) {
        this.pointWe = pointWe;
        this.pointThem = pointThem;
    }

    public int getPointWe() {
        return pointWe;
    }

    public void setPointWe(int pointWe) {
        this.pointWe = pointWe;
    }

    public int getPointThem() {
        return pointThem;
    }

    public void setPointThem(int pointThem) {
        this.pointThem = pointThem;
    }
}
