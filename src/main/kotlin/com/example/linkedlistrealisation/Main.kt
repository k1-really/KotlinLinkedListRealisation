package com.example.linkedlistrealisation

import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.stage.Stage

class Main : Application() {
    override fun start(stage: Stage) {
        val fxmlLoader = FXMLLoader(Main::class.java.getResource("app.fxml"))
        val scene = Scene(fxmlLoader.load(), 600.0, 400.0)
        stage.title = "LinkedListRealisation!"
        stage.scene = scene
        stage.show()

    }
}

fun main() {
    Application.launch(Main::class.java)
}