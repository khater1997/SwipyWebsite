@smoke
Feature: F01_Home | user go to home page to show what about Swipy

  Scenario: user going to home
    When User open the Swipy homepage and getting the page URl to confirm
    Then User show what about Swipy introduction sentence
    When User hover and click on our features
    Then User show all Swipy features
    When User hover and click on For Corporations and subscribe
    Then Will navigate to Swipy web app "https://dashboard.swipy.pro/auth"
    When User hover and click on For Individuals and download the app
    And User click on download for IOS to download Swipy
    Then user navigate to ios page "https://apps.apple.com/us/app/swipy-business-card/id6456405197" and opened in new tab
    When User click on download for Android to download Swipy
    Then user navigate to android page "https://play.google.com/store/apps/details?id=com.swipy.swipy" and opened in new tab
    When user hover and click our clients button
    Then Show our clients list
    When user hover and click about Us button
    Then Will navigate to Swipy web app "https://dashboard.swipy.pro/auth"
    When user hover and click get in touch button
    And User enter his data and click send button
    Then User click on send button to send his data

    When user hover and click on Get Started button to create corporate
    Then Will navigate to Swipy web app "https://dashboard.swipy.pro/auth"

  Scenario: user opens whatsapp link
    When user hover and click our contact us button
    And user click on whatsapp icon
    Then user navigate to whatsapp page "https://api.whatsapp.com/send?phone=+201091808186" and opened in new tab

  Scenario: user opens facebook link
    When user hover and click our contact us button
    And user click on facebook icon
    Then user navigate to facebook page "https://www.facebook.com/people/Swipy/61550753597813/" and opened in new tab

  Scenario: user opens instagram link
    When user hover and click our contact us button
    And user click on instagram icon
    Then user navigate to instagram page "https://www.instagram.com/getswipy/?igshid=YTQwZjQ0NmI0OA%3D%3D" and opened in new tab

  Scenario: user opens linkedin link
    When user hover and click our contact us button
    And user click on linkedin icon
    Then user navigate to linkedin page "https://www.linkedin.com/company/swipysolutions/" and opened in new tab

  Scenario: user opens twitter link
    When user hover and click our contact us button
    And user click on twitter  icon
    Then user navigate to twitter  page "https://twitter.com/SwipySolutions" and opened in new tab





