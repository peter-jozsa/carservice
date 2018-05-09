package hu.unideb.inf.lev.carservice.controller;

import hu.unideb.inf.lev.carservice.controller.context.ViewControllerContext;

public abstract class ViewController<CTX extends ViewControllerContext> {
    protected CTX context;

    public void setContext(CTX context) {
        this.context = context;
    }
}
