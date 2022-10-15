#Autor: jcasallas@choucairtesting.com
#language:en

@Regresion
Feature: I as user of StartSharp
  i want create a business
  to generate a meeting

  Background:
    Given user in the home page
      | user  | password |
      | admin | serenity |

  #gradlew clean test aggregate -Dcucumber.options="--tags @MEEEEEEEEEEEEEEO"
  @Business
  Scenario: Successful business create
    When he is in the dashboard, create the business "KOI" - "Technology » Test"
    Then he verifies that the business "KOI" this created

  @Meeting
  Scenario: Successful meeting create
    When he is in the dashboard, create the meeting
      | nameBusiness | typeMeeting | number | date       | hourStart | hourEnd | location | host        | reporter    |
      | KOI          | Strategy    | 10105  | 10/14/2022 | 09:00     | 10:00   | On Site  | John Brooks | Sara Torres |
      Then he verifies that the meeting "Strategy KOI" is created correctly
