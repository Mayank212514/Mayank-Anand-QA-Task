# Exploratory Testing Report: 

    • Application Name: Monefy
    • Version/Build: 1.22.8
    • OS: Android App
    • Tester: Mayank Anand
    • Date: 11.02.2026
    • Total Time Spent: 2 Hours

## Testing Approach:

    • Exploratory Testing was performed within a strict 2-hour timebox using a risk-based approach. Focus areas included financial transaction handling, navigation behaviour, calendar logic, boundary value validation, and overall UI consistency. Testing combined normal user flows, edge cases, and stress scenarios to evaluate both functionality and stability.

## Bugs Found:

    ### High Priority Issues: ###

        1. Inconsistency in the Language Upgrade Logic: The application indicates that language change requires an APP upgrade. However, after selecting a different language, the homepage and certain sections reflect the new language, while other parts remain unchanged.

            • Expectation: Either the language should not change at all without the upgrade, or the changes should be applied consistently across all sections.

        2. Date Range Selector Issue: 

            • Steps: Select a date range option (e.g., Month - January) from the "All accounts" section. Navigate/slide to another month (e.g., February). Without reselecting the month explicitly, choose another date range option (e.g., Day).

            • Observation: The homepage switches back to January (the previously selected month) instead of using February( the last visible month on the screen).

            • Expectation: The system should apply the month currently visible on the screen (in this case, February) when another date range option is selected.

            • Impact: This could lead to incorrect financial entries and impact data accuracy.

    ### Medium Priority Issues: ###

        1. Back Arrow Navigation Issue(Top -left corner of the homepage)

            • Steps: Open the side menu from the homepage( 3 dots in the top-right corner). Observe the back arrow icon in the top-left corner of the screen. Click the back arrow. 

            • Observation: Instead of closing the side menu and returning to the homepage, the application opens the "All accounts" section.

            • Expectation: The side menu should be closed and the customer should see the homepage. It should not trigger navigation to a different section.

            • Impact: This creates unexpected navigation behaviour. It may be confusing because of the wrong navigation.

        2. Currency Change Not Reflected Immediately: Although changing the currency from the settings does not reflect immediately in a few sections.

            • Steps: Change the currency in the settings. Navigate back to the homepage. Verify the "All accounts" section in the top-left corner.

            • Observation: Currency didn't change immediately; it either needs some additional navigation or needs to restart the app.

            • Expectation: Currency change should reflect immediately everywhere in the APP.

            • Impact: Users may temporarily see inconsistent currency across different sections, and it may lead users to misinterpret balances or transaction amounts.

        3. Sorting Behaviour in the Record section: Transaction list prioritizes deposited amounts instead of sorting purely by numeric value. 
        
            • Impact: This may not align with user expectations for financial analysis.

        4. Category Click Shows Error with Zero Value:

            • Steps: Click on the expense (-) or income (+) icons. Click the "CHOOSE CATEGORY" button without adding any amount.

            • Observation: It shows the red error in the amount field.

            • Expectation: It should allow adding the category without adding the amount, and the amount can be added later.

            • Impact: Displaying an error can confuse, especially if they want to see the available categories first. If it is intentional, the behavior should be clearly defined (like showing an error message); otherwise, it may appear as a functional defect. 

        5. Currency Upgrade Concern: Although the currency changes are allowed from the settings, it asks for app upgradation to change it from the menu. 

            • Impact: Many customers will not see the currency change option from the settings, and they try to change it from the menu. This may limit usability for users operating in different regions or dealing with multiple currencies. 

    ### Low Priority Issues: ###

        1. No Visible Signup/Login: Signup or Login functionality was not visible in the tested version. It is unclear whether this is restricted to upgraded accounts and requires clarification.

        2. Unlimited allowed characters in the Notes Field: Notes field accepts unlimited characters and various symbols without restriction. While no crash occurred, this could introduce backend storage or performance concerns at scale.

        3. Chart Highlight Limitation: Categories with very small percentages are not clearly distinguishable in the chart view.

            • Steps: Add expenses for all the categories(keeping very few expenses for some of them). Observe the behaviour of the highlights in the chart view.

            • Observation: It does not highlight categories that contain a very small percentage of the chart.

            • Expectation: It should highlight all the categories available on the page(if the expense has been added for those).

            • Impact: This affects readability but does not impact the underlying data.

##  Positive Findings:

    1. Maximum Boundary Value Stability: Entered 999999999 multiple times for the different categories. The application remained stable with no crash or performance degradation observed.

    2. Overflow Handling: When multiplication exceeded the maximum allowed limit, the system reset the value to 0. This prevents application crashes, although user notification could improve clarity(like showing the maximum limit).

    3. Negative Value Validation: Entering negative values triggers a validation error, preventing invalid financial entries.

    4. General Stability: Throughout the 2-hour session, no unexpected crashes or major performance issues were observed.

## Risk Evaluation:
    
    • Primary risks identified include financial data accuracy concerns due to date range selector and currency synchronization issues, business rules inconsistencies in feature gating, and UX perception risks from visual inconsistencies. Additionally, open-ended input fields may introduce scalability or backend validation concerns in the future.

## Final Result:

    • The application demonstrates good stability and resilience under boundary testing. High-priority issues related to navigation and business logic should be addressed before release. Medium-priority behavioural inconsistencies should be clarifiedand improved to enhance user trust. Overall, the application works really well and just needs some improvement.

## Author
Mayank Anand
