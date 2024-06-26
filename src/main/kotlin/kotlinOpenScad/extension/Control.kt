package kotlinOpenScad.extension

import ScadFunctionName
import ScadModuleName
import kotlinOpenScad.core.ScadCode
import kotlinOpenScad.extension.expr.ScadExpr
import kotlinOpenScad.extension.expr.ex

internal val NOOP: ScadCode.() -> Unit = { }


fun ScadCode.IF(
    expression: String,
    then: ScadCode.() -> Unit = NOOP,
    otherwise: ScadCode.() -> Unit = NOOP,
) {
    _buildGroup("if ($expression)") {
        _createCleanScope().then()
        if (otherwise !== NOOP) {
            _scadBuilder.unindent {
                _scadBuilder.appendLine("} else {")
            }
            _createCleanScope().otherwise()
        }
    }
}


fun ScadCode.MODULE(
    name: String,
    params: String = "",
    body: ScadCode.() -> Unit = NOOP
): ScadModuleName {
    _buildGroup("module $name($params)") {
        _createCleanScope().body()
    }
    return ScadModuleName(name)
}

fun ScadCode.call(
    name: ScadModuleName,
    params: String = ""
) = callModule(name.name, params)

fun ScadCode.call(
    name: ScadFunctionName,
    params: String = ""
) = callFunction(name.name, params)

fun ScadCode.callFunction(
    name: String,
    params: String = ""
): ScadExpr {
    return ex("${name}($params)")
}

fun ScadCode.callModule(
    name: String,
    params: String = ""
) {
    command("${name}($params)")
}

fun ScadCode.import(
    params: String
) {
    command("import(\"$params\")")
}

fun ScadCode.FUNCTION(
    name: String,
    params: String = "",
    expression: String
): ScadFunctionName {
    command("function $name($params) = $expression")
    return ScadFunctionName(name)
}

fun ScadCode.fn(
    fnValue: Int
) {
    command("\$fn = $fnValue")
}
