/*
 * Source++, the open-source live coding platform.
 * Copyright (C) 2022 CodeBrig, Inc.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package spp.protocol.portal

import spp.protocol.artifact.ArtifactType
import kotlinx.serialization.Serializable

/**
 * todo: description.
 *
 * @since 0.1.0
 * @author [Brandon Fergerson](mailto:bfergerson@apache.org)
 */
@Serializable
data class PortalConfiguration(
    var currentPage: PageType = PageType.ACTIVITY,
    var darkMode: Boolean = false, //todo: can be page or status bar
    var external: Boolean = false,
    var visibleOverview: Boolean = true,
    var visibleActivity: Boolean = true,
    var visibleTraces: Boolean = true,
    var visibleLogs: Boolean = true,
    var visibleConfiguration: Boolean = false,
    var autoResolveEndpointNames: Boolean = false,
    var artifactType: ArtifactType? = null, //todo: allow multiple types? (endpoint + method)
    var statusBar: Boolean = false
) {
    fun isViewable(pageType: PageType): Boolean {
        return when (pageType) {
            PageType.OVERVIEW -> visibleOverview
            PageType.ACTIVITY -> visibleActivity
            PageType.TRACES -> visibleTraces
            PageType.LOGS -> visibleLogs
            PageType.CONFIGURATION -> visibleConfiguration
        }
    }
}
