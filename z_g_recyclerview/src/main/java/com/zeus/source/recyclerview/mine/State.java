package com.zeus.source.recyclerview.mine;

public class State {
    static final int STEP_START = 1;
    static final int STEP_LAYOUT = 1 << 1;
    static final int STEP_ANIMATIONS = 1 << 2;

    int mLayoutStep = STEP_START;

}