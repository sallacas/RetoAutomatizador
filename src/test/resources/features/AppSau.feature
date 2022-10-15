#Autor: jcasallas@choucairtesting.com
#language:en

@Regresion
Feature: I as user of StartSharp
  i want create a business
  to generate a meeting

  Background:
    Given user in the home page
      | user   | password   |
      | admin  | serenity   |
  #gradlew clean test aggregate -Dcucumber.options="-tags @Business"
  @Business
  Scenario: Successful business create
    When he is in the dashboard, create the business "KOI" - "Technology » Test"
    Then he verifies that the business "KOI" this created

    @Meeting
    Scenario: Successful meeting create
      When he is in the dashboard, create the meeting for "KOI"
      #Then he verifies that the meeting is created correctly
