package kotlinOpenScad.examples

import kotlinOpenScad.core.ScadCode
import kotlinOpenScad.extension.FOR
import kotlinOpenScad.extension.ScadPoint
import kotlinOpenScad.extension.cylinder
import kotlinOpenScad.extension.linearExtrude
import kotlinOpenScad.extension.polygon
import kotlinOpenScad.extension.translate
import kotlin.math.cos
import kotlin.math.sin

fun ScadCode.boltHole(radius: Number, topRadius: Number, height: Number, coneHeight: Number) {
    translate(z = -1) {
        cylinder(radius = radius, height = height.toDouble() + 2)
        translate(z = coneHeight.toDouble() + 1).cylinder(bottomRadius = radius, topRadius = topRadius, height = coneHeight.toDouble() + 2)
    }
}

fun ScadCode.screwNut(radius: Number, height: Number = 2, edges: Int = 6) {
    val stepSize = 360 / edges

    val doubleRadius = radius.toDouble()

    val points = (0 until 360 step stepSize).map {
        val radAngle = Math.toRadians(it.toDouble())
        ScadPoint(
            x = doubleRadius * sin(radAngle),
            y = doubleRadius * cos(radAngle)
        )
    }

    linearExtrude(height = height).polygon(points)
}
