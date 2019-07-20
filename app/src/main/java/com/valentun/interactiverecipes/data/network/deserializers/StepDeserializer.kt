package com.valentun.interactiverecipes.data.network.deserializers

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import com.fasterxml.jackson.databind.node.TextNode
import com.valentun.interactiverecipes.data.pojo.Step

const val TYPE_COUNTDOWN = "timer"
const val TYPE_IMAGE = "image"
const val TYPE_TEXT = "text"

const val TYPE_KEY = "type"

class StepDeserializer : StdDeserializer<Step> {

    constructor(vc: Class<*>?) : super(vc)

    @Suppress("unused")
    constructor() : this(null)

    override fun deserialize(parser: JsonParser, ctxt: DeserializationContext): Step {
        val node: JsonNode = parser.codec.readTree(parser)

        val type = (node.get(TYPE_KEY) as TextNode).textValue()

        val stepClass = when(type) {
            TYPE_COUNTDOWN -> Step.CountdownStep::class
            TYPE_IMAGE -> Step.ImageStep::class
            TYPE_TEXT -> Step.TextStep::class
            else -> Step.UnsupportedStep::class
        }

        return ctxt.readValue(parser, stepClass.java)
    }
}