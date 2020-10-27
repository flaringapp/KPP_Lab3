package com.flaringapp.app;

import com.flaringapp.app.demo.Demo;

public class App {

    public static void main(String[] args) {
        Demo demo = DemoResolver.resolveDemo();
        demo.run();
    }
}

