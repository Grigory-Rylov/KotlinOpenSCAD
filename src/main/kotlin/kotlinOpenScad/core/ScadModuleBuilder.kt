package kotlinOpenScad.core

@Suppress("FunctionName", "PropertyName") // functions with "_" in prefix are public for extension
class ScadModuleBuilder(val _scadBuilder: ScadBuilder, private val modifier: String? = null) {

    private fun createCleanBuilder() = ScadModuleBuilder(_scadBuilder)
    val _readableModifier: String
        get() {
            if (modifier == null)
                return ""
            return "$modifier "
        }

    fun _buildGroup(def: String, func: ScadModuleBuilder.() -> Unit) {
        val finalMod = "$_readableModifier $def".trim()
        _scadBuilder.appendLine("$finalMod {")
        _scadBuilder.indent {
            func(createCleanBuilder())
        }
        _scadBuilder.appendLine("}")
    }

    fun of(func: ScadModuleBuilder.() -> Unit) {
        return _buildGroup("", func)
    }

    fun _extend(newModifier: String): ScadModuleBuilder {
        return ScadModuleBuilder(_scadBuilder, modifier = "$_readableModifier $newModifier".trim())
    }

    fun _buildParams(vararg params: Pair<String, Any?>): String {
        if (params.isEmpty())
            return ""

        return params
            .filter { x -> x.second != null }
            .joinToString(separator = ", ") { (name, value) -> "${name}=${prepareArg(value)}" }
    }

    fun _buildParamsArray(vararg params: Any?): String {
        return when (params.size) {
            0 -> "[]"
            2 -> "[${prepareArg(params[0])}, ${prepareArg(params[1])}]"
            3 -> "[${prepareArg(params[0])}, ${prepareArg(params[1])}, ${prepareArg(params[2])}]"
            else -> params.joinToString(
                separator = ", ",
                prefix = "[",
                postfix = "]"
            ) { value -> "${prepareArg(value)}" }
        }

    }

    private fun prepareArg(value: Any?): Any? {
        return when (value) {
            is Float -> {
                val longVal = value.toLong()
                when (value) {
                    longVal.toFloat() -> longVal
                    else -> value
                }
            }
            is Double -> {
                val longVal = value.toLong()
                when (value) {
                    longVal.toDouble() -> longVal
                    else -> value
                }
            }
            is Enum<*> -> _quote(value.toString())
            else -> value
        }
    }

    fun _quote(value: String) = "\"" + value.toString().replace("\"", "\\\"") + "\""


}