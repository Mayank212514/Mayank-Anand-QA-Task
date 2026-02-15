# Petstore API Test Automation

A simple REST API testing framework for Petstore API, covering both V2 and V3 versions.


## What This Project Does

    ### This framework tests the 'PET endpoint' of Petstore API. It includes: ###
    • Tests for V2 API (https://petstore.swagger.io/v2)
    • Tests for V3 API (https://petstore3.swagger.io/api/v3)
    • Both positive and negative test scenarios
    • Automated HTML test reports
    • Total Tests: 16 (8 for V2 + 8 for V3)

## Prerequisites

    ### Before running the tests, you need: ###

        1. Java: Version: 11 or higher
        2. Maven: Version: 3.6 or higher
        3. Internet Connection: Needed to connect to Petstore APIs

## Setup

    1. Extract the project
        • unzip petstore-api-automation.zip
        • cd petstore-api-automation

    2: Install dependencies (first time only)
        • mvn clean install -DskipTests
        • This downloads all required libraries.

## How to Run Tests

    1. Run all tests:
        • mvn clean test

    2. Run only V2 tests:
        • mvn test -Dtest=Petv2Tests

    3. Run only V3 tests:
        • mvn test -Dtest=PetV3Tests


## Test Reports

    ### After running tests, open the HTML report: ###
        • Location: 'TestReport.html file in the project'
        • Double-Click on the HTML file

## Test Coverage

    ### For Each API Version (V2 and V3): ###

        ### Positive Tests: ###
            • Create a new pet
            • Get pet by ID
            • Update existing pet
            • Find pets by status
            • Delete pet

        ### Negative Tests: ###
            • Get pet with invalid ID
            • Get non-existent pet
            • Create pet with missing fields

        ### Total: 8 tests per version = 16 tests total ###

---

## My Approach

    ### Why I Chose PET Endpoint ###

    • I choose to test the 'PET endpoint' instead of USER or STORE because:

        • Complete CRUD Operations - PET supports Create, Read, Update, Delete, plus Search
        • Easy to Understand - Everyone knows what a pet is (name, status, photos)
        • Good Test Variety - Multiple status (available, pending, sold), query parameters, nested data
        • Real-World Scenario - Similar to e-commerce product testing
        • Better Coverage - More endpoints and features than USER or STORE

    ### Why Test Both V2 and V3 ###

        • Compare behavior across versions
        • Check for breaking changes
        • Ensure compatibility
        • More comprehensive testing
        • V3 has known 500 error issues for most of the Endpoints

    ### Error Handling Approach ###

        1. When the API returns a 500 error (server problem), tests are 'skipped' instead of failed. Why?

            • 500 = Server issue, not a test problem
            • Skipped tests = "Can't verify, API unavailable"
            • Failed tests = "Found a bug in our code"
            • This makes reports clearer and more actionable

## Tech Stack

    ### Technologies Used

        • Java: Programming Language
        • Maven: Build and Dependency Framework - Easy dependency management and works well with CI/CD pipelines.
        • Rest Assured: API Testing Library - Built specifically for REST API testing.
        • TestNG: Test Execution Framework - Supports test priorities and dependencies and has better test grouping and reporting.
        • Jackson: Json Handling - Handles the conversion between Java objects and JSON.
        • Extent Reports: HTML Test Reports - Creates easy-to-read HTML reports and includes test details and error messages.
        • Page Object Model - Keeps tests clean and maintainable by separating UI interactions from test logic.


## Future Improvements

    • Data-driven testing (test with multiple datasets)
    • Tests with another endpoints
    • Parallel test execution
    • Integration with CI/CD pipeline

## Author
Mayank Anand
