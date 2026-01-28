package com.project.restapi.employeeManagement;

import com.project.restapi.employeeManagement.testutils.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.testcontainers.shaded.org.bouncycastle.asn1.cmp.Challenge;

import java.time.Instant;
import java.util.random.RandomGenerator;

import static org.assertj.core.api.Assertions.*;

class EmployeeApiTest extends AbstractIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    private final RandomGenerator randomGenerator = RandomGenerator.getDefault();

    private long randomLong;

    @BeforeEach
    void generateRandomLong() {
        this.randomLong = randomGenerator.nextLong();
    }

    @Nested
    @DisplayName("Admin Operations")
    class Admin {

        @DisplayName("Should create admin given valid request")
        @Test
        void shouldCreateAdmin_GivenValidRequest() {
            var request = new TestAdminCreateRequest(
                "John Doe",
                40,
                "Administrator",
                50_000D,
                "adminValid" + randomLong + "@example.com",
                true
            );

            var response = restTemplate.postForEntity(
                "/employee/admin",
                request,
                TestAdminResponse.class
            );

            // TODO: Refactor to proper status code (HttpStatus.CREATED - 201)
            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

            assertThat(response.getBody()).isNotNull();

            // TODO: Refactor to default to false (null shouldn't be possible)
            assertThat(response.getBody().isActive()).isNull();
        }

        @DisplayName("Should return error on existing email")
        @Test
        void shouldReturnError_OnExistingEmail() {
            var existingEmail = "notunique" + randomLong + "@example.org";

            var firstRequest = new TestAdminCreateRequest(
                String.valueOf(randomLong),
                40,
                "Administrator",
                50_000D,
                existingEmail,
                true
            );

            var firstResponse = restTemplate.postForEntity(
                "/employee/admin",
                firstRequest,
                TestAdminResponse.class
            );

            // Unique name and position in case they become a unique constraint too
            // Same email as firstRequest
            var secondRequest = new TestAdminCreateRequest(
                String.valueOf(randomGenerator.nextLong()),
                40,
                "Administrator3",
                50_000D,
                existingEmail,
                true
            );

            var secondResponse = restTemplate.postForEntity(
                "/employee/admin",
                secondRequest,
                String.class
            );

            // TODO: Refactor to use the proper status code (HttpStatus.CREATED - 201)
            assertThat(firstResponse.getStatusCode()).isEqualTo(HttpStatus.OK);

            assertThat(firstResponse.getBody()).isNotNull();

            // TODO: Refactor to use the proper status code (HttpStatus.CONFLICT - 409)
            assertThat(secondResponse.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        }

        @Nested
        @DisplayName("Should return 400 BAD REQUEST")
        class BadRequests {

            @DisplayName("when name is invalid")
            @ParameterizedTest(name = "Should reject name: {0}")
            @ValueSource(strings = {"         "})
            @NullAndEmptySource
            void shouldReturn400_givenInvalidName(String badName) {
                var request = new TestAdminCreateRequest(
                    badName,
                    40,
                    "Administrator",
                    50_000D,
                    "adminName" + randomLong + "@example.com",
                    true
                );

                var response = restTemplate.postForEntity(
                    "/employee/admin",
                    request,
                    String.class
                );

                assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
            }

            @DisplayName("when age is less than 18")
            @ParameterizedTest(name = "Should reject age: {0}")
            @ValueSource(ints = {0, -17, 17})
            void shouldReturn400_WhenAgeIsLessThan18(int badAge) {

                String uniqueEmail = badAge + "admin" + randomLong + "@example.com";

                var request = new TestAdminCreateRequest(
                    "John Doe",
                    badAge,
                    "Administrator",
                    50_000D,
                    uniqueEmail,
                    true
                );
                var response = restTemplate.postForEntity(
                    "/employee/admin",
                    request,
                    String.class
                );

                // Invalid ages now get a proper BAD_REQUEST response
                assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
            }

            @DisplayName("when position is invalid")
            @ParameterizedTest(name = "Should reject position: {0}")
            @ValueSource(strings = {"         "})
            @NullAndEmptySource
            void shouldReturn400_WhenPositionInvalid(String badPosition) {
                var request = new TestAdminCreateRequest(
                    "John Doe",
                    21,
                    badPosition,
                    50_000D,
                    "adminPosition" + randomLong + "@example.com",
                    true
                );

                var response = restTemplate.postForEntity(
                    "/employee/admin",
                    request,
                    String.class
                );

                assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
            }

            @DisplayName("when email is invalid")
            @ParameterizedTest(name = "Should reject email: {0}")
            @ValueSource(strings = {"@example.org", "abc"})
            @NullAndEmptySource
            void shouldReturn400_WhenEmailInvalid(String badEmail) {
                var request = new TestAdminCreateRequest(
                    "John Doe",
                    21,
                    "Administrator",
                    50_000D,
                    badEmail,
                    true
                );

                var response = restTemplate.postForEntity(
                    "/employee/admin",
                    request,
                    String.class
                );

                assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
            }

        }
    }

    @Nested
    @DisplayName("Public Operations")
    class PublicEmployee {
        @DisplayName("Should create public employee given valid request")
        @Test
        void shouldCreatePublicEmployee_GivenValidRequest() {
            var request = new TestPublicCreateRequest(
                "John Doe",
                40,
                "public" + randomLong + "@example.com",
                "Staff"
            );

            var response = restTemplate.postForEntity(
                "/employee/public",
                request,
                TestPublicResponse.class
            );

            System.out.println(response);

            // TODO: Refactor to proper status code (HttpStatus.CREATED - 201)
            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

            assertThat(response.getBody()).isNotNull();
        }

        @DisplayName("Should return error on existing email")
        @Test
        void shouldReturnError_OnExistingEmail() {

            var existingEmail = "publicNotUnique" + randomLong + "@example.com";

            var firstRequest = new TestPublicCreateRequest(
                String.valueOf(randomLong),
                40,
                existingEmail,
                "Staff"
            );

            var firstResponse = restTemplate.postForEntity(
                "/employee/public",
                firstRequest,
                TestPublicResponse.class
            );

            // Unique name and position in case they become a unique constraint too
            // Same email as firstRequest
            var secondRequest = new TestPublicCreateRequest(
                String.valueOf(randomGenerator.nextLong()),
                40,
                existingEmail,
                "Staff1"
            );

            var secondResponse = restTemplate.postForEntity(
                "/employee/public",
                secondRequest,
                String.class
            );

            // TODO: Refactor to use the proper status code (HttpStatus.CREATED - 201)
            assertThat(firstResponse.getStatusCode()).isEqualTo(HttpStatus.OK);

            assertThat(firstResponse.getBody()).isNotNull();

            // TODO: Refactor to use the proper status code (HttpStatus.CONFLICT - 409)
            assertThat(secondResponse.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        }

        @Nested
        @DisplayName("Should return 400 BAD REQUEST")
        class BadRequests {
            @DisplayName("when name is invalid")
            @ParameterizedTest(name = "Should reject name: {0}")
            @ValueSource(strings = {"         "})
            @NullAndEmptySource
            void shouldReturn400_givenInvalidName(String badName) {
                var request = new TestPublicCreateRequest(
                    badName,
                    40,
                    "publicName" + randomLong + "@example.com",
                    "Staff"
                );

                var response = restTemplate.postForEntity(
                    "/employee/public",
                    request,
                    String.class
                );

                assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
            }

            @DisplayName("when age is less than 18")
            @ParameterizedTest(name = "Should reject age: {0}")
            @ValueSource(ints = {0, -17, 17})
            void shouldReturn400_WhenAgeIsLessThan18(int badAge) {

                String uniqueEmail = badAge + "public" + randomLong + "@example.com";

                var request = new TestPublicCreateRequest(
                    "John Doe",
                    badAge,
                    uniqueEmail,
                    "Staff"
                );

                var response = restTemplate.postForEntity(
                    "/employee/public",
                    request,
                    String.class
                );

                // Invalid ages now get a proper BAD_REQUEST response
                assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
            }

            @DisplayName("when position is invalid")
            @ParameterizedTest(name = "Should reject position: {0}")
            @ValueSource(strings = {"         "})
            @NullAndEmptySource
            void shouldReturn400_WhenPositionInvalid(String badPosition) {
                var request = new TestPublicCreateRequest(
                    "John Doe",
                    21,
                    "publicPosition" + randomLong + "@example.com",
                    badPosition
                );

                var response = restTemplate.postForEntity(
                    "/employee/public",
                    request,
                    String.class
                );

                assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
            }

            @DisplayName("when email is invalid")
            @ParameterizedTest(name = "Should reject email: {0}")
            @ValueSource(strings = {"@example.org", "abc"})
            @NullAndEmptySource
            void shouldReturn400_WhenEmailInvalid(String badEmail) {
                var request = new TestPublicCreateRequest(
                    "John Doe",
                    21,
                    badEmail,
                    "Administrator"
                );

                var response = restTemplate.postForEntity(
                    "/employee/public",
                    request,
                    String.class
                );

                assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
            }

        }
    }
}
