/*
 * Source++, the continuous feedback platform for developers.
 * Copyright (C) 2022 CodeBrig, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package spp.protocol.view.rule

import io.vertx.codegen.annotations.DataObject
import io.vertx.core.json.JsonArray
import io.vertx.core.json.JsonObject

@DataObject
data class LiveViewRuleset(
    val expSuffix: String,
    val metricPrefix: String,
    val metricsRules: List<LiveViewRule>,
    val id: String? = null
) {
    constructor(json: JsonObject) : this(
        expSuffix = json.getString("expSuffix"),
        metricPrefix = json.getString("metricPrefix"),
        metricsRules = json.getJsonArray("metricsRules").map { LiveViewRule(it as JsonObject) },
        id = json.getString("id")
    )

    fun toJson(): JsonObject {
        val json = JsonObject()
        json.put("expSuffix", expSuffix)
        json.put("metricPrefix", metricPrefix)
        json.put("metricsRules", JsonArray().apply { metricsRules.forEach { add(it.toJson()) } })
        json.put("id", id)
        return json
    }
}
