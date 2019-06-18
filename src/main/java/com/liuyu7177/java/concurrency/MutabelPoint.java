package com.liuyu7177.java.concurrency;

/**
 * Created by liuyu7177 On 2019/6/4
 */
public class MutabelPoint {
    public final int x,y;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public MutabelPoint() {
        x=0;
        y=0;
    }
    public MutabelPoint(int x,int y) {
        this.x=x;
        this.y=y;
    }
    public  MutabelPoint(MutabelPoint p){
        this.x=p.x;
        this.y=p.y;
    }

    @Override
    public String toString() {
        return "MutabelPoint{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
