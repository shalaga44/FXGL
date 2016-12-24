/*
 * The MIT License (MIT)
 *
 * FXGL - JavaFX Game Library
 *
 * Copyright (c) 2015-2017 AlmasB (almaslvl@gmail.com)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.almasb.fxgl.gameplay.rpg.quest

import javafx.animation.FillTransition
import javafx.animation.ParallelTransition
import javafx.animation.RotateTransition
import javafx.beans.property.SimpleObjectProperty
import javafx.scene.paint.Color
import javafx.scene.shape.Rectangle
import javafx.util.Duration

/**
 * A check box with three states.
 * Cannot be activated from UI.
 *
 * @author Almas Baimagambetov (almaslvl@gmail.com)
 */
class QuestCheckBox : Rectangle(18.0, 18.0, Color.TRANSPARENT) {

    private val state = SimpleObjectProperty<QuestState>(QuestState.ACTIVE)

    init {
        arcWidth = 12.0
        arcHeight = 12.0
        stroke = Color.WHITESMOKE
        strokeWidth = 1.0

        state.addListener { o, oldState, newState ->
            val fill = FillTransition(Duration.seconds(0.35), this, fill as Color, newState.color)
            val rotation = RotateTransition(Duration.seconds(0.35), this)
            rotation.byAngle = 180.0

            ParallelTransition(fill, rotation).play()
        }
    }

    fun stateProperty() = state

    fun getState() = state.get()

    fun setState(state: QuestState) {
        this.state.set(state)
    }
}