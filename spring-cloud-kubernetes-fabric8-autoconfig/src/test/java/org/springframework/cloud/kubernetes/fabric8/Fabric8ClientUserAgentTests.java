/*
 * Copyright 2013-2021 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.cloud.kubernetes.fabric8;

import io.fabric8.kubernetes.client.KubernetesClient;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.kubernetes.example.App;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author wind57
 *
 * test "User-Agent" functionality
 */
class Fabric8ClientUserAgentTests {

	private static final String USER_AGENT = "spring.cloud.kubernetes.client.userAgent=non-default";

	private static final String ENABLED_K8S = "spring.main.cloud-platform=KUBERNETES";

	@Nested
	@SpringBootTest(classes = App.class, properties = ENABLED_K8S)
	class DefaultConfigurationForClient {

		@Autowired
		private KubernetesClient client;

		@Test
		void testUserAgent() {
			String userAgent = client.getConfiguration().getUserAgent();
			assertThat(userAgent).isEqualTo("Spring-Cloud-Kubernetes-Application");
		}

	}

	@Nested
	@SpringBootTest(classes = App.class, properties = { USER_AGENT, ENABLED_K8S })
	class PropertiesConfigurationForClient {

		@Autowired
		private KubernetesClient client;

		@Test
		void testUserAgent() {
			String userAgent = client.getConfiguration().getUserAgent();
			assertThat(userAgent).isEqualTo("non-default");
		}

	}

}