package com.el.b2b.service;

import java.util.concurrent.Executors;

public abstract class AsynExecutorBlh {

    public void run() {
        Executors.newSingleThreadExecutor().submit(new Thread() {
            public void run() {
                execute();
            }
        });
    }

    public abstract void execute();
}
