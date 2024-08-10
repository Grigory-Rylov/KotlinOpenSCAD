package kotlinOpenScad.extension

import kotlinOpenScad.core.ScadCode

fun ScadCode.linearExtrude(
    height: Number?,
    center: Boolean = true,
    convexity: Number? = null,
    twist: Number? = null,
    slices: Int? = null,
    scale: Number? = null,
    resolution: Int? = null,
): ScadCode {
    val params = _buildParams(
        "height" to height,
        "center" to center,
        "convexity" to convexity,
        "twist" to twist,
        "slices" to slices,
        "scale" to scale,
        "\$fn" to resolution,
    )
    return _extend("linear_extrude($params)")
}

fun ScadCode.linearExtrude(
    height: Number?,
    center: Boolean = true,
    convexity: Number? = null,
    twist: Number? = null,
    slices: Int? = null,
    scale: Number? = null,
    resolution: Int? = null,
    body: ScadCode.() -> Unit,
) {
    val params = _buildParams(
        "height" to height,
        "center" to center,
        "convexity" to convexity,
        "twist" to twist,
        "slices" to slices,
        "scale" to scale,
        "\$fn" to resolution,
    )
    return _buildGroup("linear_extrude($params)") {
        _createCleanScope().body()
    }
}

fun ScadCode.linearExtrude(
    height: Number?,
    center: Boolean = true,
    convexity: Number? = null,
    twist: Number? = null,
    slices: Int? = null,
    scaleX: Number,
    scaleY: Number,
    resolution: Int? = null,
): ScadCode {
    val params = _buildParams(
        "height" to height,
        "center" to center,
        "convexity" to convexity,
        "twist" to twist,
        "slices" to slices,
        "scale" to _buildParamsArray(scaleX, scaleY),
        "\$fn" to resolution,
    )
    return _extend("linear_extrude($params)")
}

fun ScadCode.linearExtrude(
    height: Number?,
    center: Boolean = true,
    convexity: Number? = null,
    twist: Number? = null,
    slices: Int? = null,
    scaleX: Number,
    scaleY: Number,
    resolution: Int? = null,
    body: ScadCode.() -> Unit,
) {
    val params = _buildParams(
        "height" to height,
        "center" to center,
        "convexity" to convexity,
        "twist" to twist,
        "slices" to slices,
        "scale" to _buildParamsArray(scaleX, scaleY),
        "\$fn" to resolution,
    )
    return _buildGroup("linear_extrude($params)") {
        _createCleanScope().body()
    }
}

fun ScadCode.rotateExtrude(
    convexity: Number? = null,
    angle: Number? = null,
    fragmentMinimumAngle: Number? = null,
    fragmentMinimumLength: Number? = null,
    fragmentCount: Int? = null
): ScadCode {
    val params = _buildParams(
        "convexity" to convexity,
        "angle" to angle,
        "\$fa" to fragmentMinimumAngle,
        "\$fs" to fragmentMinimumLength,
        "\$fn" to fragmentCount,
    )
    return _extend("rotate_extrude($params)")
}

fun ScadCode.rotateExtrude(
    convexity: Number? = null,
    angle: Number? = null,
    fragmentMinimumAngle: Number? = null,
    fragmentMinimumLength: Number? = null,
    fragmentCount: Int? = null,
    body: ScadCode.() -> Unit,
) {
    val params = _buildParams(
        "convexity" to convexity,
        "angle" to angle,
        "\$fa" to fragmentMinimumAngle,
        "\$fs" to fragmentMinimumLength,
        "\$fn" to fragmentCount,
    )
    return _buildGroup("rotate_extrude($params)") {
        _createCleanScope().body()
    }
}
