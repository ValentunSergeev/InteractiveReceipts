package com.valentun.interactiverecipes.data.pojo

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.valentun.interactiverecipes.data.network.deserializers.StepDeserializer

class Recipe @JsonCreator constructor(val id: Long,
                                      val name: String,
                                      @JsonProperty("required_time") val requiredTime: String,
                                      @JsonProperty("sections") val sections: List<Section> = listOf())

class Section @JsonCreator constructor(val name: String,
                                       val steps: List<Step>)

@JsonDeserialize(using = StepDeserializer::class)
interface Step {
    class TextStep(val text: String) : Step
    class ImageStep(val imageUrl: String) : Step
    class CountdownStep(val time: Int) : Step
    class UnsupportedStep : Step
}