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
package spp.protocol.instrument.throttle

import io.vertx.core.json.JsonObject
import spp.protocol.instrument.LiveInstrument

/**
 * Used to throttle the frequency of [LiveInstrument] executions inside a live probe.
 *
 * @since 0.3.0
 * @author [Brandon Fergerson](mailto:bfergerson@apache.org)
 */
data class InstrumentThrottle(
    val limit: Int,
    val step: ThrottleStep,
) {
    companion object {
        val DEFAULT: InstrumentThrottle = InstrumentThrottle(1, ThrottleStep.SECOND)
    }

    constructor(json: JsonObject) : this(
        json.getInteger("limit"),
        ThrottleStep.valueOf(json.getString("step"))
    )

    fun toJson(): JsonObject {
        return JsonObject().apply {
            put("limit", limit)
            put("step", step.name)
        }
    }
}
