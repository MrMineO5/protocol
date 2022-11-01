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
package spp.protocol.service

import io.vertx.codegen.annotations.GenIgnore
import io.vertx.codegen.annotations.ProxyGen
import io.vertx.codegen.annotations.VertxGen
import io.vertx.core.Future
import io.vertx.core.Vertx
import io.vertx.core.eventbus.DeliveryOptions
import io.vertx.core.json.JsonObject
import spp.protocol.service.SourceServices.LIVE_VIEW
import spp.protocol.view.LiveView
import spp.protocol.view.rule.LiveViewRule
import spp.protocol.view.rule.LiveViewRuleset

/**
 * Back-end service for managing [LiveView]s.
 *
 * @since 0.3.0
 * @author [Brandon Fergerson](mailto:bfergerson@apache.org)
 */
@ProxyGen
@VertxGen
interface LiveViewService {

    @GenIgnore
    companion object {
        @JvmStatic
        fun createProxy(vertx: Vertx, authToken: String? = null): LiveViewService {
            val deliveryOptions = DeliveryOptions().apply {
                authToken?.let { addHeader("auth-token", it) }
            }
            return LiveViewServiceVertxEBProxy(vertx, LIVE_VIEW, deliveryOptions)
        }
    }

    fun saveRuleset(ruleset: LiveViewRuleset): Future<LiveViewRuleset>
    fun deleteRuleset(rulesetId: String): Future<LiveViewRuleset?>
    fun saveRule(rule: LiveViewRule): Future<LiveViewRule>
    fun deleteRule(ruleId: String): Future<LiveViewRule?>

    fun addLiveView(subscription: LiveView): Future<LiveView>
    fun updateLiveView(id: String, subscription: LiveView): Future<LiveView>
    fun removeLiveView(id: String): Future<LiveView>
    fun getLiveView(id: String): Future<LiveView>
    fun getLiveViews(): Future<List<LiveView>>
    fun clearLiveViews(): Future<List<LiveView>>
    fun getLiveViewStats(): Future<JsonObject>
}
