/*
 * Source++, the continuous feedback platform for developers.
 * Copyright (C) 2022-2023 CodeBrig, Inc.
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
package spp.protocol.instrument.command

import io.vertx.codegen.annotations.DataObject
import io.vertx.core.json.JsonObject
import spp.protocol.instrument.LiveInstrument
import spp.protocol.instrument.LiveSourceLocation

@DataObject
data class LiveInstrumentCommand(
    var commandType: CommandType,
    var instruments: Set<@JvmSuppressWildcards LiveInstrument> = emptySet(),
    var locations: Set<LiveSourceLocation> = emptySet()
) {
    constructor(json: JsonObject) : this(
        CommandType.valueOf(json.getString("commandType")),
        json.getJsonArray("instruments").map { JsonObject.mapFrom(it) }.map { LiveInstrument.fromJson(it) }.toSet(),
        json.getJsonArray("locations").map { JsonObject.mapFrom(it) }.map { LiveSourceLocation(it) }.toSet()
    )

    fun toJson(): JsonObject {
        val json = JsonObject()
        json.put("commandType", commandType.name)
        json.put("instruments", instruments.map { it.toJson() })
        json.put("locations", locations.map { it.toJson() })
        return json
    }
}
